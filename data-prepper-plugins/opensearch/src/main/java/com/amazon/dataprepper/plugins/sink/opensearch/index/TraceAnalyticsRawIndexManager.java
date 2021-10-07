package com.amazon.dataprepper.plugins.sink.opensearch.index;

import com.amazon.dataprepper.plugins.sink.opensearch.OpenSearchSinkConfiguration;
import com.amazon.dataprepper.plugins.sink.opensearch.index.ismpolicy.IsmPolicyCreation;
import org.opensearch.action.admin.indices.alias.Alias;
import org.opensearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TraceAnalyticsRawIndexManager extends IndexManager {
    // TODO: replace with new _opensearch API
    private static final String POLICY_MANAGEMENT_ENDPOINT = "/_opendistro/_ism/policies/" + IndexConstants.RAW_ISM_POLICY;

    public TraceAnalyticsRawIndexManager(final RestHighLevelClient restHighLevelClient,
                                         final OpenSearchSinkConfiguration openSearchSinkConfiguration) {
        super(restHighLevelClient, openSearchSinkConfiguration);
        this.ismPolicyCreationStrategy = new IsmPolicyCreation(
                restHighLevelClient,
                POLICY_MANAGEMENT_ENDPOINT,
                IndexConstants.RAW_ISM_FILE_WITH_ISM_TEMPLATE,
                IndexConstants.RAW_ISM_FILE_NO_ISM_TEMPLATE);
    }

    @Override
    protected List<String> getIndexPatterns(final String indexAlias){
        return  Collections.singletonList(indexAlias + "-*");
    }

    @Override
    protected boolean checkIfIndexExistsOnServer(final String indexAlias) throws IOException {
        return restHighLevelClient.indices().existsAlias(new GetAliasesRequest().aliases(indexAlias), RequestOptions.DEFAULT);
    }

    @Override
    protected CreateIndexRequest getCreateIndexRequest(final String indexAlias) {
        final String initialIndexName = indexAlias + "-000001";
        final CreateIndexRequest createIndexRequest = new CreateIndexRequest(initialIndexName);
        createIndexRequest.alias(new Alias(indexAlias).writeIndex(true));
        return createIndexRequest;
    }
}
