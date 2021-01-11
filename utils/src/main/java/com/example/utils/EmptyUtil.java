package com.example.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 判断是否为空
 *
 * @author ice
 */
public class EmptyUtil {

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof Collection) {
            return isEmpty((Collection<?>) object);
        } else if (object instanceof Map) {
            return isEmpty((Map<?, ?>) object);
        }

        String str = object.toString();
        return isEmpty(str);
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
}
