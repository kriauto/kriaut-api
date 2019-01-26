package ma.kriauto.api.exception;

public class CustomErrorType {
	 
    private String Message;
 
    public CustomErrorType(String Message){
        this.Message = Message;
    }
 
    public String getMessage() {
        return Message;
    }
 
}
