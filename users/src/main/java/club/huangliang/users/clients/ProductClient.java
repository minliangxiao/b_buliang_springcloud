package club.huangliang.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * products服务的客户端接口
 */
@FeignClient(value = "products") // 表示这一个feign的组件，这里的value表示注册中心的serviceId（服务名称）（value可写可不写）
public interface ProductClient {
    @GetMapping("/product/showMsg")
     String showMsg();
    @GetMapping("/product/findAll")
     Map<String ,Object> findAll();
}
