package club.huangliang.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class UserController {
   /* @Resource
    private RestTemplate restTemplate;*/

    /** 用RestTemPlate对象展示最为原始的服务调用
     * @return
     */
    @GetMapping("user/showProdectMsg")
    public String  showProdectMsg(){
        RestTemplate restTemplate=new RestTemplate();
        String msg = restTemplate.getForObject("https://127.0.0.1:9998/product/showMsg", String.class);
        System.out.println(msg);
        return msg;
    }
}
