package cn.tegongdete.entity;

import java.util.List;

public class JueJinListRequestResult {
    private int count;
    private List<String> postUrls;
    private String lastPostCreatedTime;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getPostUrls() {
        return postUrls;
    }

    public void setPostUrls(List<String> postUrls) {
        this.postUrls = postUrls;
    }

    public String getLastPostCreatedTime() {
        return lastPostCreatedTime;
    }

    public void setLastPostCreatedTime(String lastPostCreatedTime) {
        this.lastPostCreatedTime = lastPostCreatedTime;
    }
}
