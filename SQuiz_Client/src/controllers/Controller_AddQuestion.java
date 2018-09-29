package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import mainClasses.Quiz;
import mainClasses.Sections;
import mainClasses.Subject;
import mainClasses.UidGenerator;
import questions.Question;
import request.QuizListFetchRequest;
import request.SectionFetchRequest;
import request.SubjectListFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Controller_AddQuestion {
    public JFXTextField solution;
    @FXML
    JFXButton back;

    @FXML
    JFXComboBox subject,quiz,section,questiontype;
    @FXML
    JFXTextArea questionname,option1,option2,option3,option4;
    @FXML
    Label status;
    static String quizid;

//    volatile variables,since they are used in a new thread.
    volatile List<Subject> serversubjects;
    volatile  List<Quiz> serverquiz;
    volatile  List<Sections> serversections;
    static List<Question> questionList ;
    List<Question> questionlistclient = new ArrayList<>();
    @FXML
    JFXTextField searchsubject;
    volatile String check;

//    initialises the scene .
    public void initialize(){
        quiz.setVisible(false);
        section.setVisible(false);
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
                            quiz.getItems().clear();
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
        Main.questionType.add("MCQ");
        Main.questionType.add("Single Choice");
        Main.questionType.add("True/False");
        questiontype.getItems().addAll(Main.questionType);



    }
    public void onsubjectclicked(){
        quiz.setVisible(true);
        quiz.getItems().clear();

        QuizListFetchRequest quizListFetchRequest =new QuizListFetchRequest();
        quizListFetchRequest.setTeacherID(Main.teacher.getTeacherID());
        Subject currentSubject = (Subject)subject.getSelectionModel().getSelectedItem();
//        quizlistFetch used to fetch quizs made by the teacher..
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
                    serverquiz = (List<Quiz>) ois.readObject();



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

//    back to teacher's portal
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


//    Fetches the list of sections for a particular quiz.
    public void onquizclicked(){
        section.setVisible(true);
        section.getItems().clear();
        Quiz selectedQuiz = (Quiz) quiz.getSelectionModel().getSelectedItem();
        SectionFetchRequest sectionFetchRequest = new SectionFetchRequest(selectedQuiz.getExamID());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.teachersocket;

                    ObjectOutputStream oos = Main.teacherOutputStream;
                    oos.writeObject(sectionFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.teacherInputStream;
                    serversections = (List<Sections>) ois.readObject();



                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            section.getItems().addAll(serversections);
                        }
                    });





                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
    }

//    adds the question to temp list of questins
    public void onaddtolistclicked(ActionEvent actionEvent) {
        Quiz quiz1 = (Quiz)quiz.getSelectionModel().getSelectedItem();
        quizid = quiz1.getExamID();

        status.setText("");
        Question question = new Question();
        if(questionname.getText().isEmpty()||option1.getText().isEmpty()||option2.getText().isEmpty()||solution.getText().isEmpty()) {
            status.setText("Enter all fields");
            return;
        }
        String currentQuestionType = (String) questiontype.getSelectionModel().getSelectedItem();
        status.setText("Question added");
        question.setQuestionName(questionname.getText());
        String sol = (String)solution.getText();
        question.setCorrectoption(Integer.parseInt(sol));
        question.setOption1(option1.getText());
        question.setOption2(option2.getText());
        if(!currentQuestionType.equals("True/False")){
            if(option3.getText().isEmpty()||option4.getText().isEmpty()){
                status.setText("Enter all fields");
                return;}
            question.setOption3(option3.getText());
            question.setOption4(option4.getText());

        }
        question.setQuestionType((String)questiontype.getSelectionModel().getSelectedItem());
        Quiz currentQuiz = (Quiz)quiz.getSelectionModel().getSelectedItem();
        Sections currentSection = (Sections)section.getSelectionModel().getSelectedItem();
        question.setExamID(currentQuiz.getExamID());
        question.setSectionID(currentSection.getSectionID());
        question.setQuestionID(UidGenerator.generateuid());
        questionlistclient.add(question);



        questionname.clear();
        option1.clear();
        option2.clear();
        if(!currentQuestionType.equals("True/False"))
        {
            option3.clear();

            option4.clear();
        }
        solution.clear();



    }

//    changes the scene to list of questions added by the teacher.
    public void onlistclicked(ActionEvent actionEvent) {
        questionList = questionlistclient;
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null ;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/questionlist.fxml"));
        }catch(IOException e){

            try{
                root = FXMLLoader.load(getClass().getResource("/gui/questionlist.fxml"));
            }catch (Exception d){}
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onquestionclicked(MouseEvent mouseEvent) {
        status.setText("");
    }

    public void onquestiontypeclicked(ActionEvent actionEvent) {
        String currentQuestionType = (String) questiontype.getSelectionModel().getSelectedItem();
        if(currentQuestionType.equals("True/False")){
            option3.setVisible(false);
            option4.setVisible(false);
        }else{
            option3.setVisible(true);
            option4.setVisible(true);
        }
    }
}
