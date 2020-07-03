package org.hjjang.component;

import org.hjjang.annotation.Component;

@Component
public class DIComponent {

    String name = "diComponent";

    public String getName(){
        return this.name;
    };
}
