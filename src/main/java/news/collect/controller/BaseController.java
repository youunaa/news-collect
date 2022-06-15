package news.collect.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import news.collect.controller.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

@Configuration
public class BaseController {

    private final Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();

    @Autowired
    private Environment environment;

    protected String getMessage(String key){
        return environment.getProperty(key);
    }

    protected BaseModel ok(BaseModel model) {
        model.setResultCode(200);
        model.setDesc("호출성공");
        return model;
    }

    public void renderJSON(HttpServletResponse response, int httpStatus, BaseModel model) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String output = gson.toJson(model);

        PrintWriter writer = response.getWriter();
        writer.write(output);
        writer.flush();
    }

}