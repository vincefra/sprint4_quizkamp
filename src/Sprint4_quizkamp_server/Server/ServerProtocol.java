/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

/**
 *
 * @author xingao
 */
public class ServerProtocol {

   private static final int S1JOINED = 1;
   private static final int PLAYERSJOINED = 2; 
   private static final int SENTCATALOG = 3;
   private static final int S1SELECTEDCATALOG = 4;
   private static final int SENTQUESTION = 5;
   private static final int SENTRESULT = 6;
   private static final int RONTFINISH = 7;

   
   private int state=S1JOINED;
//   int categorySize = QuestionsHandler.getCategories().size();
   
   
   public Object processInput(String theInput) {
       
        Object theOutput = null;
        int rightNum=0; 
        int wrongNum=0;
            
//        if (state == PLAYERSJOINED) {
//            theOutput = "Choose a category:" ;
//            for(int i=0;i<categorySize;i++){
//                theOutput= i + "\t"+QuestionsHandler.getCategories().get(i).toString();
//            }
//            state =SENTCATALOG ;
            
         if (state == SENTCATALOG) {
            Question[] questions=QuestionsHandler.getQuestions(Integer.parseInt(theInput), 2, false);
            for(int i=0;i<questions.length;i++){
                theOutput = questions[i];
                
                if(theInput.equals(questions[i].getCorrectAnswer())){
                    theOutput="Right";
                    rightNum++;
                }
                else
                    theOutput="Wrong";
                    wrongNum++;
            }
                state = RONTFINISH;
                
        } else if (state == RONTFINISH) {
            theOutput="Right: "+rightNum+"\n"+
                      "Wrong: "+wrongNum;
            state =PLAYERSJOINED;
        }
        return theOutput;
    }
   
}
