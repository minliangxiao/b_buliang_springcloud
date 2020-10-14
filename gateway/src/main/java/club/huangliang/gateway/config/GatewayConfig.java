package club.huangliang.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过java代码的方式配置gateway的路由规则
 * 这个好像和yml 配置有冲突  优先使用yml配置文件配置路由规则
 *
 */
/*@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
      return builder.routes().route("user_route",r->r.path("/feign/**").uri("http://localhost:9999/"))
              .build();
    }

}*/
