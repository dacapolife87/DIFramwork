package org.hjjang.component;

import org.hjjang.annotation.Autowired;
import org.hjjang.annotation.Component;

@Component
public class DIManageService {

    @Autowired
    DIComponent diComponent;

    public void getUserInfo(){

        System.out.println(diComponent.getName());
    }
}
