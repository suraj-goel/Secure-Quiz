package request;

import enumConstant.ServerRequest;
import mainClasses.Student;
import mainClasses.Teacher;

import java.io.Serializable;

public class SignUpRequest implements Serializable {

    private Teacher teacher;
    private Student student;
    private String password;

    public SignUpRequest(Student student,String password) {
        this.student = student;
        this.password=password;
    }

    public SignUpRequest(Teacher teacher,String password) {
        this.teacher = teacher;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString(){
        return String.valueOf(ServerRequest.SIGNUP_REQUEST);
    }
}