package com.yunquanlai.utils.interceptor;

import com.yunquanlai.utils.LogBodyRequest;
import groovy.lang.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 日志打印
 *
 * @author liyanjun
 */
@Singleton
@Configuration
@WebFilter(urlPatterns = "/**", filterName = "logFilter")
public class LogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (LOGGER.isDebugEnabled()) {
            LogBodyRequest logBodyRequest = new LogBodyRequest((HttpServletRequest) servletRequest);
            LOGGER.debug(logBodyRequest.getBody());
            filterChain.doFilter(logBodyRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
