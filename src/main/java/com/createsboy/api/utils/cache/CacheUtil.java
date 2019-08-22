/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils.cache;

import java.util.Set;

/**
 * Des:
 * ClassName: CacheUtil
 * Author: createsboy
 * Date: 2019/8/22
 * Time: 11:09
 */
public interface CacheUtil {

    /**
     * 添加key value seconds<1没有时限
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    void setValue(String key, int seconds, String value);

    /**
     * 添加key value seconds<1没有时限
     * 如果已经存在则返回false 如果不存在添加成功返回true
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    boolean setNXValue(String key, int seconds, String value);

    /**
     * 添加key value seconds<1没有时限
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    void setObject(String key, int seconds, Object value);

    /**
     * 续期Key值
     *
     * @param key
     * @param seconds
     */
    void updateExpire(String key, int seconds);

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 获取object
     *
     * @param key
     * @return
     */
    <T> T getObject(String key, Class<T> t);

    /**
     * 删除value
     *
     * @param key
     * @return
     */
    void del(String key);

    /**
     * 批量删除value
     *
     * @param keys
     * @return
     */
    void del(Set<String> keys);
}
