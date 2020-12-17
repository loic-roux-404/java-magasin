package Services.Entity;

import Services.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EntityManager implements Service {
    private boolean loaded;

    private File file;
    private ArrayList<Entity> entityArrayList;

    protected Entity managedEn;

    public EntityManager(Class entityClass) {
        this.file = this.createFile(entityClass.getSimpleName());
        entityArrayList = new ArrayList<>();
        this.loadEntityInstance(entityClass);
        this.load();
    }

    void loadEntityInstance(Class entityClass) {
        try {
            managedEn = (Entity) entityClass.getDeclaredConstructor().newInstance();
            // managedEn.ge
        } catch (Exception e) {
            System.err.println("This entity definition has errors " + entityClass.getName());
            System.err.println("Verify methods and empty constructor usage");
            e.printStackTrace();
        }

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
            while(i < entityArrayList.size()) {
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

    // reads entity from database file
    public ArrayList<Entity> loadEntities() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            // each lines to array
            Object[] objects = bufferedReader.lines().toArray();
            bufferedReader.close();

            for (Object o: objects) {
                String row = o.toString().trim();
                String[] rows = row.split(",");
                entityArrayList.add(managedEn.factory(rows));
            }

            return entityArrayList;
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


    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
