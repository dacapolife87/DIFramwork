package org.hjjang.core.bean.collection;

import org.hjjang.servlet.component.DIComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeanCollectionTest {

    @Test
    @DisplayName("Bean 등록 테스트")
    public void set_Bean_Object() throws IllegalAccessException, InstantiationException {
        System.out.println("DIComponent.class Name : "+DIComponent.class.getName());
        BeanCollection.registBean(DIComponent.class.getName(),DIComponent.class.newInstance());
        assertEquals(1, BeanCollection.beanSize());
    }

    @Test
    @DisplayName("Bean 조회 테스트")
    public void get_Bean_Object() throws IllegalAccessException, InstantiationException {
        System.out.println("DIComponent.class Name : "+DIComponent.class.getName());
        BeanCollection.registBean(DIComponent.class.getName(),DIComponent.class.newInstance());

        String beanName = DIComponent.class.getName();
        Object bean = BeanCollection.getBean(beanName);

        assertEquals(DIComponent.class,bean.getClass());
    }
}