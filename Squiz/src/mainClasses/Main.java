package mainClasses;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.sql.Connection;

public class Main {

    static String user="root";//Username for MySQL Connection
    static String password="shrey";//Password for MySQL Connection
    static Connection connection=MysqlConnection.connect();//Static Connection to Mysql Database
    public static void main(String[] args) {
        ServerSocket serverSocket;//initialising server socket

        Socket socket;
        try {
            serverSocket=new ServerSocket(6963);
            System.out.println("server started");
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }
        while (true){
            try {
                socket=serverSocket.accept();
                System.out.println("client connected");
                Thread t=new Thread(new HandleClientRequest(socket));
                t.start();//starting new thread to handle client request
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }

    }
}
