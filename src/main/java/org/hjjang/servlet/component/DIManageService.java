package org.hjjang.servlet.component;

import org.hjjang.core.annotation.Autowired;
import org.hjjang.core.annotation.Component;

@Component
public class DIManageService {

    @Autowired
    DIComponent diComponent;

    public String getUserInfo(){

        System.out.println(diComponent.getName());
        return diComponent.getName();
    }
}
