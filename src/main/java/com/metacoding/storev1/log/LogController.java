package com.metacoding.storev1.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {

    @GetMapping("/log")
    public String list() {
        return "log/list";
    }

}
