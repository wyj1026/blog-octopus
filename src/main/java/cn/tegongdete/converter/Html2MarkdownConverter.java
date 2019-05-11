package cn.tegongdete.converter;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;

public class Html2MarkdownConverter {
    private static final Remark remark = new Remark(Options.github());;

    public static String convert(String html) {
        return remark.convertFragment(html);
    }
}
