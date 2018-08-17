package com.spring.restful.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * 可以在连接上携带区域信息
 */
public class CustomLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取区域请求的区域信息
        String location = request.getParameter("location");
        Locale locale=Locale.getDefault();//如果没有选择那么就默认
        if (!StringUtils.isEmpty(location)){
           //去掉参数携带的下划线
            String[] split = location.split("-");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
