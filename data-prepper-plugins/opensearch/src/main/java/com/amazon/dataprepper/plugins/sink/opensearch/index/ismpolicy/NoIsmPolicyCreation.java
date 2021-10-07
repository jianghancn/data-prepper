package com.amazon.dataprepper.plugins.sink.opensearch.index.ismpolicy;

import java.io.IOException;
import java.util.Optional;

public class NoIsmPolicyCreation implements IsmPolicyCreationStrategy {

    @Override
    public Optional<String> checkAndCreatePolicy() throws IOException {
        return Optional.empty();
    }
}
