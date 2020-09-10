package club.huangliang.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController

public class UserController {
   /* @Resource
    private RestTemplate restTemplate;*/

    /** 用RestTemPlate对象展示最为原始的服务调用
     * @return
     */
    @GetMapping("user/showProductMsg")
    public String  showProdectMsg(){
        RestTemplate restTemplate=new RestTemplate();
        String msg = restTemplate.getForObject("http://127.0.0.1:9998/product/showMsg", String.class);
        System.out.println(msg);
        return msg;
    }

    /**用RestTemPlate对象展示最为原始的服务调用
     * 主要是为了熟悉RestTemplate的用法
     * @return
     */
    @GetMapping("user/findAllProduct")
    public String findAllProducet(){
        System.out.println("进入服务。。。。");
        RestTemplate restTemplate=new RestTemplate();
        String forObject = restTemplate.getForObject("http://127.0.0.1:9998/product/findAll", String.class);
        System.out.println("商品服务返回的结果为："+forObject);
        return forObject;
    }

    /**
     * 手动写了一个随机负载均衡
     * @return
     */
    public static String randomHost(){
        List<String> list=new ArrayList<String>();
        list.add("localhost:9997");
        list.add("localhost:9998");
        int i=new Random().nextInt(2);
        return  list.get(i);
    }

}
