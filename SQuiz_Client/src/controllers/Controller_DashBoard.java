package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import gui.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainClasses.Quiz;
import mainClasses.Student;
import mainClasses.Subject;
import request.QuizByStudentRequest;
import request.RankFetchRequest;
import request.RatingFetchRequest;
import request.ScoreFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
// Controller For Dashboard
public class Controller_DashBoard {
    public JFXTextArea leaderboard,quizscore;
    @FXML
    Label status;
    @FXML
    JFXListView quizs;
    volatile List<Student> serverStudents;
    volatile double quizRating,quizScore;
    volatile List<Quiz> serverquizs;


//    initialises the scene.
    public void initialize(){
        quizs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        get a list of quizs given by the current student.
        QuizByStudentRequest quizByStudentRequest = new QuizByStudentRequest(Main.student.getStudentID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(quizByStudentRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    serverquizs = (List<Quiz>) ois.readObject();


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            quizs.getItems().addAll(serverquizs);

                        }
                    });

                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();

    }


//    back to teacher portal
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/studentportal.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1303, 961));
    }
    private String rankList(List<Student> li){
        int i =1;
        String temp = "";
        for(Student s : li){
            temp+= i+". " +s.getFirstName()+"\n";
            i++;
        }
        return temp;

    }


//    Fetches rating,score and RankList corresponding to the selected quiz..
    public void handleclickedquiz(MouseEvent mouseEvent) {
        Quiz currentQuiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
//        RatingFetchRequest ratingFetchRequest = new RatingFetchRequest();
//        change rating fetch request in server side...

//      Also get a list of students for leader Board..
        RatingFetchRequest ratingFetchRequest =new RatingFetchRequest(currentQuiz.getExamID());
        ScoreFetchRequest scoreFetchRequest = new ScoreFetchRequest(currentQuiz.getExamID(),Main.student.getStudentID());
        RankFetchRequest rankFetchRequest = new RankFetchRequest(currentQuiz.getExamID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(ratingFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    quizRating = (Double) ois.readObject();

                    oos.writeObject(scoreFetchRequest);
                    oos.flush();
                    quizScore = (Double) ois.readObject();

                    oos.writeObject(rankFetchRequest);
                    oos.flush();
                    serverStudents = (List<Student>) ois.readObject();

                    String temp = rankList(serverStudents);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            leaderboard.setText(temp);
                            quizscore.setText("Quiz Rating: "+quizRating+"\n"+"Score: "+quizScore);

                        }
                    });
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();

    }

//    opens the comments scene..
    public void oncommentclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/comments.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}
