package com.kakxix.buy.user.api.feign;

import com.kakxix.buy.user.api.feign.fallback.HealthServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "buy-user" , fallbackFactory = HealthServiceFallbackFactory.class)
public interface HealthService {

    @RequestMapping(value = "/ok" )
    public String ok(@RequestParam(value="name") String name);

}
