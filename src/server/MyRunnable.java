package server;

import algorithm.Searchable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;




public class MyRunnable implements Runnable, Comparable<Runnable> {
    private int size;
    private Socket aClient;
    @SuppressWarnings("rawtypes")
    private InputStream inputS;
    private OutputStream outputS;
    @SuppressWarnings("rawtypes")
    private ClientHandler clientHandler;

    @SuppressWarnings("rawtypes")
    public MyRunnable(Socket aClient, InputStream is, OutputStream os, ClientHandler ch) {
        this.aClient = aClient;
        this.inputS = is;
        this.outputS = os;
        this.clientHandler = ch;
       // this.size = is.;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        try {
            clientHandler.handle(inputS,outputS);
            try {
                aClient.getInputStream().close();
                aClient.getOutputStream().close();
            } catch (Exception e) {
                // e.printStackTrace();
            }
            aClient.close();
        } catch (IOException e) {

            // e.printStackTrace();
        }
    }

    public int getPriority() {
        return size;
    }

    public void setPriority(int priority) {
        this.size = priority;
    }

    @Override
    public int compareTo(Runnable other) {
        return this.size - ((MyRunnable) other).size;
    }

}
