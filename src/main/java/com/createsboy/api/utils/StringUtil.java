/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils;

import org.springframework.util.StringUtils;

/**
 * Des:
 * ClassName: StringUtil
 * Author: biqiang2017@163.com
 * Date: 2019/8/22
 * Time: 10:16
 */
public class StringUtil extends StringUtils {

    public static boolean isNotEmpty(String sour) {
        return !isEmpty(sour);
    }
}
