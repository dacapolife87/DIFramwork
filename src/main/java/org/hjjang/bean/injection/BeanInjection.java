package org.hjjang.bean.injection;

import org.hjjang.annotation.Autowired;
import org.hjjang.bean.collection.BeanCollection;

import java.lang.reflect.Field;
import java.util.List;

public class BeanInjection {

    public void injectDI() throws IllegalAccessException {
        List<Object> beanList = BeanCollection.getBeanList();

        for(Object bean : beanList){
            getFieldList((Class) bean);
        }
    }

    private void getFieldList(Class bean) throws IllegalAccessException {
        Field[] declaredFields = bean.getDeclaredFields();
        for(Field field : declaredFields){
            boolean hasAutowiredAnnotaion = hasAutowiredAnnotaion(field);

            if(hasAutowiredAnnotaion){
                Class<?> type = field.getType();

                Object typeBean = BeanCollection.getBean(type.getName());

                field.setAccessible(true);
                field.set(bean,typeBean);
            }
        }

    }

    private boolean hasAutowiredAnnotaion(Field field) {
        boolean hasAutowired = false;
        Autowired declaredAnnotation = field.getDeclaredAnnotation(Autowired.class);
        if(declaredAnnotation != null){
            Class<?> fieldType = field.getType();

            System.out.println(fieldType.getName());
            return true;
        }
        return hasAutowired;
    }
}
