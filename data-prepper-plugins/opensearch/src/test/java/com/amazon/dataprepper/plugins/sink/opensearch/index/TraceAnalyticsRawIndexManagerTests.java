package com.amazon.dataprepper.plugins.sink.opensearch.index;

import com.amazon.dataprepper.model.configuration.PluginSetting;
import com.amazon.dataprepper.plugins.sink.opensearch.ConnectionConfiguration;
import com.amazon.dataprepper.plugins.sink.opensearch.OpenSearchSinkConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.opensearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class TraceAnalyticsRawIndexManagerTests {
    private static final String PLUGIN_NAME = "opensearch";
    private static final String PIPELINE_NAME = "integTestPipeline";
    private List<String> HOSTS = Arrays.asList("http://search.example.com:9200");
    private static final String TEST_TEMPLATE_V1_FILE = "test-index-template.json";
    private DefaultIndexManager defaultIndexManager;

    @Before
    public void setup(){
        final String testIndexAlias = "test-alias";
        final String testTemplateFile = Objects.requireNonNull(
                getClass().getClassLoader().getResource(TEST_TEMPLATE_V1_FILE)).getFile();
        final PluginSetting pluginSetting = generatePluginSetting(testIndexAlias, testTemplateFile);
        final OpenSearchSinkConfiguration esSinkConfig = OpenSearchSinkConfiguration.readESConfig(pluginSetting);
        final RestHighLevelClient  restHighLevelClient = esSinkConfig.getConnectionConfiguration().createClient();
        defaultIndexManager = new DefaultIndexManager(restHighLevelClient, esSinkConfig);
    }

    private PluginSetting generatePluginSetting(final String indexAlias, final String templateFilePath) {
        final Map<String, Object> metadata = new HashMap<>();
        metadata.put(IndexConfiguration.TRACE_ANALYTICS_RAW_FLAG, false);
        metadata.put(IndexConfiguration.TRACE_ANALYTICS_SERVICE_MAP_FLAG, false);
        metadata.put(ConnectionConfiguration.HOSTS, HOSTS);
        metadata.put(IndexConfiguration.INDEX_ALIAS, indexAlias);
        metadata.put(IndexConfiguration.TEMPLATE_FILE, templateFilePath);
//        final String user = System.getProperty("user");
//        final String password = System.getProperty("password");
//        if (user != null) {
//            metadata.put(ConnectionConfiguration.USERNAME, user);
//            metadata.put(ConnectionConfiguration.PASSWORD, password);
//        }

        final PluginSetting pluginSetting = new PluginSetting(PLUGIN_NAME, metadata);
        pluginSetting.setPipelineName(PIPELINE_NAME);
        return pluginSetting;
    }

    @Test
    public void testCheckAndCreatePolicy() throws IOException {
        assertEquals(Optional.empty(), defaultIndexManager.checkAndCreatePolicy());
    }


}
