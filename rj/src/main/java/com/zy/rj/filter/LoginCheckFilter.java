package com.zy.rj.filter;

import com.alibaba.fastjson.JSON;
import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public abstract class LoginCheckFilter implements Filter {


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,ServletException{

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 第一步： 把和登录有关的资源路径都写在urls数组当中 （注意：这里最好路径写全，别用通配符 ** ）
        String[] urls ={
                "/employee/login",
                "/employee/logout",
                "/backend/page/login/login.html",
                "/front/**"
        };

        //获取当前路径
        String url = request.getRequestURI().toString();

        for (String u: urls){   // 遍历urls数组里面的资源路径
            if (url.contains(u)){   // 如果客户端请求的访问资源路径包含了遍历urls数组里面的资源路径
                // 包含的话，放行即可（让客户端访问资源去吧，毕竟人家访问的资源路径和登录有关咱们也不拦截这个资源路径了。）
                chain.doFilter(request,response);
                return;
            }
        }

        //获取session中是否有用户判断是否登录
        Object employee = request.getSession().getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        if (employee != null){
            chain.doFilter(request,response);
        }
        else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("NOTLOGIN");
            response.getWriter().write(JSON.toJSONString(returnObject));
            log.info("有人没有登录直接访问程序");
        }

    }
}
