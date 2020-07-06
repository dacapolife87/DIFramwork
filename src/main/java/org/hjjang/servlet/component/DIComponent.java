package org.hjjang.servlet.component;

import org.hjjang.core.annotation.Component;

@Component
public class DIComponent {

    String name = "diComponent";

    public String getName(){
        return this.name;
    };
}
