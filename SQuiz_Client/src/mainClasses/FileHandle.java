package mainClasses;

import questions.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandle {
    public static void fileWrite(List<Question> qList , String quizid){
       String fileName = quizid;
        try(FileWriter fw = new FileWriter(fileName,true)){
            String question = "";
            for(Question q : qList) {
//          using (char)3 to identify different parts of question

                question += (char) 3 + q.getSectionID();
                question += (char) 3 + q.getQuestionID();
                question += (char) 3 + q.getQuestionType();
                question += (char) 3 + q.getQuestionName();
                question += (char) 3 + q.getOption1();
                question += (char) 3 + q.getOption2();
                if (q.getQuestionType().equals("True/False")) {
                    question += (char) 3 + "True";
                    question += (char) 3 + "True";
                } else {
                    question += (char) 3 + q.getOption3();
                    question += (char) 3 + q.getOption4();
                }

                question +=( (char)3) + "" +q.getCorrectoption();
                System.out.println(( (char)3) + "" +q.getCorrectoption());
            }
                try {

                    String encryptedQuestion = EncryptDecrypt.encrypt(question);

                    fw.write(encryptedQuestion);
                }catch (Exception e){
                    e.printStackTrace();
                }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Question> fileRead(String filename){
        List<Question> questionsList = new ArrayList<>();
        String delim=(char)3 +"";
        String encryptedText = null;
//        getting the encrypted text
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line!=null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line=br.readLine();
            }
            encryptedText = sb.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        String decryptedText = "";
        try{
            decryptedText = EncryptDecrypt.decrypt(encryptedText);
        }catch (Exception e){
            e.printStackTrace();
        }

//        storing decrypted text in the form of question object
        try(Scanner read = new Scanner(decryptedText)){
            read.useDelimiter(delim);
            while(read.hasNext()){
                try {
                    String sectionId =read.next();
                    String questionId = (read.next());
                    String questionType = (read.next());
                    String questionName = (read.next());
                    String option1 = (read.next());
                    String option2 = (read.next());
                    String option3 = (read.next());
                    String option4 = (read.next());
                    String correctOption = (read.next());
                    Question q = new Question();
                    q.setQuestionType(questionType);
                    q.setQuestionID(questionId);
                    q.setQuestionName(questionName);
                    q.setSectionID(sectionId);
                    q.setOption1(option1);
                    q.setOption2(option2);
                    q.setOption3(option3);
                    q.setOption4(option4);
                    q.setCorrectoption(Integer.parseInt(correctOption));
//                    adding the question object ti question list..
                    questionsList.add(q);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionsList;
    }
}
