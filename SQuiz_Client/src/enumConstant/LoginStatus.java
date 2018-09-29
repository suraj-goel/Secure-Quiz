package enumConstant;

public enum LoginStatus {

    SUCCESS("0"),
    WRONG_CREDENTIALS("1"),
    NO_SUCH_USER("2"),
    SERVER_SIDE_ERROR("3");

    LoginStatus(String s){
        s.toString();
    }
}