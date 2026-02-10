package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Pizza loader tests.
 */
class PizzaLoaderTest {
    @Test
    void testLoad() throws IOException {
        String filename = "test_config.txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("bakersCount 10\n");
            writer.write("couriersCount 5\n");
            writer.write("warehouseCapacity 20\n");
            writer.write("pizzaDuration 500\n");
        }

        PizzaLoader loader = PizzaLoader.load(filename);

        assertEquals(10, loader.bakersCount);
        assertEquals(5, loader.couriersCount);
        assertEquals(20, loader.warehouseCapacity);
        assertEquals(500, loader.pizzaDuration);

        new File(filename).delete();
    }

    @Test
    void testDefaults() throws IOException {
        String filename = "empty.txt";
        new FileWriter(filename).close();

        PizzaLoader loader = PizzaLoader.load(filename);

        assertEquals(3, loader.bakersCount);
        assertEquals(1, loader.couriersCount);

        new File(filename).delete();
    }
}