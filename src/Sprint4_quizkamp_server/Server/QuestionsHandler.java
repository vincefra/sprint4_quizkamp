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
    private static final String questionPath = "C:\\Users\\Asd\\Dropbox\\Nackademin\\Kurs Objektorienterad programmering och Java\\Quizkampen\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\Frågor";
    private static ArrayList<Category> categories;
    
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
    
    public static ArrayList<Category> getCategoriesCopy() {
        ArrayList<Category> copy = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            Category categoryCopy = new Category(category.name);
            ArrayList<Question> questions = (ArrayList<Question>)category.questions.clone();
            categoryCopy.questions = questions;
            
            copy.add(categoryCopy);
        }
        
        return copy;
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
