package com.yunquanlai.utils.validator;

import com.yunquanlai.utils.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object != null) {
            throw new RRException(message);
        }
    }

    /**
     * 判断两个对象是否不相等，不相等抛出异常
     *
     * @param object
     * @param object1
     * @param message
     */
    public static void isNotEqual(Object object, Object object1, String message) {
        if (!object.equals(object1) && object != object1) {
            throw new RRException(message);
        }
    }

    /**
     * 判断两个对象是否相等，相等抛出异常
     *
     * @param object
     * @param object1
     * @param message
     */
    public static void isEqual(Object object, Object object1, String message) {
        if (object.equals(object1) || object == object1) {
            throw new RRException(message);
        }
    }

}
