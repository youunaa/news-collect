package news.collect.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service("DaumNewsCollectService")
public class DaumNewsCollectService implements NewsCollectService {
    public static Logger log = LoggerFactory.getLogger(DaumNewsCollectService.class);

    @Override
    public void NewsCrawling() throws UnsupportedEncodingException {
        String text = URLEncoder.encode("코로나", "UTF-8");
        String URL = "https://search.daum.net/search?w=news&nil_search=btn&DA=NTB&enc=utf8&cluster=y&cluster_page=1&q=" + text;
        Document doc;

        try {
            doc = Jsoup.connect(URL).get();

            StringBuilder els = new StringBuilder();
            String tag = ">";
            els.append(".list_news").append(tag)
                    .append("li").append(tag)
                    .append(".wrap_cont").append(tag)
                    .append(".tit_main.fn_tit_u");

            for (Element el : doc.select(els.toString())) {
                log.info(el.text() + " " + el.attr("abs:href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}