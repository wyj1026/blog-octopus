package cn.tegongdete.enums;

public enum BlogPlatform {
    JIANSHU("jianshu",
        "jianshu.com",
        "https://www.jianshu.com/u/%s?order_by=shared_at&page=1",
        "https://www.jianshu.com/u/[\\w\\-]+\\?order_by=shared_at&page=[1-9][0-9]*",
        "https://www.jianshu.com/p/[\\w\\-]+"),
    CSDN("csdn",
        "csdn.com",
        "https://blog.csdn.net/%s/article/list/1",
        "https://blog\\.csdn\\.net/[\\w\\-]+/article/list/[\\w\\-]+",
        "https://blog\\.csdn\\.net/[\\w\\-]+/article/details/[\\w\\-]+"),
    BOKEYUAN("cnblogs",
        "cnblogs.com",
        "https://www.cnblogs.com/%s/default.html?page=1",
        "https://www.cnblogs.com/[\\w\\-]+/default.html\\?page=[1-9][0-9]*",
        "https://www.cnblogs.com/[\\w\\-]+/p/[1-9][0-9]*\\.html"),
    SEGMENTFAULT("segmentfault",
        "segmentfault.com",
        "https://segmentfault.com/u/%s/articles?page=1",
        "https://segmentfault.com/u/conardli/articles\\?page=[1-9][0-9]*",
        "https://segmentfault.com/a/[1-9][0-9]*"),
    JUEJIN("juejin",
        "juejin.im",
        "https://timeline-merger-ms.juejin.im/v1/get_entry_by_self?src=web&targetUid=%s&type=post&limit=20&order=createdAt",
        "https://timeline-merger-ms.juejin.im/.*",
        "https://juejin.im/post/[\\w\\-]+");

    private String plateform;
    private String domain;
    private String url;
    private String listPattern;
    private String detailPattern;

    BlogPlatform(String plateform, String domain, String url, String listPattern,
        String detailPattern) {
        this.plateform = plateform;
        this.domain = domain;
        this.url = url;
        this.listPattern = listPattern;
        this.detailPattern = detailPattern;
    }

    public String getPlateform() {
        return plateform;
    }

    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }

    public String getListPattern() {
        return listPattern;
    }

    public String getDetailPattern() {
        return detailPattern;
    }
}
