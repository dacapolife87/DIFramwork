package org.hjjang.core.bean;

import org.hjjang.core.bean.injection.BeanInjection;
import org.hjjang.core.bean.loader.BeanLoader;
import org.hjjang.core.handler.RequestHandlerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanManager {

    static Logger logger = LoggerFactory.getLogger(BeanManager.class);

    public static void beanProcessor() throws IllegalAccessException, InstantiationException {
        try {
            loaderComponent();
            injectDependancy();
            registRequestMapper();
        }catch (Exception e){
            logger.error("BeanProcessor",e);
        }
    }

    private static void registRequestMapper(){
        RequestHandlerMapper requestHandlerMapper = new RequestHandlerMapper();
        requestHandlerMapper.registRequestMapper();
    }

    private static void injectDependancy() throws InstantiationException, IllegalAccessException {
        BeanInjection beanInjection = new BeanInjection();
        beanInjection.injectDI();
    }

    private static void loaderComponent() throws IllegalAccessException, InstantiationException {
        logger.info("Load Component in Project");
        BeanLoader beanLoader = new BeanLoader();
        beanLoader.loaderComponent();
    }
}
