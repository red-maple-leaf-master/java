
package top.oneyi.demo.StringUtil;

import java.util.Objects;

/**
 * StringJoiner源码分析
 *
 * @author oneyi
 * @date 2023/2/9
 */

public final class StringJoiner {
    /**
     * 前缀
     */
    private final String prefix;
    /**
     * 分隔符,俩个字符串之间的数值
     */
    private final String delimiter;
    /**
     * 后缀
     */
    private final String suffix;

    /**
     * 俩个分隔符之间的字符串(或者前缀和分隔符之间的字符串或者分隔符和后缀之间的字符串)
     */
    private StringBuilder value;
    /**
     * 默认没有分隔符时候的前缀后缀的拼接字符串(如果没有前缀后缀就为空)
     */
    private String emptyValue;

    /**
     * 只传递分隔符的构造方法
     *
     * @param delimiter
     */
    public StringJoiner(CharSequence delimiter) {
        this(delimiter, "", "");
    }

    /**
     * 前置,后置,分隔符全部传递
     *
     * @param delimiter
     * @param prefix
     * @param suffix
     */
    public StringJoiner(CharSequence delimiter,
                        CharSequence prefix,
                        CharSequence suffix) {
        // 判断是否为空,为空就抛出指定信息异常
        Objects.requireNonNull(prefix, "The prefix must not be null");
        Objects.requireNonNull(delimiter, "The delimiter must not be null");
        Objects.requireNonNull(suffix, "The suffix must not be null");
        this.prefix = prefix.toString();
        this.delimiter = delimiter.toString();
        this.suffix = suffix.toString();
        // 默认把前置后置组合在一起
        this.emptyValue = this.prefix + this.suffix;
    }
 /*   public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }*/

    /**
     * 传递默认前后缀
     *
     * @param emptyValue
     * @return
     */
    public StringJoiner setEmptyValue(CharSequence emptyValue) {
        this.emptyValue = Objects.requireNonNull(emptyValue,
                "The empty value must not be null").toString();
        return this;
    }

    /**
     * StringBuilder转化为字符串
     *
     * @return
     */
    @Override
    public String toString() {
        // 如果value为null说明没有传值,返回emptyValue
        if (value == null) {
            return emptyValue;
        } else {
            // 否则拼接后缀
            if (suffix.equals("")) {
                // 后缀空字符串则直接返回
                return value.toString();
            } else {
                int initialLength = value.length();
                String result = value.append(suffix).toString();
              //  重置值为预追加initialLength
                // //重新设置value的长度，将value还原到还未添加后缀的状态,也就是说去掉刚添加的后缀，保证下一次add正确性
                value.setLength(initialLength);
                return result;
            }
        }
    }

    /**
     * 添加方法
     *
     * @param newElement
     * @return
     */
    public StringJoiner add(CharSequence newElement) {
        // 拼接分隔符或者前置 在拼接传递进来的值
        prepareBuilder().append(newElement);
        return this;
    }

    /**
     * 俩个StringJoiner 对象内容拼接,传递进来的数据在后面
     *
     * @param other
     * @return
     */
    public StringJoiner merge(StringJoiner other) {
        // 判空
        Objects.requireNonNull(other);
        if (other.value != null) {
            // 获得传递进来的 StringJoiner的长度
            final int length = other.value.length();
            // 获取当前这个 StringJoiner的前置加value
            StringBuilder builder = prepareBuilder();
            // 在传递进来的StringJoiner前加上当前StringJoiner的value
            builder.append(other.value, other.prefix.length(), length);
        }
        return this;
    }

    /**
     * 拼接分隔符
     *
     * @return
     */
    private StringBuilder prepareBuilder() {
        // 如果value不等于null 说明不是第一次添加 直接添加分隔符即可
        if (value != null) {
            value.append(delimiter);
        } else {
            // 第一次添加 拼接前缀
            value = new StringBuilder().append(prefix);
        }
        // 返回value
        return value;
    }

    /**
     * 返回当前StringJoiner的长度
     * @return
     */
    public int length() {
        return (value != null ? value.length() + suffix.length() :
                emptyValue.length());
    }
}
