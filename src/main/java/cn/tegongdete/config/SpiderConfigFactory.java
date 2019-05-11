package cn.tegongdete.config;

import cn.tegongdete.enums.BlogPlatform;
import cn.tegongdete.enums.ResolverEnabledPlatform;
import cn.tegongdete.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SpiderConfigFactory {

    private static final Logger logger = LoggerFactory.getLogger(SpiderConfigFactory.class);
    private static final String CONFIG_FILE_NAME = "config.json";
    private static JSONObject config = loadConfigFile();

    private static JSONObject loadConfigFile() {
        String text = FileUtil.readAll(CONFIG_FILE_NAME);
        JSONObject r = JSONObject.parseObject(text);
        logger.info("Config file parsed successfully!");
        return r;
    }

    public static SpiderConfig getConfig(String id, BlogPlatform platform) {
        SpiderConfig spiderConfig = new SpiderConfig(id, platform);
        if (ResolverEnabledPlatform.contains(platform)) {
            spiderConfig.enableListResolver(true);
        }
        ExtractionConfig extractionConfig;
        if (config != null && config.containsKey(platform.getPlateform())) {
            String rawConfig = config.getString(platform.getPlateform());
            JSONObject obj = JSONObject.parseObject(rawConfig);
            if (obj.containsKey("headers")) {
                JSONObject rawHeaders = obj.getJSONObject("headers");
                Map<String, String> headers = new HashMap<>();
                for (Map.Entry<String, Object> entry1 : rawHeaders.entrySet()) {
                    headers.put(entry1.getKey(), entry1.getValue().toString());
                }
                spiderConfig.setHeaders(headers);
            }
            extractionConfig = JSONObject.toJavaObject(obj, ExtractionConfig.class);
            spiderConfig.setExtractionConfig(extractionConfig);
            return spiderConfig;
        }
        logger.warn("Platform not found!");
        return null;
    }
}
