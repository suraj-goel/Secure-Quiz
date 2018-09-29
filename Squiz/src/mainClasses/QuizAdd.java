package mainClasses;

import enumConstant.Status;
import request.QuizAddRequest;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizAdd {
    public String add(QuizAddRequest quizAddRequest){
        FileReciever nioServer = new FileReciever();
        System.out.println("hi");
        SocketChannel socketChannel=nioServer.createServerSocketChannel();
        String query="UPDATE exam SET Access= 0 where ExamID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,quizAddRequest.getQuizID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nioServer.readFileFromSocket(socketChannel,quizAddRequest.getQuizID());
    }
}
