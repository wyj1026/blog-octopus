package cn.tegongdete.enums;

public enum Field {
    Title("title"),
    Author("author"),
    Date("date"),
    HtmlContent("htmlContent"),
    MarkdownContent("markdownContent")
    ;

    private String field;

    Field(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
