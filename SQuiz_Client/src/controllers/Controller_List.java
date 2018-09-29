package controllers;

import com.jfoenix.controls.JFXListView;
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
import mainClasses.FileHandle;
import mainClasses.FileSender;
import mainClasses.Sections;
import questions.Question;

import request.QuizAddRequest;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.List;

public class Controller_List {

    public JFXListView questions;
    @FXML
    Label status;
    volatile String check;


//    initialise sthe scene.
    public void initialize(){
        questions.getItems().addAll(Controller_AddQuestion.questionList);

    }

    public void onsubmitclicked(ActionEvent actionEvent) {
        FileHandle.fileWrite(Controller_AddQuestion.questionList,Controller_AddQuestion.quizid);
        QuizAddRequest quizAddRequest = new QuizAddRequest(Controller_AddQuestion.quizid);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(quizAddRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    check = (String) ois.readObject();
                    if(check.equals(String.valueOf(Status.SUCCESS))){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) status.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/teacherportal.fxml"));
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
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        FileSender fileSender = new FileSender();
        FileSender nioClient = new FileSender();
        SocketChannel socketChannel = nioClient.createChannel();
        nioClient.sendFile(socketChannel,Controller_AddQuestion.quizid);


    }

//    back to teacher portal
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/addquestion.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }

//  nothing to be done when a que is selected.
    public void handleclickedquestions(MouseEvent mouseEvent) {
    }


//    removes the question from list.
    public void onremoveclicked(ActionEvent actionEvent) {
        Question selectedQuestion = (Question)questions.getSelectionModel().getSelectedItem();
        Controller_AddQuestion.questionList.remove(selectedQuestion);
        questions.getItems().clear();
        questions.getItems().addAll(Controller_AddQuestion.questionList);
    }
}
