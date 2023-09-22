package top.oneyi.generator.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/22/16:05
 */
public class GenConstants {

    /**
     * 模板后缀
     */
    public static final String FTL_SUFFIX = ".ftl";
    /**
     * 控制层前缀
     */
    public static final String CONTROLLER = "controller";
    /**
     * 实体类前缀
     */
    public static final String DOMAIN = "domain";
    /**
     * dto前缀
     */
    public static final String DTO = "dto";
    /**
     * 持久层前缀
     */
    public static final String MAPPER = "mapper";
    /**
     * 业务层前缀
     */
    public static final String SERVICE = "service";
    /**
     * 业务层实现类前缀
     */
    public static final String SERVICE_IMPL = "serviceImpl";
    /**
     * 所有前缀
     */
    public static final String[] ALL_PREFIX = {CONTROLLER,DOMAIN,DTO,MAPPER,SERVICE,SERVICE_IMPL};

}
