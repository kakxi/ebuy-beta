package com.kakxix.base.common.apigateway.controller;

import com.kakxix.buy.user.api.feign.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <P>
 * 健康检查
 * <P>
 */
@RestController
public class HealthController {

    @Autowired
    public HealthService healthService;

    /**
     * securitys
     *
     * @return
     */
    @RequestMapping(value = "/api/securitys/hello", method = RequestMethod.GET)
    public String securitys() {
        return "securitys hello";
    }

    /**
     * ok
     *
     * @return
     */
    @RequestMapping(value = "/ok",method = RequestMethod.GET)
    public String ok(@RequestParam String name) {
        return healthService.ok(name);
    }

    /**
     * securitysOk
     *
     * @return
     */
    @RequestMapping(value = "/securitysOk")
    public String securitysOk() {
        return "securitysOk";
    }

    /**
     * health
     *
     * @return
     */
    @RequestMapping(value = "/health")
    public String health() {
        return "securitys health";
    }

    /**
     * apiHealth
     *
     * @return
     */
    @RequestMapping(value = "/api/health")
    public String apiHealth() {
        return "api health";
    }

}
