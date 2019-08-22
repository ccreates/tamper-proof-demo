/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils.cache.impl;

import com.alibaba.fastjson.JSON;
import com.createsboy.api.utils.StringUtil;
import com.createsboy.api.utils.cache.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Set;

/**
 * Des:
 * ClassName: CacheUtilImpl
 * Author: biqiang2017@163.com
 * Date: 2019/8/22
 * Time: 11:12
 */
@Component("cacheUtil")
public class CacheUtilImpl implements CacheUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtilImpl.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void setValue(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (seconds < 1) {
                jedis.set(key, value);
            } else {
                jedis.setex(key, seconds, value);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getStackTrace());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean setNXValue(String key, int seconds, String value) {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            if (seconds < 1) {
                result = jedis.set(key, value, "NX").equals("OK");
            } else {
                result = "OK".equals(jedis.set(key, value, "NX", "EX", seconds));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getStackTrace());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public void setObject(String key, int seconds, Object value) {
        this.setValue(key, seconds, JSON.toJSONString(value));
    }

    @Override
    public void updateExpire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getStackTrace());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String getValue(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e.getStackTrace());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    @Override
    public <T> T getObject(String key, Class<T> t) {
        String value = this.getValue(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return JSON.parseObject(value, t);
        }
    }

    @Override
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.getStackTrace());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    @Override
    public void del(Set<String> keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pip = jedis.pipelined();
            for (String key : keys){
                pip.del(key);
            }
            pip.sync();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.getStackTrace());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
