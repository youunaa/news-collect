package news.collect.data;

import news.collect.controller.BaseController;
import news.collect.controller.model.BaseModel;
import news.collect.controller.model.BodyModel;
import news.collect.data.model.CollectData;
import news.collect.repository.CollectDataRepository;
import news.collect.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/data")
public class DataController extends BaseController {

    public Logger log = LoggerFactory.getLogger(DataController.class);

    private final CollectDataRepository collectDataRepository;

    public DataController(CollectDataRepository collectDataRepository) {
        this.collectDataRepository = collectDataRepository;
    }

    /**
     * 수집 설정 정보 목록
     * @return
     */
    @ResponseBody
    @PostMapping("/list")
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
    @PostMapping("/keword/reg")
    public BaseModel kewordReg(@RequestBody CollectData param) {
        CollectData collectData = CollectData.builder()
                .keyword(param.getKeyword())
                .newsType(param.getNewsType())
                .build();
        collectDataRepository.save(collectData);

        BodyModel body = new BodyModel();
        return ok(body);
    }

}