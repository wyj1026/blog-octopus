package cn.tegongdete.processor;

import cn.tegongdete.config.SpiderConfig;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.handler.CompositePageProcessor;

import java.util.Map;

public class BlogProcessor extends CompositePageProcessor {

    private SpiderConfig spiderConfig;

    public BlogProcessor(SpiderConfig config) {
        super(null);
        spiderConfig = config;
        Site site = Site.me().setDomain(config.getPlatform().getDomain()).setRetryTimes(1).setCycleRetryTimes(5);
        if (config.getHeaders() != null) {
            for (Map.Entry<String, String> entry : config.getHeaders().entrySet()) {
                site.addHeader(entry.getKey(), entry.getValue());
            }
        }
        this.setSite(site);
        init();
    }

    private void init() {
        BlogListProcessor blogListProcessor = new BlogListProcessor(spiderConfig);
        BlogDetailProcessor blogDetailProcessor = new BlogDetailProcessor(spiderConfig);
        this.setSubPageProcessors(blogListProcessor, blogDetailProcessor);
    }
}
