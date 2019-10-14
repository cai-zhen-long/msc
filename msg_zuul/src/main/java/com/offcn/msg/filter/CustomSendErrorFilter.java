package com.offcn.msg.filter;


import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;


public class CustomSendErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return super.filterType();
    }

    @Override
    public int filterOrder() {
        return super.filterOrder();
    }

    @Override
    public boolean shouldFilter() {
        return super.shouldFilter();
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        try {
            int responseStatusCode = ctx.getResponseStatusCode();
            // 此处自定义响应体start
            String cumstomBody = "{error:error}";

        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}

