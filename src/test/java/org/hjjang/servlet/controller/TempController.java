package org.hjjang.servlet.controller;

import org.hjjang.core.annotation.Controller;
import org.hjjang.core.annotation.RequestMapping;
import org.hjjang.core.annotation.RequestMethod;

@Controller
public class TempController {

    @RequestMapping(value = "/api/test",method = RequestMethod.POST)
    public String tempRequest(){
        return "Tests";

    }
}
