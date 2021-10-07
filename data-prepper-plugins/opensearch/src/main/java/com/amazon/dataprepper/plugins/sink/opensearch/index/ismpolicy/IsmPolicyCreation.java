package com.amazon.dataprepper.plugins.sink.opensearch.index.ismpolicy;

import com.amazon.dataprepper.plugins.sink.opensearch.index.IndexConstants;
import org.opensearch.client.Request;
import org.opensearch.client.ResponseException;
import org.opensearch.client.RestHighLevelClient;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Optional;

public class IsmPolicyCreation implements IsmPolicyCreationStrategy {
    private final RestHighLevelClient restHighLevelClient;
    private final String policyManagementEndpoint;
    private final String policyFileWithIsmTemplate;
    private final String policyFileWithoutIsmTemplate;

    public IsmPolicyCreation(final RestHighLevelClient restHighLevelClient,
                             final String policyManagementEndpoint,
                             final String policyFileWithIsmTemplate,
                             final String policyFileWithoutIsmTemplate) {
        this.restHighLevelClient = restHighLevelClient;
        this.policyManagementEndpoint = policyManagementEndpoint;
        this.policyFileWithIsmTemplate = policyFileWithIsmTemplate;
        this.policyFileWithoutIsmTemplate = policyFileWithoutIsmTemplate;
    }

    @Override
    public Optional<String> checkAndCreatePolicy() throws IOException {

        Request request = createPolicyRequestFromFile(policyManagementEndpoint, policyFileWithIsmTemplate);
        try {
            restHighLevelClient.getLowLevelClient().performRequest(request);
        } catch (ResponseException e1) {
            final String msg = e1.getMessage();
            if (msg.contains("Invalid field: [ism_template]")) {
                request = createPolicyRequestFromFile(policyManagementEndpoint, policyFileWithoutIsmTemplate);
                try {
                    restHighLevelClient.getLowLevelClient().performRequest(request);
                } catch (ResponseException e2) {
                    if (e2.getMessage().contains("version_conflict_engine_exception")
                            || e2.getMessage().contains("resource_already_exists_exception")) {
                        // Do nothing - likely caused by
                        // (1) a race condition where the resource was created by another host before this host's
                        // restClient made its request;
                        // (2) policy already exists in the cluster
                    } else {
                        throw e2;
                    }
                }
                return Optional.of(IndexConstants.RAW_ISM_POLICY);
            } else if (e1.getMessage().contains("version_conflict_engine_exception")
                    || e1.getMessage().contains("resource_already_exists_exception")) {
                // Do nothing - likely caused by
                // (1) a race condition where the resource was created by another host before this host's
                // restClient made its request;
                // (2) policy already exists in the cluster
            } else {
                throw e1;
            }
        }

        return Optional.empty();
    }

    protected Request createPolicyRequestFromFile(final String endPoint, final String fileName) throws IOException {
        final StringBuilder policyJsonBuffer = new StringBuilder();
        try (final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            reader.lines().forEach(line -> policyJsonBuffer.append(line).append("\n"));
        }
        final Request request = new Request(HttpMethod.PUT, endPoint);
        request.setJsonEntity(policyJsonBuffer.toString());
        return request;
    }
}
