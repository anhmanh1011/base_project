package com.example.project_template.config;

import lombok.Data;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class KssInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        LogFilterRequest logFilterRequest = new LogFilterRequest();
        logFilterRequest.setClientIp(request.getRemoteAddr());
        logFilterRequest.setUri(request.getRequestURI());
        logFilterRequest.setParameter(request.getParameterMap());
        logFilterRequest.setMethod(request.getMethod());
        logFilterRequest.setHeader(getHeadersInfo(request));
        ThreadContext.put("day_la_id",request.getRequestURI());

        return true;
    }


    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        Map<String,String> showResponse = new HashMap<>();
        ThreadContext.clearAll();
        MDC.clear();
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    @Data
    class LogFilterRequest {
        String uuid;
        String uri;
        String clientIp;
        String body;
        String method;
        Map parameter;
        Map<String, String> header;

    }

}
