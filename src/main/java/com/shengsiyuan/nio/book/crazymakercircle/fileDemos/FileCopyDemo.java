package com.shengsiyuan.nio.book.crazymakercircle.fileDemos;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.IOUtil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.*;

public class FileCopyDemo {

    public static void main(String[] args) {
        copyResourceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    public static void copyResourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcDecodePath = IOUtil.getResourcePath(sourcePath);
        Logger.debug("srcDecodePath=" + srcDecodePath);

        String destPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destDecodePath = IOUtil.builderResourcePath(destPath);
        Logger.debug("destDecodePath=" + destDecodePath);

        blockCopyFile(srcDecodePath, destDecodePath);
    }

    private static void blockCopyFile(String srcPath, String destPath) {

        InputStream input = null;
        OutputStream output = null;

        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            // 如果目标文件不存在，则新建
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            long startTime = System.currentTimeMillis();

            input = new FileInputStream(srcFile);
            output = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            long endTime = System.currentTimeMillis();
            Logger.debug("IO流复制毫秒数:" + (endTime - startTime));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(input);
            IOUtil.closeQuietly(output);
        }
    }
}
