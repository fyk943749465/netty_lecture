package com.shengsiyuan.nio.book.crazymakercircle.fileDemos;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.IOUtil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileNIOFastCopyDemo {

    public static void main(String[] args) {

        nioCopyResourceFile();
    }

    private static void nioCopyResourceFile() {

        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcDecodePath = IOUtil.getResourcePath(sourcePath);
        Logger.debug("srcDecodePath=" + srcDecodePath);

        String destPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destDecodePath = IOUtil.builderResourcePath(destPath);
        Logger.debug("destDecodePath" + destDecodePath);

        nioCopyFile(srcDecodePath, destDecodePath);
    }

    private static void nioCopyFile(String srcDecodePath, String destDecodePath) {

        File srcFile = new File(srcDecodePath);
        File destFile = new File(destDecodePath);

        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            long startTime = System.currentTimeMillis();

            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannle = null;
            FileChannel outChannel = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannle = fis.getChannel();
                outChannel = fos.getChannel();

                long size = inChannle.size();
                long pos = 0;
                long count = 0;
                while (pos < size) {
                    // 每次复制最多1024个字节，没有就复制剩余的
                    count = size - pos > 1024 ? 1024 : size - pos;
                    // 复制内存，偏移量 + count长度
                    pos += outChannel.transferFrom(inChannle, pos, count);
                }
                outChannel.force(true);
            } finally {
                IOUtil.closeQuietly(outChannel);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(inChannle);
                IOUtil.closeQuietly(fis);
            }
            long endTime = System.currentTimeMillis();
            Logger.debug("base 复制毫秒数" + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
