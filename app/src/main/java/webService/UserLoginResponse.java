package webService;

/**
 * @author Matthias Heinen
 */
public class UserLoginResponse {

    private String text;
    private int returnCode;
    private String message;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setText(String text)
    {
        this.text=text;

    }

    public String  getText() {
        return text;
    }

}
