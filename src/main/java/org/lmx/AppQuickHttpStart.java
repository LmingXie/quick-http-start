package org.lmx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 功能描述: 账户服务启动
 *
 * @author LM.X
 * <p>
 * @SpringBootApplication 启用Springboot
 * @MapperScan Mybatis 扫包范围
 * @EnableDiscoveryClient 启用 Nacos
 * @EnableSwagger2Doc 启用 Swagger
 * @EnableFeignClients 启用 Feign
 * @EnableAsync 启用异步方法
 *
 * </p>
 * @date 2020/4/24 18:13
 */
@Slf4j
@SpringBootApplication
public class AppQuickHttpStart {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppQuickHttpStart.class);
        log.info("服务启动成功，bean总数:{}", context.getBeanDefinitionCount());
    }
}
