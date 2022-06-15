package news.collect.data;

import news.collect.controller.BaseController;
import news.collect.controller.model.BaseModel;
import news.collect.controller.model.BodyModel;
import news.collect.crawling.DaumNewsCollectService;
import news.collect.crawling.NaverNewsCollectService;
import news.collect.crawling.model.NewsType;
import news.collect.data.model.CollectData;
import news.collect.repository.CollectDataRepository;
import news.collect.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class DataController extends BaseController {

    public Logger log = LoggerFactory.getLogger(DataController.class);

    private final CollectDataRepository collectDataRepository;

    private final NewsRepository newsRepository;

    final private NaverNewsCollectService naverNewsCollectService;

    final private DaumNewsCollectService daumNewsCollectService;

    public DataController(CollectDataRepository collectDataRepository, NewsRepository newsRepository, NaverNewsCollectService naverNewsCollectService, DaumNewsCollectService daumNewsCollectService) {
        this.collectDataRepository = collectDataRepository;
        this.newsRepository = newsRepository;
        this.naverNewsCollectService = naverNewsCollectService;
        this.daumNewsCollectService = daumNewsCollectService;
    }

    /**
     * 수집 설정 정보 목록
     * @return
     */
    @ResponseBody
    @PostMapping("data/list")
    public BaseModel kewordList() {
        BodyModel body = new BodyModel();
        body.setBody(collectDataRepository.findAll());
        return ok(body);
    }

    /**
     * 키워드 설정
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping("data/keword/reg")
    public BaseModel kewordReg(@RequestBody CollectData param) throws UnsupportedEncodingException {
        CollectData collectData = CollectData.builder()
                .keyword(param.getKeyword())
                .newsType(param.getNewsType())
                .build();
        collectDataRepository.save(collectData);

        if (param.getNewsType().equals(NewsType.Naver.name())) {
            naverNewsCollectService.NewsCrawling(param.getKeyword());
        } else if (param.getNewsType().equals(NewsType.Daum.name())) {
            daumNewsCollectService.NewsCrawling(param.getKeyword());
        }

        BodyModel body = new BodyModel();
        return ok(body);
    }

    /**
     * 수집 설정 정보 목록
     * @return
     */
    @ResponseBody
    @PostMapping("news/list")
    public BaseModel newsList() {
        BodyModel body = new BodyModel();
        body.setBody(newsRepository.findAll());
        return ok(body);
    }

}