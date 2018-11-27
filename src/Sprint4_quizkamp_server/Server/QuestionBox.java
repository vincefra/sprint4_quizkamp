package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Sprint4_quizkamp_server;

import java.io.UncheckedIOException;
import java.util.ArrayList;

// Innehåller en uppsättning av alla frågor.
public class QuestionBox {
    
    private ArrayList<Category> categories;
    
    public ArrayList<Category> getCategories() {
        return categories;
    }
    
    public QuestionBox() {
        categories = QuestionsHandler.getCategoriesCopy();
    }
    
    // Denna kan användas om man vill skicka in kategorin som en string istället för en int.
    public Question[] getQuestions(String category, int numberOfQuestions, boolean remove) {
        int index = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).name.equalsIgnoreCase(category)) {
                index = i;
                break;
            }
        }
        
        return getQuestions(index, numberOfQuestions, remove);
    }
    
    public Question[] getQuestions(int categoryNum, int numberOfQuestions,
                                          boolean remove) {
        Category category = categories.get(categoryNum);
        Question[] questionsToReturn = new Question[numberOfQuestions];
        
        // Om vi frågar efter fler frågor än det finns.
        if (numberOfQuestions > category.questions.size()) {
            throw new UncheckedIOException("Finns inte tillräckligt med frågor!", null);
        }
        
        // Hämta ut frågorna.
        for (int i = 0; i < questionsToReturn.length; i++) {
            int index = Sprint4_quizkamp_server.GetRandomNum(0, category.questions.size());
            Question question = category.questions.get(index);
            
            // Lägg till i arrayen.
            questionsToReturn[i] = question;
            
            // Ta ev. bort frågan.
            if (remove) {
                category.questions.remove(index);
            }
        }
        
        return questionsToReturn;
    }
}
