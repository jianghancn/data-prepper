package com.amazon.dataprepper.plugins.sink.opensearch.index;
//
//import com.amazon.dataprepper.plugins.sink.opensearch.OpenSearchSinkConfiguration;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.opensearch.action.admin.cluster.settings.ClusterGetSettingsRequest;
//import org.opensearch.action.admin.cluster.settings.ClusterGetSettingsResponse;
//import org.opensearch.client.ClusterClient;
//import org.opensearch.client.RequestOptions;
//import org.opensearch.client.RestHighLevelClient;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;

public class DefaultIndexManagerTests {
//    private static final String PLUGIN_NAME = "opensearch";
//    private static final String PIPELINE_NAME = "integTestPipeline";
//    private List<String> HOSTS = Arrays.asList("http://search.example.com:9200");
//    private static final String TEST_TEMPLATE_V1_FILE = "test-index-template.json";
//    private DefaultIndexManager defaultIndexManager;
//
//    @Mock
//    private RestHighLevelClient  restHighLevelClient;
//
//    @Mock
//    private OpenSearchSinkConfiguration openSearchSinkConfiguration;
//
//    private ClusterClient cluster;
//
//    @Mock
//    private ClusterGetSettingsResponse clusterGetSettingsResponse;
//
//    @Before
//    public void setup(){
//        EasyMock.expect();
//        MockitoAnnotations.initMocks(this);
//        final String testIndexAlias = "test-alias";
//        final String testTemplateFile = Objects.requireNonNull(
//                getClass().getClassLoader().getResource(TEST_TEMPLATE_V1_FILE)).getFile();
//        final PluginSetting pluginSetting = generatePluginSetting(testIndexAlias, testTemplateFile);
//        final OpenSearchSinkConfiguration esSinkConfig = OpenSearchSinkConfiguration.readESConfig(pluginSetting);
//        final RestHighLevelClient  restHighLevelClient = esSinkConfig.getConnectionConfiguration().createClient();
        //cluster = new ClusterClient();
//        cluster = restHighLevelClient.cluster();
//        cluster = restHighLevelClient.cluster();// Mockito.mock();
//        defaultIndexManager = new DefaultIndexManager(restHighLevelClient, openSearchSinkConfiguration);
////        Mockito.when().thenReturn(cluster);
//    }

//    private PluginSetting generatePluginSetting(final String indexAlias, final String templateFilePath) {
//        final Map<String, Object> metadata = new HashMap<>();
//        metadata.put(IndexConfiguration.TRACE_ANALYTICS_RAW_FLAG, false);
//        metadata.put(IndexConfiguration.TRACE_ANALYTICS_SERVICE_MAP_FLAG, false);
//        metadata.put(ConnectionConfiguration.HOSTS, HOSTS);
//        metadata.put(IndexConfiguration.INDEX_ALIAS, indexAlias);
//        metadata.put(IndexConfiguration.TEMPLATE_FILE, templateFilePath);
////        final String user = System.getProperty("user");
////        final String password = System.getProperty("password");
////        if (user != null) {
////            metadata.put(ConnectionConfiguration.USERNAME, user);
////            metadata.put(ConnectionConfiguration.PASSWORD, password);
////        }
//
//        final PluginSetting pluginSetting = new PluginSetting(PLUGIN_NAME, metadata);
//        pluginSetting.setPipelineName(PIPELINE_NAME);
//        return pluginSetting;
//    }
//
//    @Test
//    public void testCheckAndCreatePolicy() throws IOException {
//        assertEquals(Optional.empty(), defaultIndexManager.checkAndCreatePolicy());
//    }
//
//    @Test
//    public void testCheckISMEnabled_True() throws IOException {
//
//        Mockito.when(cluster.getSettings(any(ClusterGetSettingsRequest.class), RequestOptions.DEFAULT))
//                .thenReturn(clusterGetSettingsResponse);
//        Mockito.when(clusterGetSettingsResponse.getSetting(IndexConstants.ISM_ENABLED_SETTING)).thenReturn("true");
//        assertEquals(true, defaultIndexManager.checkISMEnabled());
//    }
//
//    @Test
//    public void testCheckISMEnabled_False() throws IOException {
//
//        Mockito.when(cluster.getSettings(any(ClusterGetSettingsRequest.class), RequestOptions.DEFAULT))
//                .thenReturn(clusterGetSettingsResponse);
//        Mockito.when(clusterGetSettingsResponse.getSetting(IndexConstants.ISM_ENABLED_SETTING)).thenReturn("false");
//        assertEquals(true, defaultIndexManager.checkISMEnabled());
//    }
}
