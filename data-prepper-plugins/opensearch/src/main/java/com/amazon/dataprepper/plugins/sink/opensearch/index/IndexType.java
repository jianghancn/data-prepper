package com.amazon.dataprepper.plugins.sink.opensearch.index;

public enum IndexType {
    TRACE_ANALYTICS_RAW(DefaultIndexManager.class),
    TRACE_ANALYTICS_SERVICE_MAP(DefaultIndexManager.class),
    CUSTOM(DefaultIndexManager.class);

    Class<? extends IndexManager> indexManagerClazz;
    private IndexType(Class<? extends IndexManager> indexManagerClazz){
        this.indexManagerClazz = indexManagerClazz;
    }
}
