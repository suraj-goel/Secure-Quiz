package mainClasses;

import enumConstant.ServerRequest;
import request.*;

import java.io.*;
import java.net.Socket;

public class HandleClientRequest implements Runnable {
    private Socket socket;
    private ObjectInputStream ois;//Input Stream of client socket
    private ObjectOutputStream oos;//Output Stream of client socket

    public HandleClientRequest(Socket socket){
        this.socket=socket;
        try {
            ois=new ObjectInputStream(socket.getInputStream());
            oos=new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(socket.getInetAddress().getHostAddress());
        while (true) {

            Object obj = null;
            try {
                try{
                    obj = ois.readObject();
                }catch (EOFException e){
                    System.out.println("Client disconnected");
                    break;
                }
                String req = (String) obj.toString();

                if (req.equals(String.valueOf(ServerRequest.SIGNUP_REQUEST))) {
                    SignUpRequest signUpRequest = (SignUpRequest) obj;
                    oos.writeObject(new SignUp().signup(signUpRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.STUDENT_LOGIN_REQUEST))) {
                    StudentLoginRequest studentLoginRequest = (StudentLoginRequest) obj;
                    oos.writeObject(new Login().studentLogin(studentLoginRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.TEACHER_LOGIN_REQUEST))) {
                    TeacherLoginRequest teacherLoginRequest = (TeacherLoginRequest) obj;
                    oos.writeObject(new Login().teacherLogin(teacherLoginRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SUBJECT_ADD_REQUEST))) {
                    SubjectAddRequest subjectAddRequest = (SubjectAddRequest) obj;
                    oos.writeObject(new SubjectAdd().add(subjectAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SUBJECT_LIST_FETCH_REQUEST))){
                    SubjectListFetchRequest subjectListFetchRequest=(SubjectListFetchRequest) obj;
                    oos.writeObject(new SubjectListFetch().fetch(subjectListFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.EXAM_ADD_REQUEST))){
                    QuizNameAddRequest quizNameAddRequest =(QuizNameAddRequest) obj;
                    oos.writeObject(new QuizNameAdd().add(quizNameAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.EXAM_LIST_FETCH_REQUEST))){
                    QuizListFetchRequest quizListFetchRequest =(QuizListFetchRequest)obj;
                    oos.writeObject(new QuizListFetch().fetchBySubject(quizListFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SECTION_ADD_REQUEST))){
                    SectionsAddRequest sectionsAddRequest=(SectionsAddRequest) obj;
                    oos.writeObject(new SectionAdd().add(sectionsAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SECTION_FETCH_REQUEST))){
                    SectionFetchRequest sectionFetchRequest=(SectionFetchRequest) obj;
                    oos.writeObject(new SectionFetch().sectionsListFetch(sectionFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.QUIZ_ADD_REQUEST))){
                    QuizAddRequest quizAddRequest=(QuizAddRequest)obj;
                    System.out.println("hi");
                    oos.writeObject(new QuizAdd().add(quizAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.QUESTION_FETCH_REQUEST))){
                    QuestionFetchRequest questionFetchRequest=(QuestionFetchRequest) obj;
                    oos.writeObject(new QuestionFetch().fetch(questionFetchRequest,socket.getInetAddress().getHostAddress()));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.QUESTION_LIST_FETCH_STUDENT_REQUEST))){
                    QuizListFetchStudentRequest quizListFetchStudentRequest=(QuizListFetchStudentRequest)obj;
                    oos.writeObject(new QuizListFetch().fetchByStudent(quizListFetchStudentRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.RATING_ADD_REQUEST))){
                    RatingAddRequest ratingAddRequest=(RatingAddRequest)obj;
                    oos.writeObject(new RatingAddFetch().add(ratingAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.RATING_FETCH_REQUEST))){
                    RatingFetchRequest ratingFetchRequest=(RatingFetchRequest)obj;
                    oos.writeObject(new RatingAddFetch().fetch(ratingFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SCORE_FETCH_REQUEST))){
                    ScoreFetchRequest scoreFetchRequest= (ScoreFetchRequest) obj;
                    oos.writeObject(new ScoreAddFetch().fetch(scoreFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.SCORE_ADD_REQUEST))){
                    ScoreAddRequest scoreAddRequest = (ScoreAddRequest) obj;
                    oos.writeObject(new ScoreAddFetch().add(scoreAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.RANK_FETCH_REQUEST))){
                    RankFetchRequest rankFetchRequest=(RankFetchRequest)obj;
                    oos.writeObject(new ScoreAddFetch().fetchByRank(rankFetchRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.QUIZ_BY_STUDENT_REQUEST))){
                    QuizByStudentRequest quizByStudentRequest=(QuizByStudentRequest)obj;
                    oos.writeObject(new QuizByStudent().fetch(quizByStudentRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.QUIZ_LIST_FETCH_TEACHER_REQUEST))){
                    QuizListFetchTeacherRequest quizListFetchTeacherRequest=(QuizListFetchTeacherRequest) obj;
                    oos.writeObject(new QuizScoreListFetch().fetch(quizListFetchTeacherRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.COMMENT_ADD_REQUEST))){
                    CommentAddRequest commentAddRequest=(CommentAddRequest)obj;
                    oos.writeObject(new CommentAddFetch().add(commentAddRequest));
                    oos.flush();
                }
                if (req.equals(String.valueOf(ServerRequest.COMMENT_FETCH_REQUEST))){
                    CommentFetchRequest commentFetchRequest=(CommentFetchRequest)obj;
                    oos.writeObject(new CommentAddFetch().fetch(commentFetchRequest));
                    oos.flush();
                }
            } catch (StreamCorruptedException e){
                try {
                    ois=new ObjectInputStream(socket.getInputStream());
                    oos=new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
