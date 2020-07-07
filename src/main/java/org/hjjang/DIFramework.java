package org.hjjang;

import org.hjjang.core.bean.BeanLoader;

public class DIFramework {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        BeanLoader beanLoader = new BeanLoader();
        beanLoader.beanProcessor();
    }
}
