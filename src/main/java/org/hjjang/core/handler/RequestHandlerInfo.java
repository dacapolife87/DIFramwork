package org.hjjang.core.handler;


import org.hjjang.core.annotation.RequestMethod;

import java.lang.reflect.Method;

public class RequestHandlerInfo {

    private String reqUrl;
    private Object classObj;
    private Method method;
    private RequestMethod requestMethod;

    public RequestHandlerInfo(String reqUrl, Object classObj, Method method, RequestMethod requestMethod){
        this.reqUrl = reqUrl;
        this.classObj = classObj;
        this.method = method;
        this.requestMethod = requestMethod;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public Object getClassObj() {
        return classObj;
    }

    public Method getMethod() {
        return method;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }
}
