package cn.tegongdete.pipeline;

import cn.tegongdete.config.SpiderConfig;
import us.codecraft.webmagic.handler.CompositePipeline;

public class BlogPipeline extends CompositePipeline {

    private SpiderConfig spiderConfig;

    public BlogPipeline(SpiderConfig spiderConfig) {
        this.spiderConfig = spiderConfig;
        init();
    }

    private void init() {
        BlogListPipeline blogListProcessor = new BlogListPipeline(spiderConfig);
        BlogDetailPipeline blogDetailProcessor = new BlogDetailPipeline(spiderConfig);
        this.setSubPipeline(blogListProcessor, blogDetailProcessor);
    }
}
