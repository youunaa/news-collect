package news.collect.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("test")
    public HashMap<String, Object> userSave() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("test", "ok");
        return map;
    }

}
