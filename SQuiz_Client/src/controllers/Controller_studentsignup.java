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
import mainClasses.Student;
import mainClasses.UidGenerator;
import request.SignUpRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller_studentsignup {
    @FXML
    JFXButton back;
    @FXML
    JFXTextField firstname,lastname,email,age;
    @FXML
    JFXPasswordField password;
    @FXML
    Label status;
    volatile String check;
    Student student;
    SignUpRequest signUpRequest;


//    Signup for the student
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(firstname.getText().isEmpty()||lastname.getText().isEmpty()||email.getText().isEmpty()||age.getText().isEmpty()||password.getText().isEmpty())
        {
            status.setText("Enter all fields");
            return;
        }
        String string=UidGenerator.generateuid();
        System.out.println(string);
        student = new Student(string, UidGenerator.generateuid(email.getText()));
        System.out.println(student.getUserID());
        student.setEmail(email.getText());
        student.setAge(Integer.parseInt(age.getText()));
        student.setFirstName(firstname.getText());
        student.setLastName(lastname.getText());
        student.setJob(String.valueOf(UserJob.STUDENT));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    signUpRequest = new SignUpRequest(student, HashGenerator.hash(password.getText()));

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

                                    root = FXMLLoader.load(getClass().getResource("/gui/studentlogin.fxml"));
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

//    back to login scene.
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
