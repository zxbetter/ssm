package devin.spittr.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devin
 */
public class BeanUtils {

    /**
     * 按照 getter 将 javabean 转换成 map 对象, 字段名为 key, 字段值为 value
     * @param obj    源对象
     * @param selective    是否忽略值为 null 的字段
     * @return    转化后的 map 对象
     */
    public static Map<String, Object> convertToMap(Object obj, boolean selective) {
        Class clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        Map<String, Object> map = new HashMap<>();
        for (Method m : methods) {
            String name = m.getName();

            // 只计算 getter 方法
            if ((name.startsWith("get") && !"getClass".equals(name)) || name.startsWith("is")) {
                Object o = null;
                try {
                    o = m.invoke(obj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                // 判断是否忽略 null 值
                if (selective && o == null) {
                    continue;
                }

                // 从 getter 方法名中截取字段名
                String key = name.startsWith("get") ? name.substring(3) : name.substring(2);

                // 将首字母小写
                key = StringUtils.unTitleCase(key);

                map.put(key, o);
            }
        }
        return map;
    }

    public static Map<String, Object> convertToMap(Object obj) {
        return convertToMap(obj, false);
    }

    public static <T> List<T> resultSetToBean(ResultSet rs, Class<T> clazz) {

        if (null == rs || null == clazz) {
            return null;
        }

        Method[] methods = clazz.getMethods();
        Map<String, Method> setterMap = new HashMap<>();
        List<T> result = new ArrayList<T>();

        try {
            for (Method method : methods) {
                String name = method.getName();

                // 只计算 setter 方法
                if (name.startsWith("set")) {
                    String key = StringUtils.camelToUnderline(name.substring(3), StringUtils.ALL_UPPER);
                    setterMap.put(key, method);
                }
            }

            while (rs.next()) {
                T t = clazz.newInstance();
                for (Map.Entry<String, Method> entry : setterMap.entrySet()) {
                    Object setValue = rs.getObject(entry.getKey());
                    entry.getValue().invoke(t, setValue);
                }
                result.add(t);
            }
        } catch (SQLException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }
}
