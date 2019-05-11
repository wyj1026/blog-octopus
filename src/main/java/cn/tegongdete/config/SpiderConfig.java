package cn.tegongdete.config;

import cn.tegongdete.enums.BlogPlatform;

import java.util.Map;

public class SpiderConfig {
    private String id;
    private BlogPlatform platform;
    private ExtractionConfig extractionConfig;
    private String initialUrl;
    private Map<String, String> headers;
    private boolean enableListResolver;

    public Boolean isEnableListResolver() {
        return enableListResolver;
    }

    public void enableListResolver(Boolean enableListResolver) {
        this.enableListResolver = enableListResolver;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setHeader(String k, String v) {
        this.headers.put(k, v);
    }

    public SpiderConfig(String id, BlogPlatform platform) {
        this.id = id;
        this.platform = platform;
        this.initialUrl = String.format(platform.getUrl(), id);
    }

    public String getInitialUrl() {
        return initialUrl;
    }

    public void setInitialUrl(String initialUrl) {
        this.initialUrl = initialUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlogPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(BlogPlatform platform) {
        this.platform = platform;
    }

    public ExtractionConfig getExtractionConfig() {
        return extractionConfig;
    }

    public void setExtractionConfig(ExtractionConfig extractionConfig) {
        this.extractionConfig = extractionConfig;
    }
}
