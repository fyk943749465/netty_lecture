package com.shengsiyuan.nio.book.crazymakercircle.fileDemos;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.IOUtil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.*;

public class FileReadDemo {

    public static void main(String[] args) {

        readSourceFile();
    }

    private static void readSourceFile() {

        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String decodePath = IOUtil.getResourcePath(sourcePath);

        Logger.debug("decodePath=" + decodePath);

        readFile(decodePath);
    }

    private static void readFile(String fileName) {

        File file = new File(fileName);
        try {
            Reader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String data = null;
            while((data = bufferedReader.readLine()) != null) {
                Logger.debug(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
