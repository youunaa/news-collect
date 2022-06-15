package news.collect.crawling.model;


import news.collect.repository.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class NewsTest {

    final private NewsRepository newsRepository;

    NewsTest(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Test
    public void saveNews() {
        //given

    }

}