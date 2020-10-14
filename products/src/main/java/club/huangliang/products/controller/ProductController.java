package club.huangliang.products.controller;

import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ProductController {
    @Value("${server.port}")
    private int port;
    @GetMapping("/product/showMsg")
    public String showMsg(){
        log.info("进入商品服务，展示商品信息");
        return "进入商品服务，展示商品信息!!!服务端口为"+port;
    }
    @GetMapping("/product/findAll")
    public Map<String ,Object> findAll(){
        Map<String,Object> map=new HashMap<String, Object>();
        log.info("进入商品服务 ！！查询商品信息！！");
        map.put("status",true);
        map.put("msg","查询所有商品信息成功！！当前服务端口为：" +port);
        return map;
    }
    @GetMapping("/product/break")
    public String testBreak( Integer id){
        System.out.println(id);
        if (id<0){
            throw new RuntimeException("非法参数,id不能小于0");

        }
        return "访问成功,当前查询id为："+id;
    }

}
