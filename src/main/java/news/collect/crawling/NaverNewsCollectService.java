package news.collect.crawling;

import news.collect.crawling.model.News;
import news.collect.repository.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service("NaverNewsCollectService")
public class NaverNewsCollectService implements NewsCollectService {
    public Logger log = LoggerFactory.getLogger(NaverNewsCollectService.class);

    private final NewsRepository newsRepository;

    public NaverNewsCollectService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void NewsCrawling() throws UnsupportedEncodingException {
        String keyword = "코로나";
        String text = URLEncoder.encode(keyword, "UTF-8");
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
                News news = News.builder()
                        .type("naver")
                        .keyword(keyword)
                        .subject(el.text())
                        .newsUrl(el.attr("abs:href"))
                        .build();
                newsRepository.save(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}