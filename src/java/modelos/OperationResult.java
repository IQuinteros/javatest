/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Yunnicio
 */
public class OperationResult<T> {
    private T result;
    private boolean success;
    private String message;
    private String detailMessage;

    private OperationResult(T result, boolean success, String message, String detailMessage) {
        this.result = result;
        this.success = success;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public T getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
    
    public boolean hasMessage(){
        return message != null && !message.isEmpty();
    }
    
    public boolean hasDetailMessage(){
        return detailMessage != null && !detailMessage.isEmpty();
    }
    
    public static <T> OperationResult success(T operationResult){
        return new OperationResult(operationResult, true, "", "");
    }
    
    public static <T> OperationResult failure(T operationResult, String message){
        return new OperationResult(operationResult, false, message, "");
    }
    
    public static <T> OperationResult failure(T operationResult, String message, String detailMessage){
        return new OperationResult(operationResult, false, message, detailMessage);
    }
}
