package controllers;

import com.jfoenix.controls.JFXButton;
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
import request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Controller_TeacherDashboard {
    public JFXTextArea leaderboard,quizscore;
    @FXML
    Label status;
    @FXML
    JFXListView quizs,students;
    @FXML
    JFXButton back;
    volatile List<Student> serverStudents;
    volatile double quizRating,quizScore;
    volatile List<Quiz> serverquizs;

//    initialises the scene with a list of quizs..
    public void initialize(){
        quizs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        get a list of quizs given by the current student.
        QuizListFetchTeacherRequest quizListFetchTeacherRequest = new QuizListFetchTeacherRequest(Main.teacher.getTeacherID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(quizListFetchTeacherRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
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

//    displays a rank list corresponding to the selected quiz..
    public void handleclickedquiz(MouseEvent mouseEvent) {
        Quiz currentQuiz = (Quiz)quizs.getSelectionModel().getSelectedItem();

        RankFetchRequest rankFetchRequest = new RankFetchRequest(currentQuiz.getExamID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Socket socket = Main.teachersocket;
//
                    ObjectOutputStream oos = Main.teacherOutputStream;
                    ObjectInputStream ois = Main.teacherInputStream;


                    oos.writeObject(rankFetchRequest);
                    oos.flush();
                    serverStudents = (List<Student>) ois.readObject();

//                    String temp = rankList(serverStudents);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            students.getItems().setAll(serverStudents);


                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

//back to teacher portal.
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherportal.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
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

//    displays score of the student and rating of the quiz..
    public void handleclickedstudents(MouseEvent mouseEvent) {
        Quiz currentQuiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        Student currentStudent = (Student)students.getSelectionModel().getSelectedItem();

        //        RatingFetchRequest ratingFetchRequest = new RatingFetchRequest();
//        change rating fetch request in server side...

//      Also get a list of students for leader Board..
        RatingFetchRequest ratingFetchRequest =new RatingFetchRequest(currentQuiz.getExamID());
        ScoreFetchRequest scoreFetchRequest = new ScoreFetchRequest(currentQuiz.getExamID(), currentStudent.getStudentID());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Socket socket = Main.teachersocket;
//
                    ObjectOutputStream oos = Main.teacherOutputStream;
                    ObjectInputStream ois = Main.teacherInputStream;


                    oos.writeObject(ratingFetchRequest);
                    oos.flush();
                    quizRating = (double) ois.readObject();

                    oos.writeObject(scoreFetchRequest);
                    oos.flush();
                    quizScore = (double) ois.readObject();



                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            quizscore.setText("Quiz Rating: "+quizRating +"\n"+"Score: "+quizScore +"\n");
                        }
                    });
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();

    }

//    displays comment scene of for the teacher..
    public void oncommentclicked(){
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/comments_Teacher.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}


