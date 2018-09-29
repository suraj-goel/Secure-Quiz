package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import enumConstant.Status;
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
import mainClasses.FileHandle;
import mainClasses.FileReciever;
import mainClasses.Quiz;
import mainClasses.Subject;
import questions.Question;
import request.QuestionFetchRequest;
import request.QuizListFetchRequest;
import request.QuizListFetchStudentRequest;
import request.SubjectListFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Controller_studentportal {
    @FXML
    Label status;
    @FXML
    JFXListView subjects,quizs;
    volatile List<Subject> serversubjects;
    volatile List<Quiz> serverquiz;
    static List<Question> decryptedunsortedQuestions;
    static Quiz selectedQuiz;
    @FXML
    JFXTextArea info;
    volatile String check;

//    initialses the scene.
    public void initialize(){
        subjects.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        quizs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        SubjectListFetchRequest subjectListFetchRequest =new SubjectListFetchRequest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(subjectListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    serversubjects = (List<Subject>) ois.readObject();


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            subjects.getItems().addAll(serversubjects);

                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();


    }
//

//    Back to login scene..
    public void onlogoutclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/studentlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }


//      starts the selected quiz..
    public void onstartquizclicked(ActionEvent actionEvent) {
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        selectedQuiz=quiz;
        QuestionFetchRequest questionFetchRequest = new QuestionFetchRequest(quiz.getExamID());
        questionFetchRequest.setStudentID(Main.student.getStudentID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(questionFetchRequest);
                    oos.flush();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            status.setText("Please wait.");
                        }
                    });

                    FileReciever fileReciever = new FileReciever();
                    SocketChannel socketChannel = fileReciever.createServerSocketChannel();
                    System.out.println("Socket channel created...");
                    fileReciever.readFileFromSocket(socketChannel,quiz.getExamID());
                    System.out.println("Reading files..");
                    List<Question> questions = FileHandle.fileRead(quiz.getExamID());
                    decryptedunsortedQuestions = questions;
                    ObjectInputStream ois = Main.studentInputStream;
                    check = (String)ois.readObject();
                    if(check.equals(String.valueOf(Status.SUCCESS))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                status.setText("Success");
                                Stage primaryStage = (Stage) status.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/quizmain.fxml"));
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1303, 961));
                            }
                        });
                    }else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                status.setText("Error");
                            }
                        });
                    }







                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();


    }

//    opens student's dasboard..
    public void ondashboardclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/dashboard.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));


    }
    private String quizinfo(Quiz quiz){
        String temp = "Quiz Info:"+"\n";
        temp+= "ExamName: "+quiz.getExamName()+"\n";
        temp+="TeacherId: "+quiz.getTeacherID()+"\n";
        return temp;
    }



//    displays quizs corresponding to selected subject..
    public void handleclickedsubject(MouseEvent mouseEvent) {

        Subject currentSubject = (Subject)subjects.getSelectionModel().getSelectedItem();
        QuizListFetchStudentRequest quizListFetchRequest =new QuizListFetchStudentRequest(Main.student.getStudentID(),currentSubject.getSubjectID());
        quizListFetchRequest.setStudentID(Main.student.getStudentID());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(quizListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    serverquiz = (List<Quiz>) ois.readObject();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            quizs.getItems().clear();
                            for (Quiz quiz:serverquiz){
                                System.out.println(quiz.getExamName());
                            }
                            quizs.getItems().addAll(serverquiz);
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
    }


//    displays info of the quiz..
    public void handleclickedquiz(MouseEvent mouseEvent) {
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        String in = quizinfo(quiz);
        info.setText(in);
    }
}
