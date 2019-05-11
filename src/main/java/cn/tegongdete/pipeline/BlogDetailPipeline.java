package cn.tegongdete.pipeline;

import cn.tegongdete.Blog;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.tegongdete.config.SpiderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternRequestMatcher;
import us.codecraft.webmagic.handler.SubPipeline;

import java.util.Map;

public class BlogDetailPipeline extends PatternRequestMatcher implements SubPipeline {
    private static final Logger logger = LoggerFactory.getLogger(BlogDetailPipeline.class);

    public BlogDetailPipeline(SpiderConfig spiderConfig) {
        super(spiderConfig.getPlatform().getDetailPattern());
    }

    public MatchOther processResult(ResultItems resultItems, Task task) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = resultItems.getAll();
        Blog blog = objectMapper.convertValue(map, Blog.class);

        //Test
        logger.info("Title: " + blog.getTitle());
        //logger.info("Author: " + blog.getAuthor());
        logger.info("Date: " + blog.getDate());
        //logger.info(blog.getContent());
        //logger.info("Tag: "+ blog.getTags().toString());
        return MatchOther.NO;
    }
}
