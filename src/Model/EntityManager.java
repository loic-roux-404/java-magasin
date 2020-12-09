package Model;

import java.io.*;
import java.util.ArrayList;

public class EntityManager {

    private ArrayList<Entity> entityArrayList;

    public EntityManager() {
        entityArrayList = new ArrayList<>();
    }

    // adds user to our collection
    public void add(Entity item) {
        entityArrayList.add(item);
    }

    // saves user to database file
    public void save(File file) {
        try {
            // user model
            Entity item;
            String save_data = "";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            while( i < entityArrayList.size()) {
                item = entityArrayList.get(i);
                save_data = item.toString();
                i++;
            }
            bufferedWriter.write(save_data);
            bufferedWriter.newLine();
            // prevents memory leak
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads user from database file
    public Object[] loadEntities(File file) {
        Object[] objects;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
