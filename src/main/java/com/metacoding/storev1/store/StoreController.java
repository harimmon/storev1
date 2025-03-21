package com.metacoding.storev1.store;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller // IoC(제어의 역전) => HashSet
public class StoreController {

    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String list(HttpServletRequest request) { // MVC
        List<Store> storeList = storeService.상품목록();
        request.setAttribute("models", storeList);
        return "store/list";
    }

    @GetMapping("/store/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        Store store = storeService.상세보기(id);
        request.setAttribute("model", store);
        return "store/detail";
    }

    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {

        request.setAttribute("model", storeService.상세보기(id));
        return "store/update-form";
    }

    // 2번 : board 프로젝트의 BoardController 참고
    @PostMapping("/store/{id}/delete")
    public String delete(@PathVariable("id") int id) {

        storeService.상품삭제(id);
        return "redirect:/";
    }

    @GetMapping("/store/save-form")
    public String saveForm() {
        return "store/save-form";
    }

    @PostMapping("/store/save")
    public String save(@RequestParam("name") String name, @RequestParam("stock") int stock,
            @RequestParam("price") int price) {

        storeService.상품등록(name, stock, price);

        return "redirect:/";
    }

    @PostMapping("/store/{id}/update")
    public String update(@PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("stock") int stock,
            @RequestParam("price") int price) {

        storeService.상품수정(id, name, stock, price);

        return "redirect:/store/" + id;
    }
}
