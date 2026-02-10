package ru.nsu.kataeva;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Pizza loader class.
 */
public class PizzaLoader {
    public int bakersCount = 3;
    public int couriersCount = 1;
    public int warehouseCapacity = 5;
    public int pizzaDuration = 1000;

    /**
     * To construct pizzeria.
     *
     * @param filename to read config.
     * @return the pizzeria.
     */
    public static PizzaLoader load(String filename) {
        PizzaLoader loader = new PizzaLoader();

        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("[^a-zA-Z0-9+]");
            while (scanner.hasNext()) {
                String line = scanner.next();

                if (scanner.hasNextInt()){
                    int value = scanner.nextInt();

                    switch (line) {
                        case "bakersCount": loader.bakersCount = value; break;
                        case "couriersCount": loader.couriersCount = value; break;
                        case "warehouseCapacity": loader.warehouseCapacity = value; break;
                        case "pizzaDuration": loader.pizzaDuration = value; break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loader;
    }
}
