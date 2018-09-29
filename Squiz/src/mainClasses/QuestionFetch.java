package mainClasses;

import request.QuestionFetchRequest;

import java.nio.channels.SocketChannel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionFetch {
    public String fetch(QuestionFetchRequest questionFetchRequest,String ip){
        System.out.println("request started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FileSender nioClient = new FileSender();
        System.out.println("file sender initialised");
        SocketChannel socketChannel = nioClient.createChannel(ip);
        System.out.println("chnnel created");
        String query="Insert into StudentExam(StudentID, ExamID) values(?,?);";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,questionFetchRequest.getStudentID());
            preparedStatement.setString(2,questionFetchRequest.getQuizID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Transfer starting");
        return nioClient.sendFile(socketChannel,questionFetchRequest.getQuizID());
    }
}
