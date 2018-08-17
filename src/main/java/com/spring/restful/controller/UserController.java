package com.spring.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @PostMapping(value = "/user/login")//限定请求方式为post
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功,登录成功后为了防止表单重复提交,我们用重定向解决
            //把用户登录成功的信息存入域对象
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }


}
