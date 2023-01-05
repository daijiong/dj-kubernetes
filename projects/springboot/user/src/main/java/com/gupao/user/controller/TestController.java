package com.gupao.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/test")
    public List<ServiceInstance> findServiceInstance() throws Exception {
        //查询指定服务名称下的所有实例的信息
        List<ServiceInstance> list = this.discoveryClient.getInstances("order");
        ServiceInstance serviceInstance = list.get(0);
        URI uri = serviceInstance.getUri();
        System.out.println(uri.toString());
        this.testUrl(uri.toString());
        return list;
    }

    /**
     * 判断地址是否可以连接
     * @param urlString
     */
    public void testUrl(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co = url.openConnection();
            co.connect();
            System.out.println("连接可用");
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
        }
    }
}
