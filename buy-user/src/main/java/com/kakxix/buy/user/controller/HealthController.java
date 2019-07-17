package com.kakxix.buy.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 健康检查
 * <p>
 */
@RestController
public class HealthController {

    @Value("${server.port}")
    private String port;

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
    @RequestMapping(value = "/ok")
    public String ok(@RequestParam String name) {
        return name + ",ok:" + port;
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
