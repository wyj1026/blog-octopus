package cn.tegongdete.util;

import cn.tegongdete.config.SpiderConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(SpiderConfigFactory.class);
    public static String readAll(String filename) {
        URL url = FileUtil.class.getClassLoader().getResource(filename);
        if (null == url) {
            logger.error("File not exists: " + filename);
            return null;
        }

        FileInputStream fis;
        try {
            File configFile = new File(url.getPath());
            byte[] data = new byte[(int) configFile.length()];
            fis = new FileInputStream(configFile);
            fis.read(data);
            fis.close();
            String text = new String(data, StandardCharsets.UTF_8);
            return text;
        }
        catch (Exception e) {
            logger.error("Failed to read file :" + e.getMessage());
        }
        return null;
    }
}
