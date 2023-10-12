package top.oneyi.demo.store.enumType;

/**
 * Created with IntelliJ IDEA.
 * 创建枚举 优化if else 结构
 *
 * @Author: wanyi
 * @Date: 2023/10/12/14:16
 */
public enum ShapeTypeEnum {

    CF("top.oneyi.demo.store.impl.CF",1),
    COD("top.oneyi.demo.store.impl.COD",2),
    DNF("top.oneyi.demo.store.impl.DNF",3);

    private String className;
    private Integer code;

    ShapeTypeEnum(String className,Integer code) {
        this.className = className;
        this.code = code;
    }
    public String getClassName() {
        return className;
    }

    /**
     * 根据code 返回对应的对象全类名
     * @param code
     * @return
     */
    public String getClassName(Integer code) {
        for (ShapeTypeEnum value : ShapeTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getClassName();
            }
        }
        return null;
    }
    public Integer getCode() {
        return code;
    }
}
