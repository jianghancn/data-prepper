package com.amazon.dataprepper.plugins.sink.opensearch.index.ismpolicy;

import java.io.IOException;
import java.util.Optional;

public interface IsmPolicyCreationStrategy {
    Optional<String> checkAndCreatePolicy() throws IOException;
}
