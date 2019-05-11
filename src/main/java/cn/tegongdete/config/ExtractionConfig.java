package cn.tegongdete.config;

public class ExtractionConfig {
    private String titlePath;
    private String authorPath;
    private String datePath;
    private String contentPath;
    private String tagPath;
    private String listPath;

    public String getListPath() {
        return listPath;
    }

    public void setListPath(String listPath) {
        this.listPath = listPath;
    }

    public String getTitlePath() {
        return titlePath;
    }

    public void setTitlePath(String titlePath) {
        this.titlePath = titlePath;
    }

    public String getAuthorPath() {
        return authorPath;
    }

    public void setAuthorPath(String authorPath) {
        this.authorPath = authorPath;
    }

    public String getDatePath() {
        return datePath;
    }

    public void setDatePath(String datePath) {
        this.datePath = datePath;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getTagPath() {
        return tagPath;
    }

    public void setTagPath(String tagPath) {
        this.tagPath = tagPath;
    }
}
