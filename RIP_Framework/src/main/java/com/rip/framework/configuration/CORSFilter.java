package com.rip.framework.configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ~~~~  RIP Framework got Request ~~~~  >>>>>>>>>>>>>>>>>>>>>>\n " + req);
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        //  response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        // response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        chain.doFilter(req, res);
        System.out.println("RIP Framework Response Character Encoding ~~~~ " + res.getCharacterEncoding());
        System.out.println("RIP Framework Response Chain ~~~~ " + chain.toString());
        System.out.println("RIP Framework Response Content Type ~~~~ " + response.getContentType());
        System.out.println("RIP Framework Response Header Names ~~~~ " + response.getHeaderNames());
        System.out.println("RIP Framework Response Buffer Size ~~~~ " + response.getBufferSize());
        System.out.println("RIP Framework Response Output Stream ~~~~ " + response.getOutputStream().toString());
        System.out.println("RIP Framework Response Status ~~~~ " + response.getStatus());
        System.out.println("RIP Framework Response Locale ~~~~ " + response.getLocale());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ~~~~  RIP Framework Send Response  ~~~~ >>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
