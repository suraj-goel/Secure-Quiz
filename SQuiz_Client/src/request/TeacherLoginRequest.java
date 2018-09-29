package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class TeacherLoginRequest implements Serializable {

    private String email;
    private String password;

    public TeacherLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return String.valueOf(ServerRequest.TEACHER_LOGIN_REQUEST);
    }
}