package com.spring.restful.config;

import com.spring.restful.component.CustomLocalResolver;
import com.spring.restful.filter.CustomFilter;
import com.spring.restful.interceptor.LoginHandlerInterceptor;
import com.spring.restful.listener.CustomListener;
import com.spring.restful.servlet.CustomServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;

//使用WebMvcConfigurerAdapter可以扩展springmvc的功能
@Configuration//这是一个配置类
//@EnableWebMvc配置了该注解springboot的所有自动配置都将失效
public class CustomConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //浏览器发送hello请求也来到success页面
        registry.addViewController("/qimao").setViewName("success");
    }


    //所有的WebMvcConfigurerAdapter都会一起起作用
    @Bean//将组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {

        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login");
            }
        };

        return adapter;

    }

    @Bean//把我们自己写的区域信息解析器注入到spring容器中代替自带的
    public LocaleResolver LocaleResolver() {

        return new CustomLocalResolver();
    }

    @Bean//把我们自第一的servlet加入容器中
  public  ServletRegistrationBean ServletRegistrationBean(){

      ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CustomServlet(),"/customservlet");

      return servletRegistrationBean;
  }

  @Bean//把我们自定义的fliter加入容器中
    public FilterRegistrationBean FilterRegistrationBean(){
      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
      registrationBean.setFilter(new CustomFilter());
      //设置要过滤的路径,
      registrationBean.setUrlPatterns(Arrays.asList("/hello","/customserlet"));
      return registrationBean;
    }
    @Bean//把我们自定义的Listener加入容器中
    public ServletListenerRegistrationBean ServletListenerRegistrationBean(){

        //从原码发现他支持泛型
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean<>(new CustomListener());

        return listenerRegistrationBean;
    }
}

