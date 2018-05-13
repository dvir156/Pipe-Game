import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    private static final int port  = 12345;
    ServerSocket server=new ServerSocket(port);
server.setSoTimeout(1000);
try{
        Socket aClient=server.accept(); // blocking call
        InputStreamin FromClient=aClient.getInputStream();
        OutputStream outToClient=aClient.getOutputStream();
// interact (read & wite) with the client according to protocol
        inFromClient.close();
        outToClient.close();
        aClient.close();
        server.close();
    }catch(SocketTimeoutExceptione){/*...*/}

    publicvoidstart(String ip, int port){
        try{
            Socket theServer=new Socket(ip, port);
            System.out.println("connected to server");
            BufferedReaderuserInput=new BufferedReader(newInputStreamReader(System.in));
            BufferedReaderserverInput=new BufferedReader(newInputStreamReader(theServer.getInputStream()));
            PrintWriteroutToServer=new PrintWriter(theServer.getOutputStream());
            PrintWriteroutToScreen=new PrintWriter(System.out);
// correspond according to a well-defined protocol
            readInputsAndSend(userInput,outToServer,"exit");
            readInputsAndSend(serverInput,outToScreen,"bye");
            userInput.close();
            serverInput.close();
            outToServer.close();
            outToScreen.close();
            theServer.close();
        } catch(UnknownHostExceptione){/*...*/}
        catch(IOExceptione){/*...*/}
}
