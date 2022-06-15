package news.collect.scheduler;

import news.collect.crawling.DaumNewsCollectService;
import news.collect.crawling.NaverNewsCollectService;
import news.collect.crawling.model.NewsType;
import news.collect.data.model.CollectData;
import news.collect.repository.CollectDataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

@Component
public class CrawlingScheduler {

    private final CollectDataRepository collectDataRepository;

    final private NaverNewsCollectService naverNewsCollectService;

    final private DaumNewsCollectService daumNewsCollectService;

    public CrawlingScheduler(CollectDataRepository collectDataRepository, NaverNewsCollectService naverNewsCollectService, DaumNewsCollectService daumNewsCollectService) {
        this.collectDataRepository = collectDataRepository;
        this.naverNewsCollectService = naverNewsCollectService;
        this.daumNewsCollectService = daumNewsCollectService;
    }

    @Scheduled(cron = "10 * * * * *")
    public void NewsCrawling() {

        Iterable<CollectData> keword = collectDataRepository.findAll();

        keword.forEach(it -> {
            if (it.getNewsType().equals(NewsType.Naver.name())) {
                try {
                    naverNewsCollectService.NewsCrawling(it.getKeyword());
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } else if(it.getNewsType().equals(NewsType.Daum.name())) {
                try {
                    daumNewsCollectService.NewsCrawling(it.getKeyword());
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}