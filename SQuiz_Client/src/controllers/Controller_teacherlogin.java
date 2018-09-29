package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import mainClasses.HashGenerator;
import mainClasses.Teacher;
import request.TeacherLoginRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_teacherlogin {
    @FXML
    JFXButton back;
    @FXML
    JFXTextField email;
    @FXML
    JFXPasswordField password;
    @FXML
    Label status;
    volatile Teacher teacher;

//    displays teacher signup scene
    public void onsignupclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teachersignup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }



//send login details of the teacher to the server..
    public void onloginclicked(ActionEvent actionEvent) {



            if(email.getText().isEmpty()||password.getText().isEmpty()){
                status.setText("Enter both fields");
                return;
            }
            TeacherLoginRequest teacherLoginRequest = new TeacherLoginRequest(email.getText(), HashGenerator.hash(password.getText()));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{


                        Socket socket = new Socket(Main.serverip,Main.portno);

                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(teacherLoginRequest);
                        oos.flush();
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        teacher = (Teacher) ois.readObject();

                        if(teacher.getTeacherLoginStatus().equals(String.valueOf(Status.SUCCESS))){
                            Main.teacher=teacher;
                            Main.teachersocket=socket;
                            Main.teacherInputStream=ois;
                            Main.teacherOutputStream=oos;

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


//        back to main login scene..
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/studentlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}
