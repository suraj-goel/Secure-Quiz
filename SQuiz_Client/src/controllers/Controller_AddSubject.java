package controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import mainClasses.UidGenerator;
import request.SubjectAddRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_AddSubject {
    @FXML
    JFXButton back;
    @FXML
    JFXTextField name;
    @FXML
    Label status;
    volatile String check;

//    sends a subjectAddRequest to the server.
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(name.getText().isEmpty()){
            status.setText("Enter Subject!");
            return;
        }

        SubjectAddRequest sar = new SubjectAddRequest(name.getText(), Main.teacher.getTeacherID(), UidGenerator.generateuid());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(sar);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    check = (String)ois.readObject();
                    System.out.println(check);
                    if(check.equals(String.valueOf(Status.SUCCESS))){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) back.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/teacherportal.fxml"));
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1081, 826));
                            }
                        });

                    }else {
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


//    back to teacher portal.
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
}
