package club.huangliang.consulclient;

import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsulClientApplication {
    IRule iRule;
    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApplication.class, args);
    }

}
