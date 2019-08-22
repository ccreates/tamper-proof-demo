/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.base.response;

/**
 * Des:
 * ClassName: Response
 * Author: createsboy
 * Date: 2019/8/21
 * Time: 18:24
 */
public class Response {
    public static final int OK = 200;//正常

    public static final int BAD_REQUEST = 400;//错误的请求

    public static final int SERVER_ERROR = 500;//服务器内部错误
    /**
     * 状态值 200正常
     */
    private int status;

    /**
     * 返回错误信息
     */
    private String error;

    /**
     * 结果集
     */
    private Object res;

    public Response(int status, String error, Object res) {
        this.status = status;
        this.error = error;
        this.res = res;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }
}
