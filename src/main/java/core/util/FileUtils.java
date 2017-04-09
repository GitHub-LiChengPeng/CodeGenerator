package core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 文件操作工具类.
 *
 * @author 李程鹏
 */
public class FileUtils {
    /**
     * <strong>Description:</strong>
     * <pre>
     * 生成文件.
     * </pre>
     *
     * @param relativePath 相对路径
     * @param fileName     文件名
     * @param content      文件内容
     */
    public static void generateFile(String relativePath, String fileName, String content) throws Exception {
        // 获取工程的相对路径
        String projectPath = "\\" + NameUtils.PROJECT.getValue();
        // 拼接出文件的绝对路径
        String absolutePath = PathUtils.DESKTOP.getValue() + projectPath + relativePath;
        // 定义文件路径对象
        File directory = new File(absolutePath);
        // 如果路径不存在
        if (!directory.exists()) {
            // 创建路径
            directory.mkdirs();
        }
        // 定义文件对象
        File file = new File(absolutePath + fileName);
        // 根据文件对象创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        // 创建输出流写入器
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        // 创建缓冲区写入器
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        // 写入文件内容
        bufferedWriter.write(content);
        // 关闭缓冲区写入器
        bufferedWriter.close();
    }
}
