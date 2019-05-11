package cn.tegongdete.processor;

import cn.tegongdete.config.SpiderConfig;
import cn.tegongdete.enums.ResolverEnabledPlatform;
import cn.tegongdete.resolver.Resolver;
import cn.tegongdete.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.handler.PatternRequestMatcher;
import us.codecraft.webmagic.handler.SubPageProcessor;

import java.util.List;

public class BlogListProcessor extends PatternRequestMatcher implements SubPageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BlogListProcessor.class);
    private SpiderConfig spiderConfig;

    public BlogListProcessor(SpiderConfig spiderConfig) {
        super(spiderConfig.getPlatform().getListPattern());
        this.spiderConfig = spiderConfig;
    }

    @Override
    public MatchOther processPage(Page page) {
        // If the particular page resolver enabled, use resolver to process the page.
        if (spiderConfig.isEnableListResolver()) {
            Resolver resolver = ResolverEnabledPlatform.getResolver(spiderConfig.getPlatform());
            //Resolver resolver = spiderConfig.getListResolver();
            resolver.resolvePage(page);
            return MatchOther.NO;
        }

        // Otherwise, use the default way to extract urls
        List<String> blogUrls = page.getHtml().xpath(spiderConfig.getExtractionConfig().getListPath()).all();
        logger.info(String.format("%d blogs found in this page!", blogUrls.size()));

        String currentUrl = page.getRequest().getUrl();

        // Add next page
        if (blogUrls.size() != 0) {
            page.addTargetRequest(UrlUtil.getNextPageUrl(currentUrl));
        }

        // Current page's blogs
        page.addTargetRequests(blogUrls);
        return MatchOther.NO;
    }
}