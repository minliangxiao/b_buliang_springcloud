package club.huangliang.users.controller;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 这个类是做服务调用列子的
 */
@RestController
public class UserController {

   @Autowired
    private DiscoveryClient discoveryClient; //只要引入了ribbon依赖就会有这个对象
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

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
        // 1. 使用restTemplate直接调用的方式
        RestTemplate restTemplate=new RestTemplate();
        String forObject = restTemplate.getForObject("http://"+randomHost()+"/product/findAll", String.class);
        System.out.println("商品服务返回的结果为："+forObject);
        return forObject;
    }

    /**这是用ribbon discovery调用服务的例子
     *好处：能拉取注册中心的服务列表
     * 缺点：无负载均衡
     * @return
     */
    @GetMapping("user/Ribbon/discovery/findAllProduct")
    public List<ServiceInstance> ribbonFindAllProduct(){
        System.out.println("进入服务");
        List<ServiceInstance> products = discoveryClient.getInstances("products");
        for (ServiceInstance product : products) {
            System.out.println(product.getHost());
            System.out.println(product.getPort());
        }
        return products;
    }

    /**2.ribbon的第二种服务调用
     * loadBalancerClient 调用服务
     * 优点： 不仅进过注册中心拿到服务咧表，还运用负载均衡(默认轮询)选出一个服务
     * @return
     */
    @GetMapping("user/Ribbon/LoadBalance/findAllProduct")
    public String ribbonLoadBalancerFindAllproduct(){
        ServiceInstance products = loadBalancerClient.choose("products");
        System.out.println(products.getHost());
        System.out.println(products.getPort());
        RestTemplate restTemplate=new RestTemplate();
        String url="http://"+products.getHost()+":"+products.getPort()+"/product/findAll";
        String forObject = restTemplate.getForObject(url, String.class);

        return forObject;
    }

    /** 3. 其实就是2的 的升级版  具有ribbon的负载均衡的restTemplate
     * @return
     */
    @GetMapping("user/Ribbon/LoadBalance/findAllProduct2")
    public String ribbonLoadBalancerFindAllproduct2(){
        //  http://products/product/findAll 中products表示再注册中心的服务名称
        String forObject = restTemplate.getForObject("http://products/product/findAll", String.class);
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
    /*
    * 测试类
    * */
    public static void main(String[] args) {
        System.out.println(randomHost());
    }



}
