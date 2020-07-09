package org.hjjang.core.bean.injection;

import org.hjjang.core.bean.BeanManager;
import org.hjjang.core.bean.collection.BeanCollection;
import org.hjjang.servlet.component.DIComponent;
import org.hjjang.servlet.controller.HelloController;
import org.hjjang.servlet.service.DIManageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BeanInjectionTest {

    @Test
    @DisplayName("Inject 테스트")
    public void injectTest() throws InstantiationException, IllegalAccessException {
        BeanInjection bi = new BeanInjection();

        BeanCollection.registBean(DIManageService.class.getName(),DIManageService.class.newInstance());
        BeanCollection.registBean(DIComponent.class.getName(),DIComponent.class.newInstance());

        bi.injectDI();

        String beanName = DIManageService.class.getName();
        Object bean = BeanCollection.getInjectBean(beanName);
        assertEquals("diComponent",((DIManageService)bean).getUserInfo());
    }

    @Test
    @DisplayName("Inject 테스트2")
    public void injectTestController() throws InstantiationException, IllegalAccessException {
        BeanManager.beanProcessor();

        List<Object> ibjectBeanList = BeanCollection.getIbjectBeanList();


        HelloController bean = (HelloController) BeanCollection.getBean(HelloController.class.getName());


        System.out.println(bean.helloRequest());
        System.out.println(bean.diRequest());
        System.out.println(bean.directDiRequest());
    }
}