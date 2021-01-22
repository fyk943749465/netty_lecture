package com.shengsiyuan.nio.book.crazymakercircle.fileDemos;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.IOUtil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOCopyDemo {

    public static void main(String[] args) {
        nioCopyResourceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    private static void nioCopyResourceFile() {

        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcPath = IOUtil.getResourcePath(sourcePath);
        Logger.debug("srcPath=" + srcPath);

        String destShortPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destDePath = IOUtil.builderResourcePath(destShortPath);
        Logger.debug("destdePath=" + destDePath);

        nioCopyFile(srcPath, destDePath);
    }

    private static void nioCopyFile(String srcPath, String destDePath) {

        File srcFile = new File(srcPath);
        File destFile = new File(destDePath);

        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            long startTime = System.currentTimeMillis();

            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();

                int length = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                while ((length = inChannel.read(buf)) != -1) {
                   // 翻转buf 变成读模式
                    buf.flip();

                    int outlength = 0;
                    //　将buf写入到输出的通道
                    while ((outlength = outChannel.write(buf)) != 0) {
                        System.out.println("写入字节数:" + outlength);
                    }
                    // 清除buf, 变成写模式
                    buf.clear();
                }
                // 强制刷新磁盘
                outChannel.force(true);
            } finally {
                IOUtil.closeQuietly(outChannel);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(inChannel);
                IOUtil.closeQuietly(fis);
            }

            long endTime = System.currentTimeMillis();
            Logger.debug("base 复制毫秒数:" + (endTime - startTime));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
