/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.test.domain.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Des:
 * ClassName: TestParams
 * Author: createsboy
 * Date: 2019/8/22
 * Time: 15:21
 */
public class TestParams {
    @NotBlank(message = "请指定名称")
    @Length(max = 32,message = "名称长度<1~32位>")
    private String name;

    @NotBlank(message = "请指定编码")
    @Length(max = 16,message = "编码长度<1~16位>")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
