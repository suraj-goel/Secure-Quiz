package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.Student;
import mainClasses.Teacher;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    public static Socket studentSocket,teachersocket ;
    public static ObjectOutputStream studentoutputStream,teacherOutputStream;
    public static ObjectInputStream studentInputStream,teacherInputStream;
    public static OutputStream os;
    public static InputStream is;
    public static String serverip="192.168.43.10";
    public static int portno = 6963;
    public static Student student;
    public static Teacher teacher;
    public static List<String> questionType =new ArrayList<>();
    public static HashMap<String,Integer> submittedAnswers = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        primaryStage.setTitle("SQuiz");
        primaryStage.setScene(new Scene(root, 1081, 826));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
