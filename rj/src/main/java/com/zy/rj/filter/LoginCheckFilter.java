//package com.zy.rj.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.zy.rj.common.BaseContext;
//import com.zy.rj.common.contants.Contants;
//import com.zy.rj.common.domain.ReturnObject;
//import com.zy.rj.entity.Employee;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 检查用户是否已经完成登录
// */
//@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter{
//    //路径匹配器，支持通配符
//    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        //1、获取本次请求的URI
//        String requestURI = request.getRequestURI();// /backend/index.html
//
//        log.info("拦截到请求：{}",requestURI);
//
//        //定义不需要处理的请求路径
//        String[] urls = new String[]{
//                "/employee/login",
//                "/employee/logout",
//                "/backend/**",
//                "/front/**",
//                "/common/**",
//                "/user/sendMsg",
//                "/user/login"
//        };
//
//        //2、判断本次请求是否需要处理
//        boolean check = check(urls, requestURI);
//
//        //3、如果不需要处理，则直接放行
//        if(check){
//            log.info("本次请求{}不需要处理",requestURI);
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        Employee user = (Employee) request.getSession().getAttribute(Contants.SESSION_USER);
//
//        //4-1、判断登录状态，如果已登录，则直接放行
//        if(request.getSession().getAttribute(Contants.SESSION_USER) != null){
//            log.info("用户已登录，用户id为：{}",user.getId());
//
//            //String empId = (String) request.getSession().getAttribute(user.getId());
//            BaseContext.setCurrentId(user.getId());
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        //4-2、判断登录状态，如果已登录，则直接放行
//        if(request.getSession().getAttribute(Contants.SESSION_USER) != null){
//            log.info("用户已登录，用户id为：{}",user.getId());
//
//            //String userId = (String) request.getSession().getAttribute(user.getId());
//            BaseContext.setCurrentId(user.getId());
//
//            filterChain.doFilter(request,response);
//            return;
//        }
//
//        log.info("用户未登录");
//        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
//        ReturnObject returnObject =new ReturnObject();
//        returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
//        returnObject.setMessage("NOTLOGIN");
//        response.getWriter().write(JSON.toJSONString(returnObject));
//        return;
//
//    }
//
//    /**
//     * 路径匹配，检查本次请求是否需要放行
//     * @param urls
//     * @param requestURI
//     * @return
//     */
//    public boolean check(String[] urls,String requestURI){
//        for (String url : urls) {
//            boolean match = PATH_MATCHER.match(url, requestURI);
//            if(match){
//                return true;
//            }
//        }
//        return false;
//    }
//}
