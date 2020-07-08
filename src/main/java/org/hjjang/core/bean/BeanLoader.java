package org.hjjang.core.bean;

import org.hjjang.core.annotation.Component;
import org.hjjang.core.annotation.Controller;
import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;
import org.hjjang.core.bean.collection.BeanCollection;
import org.hjjang.core.bean.injection.BeanInjection;
import org.hjjang.core.handler.RequestHandlerInfo;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class BeanLoader {

    Logger logger = LoggerFactory.getLogger(BeanLoader.class);

    public void beanProcessor() throws IllegalAccessException, InstantiationException {
        try {
            loaderComponent();
            injectDependancy();
            registRequestMapper();
        }catch (Exception e){
            logger.error("BeanProcessor",e);
        }


    }

    private void registRequestMapper() {
        Reflections reflector = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(""))
                .setScanners(new MethodAnnotationsScanner())
        );
        Set<Method> methodSet = reflector.getMethodsAnnotatedWith(RequestMapping.class);

        for(Method method : methodSet){
            registRequestMapperList(method);
        }
        logger.debug("Register RequestMapper Finish! Total : {}",BeanCollection.requestHandlerSize());
    }

    private void registRequestMapperList(Method method){
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

    private List<String> getAllCaseRequestUrl(Class<?> clazz, RequestMapping requestMapping){
        List<String> urlListInClass = getUrlInRequestMappingFromInClass(clazz);
        return getAllCaseByRequestMapping(urlListInClass, requestMapping.value());
    }
    private List<String> getAllCaseByRequestMapping(List<String> classUrls,String[] methodUrls){
        List<String> urlList = new ArrayList<>();

        for(String classUrl : classUrls){
            for(String methodUrl : methodUrls){
                String mergedUrl = classUrl+methodUrl;
                urlList.add(mergedUrl);
            }
        }

        return urlList;
    }
    private List<String> getUrlInRequestMappingFromInClass(Class<?> clazz){
        List<String> urlList = new ArrayList<>();
        RequestMapping requestMappingInClass = clazz.getAnnotation(RequestMapping.class);
        if(requestMappingInClass != null){
            String[] classUrls = requestMappingInClass.value();
            urlList = new ArrayList<String>(Arrays.asList(classUrls));
        }

        return urlList;
    }

    private void injectDependancy() throws InstantiationException, IllegalAccessException {
        BeanInjection.injectDI();
    }

    private void loaderComponent() throws IllegalAccessException, InstantiationException {
        logger.info("Load Component in Project");
        Reflections reflector = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("")));
        Set<Class<?>> classSet = reflector.getTypesAnnotatedWith(Component.class);
        for(Class clazz : classSet){
            if(!clazz.isAnnotation()){
                logger.debug("Regist Component : {}",clazz.getName());
                BeanCollection.registBean(clazz.getName(),clazz.newInstance());
            }
        }

    }
}
