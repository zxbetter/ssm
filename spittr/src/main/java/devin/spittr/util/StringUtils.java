package devin.spittr.util;

/**
 * @author devin
 */
public class StringUtils {

    /** 全大写 */
    public static final int ALL_UPPER = 1;

    /** 首字母大写 */
    public static final int FIRST_UPPER = 2;

    /** 全小写 */
    public static final int ALL_LOWER = 3;

    /** 首字母小写 */
    public static final int FIRST_LOWER = 4;

    /**
     * 首字母转成大写, 不是字母不转
     * eg: abc -> Abc
     *
     * @param str    源字符串
     * @return    转化后的字符串
     */
    public static String titleCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 97 && ch[0] <= 122) {
            ch[0] -= 32;
        }
        return String.valueOf(ch);
    }

    /**
     * 首字母转小写, 不是小写不转
     * @param str    源字符串
     * @return    转化后的字符串
     */
    public static String unTitleCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 65 && ch[0] <= 90) {
            ch[0] += 32;
        }
        return String.valueOf(ch);
    }

    /**
     * 把带有下划线的字符串转成驼峰格式
     * eg: abc_def_gh -> abcDefGh
     *
     * @param str
     * @return
     */
    public static String underlineToCamel(String str) {
        if (null == str || str.isEmpty()) {
            return "";
        }

        if (!str.contains("_")) {
            return unTitleCase(str);
        }

        StringBuilder sb = new StringBuilder();
        String[] camels = str.split("_");

        // 第一个单词首字母小写
        sb.append(camels[0].toLowerCase());

        // 其他单词首字母大写
        for (int i = 1; i < camels.length; i++) {
            sb.append(titleCase(camels[i]));
        }

        return sb.toString();
    }

    /**
     * 把驼峰格式的字符串转成下划线格式
     *
     * @param str
     * @param format
     *     ALL_LOWER: abCdEF -> ab_cd_e_f
     *     FIRST_LOWER: abCdEF -> ab_cd_e_f
     *     ALL_UPPER: abCdEF -> AB_CD_E_F
     *     FIRST_UPPER: abCdEF -> Ab_Cd_E_F
     * @return
     */
    public static String camelToUnderline(String str, int format) {
        if (null == str || str.isEmpty()) {
            return "";
        }

        char[] ch = str.toCharArray();
        StringBuilder result = new StringBuilder();    // 保存结果
        StringBuilder sb = new StringBuilder();    // 缓存当前的驼峰段

        boolean lowerFlag = format == ALL_LOWER || format == FIRST_LOWER;    // 标记单词首字母是否小写

        // 处理第一个字母
        sb.append(lowerFlag ? Character.toLowerCase(ch[0]) : Character.toUpperCase(ch[0]));

        // 处理剩余字母
        for (int i = 1; i < ch.length; i++) {

            // 遇到大写字母
            if (ch[i] == Character.toUpperCase(ch[i])) {

                // 将本段驼峰保存到 result 中
                result.append(format == ALL_UPPER ? sb.toString().toUpperCase() : sb.toString()).append("_");

                sb = new StringBuilder();
                sb.append(lowerFlag ? Character.toLowerCase(ch[i]) : ch[i]);
            } else {
                sb.append(ch[i]);
            }

            // 最后将 sb 中的缓存刷到 result 中
            if (i == ch.length - 1) {
                result.append(format == ALL_UPPER ? sb.toString().toUpperCase() : sb.toString());
            }
        }
        return result.toString();
    }
}
