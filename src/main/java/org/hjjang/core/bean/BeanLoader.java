package org.hjjang.core.bean;

import org.hjjang.core.annotation.Component;
import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.bean.collection.BeanCollection;
import org.hjjang.core.bean.injection.BeanInjection;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class BeanLoader {

    public void beanProcessor() throws IllegalAccessException, InstantiationException {
        loaderComponent();
        injectDependancy();
        registRequestMapper();
    }

    private void registRequestMapper() {
        Reflections reflector = new Reflections("");
        Set<Method> methodSet = reflector.getMethodsAnnotatedWith(RequestMapping.class);

        for(Method method : methodSet){
            Class<?> methodDeclaringClass = method.getDeclaringClass();

            methodDeclaringClass.getName();
        }
    }

    private void injectDependancy() throws InstantiationException, IllegalAccessException {
        BeanInjection.injectDI();
    }
    public void loaderComponent(){
        Reflections reflector = new Reflections("");

        Set<Class<?>> classSet = reflector.getTypesAnnotatedWith(Component.class);

        System.out.println("BeanList!");
        for(Class clazz : classSet){
            System.out.println(clazz.getName());
            System.out.println(clazz.isAnnotation());
            BeanCollection.registBean(clazz);
        }
    }
}
