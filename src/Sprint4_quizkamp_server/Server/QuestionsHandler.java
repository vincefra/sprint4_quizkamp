package Sprint4_quizkamp_server.Server;



import Sprint4_quizkamp_server.Sprint4_quizkamp_server;

import java.io.File;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class QuestionsHandler {

    // TODO: ändra till relativ sökväg.
    private static final String questionPath = "src/Sprint4_quizkamp_server/Server/Frågor";
    private static ArrayList<Category> categories;
    
    public static ArrayList<Category> getCategories() {
        return categories;
    }
    
    // Läser in alla frågor och kategorier från hårddisken.
    public static void init() {
        categories = new ArrayList<>();
        
        // Hämta alla filer/kategorier.
        File folder = new File(questionPath);
        File[] files = folder.listFiles();
        
        for (File file : files) {
            // Skapar kategorin.
            String categoryName = removeFileExtension(file);
            Category category = new Category(categoryName);
            categories.add(category);
            
            // Läser in frågorna från filen.
            category.questions = getQuestionsFromFile(file);
        }
    }
    
    public static int GetCategoryNum(String category)
    {
        for (int x = 0; x < getCategories().size() - 1; x++)
            if (getCategories().get(x).name.equalsIgnoreCase(category))
                return x;
        return 0;
    }
    
    public static Question[] GetQuestions(int categoryNum, int numberOfQuestions,
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
    
    private static ArrayList<Question> getQuestionsFromFile(File file) {
            ArrayList<Question> questions = new ArrayList<>();
            List<String> lines = null;
            try {
                Path a = file.toPath();
                lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            for (String line : lines) {
                // Omvandla raden till ett fråge-objekt.
                Question q = getQuestionFromLine(line);
                questions.add(q);
            }
            
            
            return questions;
    }
    
    private static Question getQuestionFromLine(String line) {
        String splitChar = ";";
        String[] split = line.split(splitChar);
        String question = split[0];
        String[] answers = new String[4];
        
        answers[0] = split[1];
        answers[1] = split[2];
        answers[2] = split[3];
        answers[3] = split[4];
        
        return new Question(question, answers);
    }
    
    // Gör om t.ex. "Djur och natur.txt" till "Djur och natur".
    private static String removeFileExtension (File file) {
        String name = file.getName();
        return name.substring(0, name.lastIndexOf('.'));
    }

}
