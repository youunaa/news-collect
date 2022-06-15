package news.collect.user;

import news.collect.config.jwt.JwtManager;
import news.collect.controller.BaseController;
import news.collect.controller.model.BaseModel;
import news.collect.controller.model.BodyModel;
import news.collect.repository.UserRepository;
import news.collect.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    public Logger log = LoggerFactory.getLogger(UserController.class);

    final private UserRepository userRepository;

    final private JwtManager jwtManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, JwtManager jwtManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping("join")
    public BaseModel userSave(@RequestBody User param) {
        String encodedPassword = passwordEncoder.encode(param.getPassword());

        User user = User.builder()
                .userId(param.getUserId())
                .password(encodedPassword)
                .userName(param.getUserName())
                .build();
        userRepository.save(user);

        return ok(new BodyModel());
    }

    /**
     * 로그인
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseModel userLogin(@RequestBody User param) {
        BodyModel body = new BodyModel();

        User user = User.builder()
                .userId(param.getUserId())
                .password(param.getPassword())
                .build();

        String token = jwtManager.generateJwtToken(user);

        body.setBody(token);
        return ok(body);
    }

}