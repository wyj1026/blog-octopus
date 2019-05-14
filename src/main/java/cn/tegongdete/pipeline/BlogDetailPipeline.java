package cn.tegongdete.pipeline;

import cn.tegongdete.Blog;
import cn.tegongdete.config.CommonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.tegongdete.config.SpiderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.handler.PatternRequestMatcher;
import us.codecraft.webmagic.handler.SubPipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class BlogDetailPipeline extends PatternRequestMatcher implements SubPipeline {
    private static final Logger logger = LoggerFactory.getLogger(BlogDetailPipeline.class);
    private SpiderConfig spiderConfig;

    public BlogDetailPipeline(SpiderConfig spiderConfig) {
        super(spiderConfig.getPlatform().getDetailPattern());
        this.spiderConfig = spiderConfig;
    }

    public MatchOther processResult(ResultItems resultItems, Task task) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = resultItems.getAll();
        Blog blog = objectMapper.convertValue(map, Blog.class);
        CommonConfig commonConfig = spiderConfig.getCommonConfig();

        if (commonConfig.isDefault() || commonConfig.isToConsole()) {
            logger.info("Title: " + blog.getTitle());
            logger.info("Author: " + blog.getAuthor());
            logger.info("Date: " + blog.getDate());
            logger.info("Content: " + blog.getHtmlContent());
            logger.info("Tag: "+ blog.getTags());
        }


        if (commonConfig.isToBean()) {
            synchronized (commonConfig.getResultSaver()) {
                commonConfig.getResultSaver().add(blog);
            }
        }

        if (commonConfig.isToPDF()) {
            String path = commonConfig.getPath();
            File file = new File(path);
        }

        return MatchOther.NO;
    }
}
