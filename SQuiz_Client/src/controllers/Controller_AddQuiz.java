package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import mainClasses.Subject;
import mainClasses.UidGenerator;
import request.QuizNameAddRequest;
import request.SubjectListFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Controller_AddQuiz {
    @FXML
    JFXButton back;
    @FXML
    JFXComboBox subject;
    @FXML
    JFXTextField quizname;
    @FXML
    Label status;
    volatile List<Subject> serversubjects;
    @FXML
    JFXTextField searchsubject;
    volatile String check;

//    initialises the scene.
    public void initialize(){
        subject.getItems().clear();
        SubjectListFetchRequest subjectListFetchRequest =new SubjectListFetchRequest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(subjectListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    serversubjects = (List<Subject>) ois.readObject();


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                subject.getItems().addAll(serversubjects);
                            }
                        });
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();

    }

//    back to teacher portal.
    @FXML
    public void onbackclicked( ) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherportal.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onsubmitclicked(ActionEvent actionEvent) {
        if(quizname.getText().isEmpty()){
            status.setText("Enter Quiz name");
            return;
        }
        if(subject.getSelectionModel().isEmpty()){
            status.setText("Select subject");
            return;
        }
        Subject selectedSubject =(Subject)subject.getSelectionModel().getSelectedItem();
        QuizNameAddRequest quizNameAddRequest = new QuizNameAddRequest(UidGenerator.generateuid(),Main.teacher.getTeacherID(),selectedSubject.getSubjectID());

        quizNameAddRequest.setSubject(selectedSubject);
        quizNameAddRequest.setExamName(quizname.getText());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(quizNameAddRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    check = (String)ois.readObject();

                    if(check.equals(String.valueOf(Status.SUCCESS))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                onbackclicked();
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

//    searches the list of available subjects.
    public void onsearchclicked(ActionEvent actionEvent) {
        SubjectListFetchRequest subjectListFetchRequest =new SubjectListFetchRequest();
        if(searchsubject.getText().isEmpty()){
            status.setText("Enter search value");
            return;
        }
        subjectListFetchRequest.setSearch(searchsubject.getText());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(subjectListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;

                    serversubjects = (List<Subject>) ois.readObject();
                    System.out.println(serversubjects);


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            subject.getItems().clear();
                            subject.getItems().addAll(serversubjects);
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();


    }

//    fetches the list of all subjects.
    public void onallsubjectsclicked(){
        SubjectListFetchRequest subjectListFetchRequest =new SubjectListFetchRequest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(subjectListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    serversubjects = (List<Subject>) ois.readObject();


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            subject.getItems().clear();
                            subject.getItems().addAll(serversubjects);
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
    }

}
