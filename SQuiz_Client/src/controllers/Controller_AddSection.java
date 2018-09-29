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
import mainClasses.Quiz;
import mainClasses.Subject;
import mainClasses.UidGenerator;
import request.QuizListFetchRequest;
import request.SectionsAddRequest;
import request.SubjectListFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Controller_AddSection {
    @FXML
    JFXButton back;
    @FXML
    JFXComboBox subject,quiz;
    @FXML
    JFXTextField sectionname,sectiontime;
    @FXML
    Label status;
    volatile List<Subject> serversubjects,serverquiz;
    @FXML
    JFXTextField searchsubject;
    volatile String check;

//    initialises the scene.
    public void initialize(){
        quiz.setVisible(false);
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

//    back to teacher portal
    public void onbackclicked() {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/teacherportal.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

//    add the section and send the server a section add request.
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(sectionname.getText().isEmpty()||sectiontime.getText().isEmpty()){
            status.setText("Enter both fields");
            return;
        }
        SectionsAddRequest sectionsAddRequest = new SectionsAddRequest();
        Quiz quiz = (Quiz) this.quiz.getSelectionModel().getSelectedItem();
        sectionsAddRequest.setExamID(quiz.getExamID());
        sectionsAddRequest.setSectionID(UidGenerator.generateuid());
        sectionsAddRequest.setSectionName(sectionname.getText());
        int t = Integer.parseInt(sectiontime.getText());

        sectionsAddRequest.setSectionTime(t);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(sectionsAddRequest);
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

//    fetches a list of sections corresponding to a subject.
    public void onsubjectclicked(){
        quiz.setVisible(true);
        quiz.getItems().clear();

        QuizListFetchRequest quizListFetchRequest =new QuizListFetchRequest();
        quizListFetchRequest.setTeacherID(Main.teacher.getTeacherID());
        Subject currentSubject = (Subject)subject.getSelectionModel().getSelectedItem();
        quizListFetchRequest.setSubjectID(currentSubject.getSubjectID());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(quizListFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    serverquiz = (List<Subject>) ois.readObject();



                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            quiz.getItems().addAll(serverquiz);
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();


    }


}
