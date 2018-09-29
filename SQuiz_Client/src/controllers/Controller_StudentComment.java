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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainClasses.*;
import request.CommentAddRequest;
import request.CommentFetchRequest;
import request.QuizByStudentRequest;
import request.QuizListFetchStudentRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Controller_StudentComment {
    @FXML
    Label status;
    @FXML
    JFXTextField comment;
    @FXML
    JFXTextArea commentinfo;
    @FXML
    JFXListView quizs;
    volatile List<Quiz> serverquizs;
    volatile String check;
    volatile List<Comment> serverComments;


//    initialises the scene..
    public void initialize(){

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

//    submits the comment entered by the student.
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(comment.getText().isEmpty()){
            status.setText("Enter a comment");
            return;
        }
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        Comment commentobj =new Comment(quiz.getExamID(), UidGenerator.generateuid(),Main.student.getStudentID());
        commentobj.setComment(comment.getText());
        commentobj.setPosterName(Main.student.getFirstName());
        CommentAddRequest commentAddRequest = new CommentAddRequest(commentobj);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(commentAddRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    check = (String) ois.readObject();

                    if(check.equals(String.valueOf(Status.SUCCESS))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) status.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/dashboard.fxml"));
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1081, 826));

                            }
                        });
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();


    }


//    back to student dashboard..
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/dashboard.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }


//    returns a String of all the comments..
    private String commentinformation(List<Comment> li){
        String temp = "";
        for(Comment comment : li){
            temp+= comment.getPosterName()+" : "+comment.getComment()+"\n";
        }
        return temp;
    }


//    Displays comments related to the selected quiz..
    public void handleclickedquiz(MouseEvent mouseEvent) {
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        CommentFetchRequest commentFetchRequest =new CommentFetchRequest(quiz.getExamID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    oos.writeObject(commentFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    serverComments = (List<Comment>) ois.readObject();


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                commentinfo.setText(commentinformation(serverComments));

                            }
                        });

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
