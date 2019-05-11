package cn.tegongdete.processor;

import cn.tegongdete.config.ExtractionConfig;
import cn.tegongdete.config.SpiderConfig;
import cn.tegongdete.converter.DateConverter;
import cn.tegongdete.converter.Html2MarkdownConverter;
import cn.tegongdete.converter.RawContent2HtmlConverter;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.handler.PatternRequestMatcher;
import us.codecraft.webmagic.handler.SubPageProcessor;

public class BlogDetailProcessor extends PatternRequestMatcher implements SubPageProcessor {

    private SpiderConfig spiderConfig;

    public BlogDetailProcessor(SpiderConfig spiderConfig) {
        super(spiderConfig.getPlatform().getDetailPattern());
        this.spiderConfig = spiderConfig;
    }

    @Override
    public MatchOther processPage(Page page) {
        ExtractionConfig extractionConfig = spiderConfig.getExtractionConfig();
        page.putField("title", page.getHtml().xpath(extractionConfig.getTitlePath()).get());
        page.putField("author", page.getHtml().xpath(extractionConfig.getAuthorPath()).get());
        String rawDate = page.getHtml().xpath(extractionConfig.getDatePath()).get();
        page.putField("date", DateConverter.convert(rawDate, spiderConfig.getPlatform()));
        String content = page.getHtml().xpath(extractionConfig.getContentPath()).get();
        String html_content = RawContent2HtmlConverter.convert(content);
        page.putField("htmlContent", html_content);
        page.putField("markdownContent", Html2MarkdownConverter.convert(html_content));
        page.putField("tags", String.join("|", page.getHtml().xpath(extractionConfig.getTagPath()).all()));
        return MatchOther.NO;
    }
}
