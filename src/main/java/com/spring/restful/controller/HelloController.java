package com.spring.restful.controller;

import com.spring.restful.exception.UserNoExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * 控制层测试类
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {

            throw new UserNoExistException();
        }
        return "Hello World";
    }


    @RequestMapping("/success")//只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染
    public String success(Map<String, Object> map) {

        map.put("hello", "你好");
        map.put("users", Arrays.asList("tom", "bob", "lucy"));

        return "success";
    }
}