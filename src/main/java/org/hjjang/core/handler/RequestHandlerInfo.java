package org.hjjang.core.handler;


import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class RequestHandlerInfo {

    private String reqUrl;
    private Object classObj;
    private Method method;
    private Set<RequestMethod> requestMethodSet;
    private Object[] params;

    public RequestHandlerInfo(String reqUrl, Object classObj, Method method){
        this.reqUrl = reqUrl;
        this.classObj = classObj;
        this.method = method;
        this.requestMethodSet = new HashSet<>();
    }

    public static RequestHandlerInfo create(String reqUrl, Object classObj, Method method,RequestMethod[] methods){
        RequestHandlerInfo requestHandlerInfo = new RequestHandlerInfo(reqUrl, classObj, method);
        requestHandlerInfo.addRequestMethod(methods);
        return requestHandlerInfo;
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

    public Set<RequestMethod> getRequestMethods() {
        return requestMethodSet;
    }

    public void addRequestMethod(RequestMethod[] requestMethods){
        for(RequestMethod method : requestMethods){
            addRequestMethod(method);
        }
    }
    public void addRequestMethod(RequestMethod requestMethod){
        if(!requestMethodSet.contains(requestMethod)){
            requestMethodSet.add(requestMethod);
        }
    }
}
