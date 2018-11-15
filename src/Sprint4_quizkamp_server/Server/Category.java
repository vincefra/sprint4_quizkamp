package Sprint4_quizkamp_server.Server;

import java.util.ArrayList;

public class Category {
    public ArrayList<Question> questions;
    private String name;
    
    public Category(String name) {
        this.name = name;
    }
    
}
