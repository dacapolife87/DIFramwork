package org.hjjang.servlet.service;

import org.hjjang.core.annotation.Autowired;
import org.hjjang.core.annotation.Component;
import org.hjjang.servlet.component.DIComponent;

@Component
public class DIManageService {

    @Autowired
    DIComponent diComponent;

    public String getUserInfo(){

        System.out.println(diComponent.getName());
        return diComponent.getName();
    }
}
