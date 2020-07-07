package org.hjjang.servlet.controller;

import org.hjjang.core.annotation.Controller;
import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public void printHello(){
    }
}
