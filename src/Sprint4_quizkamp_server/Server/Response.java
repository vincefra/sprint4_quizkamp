/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import java.io.Serializable;

/**
 *
 * @author xingao
 */
public class Response implements Serializable {
    private boolean success;
    private Category cate;
    private Question questions;
    
    public Response(){}
    
    public Response(boolean success){
       this.success = success; 
    }
    
    public Response(boolean success, Category cate){
       this.success = success; 
       this.cate = cate;
    }
    
    public Response(boolean success, Question ques){
        this.success= success;
        this.questions= ques;
    }
    
    public void setCategory(Category cate){
        this.cate = cate;
    }

    public Category getCategory(){
        return cate;
    }
    
    public void setQuestion(Question ques){
        this.questions= ques;
    }
    
    public Question getQuestion(){
        return questions;
    }
    
    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean getSuccess(){
        return success;
    }
}
