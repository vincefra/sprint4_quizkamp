/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author xingao
 */
public class Dao implements Serializable{
//   Category c1= new Category("Jorden runt");
//    Category c2= new Category("Mat");
//    Category c3= new Category("Tecknat");
    
//    List<Category> categories= new LinkedList<>();
   ShowCategoriesAction sa= new ShowCategoriesAction();


//    Category[] categoriesArray= new Category[categories.size()];
//    Category[] categoriesArray=categories.toArray(new Category[categories.size()]);
//    Category[] categoriesArray={c1,c2,c3};
//    Category[] categoriesArray = categories.parallelStream().toArray(Category[]::new);
    
    public Dao(){
        sa.categories = new ArrayList<>();
        sa.categories.add("Djur");
        sa.categories.add("Mat");
        sa.categories.add("Tech");
 
    }
    
    public ShowCategoriesAction getCategories(){
        return sa;
    }
    
    
    
    
}
