package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import enumConstant.Status;
import enumConstant.UserJob;
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
import mainClasses.UidGenerator;
import request.SignUpRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_teachersignup {
    @FXML
    JFXButton back;
    @FXML
    JFXTextField firstname,lastname,email,contact,age;
    @FXML
    JFXPasswordField password;
    @FXML
    Label status;
    volatile String check;


//    sends signup request of teacher to the server..
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(firstname.getText().isEmpty()||lastname.getText().isEmpty()||email.getText().isEmpty()||contact.getText().isEmpty()||age.getText().isEmpty()||password.getText().isEmpty())
        {
            status.setText("Enter all fields");
            return;
        }
        Teacher teacher = new Teacher(UidGenerator.generateuid(),UidGenerator.generateuid(email.getText()));
        teacher.setAge(Integer.parseInt(age.getText()));
        teacher.setEmail(email.getText());
        teacher.setFirstName(firstname.getText());
        teacher.setLastName(lastname.getText());
        teacher.setJob(String.valueOf(UserJob.TEACHER));
        SignUpRequest signUpRequest = new SignUpRequest(teacher, HashGenerator.hash(password.getText()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = new Socket(Main.serverip,Main.portno);

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(signUpRequest);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    check = (String)ois.readObject();
                    if(check.equals(String.valueOf(Status.SUCCESS))){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) back.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/teacherlogin.fxml"));
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

//    back to teacher login scene..
    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}
