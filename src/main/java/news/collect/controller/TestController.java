package news.collect.controller;

import news.collect.crawling.NaverNewsCollectService;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {

    final private NaverNewsCollectService naverNewsCollectService;

    public TestController(NaverNewsCollectService naverNewsCollectService) {
        this.naverNewsCollectService = naverNewsCollectService;
    }

    @GetMapping("test")
    public HashMap<String, Object> userSave() throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        naverNewsCollectService.NewsCrawling();

        map.put("test", "ok");
        return map;
    }

}
