package core.util;

import java.io.*;

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

    /**
     * <strong>Description:</strong>
     * <pre>
     * 拷贝文件.
     * </pre>
     *
     * @param from 来源文件
     * @param to   目的文件
     */
    public static void copyFile(String from, String to) {
        // 定义缓冲读取器
        BufferedReader bufferedReader = null;
        // 定义打印流对象
        PrintStream printStream = null;
        try {
            // 初始化缓冲去读器
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
            // 初始化打印流对象
            printStream = new PrintStream(new FileOutputStream(to));
            // 定义行内容变量
            String line;
            // 读取数据
            while ((line = bufferedReader.readLine()) != null) {
                // 将读取到的内容打印到目标文件中
                printStream.println(line);
                // 刷新打印流对象
                printStream.flush();
            }
        } catch (Exception e) {
            // 如果出了异常,打印异常信息.
            System.out.println("拷贝文件出错!");
        } finally {
            try {
                // 关闭缓冲读取器
                if (bufferedReader != null) bufferedReader.close();
                // 关闭打印流对象
                if (printStream != null) printStream.close();
            } catch (IOException e) {
                // 如果出了异常,打印异常信息.
                System.out.println("关闭IO对象出错!");
            }
        }
    }

    /**
     * <strong>Description:</strong>
     * <pre>
     * 拷贝文件夹.
     * </pre>
     *
     * @param from 来源路径
     * @param to   目的路径
     */
    public static void copyDirectory(String from, String to) {
        // 根据来源路径新建一个文件夹对象
        File sourceDirectory = new File(from);
        // 获取来源路径下的所有文件和文件夹
        File[] sourceFiles = sourceDirectory.listFiles();
        // 根据目的路径新建一个文件夹对象
        File destinationDirectory = new File(to);
        // 如果目的路径不存在
        if (!destinationDirectory.exists()) {
            // 创建目的路径
            destinationDirectory.mkdirs();
        }
        // 遍历来源路径下的文件和文件夹
        for (File file : sourceFiles) {
            // 如果是文件
            if (file.isFile()) {
                // 拷贝文件到指定目录下
                copyFile(file.getPath(), to + "\\" + file.getName());
            } else if (file.isDirectory()) {// 如果是文件夹
                // 递归调用拷贝文件夹的方法
                copyDirectory(file.getPath(), to + "\\" + file.getName());
            }
        }
    }
}