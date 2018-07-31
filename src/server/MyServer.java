package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements Server
{
    private int port;
    private volatile boolean stop;

    public MyServer(int port)
    {
        this.port = port;
        stop = false;
    }

    // "127.0.0.1" / "localhost"
    private void runServer(ClientHandler ch) throws Exception
    {

        InputStream inputS;
        OutputStream outputS;
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        System.out.println("Listening on port " + port);
        while (!stop) {
            try {
                Socket aClient = server.accept(); // blocking call
                try {
                    inputS = aClient.getInputStream();
                    outputS = aClient.getOutputStream();
                    ch.handle(inputS, outputS);
                    inputS.close();
                    outputS.close();
                    aClient.close();
                    System.out.println("exit");
                } catch (IOException e) {e.printStackTrace(); }
            }catch(SocketTimeoutException ignored){ }
        } server.close();
    }
    public void start(ClientHandler ch){
        new Thread(()->{try {
            runServer(ch);
        }catch (Exception e){
            e.printStackTrace();
        }
        }).start();
    }
    public void stop()
    {
        stop=true;
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer(6400);
        myServer.start(new MyClientHandler());
    }
}
