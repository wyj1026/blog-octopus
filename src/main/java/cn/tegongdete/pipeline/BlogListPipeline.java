package cn.tegongdete.pipeline;

import cn.tegongdete.config.SpiderConfig;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternRequestMatcher;
import us.codecraft.webmagic.handler.SubPipeline;

public class BlogListPipeline extends PatternRequestMatcher implements SubPipeline {

    public BlogListPipeline(SpiderConfig spiderConfig) {
        super(spiderConfig.getPlatform().getListPattern());
    }

    // Do nothing
    public MatchOther processResult(ResultItems resultItems, Task task) {
        return MatchOther.NO;
    }
}
