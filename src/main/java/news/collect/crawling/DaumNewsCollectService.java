package news.collect.crawling;

import news.collect.crawling.model.News;
import news.collect.crawling.model.NewsType;
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

@Service("DaumNewsCollectService")
public class DaumNewsCollectService implements NewsCollectService {

    public static Logger log = LoggerFactory.getLogger(DaumNewsCollectService.class);

    private final NewsRepository newsRepository;

    public DaumNewsCollectService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void NewsCrawling() throws UnsupportedEncodingException {
        String keyword = "코로나";
        String text = URLEncoder.encode(keyword, "UTF-8");
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
                News news = News.builder()
                        .type(NewsType.Daum.name())
                        .keyword(keyword)
                        .subject(el.text())
                        .newsUrl(el.attr("abs:href"))
                        .build();
                newsRepository.save(news);
                log.info(String.valueOf(news));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}