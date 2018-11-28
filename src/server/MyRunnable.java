package server;

import algorithm.Searchable;
import sun.misc.IOUtils;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class MyRunnable implements Runnable, Comparable<Runnable> {
    private int size;
    private Socket aClient;
    @SuppressWarnings("rawtypes")
    private InputStream inputS;
    private OutputStream outputS;
    @SuppressWarnings("rawtypes")
    private ClientHandler clientHandler;

    @SuppressWarnings("rawtypes")
    public MyRunnable(Socket aClient, InputStream is, OutputStream os, ClientHandler ch) throws IOException {
        this.aClient = aClient;
        this.inputS = is;
        this.outputS = os;
        this.clientHandler = ch;
        size = is.available();

    }
    
//    public void getFileSize(InputStream inputStream ){
//        InputStream is = size.getInputStream();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        int b;
//        while ((b = is.read()) != -1)
//            os.write(b);
//        response.setContentLength(os.size());
//        response.getOutputStream().write(os.toByteArray());
//
//    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {

        clientHandler.handle(inputS, outputS);
        try {
            inputS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            aClient.close();
        } catch (IOException e) {
            e.printStackTrace();
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
