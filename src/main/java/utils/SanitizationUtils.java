package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class SanitizationUtils {

    /**
     * Makes a sanitization in the posible values in filter fields
     *
     * @param filter filter to be sanitized
     * @return valid string to use
     */
    public static String checkValidFilter(String filter) {
        if (StringUtils.isEmpty(filter))
            return filter;

        return Jsoup.clean(
                StringEscapeUtils.escapeHtml4(StringEscapeUtils
                        .escapeEcmaScript(StringUtils.replace(filter, "'", "''")))
                , Safelist.basic());
    }

}
