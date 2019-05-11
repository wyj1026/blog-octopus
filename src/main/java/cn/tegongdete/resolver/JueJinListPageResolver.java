package cn.tegongdete.resolver;

import cn.tegongdete.entity.JueJinListRequestResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;


public class JueJinListPageResolver implements Resolver {

    private static final Logger logger = LoggerFactory.getLogger(JueJinListPageResolver.class);
    private static final String REQUEST_URL = "https://timeline-merger-ms.juejin.im/v1/get_entry_by_self?src=web&targetUid=%s&type=post&before=%s&limit=20&order=createdAt";

    public JueJinListPageResolver() {
    }

    @Override
    public void resolvePage(Page page) {
        String text = page.getRawText();
        JueJinListRequestResult result = resolve(text);
        page.addTargetRequests(result.getPostUrls());
        logger.info(String.format("%d blogs found in this page!", result.getCount()));
        if (result.getCount() > 0) {
            String nextUrl = getNextPageUrl(result.getLastPostCreatedTime(), page.getRequest().getUrl());
            page.addTargetRequest(nextUrl);
        }
    }

    public JueJinListRequestResult resolve(String text) {
        try {
            JueJinListRequestResult result = new JueJinListRequestResult();
            JSONObject object = JSONObject.parseObject(text).getJSONObject("d");
            result.setCount(Integer.valueOf(object.getString("total")));
            JSONArray array = object.getJSONArray("entrylist");
            if (array.size() != result.getCount()) {
                logger.error("Post count does not match: %d expected: %d",array.size(), result.getCount());
                return null;
            }
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject postObj = array.getJSONObject(i);
                String url = postObj.getString("originalUrl");
                urls.add(url);
            }
            if (array.size() > 0) {
                JSONObject last = array.getJSONObject(array.size() - 1);
                result.setLastPostCreatedTime(last.getString("createdAt"));
            }
            result.setPostUrls(urls);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getNextPageUrl(String lastCreatedTime, String url) {
        String uid = getUid((url));
        String t = lastCreatedTime.replaceAll(":", "%3A");
        return String.format(REQUEST_URL, uid, t);
    }

    private String getUid(String url) {
        int start = url.indexOf("targetUid=") + "targetUid".length() + 1;
        int end  =start;
        while (url.charAt(end) != '&') {
            end++;
        }
        return url.substring(start, end);
    }
}
