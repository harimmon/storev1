package com.metacoding.storev1.log;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LogController {

    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/log/save")
    public String save(@RequestParam("storeId") int storeId, @RequestParam("buyer") String buyer, @RequestParam("qty") int qty){
        logService.구매하기(storeId,buyer,qty);

        return "redirect:/log";
    }

    @GetMapping("/log")
    public String list(HttpServletRequest request) {
        List<LogResponse.ListPage> listPage = logService.구매목록();
        request.setAttribute("models", listPage);
        return "log/list";
    }

}
