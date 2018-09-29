package controllers;

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
import mainClasses.Subject;
import questions.Question;
import request.RatingAddRequest;
import request.ScoreAddRequest;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Controller_QuizOver {
    private int total=0;
    volatile int score = 0;
    @FXML
    Label scorelabel,status;
    @FXML
    JFXTextField rating;
    volatile String check;

//    sends the rating and the score of the student to the server.
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(rating.getText().isEmpty()){
            status.setText("Enter Ratings");
            return;
        }
        int ratings = Integer.parseInt(rating.getText());
        if(ratings<0||ratings>10){
            status.setText("0-10..!!");
            return;
        }
        if(scorelabel.getText().isEmpty()){
            status.setText("Calculate score");
            return;
        }
        RatingAddRequest ratingAddRequest =new RatingAddRequest(Controller_studentportal.selectedQuiz.getExamID(),ratings);
        ScoreAddRequest scoreAddRequest = new ScoreAddRequest(Controller_studentportal.selectedQuiz.getExamID(),(double)score);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;
                    try {

                        ObjectOutputStream oos = Main.studentoutputStream;
                        oos.writeObject(ratingAddRequest);
                        oos.flush();
                        ObjectInputStream ois = Main.studentInputStream;
                        check = (String) ois.readObject();
                        oos.writeObject(scoreAddRequest);
                        check = (String) ois.readObject();
//                        creating a new socket if their is a connection breakdown..
                    }catch (EOFException e){

//                        creating a new socket incase of connection breakdown..
                        try{
                            Socket newSocket = new Socket(Main.serverip,Main.portno);
                            Main.studentSocket=newSocket;
                            ObjectOutputStream oos =new ObjectOutputStream(newSocket.getOutputStream());
                            Main.studentoutputStream = oos;
                            oos.writeObject(ratingAddRequest);
                            ObjectInputStream ois = new ObjectInputStream(newSocket.getInputStream());
                            Main.studentInputStream = ois;
                            check = (String)ois.readObject();
                            oos.writeObject(scoreAddRequest);
                            check = (String)ois.readObject();
                        }catch (IOException g){
                            g.printStackTrace();
                            System.out.println("Server Connection Unavailable");
                        }
                    }

                    if(check.equals(String.valueOf(Status.SUCCESS))) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) scorelabel.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("/gui/studentportal.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1303, 961));
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
                    e.printStackTrace();
                }
            }
        }).start();


    }


//    calculates the score
    public void scorebutton(ActionEvent actionEvent) {
        total =Controller_studentportal.decryptedunsortedQuestions.size();
        for(Map.Entry<String,Integer>entry : Main.submittedAnswers.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key);
            System.out.println(value);

            for(Question q : Controller_studentportal.decryptedunsortedQuestions){
                if(q.getQuestionID().equals(key)&&q.getCorrectoption()==value){

                    score++;
                }
            }
        }
        scorelabel.setText(score+" out of "+total);
        Main.submittedAnswers.clear();
    }


}
