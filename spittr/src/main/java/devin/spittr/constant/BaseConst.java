package devin.spittr.constant;

/**
 * 系统的常量
 *
 * @author devin
 * @since 1.0.0
 */
public interface BaseConst {
    // 运行时常量, String.valueOf() 也是
    // String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
    // 编译时常量
    String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";
}
