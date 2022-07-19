package com.example.search.util;

import org.jsoup.Jsoup;

public class HtmlTagUtil {

    public static String removeHtmlTag(String s) {
        String jsonupParse = Jsoup.parse(s).text();
        String removedTag = jsonupParse.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return removedTag;
    }
}
