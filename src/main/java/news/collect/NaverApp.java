package news.collect;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NaverApp {

    public static Logger log = LoggerFactory.getLogger(NaverApp.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
        String text = URLEncoder.encode("코로나", "UTF-8");
        String URL = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=" + text;
        Document doc;

        try {
            doc = Jsoup.connect(URL).get();

            StringBuilder els = new StringBuilder();
            String tag = ">";
            els.append(".list_news").append(tag)
                    .append(".bx").append(tag)
                    .append(".news_wrap.api_ani_send").append(tag)
                    .append(".news_area").append(tag)
                    .append(".news_tit");

            for (Element el : doc.select(els.toString())) {
                log.info(el.text() + " " + el.attr("abs:href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
