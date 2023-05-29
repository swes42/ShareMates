/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author jobe + Selina A.S.
 */
public class ExceptionDTO {
    

  public ExceptionDTO(int code, String description){
      this.code = code;
      this.message = description;
  }
  
  public ExceptionDTO(String message) {
        this.message = message;
  }
  private int code;
    private String message;
  

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
  
  
}