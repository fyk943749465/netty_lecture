package com.shengsiyuan.nio.book.crazymakercircle.socketDemos;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.*;
import java.net.Socket;

public class BlockSendClient extends Socket {

    private Socket client;

    private FileInputStream fis;

    private DataOutputStream dos;

    /**
     * 构造函数
     * 与服务器建立连接
     * @throws IOException
     */
    public BlockSendClient() throws IOException {
        super(NioDemoConfig.SOCKET_SERVER_IP,
                NioDemoConfig.SOCKET_SERVER_PORT);

        this.client = this;
        Logger.debug("Client[port:" + client.getLocalPort() + "] 成功连接服务器端");
    }

    public void sendFile() {
        try {
            File file = new File(NioDemoConfig.SOCKET_SEND_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());

                // 文件名和长度
                dos.writeUTF("copy_" + file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();

                // 开始传输文件
                Logger.debug("========= 开始传输文件 ============");
                byte[] bytes = new byte[1024];
                int length = 0;
                long progress = 0;
                while((length = fis.read(bytes, 0, bytes.length)) != -1) {
                    dos.write(bytes, 0, length);
                    dos.flush();
                    progress += length;
                    Logger.debug("|" + (100 * progress / file.length()) + "% |");
                }
                Logger.debug("========= 文件传输成功 =========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            BlockSendClient client = new BlockSendClient(); // 启动客户端连接
            client.sendFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
