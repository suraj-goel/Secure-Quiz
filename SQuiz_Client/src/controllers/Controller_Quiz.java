package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import gui.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainClasses.Sections;
import questions.Question;
import request.SectionFetchRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class Controller_Quiz {

    public JFXButton endquiz,submit;
    @FXML
    Label status,status2,sectiontimer;
    List<Sections> sectionsClient;
    volatile List<Sections> serverList,completelist;
    @FXML
    JFXListView sections,questions;
    @FXML
    JFXTextArea question,option1,option2,option3,option4;
    @FXML
    JFXTextField answer;

    volatile Sections currentSection;


//  retrieve a list of sections containing list of sections from the questions decrypted from the file.
    private List<Sections> sectionsList(List<Question> questionList){
        List<Sections> sections=new ArrayList<>();
        for (Question question : questionList){
            Sections section=null;
            int i=0;
            for (Sections section1:sections){
                if(section1.getSectionID().equals(question.getSectionID())){
                    section=sections.get(i);
                    break;
                }
                i++;
            }
            System.out.println(section);
            if (section==null){
                section=new Sections();
            }
            section.setSectionID(question.getSectionID());
            Question question1=new Question();
            question1.setExamID(question.getExamID());
            question1.setQuestionID(question.getQuestionID());
            question1.setSectionID(question.getSectionID());
            question1.setQuestionName(question.getQuestionName());
            question1.setQuestionType(question.getQuestionType());
            question1.setCorrectoption(question.getCorrectoption());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            section.addQuestion(question1);
            int index=sections.indexOf(section);
            if (index==-1)
                sections.add(section);
            else sections.add(index,section);
        }
        Set<Sections> tempSections=new HashSet<Sections>(sections);
        List<Sections> tempTempsSections = new ArrayList<>(tempSections);
        return tempTempsSections;
    }

    private List<Sections> sectionsListToList(List<Sections> sections1,List<Sections> sections2){
        int i=0;
        for (Sections sections:sections1){
            Sections tempSection=null;
            Sections temp=sections1.get(i);i++;
            int j=0;
            for (Sections sections3:sections2){
                if (sections3.getSectionID().equals(sections.getSectionID())){
                    temp.setSectionName(sections3.getSectionName());
                    temp.setSectionTime(sections3.getSectionTime());
                    j++;
                    break;
                }
            }

        }


        return sections1;
    }

//    initialises the scene..
    public void initialize(){
        Collections.shuffle(Controller_studentportal.decryptedunsortedQuestions);
        sectionsClient = sectionsList(Controller_studentportal.decryptedunsortedQuestions);
        SectionFetchRequest sectionFetchRequest = new SectionFetchRequest(Controller_studentportal.selectedQuiz.getExamID());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    Socket socket = Main.studentSocket;

                    ObjectOutputStream oos = Main.studentoutputStream;
                    System.out.println(oos);
                    oos.writeObject(sectionFetchRequest);
                    oos.flush();
                    ObjectInputStream ois = Main.studentInputStream;
                    serverList = (List<Sections>) ois.readObject();
                   // for (Sections sections:serverList){
                   //     System.out.println(sections.getSectionName());
                   // }
                    completelist = sectionsListToList(sectionsClient,serverList);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            sections.getItems().addAll(completelist);
                            sections.getSelectionModel().select(0);
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        currentSection = completelist.get(0);
        Integer ti = currentSection.getSectionTime();
        final int  t[] ={ti};
//        using array,since primitive var can't be accesses from inner class.

        Timeline time = new Timeline();
        time.setCycleCount(t[0]);
        if(time!=null){
            time.stop();

        }

//        Timer starts when the scene starts...
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                t[0]=currentSection.getSectionTime();

                t[0]--;
                sectiontimer.setText("Time Left: "+t[0]);
                long tomi=t[0];
                currentSection.setSectionTime(t[0]);
                if(t[0]<=0||Thread.currentThread().isInterrupted()){
                    question.setText("Time Over For This section");
                    submit.setVisible(false);
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(keyFrame);
        time.playFromStart();


    }

//    displays list of questions corresponding to a question..
    public void handleclickedsection(MouseEvent mouseEvent) {
        currentSection = (Sections)sections.getSelectionModel().getSelectedItem();

        questions.getItems().clear();

        questions.getItems().addAll(currentSection.getQuestions());
        sectiontimer.setText(String.valueOf(currentSection.getSectionTime()));
        question.clear();
        option1.clear();
        option3.clear();
        option2.clear();
        option4.clear();

        if(currentSection.getSectionTime()<=0){
            question.setText("Time Over For This Section");
            questions.getItems().clear();
            submit.setVisible(false);
        }



    }


//    displays the question for a particular section..
    public void handleclickedquestion(MouseEvent mouseEvent) {
        Question q = (Question)questions.getSelectionModel().getSelectedItem();

        question.setText(q.getQuestionName());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        if(q.getQuestionType().equals("True/False")) {
            option3.setVisible(false);
            option4.setVisible(false);
        }else{
            option3.setText(q.getOption3());
            option4.setText(q.getOption4());
        }
        if(q.isAnswered()){
            status.setText("Question already Answered");
        }else{
            status.setText("");
        }




    }

//    submits a question
    public void onsubmitclicked(ActionEvent actionEvent) {
        if(answer.getText().isEmpty()){
            status.setText("Enter Option 1/2/3/4");
            return;
        }
        Question q = (Question)questions.getSelectionModel().getSelectedItem();
        Main.submittedAnswers.put(q.getQuestionID(),Integer.parseInt(answer.getText()));
        System.out.println(q.getQuestionID());
        q.setAnswered(true);
        question.clear();
        option1.clear();
        option3.clear();
        option2.clear();
        option4.clear();



    }

//    displays the quizover scene..
    public void onendquizclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) status.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("/gui/quizover.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}
