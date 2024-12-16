import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class VideoReceiverServer {
    public static void main(String[] args) {
        int port = 8080; // 监听端口
        String savePath = "D:/aa/video.mp4"; // 保存视频文件的路径

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // 接收视频数据
                try (InputStream inputStream = clientSocket.getInputStream();
                     FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Video file received and saved successfully!");
                }

                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}