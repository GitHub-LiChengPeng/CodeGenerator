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
        // 新建源文件对象
        File sourceFile = new File(from);
        // 新建目标文件对象
        File destinationFile = new File(to);
        // 定义文件输入流
        FileInputStream fileInputStream = null;
        // 定义文件输出流
        FileOutputStream fileOutputStream = null;
        try {
            // 如果目标文件对象不存在
            if (!destinationFile.exists()) {
                // 新建一个目标文件对象
                destinationFile.createNewFile();
            }
            // 初始化文件输入流
            fileInputStream = new FileInputStream(sourceFile);
            // 初始化文件输出流
            fileOutputStream = new FileOutputStream(destinationFile);
            // 创建字节数组,图片只能使用字节流的形式进行拷贝.
            byte data[] = new byte[1024];
            // 读取的字节数
            int count = 0;
            // 循环读取输入流的内容
            while ((count = fileInputStream.read(data)) != -1) {
                // 写出读取到的内容
                fileOutputStream.write(data, 0, count);
            }
        } catch (Exception e) {
            // 如果出了异常,打印异常信息.
            System.out.println("拷贝文件过程出错!");
        } finally {
            try {
                // 关闭文件输入流
                if (fileInputStream != null) fileInputStream.close();
                // 关闭文件输出流
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                // 如果出了异常,打印异常信息.
                System.out.println("关闭IO流对象出错!");
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