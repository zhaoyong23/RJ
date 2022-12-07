//package com.zy.rj.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//@Slf4j
//@Configuration
//public class FilerSessionOut implements HandlerInterceptor {
//    @Override  // 在执行目标方法之前执行
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("------:进来拦截器了！--1");
//        //获取session
//        HttpSession session = request.getSession(false);
//        //取session用false,存session用true，默认true
//
//        //判断session是否为空，不存在就跳转到登录界面
//        if (session == null) {
//            log.info("session失效了------:跳转到登录页面！");
//            response.sendRedirect(request.getContextPath() + "/employee/login");
//            return false;
//        } else {
//            log.info("session未失效----------:不用再登录");
//            log.info("session有效期是：---->" + session.getMaxInactiveInterval());
//
//            return true;
//        }
//    }
//}
