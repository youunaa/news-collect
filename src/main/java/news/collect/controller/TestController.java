package news.collect.controller;

import news.collect.crawling.DaumNewsCollectService;
import news.collect.crawling.NaverNewsCollectService;
import news.collect.crawling.model.NewsType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {

    final private NaverNewsCollectService naverNewsCollectService;

    final private DaumNewsCollectService daumNewsCollectService;

    public TestController(NaverNewsCollectService naverNewsCollectService, DaumNewsCollectService daumNewsCollectService) {
        this.naverNewsCollectService = naverNewsCollectService;
        this.daumNewsCollectService = daumNewsCollectService;
    }

    @GetMapping("collect")
    public HashMap<String, Object> newsCollect(String type) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        if (type.equals(NewsType.Naver.name())) {
            naverNewsCollectService.NewsCrawling();
        } else if (type.equals(NewsType.Daum.name())) {
            daumNewsCollectService.NewsCrawling();
        }

        map.put("test", "ok");
        return map;
    }

}