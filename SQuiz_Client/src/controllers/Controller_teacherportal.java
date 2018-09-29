package controllers;

import com.jfoenix.controls.JFXButton;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_teacherportal {
    @FXML
    JFXButton logout;
//      basic teacher portal for adding questions,student analysis etc..
    public void onaddsubjectclicked(ActionEvent actionEvent) {
//        send the request to user and recieve confirmation and then change execute the below code.
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/addsubject.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onaddquestionclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/addquestion.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }

    public void onlogoutclicked(ActionEvent actionEvent) {
        Main.teacher=null;
        Main.teachersocket=null;
        Main.teacherOutputStream=null;
        Main.teacherInputStream=null;
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }

    public void onaddquizclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/addquiz.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onadsectionsclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/addsection.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onstudentsclicked(){
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherdashboard.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));

    }

    public void onbackclicked(ActionEvent actionEvent) {
    }

    public void handleclickedsection(MouseEvent mouseEvent) {
    }

    public void handleclickedquestion(MouseEvent mouseEvent) {
    }

    public void onsubmitclicked(ActionEvent actionEvent) {
    }
}
