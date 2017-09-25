package com.lai.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lailai on 2017/9/22.
 */
@RestController
public class ComputerController {
    private final Logger logger=Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;  //需要提前开启注解@EnableDiscoveryClient

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a,@RequestParam Integer b){
        ServiceInstance instance=client.getLocalServiceInstance();
        Integer r=a+b;
        logger.info("/add,host:"+instance.getHost()+",service_id:"+instance.getServiceId()+",port:"+instance.getPort()+",result:"+r);
        logger.info("service:"+client.getServices());
        return r;
    }
}
