package org.hjjang.servlet.service;

import org.hjjang.core.annotation.Autowired;
import org.hjjang.core.annotation.Component;
import org.hjjang.servlet.component.DIComponent;

@Component
public class HelloService {

    @Autowired
    DIComponent diComponent;

    public String sayHello(){
        return "hello!";
    }

    public String sayDiComponent(){
        return diComponent.getName();
    }
}
