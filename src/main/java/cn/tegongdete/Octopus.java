package cn.tegongdete;

import cn.tegongdete.config.CommonConfig;
import cn.tegongdete.config.SpiderConfig;
import cn.tegongdete.config.SpiderConfigFactory;
import cn.tegongdete.downloader.HttpClientDownloader;
import cn.tegongdete.enums.BlogPlatform;
import cn.tegongdete.pipeline.BlogPipeline;
import cn.tegongdete.processor.BlogProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import java.util.*;

public class Octopus {
    private static final Logger logger = LoggerFactory.getLogger(Octopus.class);
    private static final HttpClientDownloader downloader = new HttpClientDownloader();
    private final CommonConfig commonConfig = new CommonConfig();
    private Map<BlogPlatform, List<String>> target = new HashMap<>();
    private int threadPerSite = 1;

    public Octopus threadPerSite(int thread) {
        this.threadPerSite = thread;
        return this;
    }

    public Octopus add(BlogPlatform platform, String id) {
        if (target.containsKey(platform)) {
            target.get(platform).add(id);
        } else {
            List<String> ids = new ArrayList<String>();
            ids.add(id);
            target.put(platform, ids);
        }
        return this;
    }

    public Octopus add(BlogPlatform platform, Collection<String> c) {
        if (target.containsKey(platform)) {
            target.get(platform).addAll(c);
        } else {
            List<String> ids = new ArrayList<>(c);
            target.put(platform, ids);
        }
        return this;
    }

    public Octopus format2Markdown() {
        this.commonConfig.setDefault(false);
        this.commonConfig.setToMarkdown(true);
        return this;
    }

    public Octopus toConsole() {
        this.commonConfig.setDefault(false);
        this.commonConfig.setToConsole(true);
        return this;
    }

    public Octopus toBean(Collection<Blog> c) {
        this.commonConfig.setDefault(false);
        this.commonConfig.setToBean(true);
        this.commonConfig.setResultSaver(c);
        return this;
    }

    public Octopus toPDF(String path) {
        this.commonConfig.setDefault(false);
        this.commonConfig.setToPDF(true);
        this.commonConfig.setPath(path);
        return this;
    }

    public static Octopus fromSingle(BlogPlatform platform, String id) {
        Octopus octopus = new Octopus();
        octopus.add(platform, id);
        return octopus;
    }

    public static Octopus fromCollection(BlogPlatform platform, Collection<String> c) {
        Octopus octopus = new Octopus();
        octopus.add(platform, c);
        return octopus;
    }

    public static Octopus fromMap(Map<BlogPlatform, Collection<String>> m) {
        Octopus octopus = new Octopus();
        for (Map.Entry<BlogPlatform, Collection<String>> entry : m.entrySet()) {
            octopus.add(entry.getKey(), entry.getValue());
        }
        return octopus;
    }

    public void start() {
        for (Map.Entry<BlogPlatform, List<String>> entry : target.entrySet()) {
            for (String id : entry.getValue()) {
                execute(entry.getKey(), id);
            }
        }
    }

    private void execute(BlogPlatform platform, String id) {
        if (id == null) {
            throw new NullPointerException("Blog id cannot be null");
        }
        SpiderConfig config = SpiderConfigFactory.getConfig(id, platform);
        Objects.requireNonNull(config, "Can't get config!");
        config.setCommonConfig(commonConfig);
        BlogPipeline pipeline = new BlogPipeline(config);
        Spider.create(new BlogProcessor(config))
                .addUrl(config.getInitialUrl())
                .addPipeline(pipeline).thread(threadPerSite)
                .setDownloader(downloader)
                .run();
    }

    public static void main(String[] args) {
        //Octopus octopus = Octopus.fromSingle(BlogPlatform.CSDN, "lonely_fireworks").threadPerSite(1);
        //Octopus octopus = Octopus.fromSingle(BlogPlatform.BOKEYUAN, "binyue").threadPerSite(1);
        //Octopus octopus = Octopus.fromSingle(BlogPlatform.JIANSHU, "44a252a62ec6").threadPerSite(1);
        //Octopus octopus = Octopus.fromSingle(BlogPlatform.JUEJIN, "5c3d35ef6fb9a04a09564a6d").threadPerSite(1);
        //Octopus octopus = Octopus.fromSingle(BlogPlatform.SEGMENTFAULT, "conardli").threadPerSite(1);

        //Map<BlogPlatform, Collection<String>> all = new HashMap<>();
        //all.put(BlogPlatform.CSDN, Arrays.asList("lonely_fireworks", "so", "on"));
        //all.put(BlogPlatform.BOKEYUAN, Arrays.asList("binyue"));
        //Octopus octopus = Octopus.fromMap(all);
        //octopus.start();

        //System.out.println(DateConverter.convert("2小时前", BlogPlatform.SEGMENTFAULT));
    }
}
