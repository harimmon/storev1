package com.metacoding.storev1.log;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LogController {

    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/log")
    public String list(HttpServletRequest request) {
        List<LogResponse.ListPage> listPage = logService.구매목록();
        request.setAttribute("models", listPage);
        return "log/list";
    }

}
