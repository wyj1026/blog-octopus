package cn.tegongdete.config;


import cn.tegongdete.Blog;

import java.util.Collection;

public class CommonConfig {
    private boolean isDefault = true;
    private boolean toMarkdown;
    private boolean toConsole;
    private boolean toBean;
    private boolean toPDF;
    private String path;
    private Collection<Blog> resultSaver;

    public Collection<Blog> getResultSaver() {
        return resultSaver;
    }

    public void setResultSaver(Collection<Blog> resultSaver) {
        this.resultSaver = resultSaver;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isToMarkdown() {
        return toMarkdown;
    }

    public void setToMarkdown(boolean toMarkdown) {
        this.toMarkdown = toMarkdown;
    }

    public boolean isToConsole() {
        return toConsole;
    }

    public void setToConsole(boolean toConsole) {
        this.toConsole = toConsole;
    }

    public boolean isToBean() {
        return toBean;
    }

    public void setToBean(boolean toBean) {
        this.toBean = toBean;
    }

    public boolean isToPDF() {
        return toPDF;
    }

    public void setToPDF(boolean toPDF) {
        this.toPDF = toPDF;
    }
}
