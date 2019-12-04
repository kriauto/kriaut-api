package ma.kriauto.api.exception;

public class CustomErrorType {
	 
    private String Message;
    private String label;
    private String code;

    public CustomErrorType(String Message){
        this.Message = Message;
    }

    public CustomErrorType(String Message, String label){
        this.Message = Message;
        this.label = label;
    }
 
    public CustomErrorType(String Message, String label, String code){
        this.Message = Message;
        this.label = label;
        this.code = code;
    }
 
    public String getMessage() {
        return Message;
    }
    public String getLabel() {
        return label;
    }
    public String getCode() {
        return code;
    }
}
