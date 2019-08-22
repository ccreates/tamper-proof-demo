/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.base.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Des:
 * ClassName: Request
 * Author: createsboy
 * Date: 2019/8/21
 * Time: 18:24
 */
public class Request<T>{
    /**
     * 参数
     */
    @Valid
    private T params;

    /**
     * 校验
     */
    @NotBlank(message = "参数有误")
    private String check;

    /**
     * 版本
     */
    @NotNull(message = "参数有误")
    private String version;

    /**
     * 时间戳
     */
    @NotNull(message = "参数有误")
    private Long timestamp;

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check == null ? null : check.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
