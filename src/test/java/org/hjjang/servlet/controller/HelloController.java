package org.hjjang.servlet.controller;

import org.hjjang.core.annotation.Autowired;
import org.hjjang.core.annotation.Controller;
import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;
import org.hjjang.servlet.component.DIComponent;
import org.hjjang.servlet.service.HelloService;

import static org.junit.jupiter.api.Assertions.*;

@Controller
@RequestMapping("/test/api")
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    DIComponent diComponent;

    @RequestMapping(value = {"/hello","/sayHello"},method = RequestMethod.GET)
    public String helloRequest(){
        return helloService.sayHello();
    }

    @RequestMapping(value = {"/helloworld","/sayHelloWorld"},method = {RequestMethod.GET,RequestMethod.POST})
    public String helloWorldRequest(){
        return "helloWorld";
    }

    @RequestMapping(value = "/di",method = RequestMethod.POST)
    public String diRequest(){
        return helloService.sayDiComponent();
    }

    @RequestMapping(value = "/direct/di",method = RequestMethod.POST)
    public String directDiRequest(){
        return diComponent.getName();
    }
}