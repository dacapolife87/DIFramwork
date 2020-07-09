package org.hjjang.core.handler;

import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;
import org.hjjang.core.bean.BeanLoader;
import org.hjjang.core.bean.collection.BeanCollection;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RequestHandlerMapper {

    static Logger logger = LoggerFactory.getLogger(BeanLoader.class);

    public static void registRequestMapper() {
        Reflections reflector = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(""))
                .setScanners(new MethodAnnotationsScanner())
        );
        Set<Method> methodSet = reflector.getMethodsAnnotatedWith(RequestMapping.class);

        for(Method method : methodSet){
            registRequestMapperList(method);
        }
        logger.debug("Register RequestMapper Finish! Total : {}", BeanCollection.requestHandlerSize());
    }

    public static void registRequestMapperList(Method method){
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        Class<?> methodDeclaringClass = method.getDeclaringClass();
        logger.debug("=====================================");
        logger.debug(methodDeclaringClass.getName());
        logger.debug(method.getName());
        RequestMethod[] requestMethods = requestMapping.method();

        List<String> allCaseUrls = getAllCaseRequestUrl(methodDeclaringClass, requestMapping);
        for(String url : allCaseUrls){
            logger.debug("Register RequestHandler Url : {}, Class : {}, invokeMethod : {}, RequestMethod : {}",url,methodDeclaringClass,method,requestMethods);
            RequestHandlerInfo requestHandlerInfo = RequestHandlerInfo.create(url, methodDeclaringClass, method, requestMethods);
            BeanCollection.registRequestHandlerInfo(requestHandlerInfo);
        }
    }

    private static List<String> getAllCaseRequestUrl(Class<?> clazz, RequestMapping requestMapping){
        List<String> urlListInClass = getUrlInRequestMappingFromInClass(clazz);
        if(urlListInClass.size() < 1) urlListInClass.add("");
        return getAllCaseByRequestMapping(urlListInClass, requestMapping.value());
    }

    private static List<String> getAllCaseByRequestMapping(List<String> classUrls, String[] methodUrls){
        List<String> urlList = new ArrayList<>();

        for(String classUrl : classUrls){
            for(String methodUrl : methodUrls){
                String mergedUrl = classUrl+methodUrl;
                urlList.add(mergedUrl);
            }
        }
        return urlList;
    }


    private static List<String> getUrlInRequestMappingFromInClass(Class<?> clazz){
        List<String> urlList = new ArrayList<>();
        RequestMapping requestMappingInClass = clazz.getAnnotation(RequestMapping.class);

        if(requestMappingInClass != null){
            String[] classUrls = requestMappingInClass.value();
            urlList = new ArrayList<String>(Arrays.asList(classUrls));
        }

        return urlList;
    }
}
