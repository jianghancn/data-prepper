package com.amazon.dataprepper.plugins.sink.opensearch.index;

import com.amazon.dataprepper.plugins.sink.opensearch.OpenSearchSinkConfiguration;
import com.amazon.dataprepper.plugins.sink.opensearch.index.ismpolicy.NoIsmPolicyCreation;
import org.opensearch.client.RestHighLevelClient;

public class DefaultIndexManager extends IndexManager {

    public DefaultIndexManager(final RestHighLevelClient restHighLevelClient,
                               final OpenSearchSinkConfiguration openSearchSinkConfiguration) {
        super(restHighLevelClient, openSearchSinkConfiguration);
        this.ismPolicyCreationStrategy = new NoIsmPolicyCreation();
    }

}
