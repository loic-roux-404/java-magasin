package Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EntityManager {

    private File file;
    private ArrayList<Entity> entityArrayList;

    public EntityManager(Class entityClass) {
        this.file = this.createFile(entityClass.getSimpleName());
        entityArrayList = new ArrayList<>();
    }

    // adds user to our collection
    public void add(Entity item) {
        entityArrayList.add(item);
    }

    // saves user to database file
    public void save() {
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
    public Object[] loadEntities() {
        Object[] objects;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File createFile(String fileName) {

        if (Files.exists(Paths.get(fileName))) {
            return file;
        }

        File myFile = new File("db/" + fileName + ".txt");
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return myFile;
    }
}
