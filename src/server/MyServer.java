package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Comparator;

public class MyServer implements Server
{
    private int port;
    private volatile boolean stop;
    private Thread thread;
    private PriorityExecutorService executor;

    public MyServer(int port,int M)
    {
        this.port = port;
        stop = false;
        this.executor = new PriorityExecutorService(M, M, new Comparator<Runnable>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(Runnable o1, Runnable o2) {
                return ((Comparable<Runnable>) o1).compareTo(o2);
            }
        });
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
                   // ch.handle(inputS, outputS);
                    MyRunnable myRunnable = new MyRunnable(aClient, inputS, outputS,
                            ch);
                    executor.addRunnable(myRunnable);
                   // inputS.close();
                    //outputS.close();
                    //aClient.close();
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
        MyServer myServer = new MyServer(6400,2);
        myServer.start(new MyClientHandler());
    }
}
