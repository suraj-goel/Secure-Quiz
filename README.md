# Secure-Quiz
A secure platform for quiz where everything is secure and a teacher can add a quiz and multiple students can give the quiz.
## Basic Features:
1. Create a Teacher/Student Account.
2. Teacher can add new subject and host multiple quizzes under one subject.
3. Teacher can add various sections to a quiz and allot different time limit to each section.
4. Teacher can also add solution to quiz.
5. Multiple students can login at the same time (From their own devices) and give the quiz.
6. All the students should have the same set of questions in one section but in random order.
7. There are individual timers for each section and the students can switch between the different sections throughout the exam.
8. The student is shown with the total points after the completion of the exam, if the teacher has added the solution by 
the time student submitted his solution otherwise show the status as pending for evaluation.
9. The students can ask a query to the teacher to which the teacher may/may not respond.
## Advance Features:
1. The questions can be of different types(Single Choice Correct MCQs, Multiple
Choice Correct MCQs, T/F).
2. The students can give a rating to the quiz and the platform.
3. A profile dashboard for both students and teacher where they can see all the final
ranking (Once quiz has been graded) and other statistics like quiz overall rating.(More
the statistics, more the points)
4. All the network operations like transfer of quiz questions and responses must be
encrypted.
5. In case of a connection breakdown all the answers should be stored in a file/local
cache (Again in encrypted form) and restored automatically when the connection is
re-established. 
## Instructions to Run:
##### 1.import mysql-connector-java-5.1.46.jar from dependency folder in Sacred_Bazaar and Jfoenix.jar from Sacred_Bazaar_Client into the library of IDE.
##### 2.import SQuiz.sql into your mysql workbench.
##### 3.In the Main.java of SQuiz change username and password to that of your Mysql.
##### 4.The IP of the server in the client side can be set in Main.java of SQuiz_Client.
## For more details:
#### Suraj Goel 
surajgoel.1225@gmail.com

![screenshot from 2018-09-29 16-13-17](https://user-images.githubusercontent.com/29855009/46244855-02757c00-c403-11e8-9961-8c9575dd90e3.png)
![screenshot from 2018-09-29 16-13-21](https://user-images.githubusercontent.com/29855009/46244858-0b664d80-c403-11e8-8da3-9363cd97c79d.png)

