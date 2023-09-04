package com.sathish.customer.config;



import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class AppInterceptor implements HandlerInterceptor {
    
    private String TRACE_HEADER = "X-Trace-Id";
    
    public AppInterceptor() {
        MDC.put("transactionId", UUID.randomUUID().toString());
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceID = UUID.randomUUID().toString();
        if (request.getHeader(TRACE_HEADER) != null) {
            traceID = request.getHeader(TRACE_HEADER);
        }
        MDC.put("transactionId", traceID);
        return true;
    }
}