package core.util;

/**
 * 文本操作工具类.
 *
 * @author 李程鹏
 */
public class TextUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 为文本添加Tab缩进.
     * </pre>
     *
     * @param text        目标文本
     * @param indentCount 缩进次数
     */
    public static void addIndentation(StringBuilder text, int indentCount) {
        // 循环添加Tab缩进
        for (int count = 0; count < indentCount; count++) {
            // 添加一个Tab缩进
            text.append("\t");
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为文本添加换行符.
     * </pre>
     *
     * @param text 目标文本
     */
    public static void addLine(StringBuilder text) {
        // 添加一个换行符
        text.append("\n");
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 为文本添加空格.
     * </pre>
     *
     * @param text 目标文本
     */
    public static void addSpace(StringBuilder text) {
        // 添加一个空格
        text.append(" ");
    }
}