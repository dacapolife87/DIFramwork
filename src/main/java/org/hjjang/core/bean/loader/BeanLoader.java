package org.hjjang.core.bean.loader;

import org.hjjang.core.annotation.Component;
import org.hjjang.core.bean.collection.BeanCollection;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class BeanLoader {

    Logger logger = LoggerFactory.getLogger(BeanLoader.class);

    public void loaderComponent() throws IllegalAccessException, InstantiationException {
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
