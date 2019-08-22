/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.aop;

import com.alibaba.fastjson.JSON;
import com.createsboy.api.base.request.Request;
import com.createsboy.api.utils.MD5Util;
import com.createsboy.api.utils.cache.CacheUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Des:
 * ClassName: TamperProofParamsAop
 * Author: createsboy
 * Date: 2019/7/19
 * Time: 16:33
 */
@Aspect
@Component
public class TamperProofParamsAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(TamperProofParamsAop.class);
    /**
     * 缓存
     */
    @Autowired
    private CacheUtil cacheUtil;

    /**
     * 此处的切点是注解的方式
     * 也可以用包名匹配的方式达到同样的效果
     * '@Pointcut("execution(* com.createsboy.api.*.controller.*(..))")'
     */
    @Pointcut("@annotation(com.createsboy.api.annotation.TamperProofParams)")
    public void tamperProofParams() {
    }


    /**
     * 参数防篡改校验
     *
     * @param joinPoint
     */
    @Before("tamperProofParams()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("参数防篡改校验.....");
        Request request = null;
        for (Object item : joinPoint.getArgs()) {
            if (item instanceof Request) {
                request = (Request) item;
                break;
            }
        }
        if (request == null) {
            throw new RuntimeException("请求异常");
        } else {
            long diff = Math.abs(System.currentTimeMillis() - request.getTimestamp());
            if (diff > 180000) {
                throw new RuntimeException("请求异常，超时");
            }
            String str = request.getTimestamp() + JSON.toJSONString(request.getParams());
            String md5 = MD5Util.encrytor(str);
            if (md5.equals(request.getCheck())) {
                if (!cacheUtil.setNXValue(md5, 180, "repeat")) {//预防重放攻击
                    throw new RuntimeException("请求异常，重放攻击");
                }
                LOGGER.info("参数未被篡改");
            } else {
                throw new RuntimeException("请求异常，校验码异常");
            }
        }
    }
}
