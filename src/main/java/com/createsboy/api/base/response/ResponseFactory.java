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
 * ClassName: ResponseFactory
 * Author: createsboy
 * Date: 2019/8/21
 * Time: 18:25
 */
public class ResponseFactory {

    /**
     * 生成错误返回对象
     * @param error
     * @return
     */
    public static Response bad(String error){
        return bad(Response.BAD_REQUEST,error);
    }

    /**
     * 生成错误返回对象
     * @param error
     * @return
     */
    public static Response bad(int status,String error){
        return new Response(status,error,null);
    }

    /**
     * 生成正常返回对象
     * @param res
     * @return
     */
    public static Response ok(Object res){
        return new Response(Response.OK,null,res);
    }
}
