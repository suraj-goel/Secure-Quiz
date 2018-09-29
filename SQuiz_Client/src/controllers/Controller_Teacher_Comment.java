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
import request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Controller_Teacher_Comment {
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


//    scene similar to student's comment scene
    public void initialize(){

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
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(comment.getText().isEmpty()){
            status.setText("Enter a comment");
            return;
        }
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();

        Comment commentobj =new Comment(quiz.getExamID(), UidGenerator.generateuid(),Main.teacher.getTeacherID());
        commentobj.setComment(comment.getText());
        commentobj.setPosterName(Main.teacher.getFirstName());
        CommentAddRequest commentAddRequest = new CommentAddRequest(commentobj);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(commentAddRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    check = (String) ois.readObject();

                    if(check.equals(String.valueOf(Status.SUCCESS))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) status.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/teacherdashboard.fxml"));
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

    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherdashboard.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    private String commentinformation(List<Comment> li){
        String temp = "";
        for(Comment comment : li){
            temp+= comment.getPosterName()+" : "+comment.getComment()+"\n";
        }
        return temp;
    }

    public void handleclickedquiz(MouseEvent mouseEvent) {
        Quiz quiz = (Quiz)quizs.getSelectionModel().getSelectedItem();
        CommentFetchRequest commentFetchRequest =new CommentFetchRequest(quiz.getExamID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(commentFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
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
