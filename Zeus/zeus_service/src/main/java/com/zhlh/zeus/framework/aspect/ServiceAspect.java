package com.zhlh.zeus.framework.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Service中方法调用统计</p>
 *
 * @author warship
 * @version 1.0
 * @since 1.0
 */
@Component
@Aspect
public class ServiceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceAspect.class);

    /**
     * 包、类、方法、参数、耗时
     * 切面收集日志，放入内存（CurrentHashMap），单独线程定时从内存中把日志转移到文件或数据库（*.log或者mongoDB）
     * 固定代码 or SPI扩展
     * <p/>
     * 方法调用切面
     */
    @Around(value = "execution(public * com.zhlh.zeus.service.*.*(..))")
    public Object aroundServiceMethod(final ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        String serviceName = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        Object returnVal = pjp.proceed();

        LOG.info("[Service]: {}, [Args]: {}, [Return]: {}, [Cost]: {}.", serviceName, JSON
                        .toJSONString
                                (pjp.getArgs()),
                JSON.toJSONString(returnVal), (System.currentTimeMillis() - start) + "ms");

        return returnVal;

    }
}