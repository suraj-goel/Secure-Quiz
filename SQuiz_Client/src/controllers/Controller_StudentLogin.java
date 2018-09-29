package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import enumConstant.LoginStatus;
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
import mainClasses.Student;
import request.StudentLoginRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_StudentLogin {
    @FXML
    JFXButton signup;
    @FXML
    JFXTextField email;
    @FXML
    JFXPasswordField password;
    @FXML
    Label status;

    volatile Student check;


//    creates a new socket and sends the login info of the student to the server..
    public void onloginclicked(ActionEvent actionEvent) {
        StudentLoginRequest slr = new StudentLoginRequest(email.getText(), HashGenerator.hash(password.getText()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket socket = new Socket(Main.serverip,Main.portno);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(slr);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    check = (Student)ois.readObject();
                    if(check.getStudentLoginStatus().equals(String.valueOf(LoginStatus.SUCCESS))){
                        Main.studentSocket=socket;
                        Main.student=check;
                        Main.studentInputStream=ois;
                        Main.studentoutputStream=oos;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) signup.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/studentportal.fxml"));
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1303, 961));

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
//


    }


//      opens the signup scene
    public void onsignupclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) signup.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/studentsignup.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }


//    opens the teacher scene.
    public void onteacherclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) signup.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }
}
