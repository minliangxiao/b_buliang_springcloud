package club.huangliang.products.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemlateConfig {
    /**向工厂中添加一个RestTemplate对象，应为添加了@LoadBalanced注解所以这个对象具有ribbon的负载均衡功能
     * @return
     */
    @Bean
    @LoadBalanced //代表ribbon负载均衡的restTemplate客户端对象
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
