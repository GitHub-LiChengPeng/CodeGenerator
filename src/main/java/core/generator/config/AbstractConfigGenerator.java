package core.generator.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件生成器抽象类.
 *
 * @author 李程鹏
 */
public abstract class AbstractConfigGenerator {
    /**
     * 数据映射对象
     */
    protected Map<String, Object> data = new HashMap<>();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为数据映射对象放入数据.
     * </pre>
     */
    protected abstract void putData();

    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成配置文件.
     * </pre>
     */
    protected abstract void generate() throws Exception;
}