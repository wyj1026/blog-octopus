package cn.tegongdete.util;

public class UrlUtil {
    public static String getNextPageUrl(String curr) {
        if (curr == null || curr.length() == 0) return "";
        int l = curr.length()-1;
        StringBuilder sb = new StringBuilder();
        while (l >= 0) {
            if (curr.charAt(l) >= '0' && curr.charAt(l) <= '9') {
                sb.append(curr.charAt(l));
            }
            else {
                break;
            }
            l--;
        }
        sb.reverse();
        int nextPageIndex = Integer.valueOf(sb.toString()) + 1;
        return curr.substring(0, l+1) + nextPageIndex;
    }
}
