package club.huangliang.gateway.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局Filter代表所有路由都会经过这个Filter
 */
@Configuration
@Slf4j
public class CustomGlobaFilter implements GlobalFilter, Ordered {
    /**
     * @param exchange 这个是在springmvc框架中再次做了封装，封装成了spring webFlux request和response都可以从这个对象中拿
     * @param chain  这个和filterchain有点类似
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入自定义的全局Filter");
        if (exchange.getRequest().getQueryParams().get("username")!=null){
            log.info("合法身份进行放行");
            return chain.filter(exchange); //放行请求
        }
        log.info("非法用户，拒绝访问！！");
        return exchange.getResponse().setComplete();//响应完成（相当于不放行）
    }

    @Override
    public int getOrder() {//filter 数字越小filter越先执行
        return -1;   //-1 最先执行
    }
}
