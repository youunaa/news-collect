package news.collect.scheduler;

import news.collect.crawling.DaumNewsCollectService;
import news.collect.crawling.NaverNewsCollectService;
import news.collect.crawling.model.NewsType;
import news.collect.data.model.CollectData;
import news.collect.repository.CollectDataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

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

    /**
     * 특정 키워드가 들어간 뉴스를 수집하는 스케줄러
     */
    @Scheduled(cron = "0 0 * * * *")
    public void NewsCrawling() {
        // 수집 키워드 목록
        Iterable<CollectData> keword = collectDataRepository.findAll();

        keword.forEach(it -> {
            // 수집 대상 뉴스 사이트에 따라 크롤링 시작
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