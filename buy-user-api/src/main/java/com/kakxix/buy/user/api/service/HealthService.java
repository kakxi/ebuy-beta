package com.kakxix.buy.user.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "buy-user")
public interface HealthService {

    @RequestMapping(value = "/ok")
    public String ok(@RequestParam(value="name") String name);

}
