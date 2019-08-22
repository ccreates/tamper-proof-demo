/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.test.controller;

import com.createsboy.api.annotation.TamperProofParams;
import com.createsboy.api.base.BaseController;
import com.createsboy.api.base.request.Request;
import com.createsboy.api.base.response.Response;
import com.createsboy.api.base.response.ResponseFactory;
import com.createsboy.api.test.domain.request.TestParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Des:
 * ClassName: TestController
 * Author: biqiang2017@163.com
 * Date: 2019/8/22
 * Time: 11:26
 */
@Controller
public class TestController extends BaseController{

    @TamperProofParams
    @RequestMapping(value = "/test",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response test(@RequestBody @Valid Request<TestParams> req){
        return ResponseFactory.ok("ok");
    }

}
