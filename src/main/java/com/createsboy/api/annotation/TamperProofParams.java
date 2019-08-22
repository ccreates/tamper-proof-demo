/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.annotation;

import java.lang.annotation.*;

/**
 * Des:
 * ClassName: TamperProofParams
 * Author: createsboy
 * Date: 2019/7/19
 * Time: 16:38
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TamperProofParams {
}
