package com.joe.morte.backend.api;

import java.io.InputStream;

public interface AzureEmotion {
    public String detectEmotion(InputStream input);
}
