package club.huangliang.users.controller;

import club.huangliang.users.clients.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestOpenFeignController {
    @Autowired
    private ProductClient productClient;
    @GetMapping("feign/showMsg")
    public String showMsg(){
        System.out.println("通过feign组件调用信息");
        String s = productClient.showMsg();
        return s;
    }

    @GetMapping("feign/findAll")
    public Map<String, Object> findAll(){
        System.out.println("通过feign组件调用findAll");
        Map<String, Object> all = productClient.findAll();
        return all;
    }


}
