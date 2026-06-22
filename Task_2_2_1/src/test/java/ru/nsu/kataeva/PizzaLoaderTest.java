package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Pizza loader tests.
 */
class PizzaLoaderTest {

    @Test
    void testLoad() {
        String json = """
                {
                  "warehouseCapacity": 20,
                  "bakers": [
                    {"id": 1, "speed": 500},
                    {"id": 2, "speed": 600}
                  ],
                  "couriers": [
                    {"id": 1, "backpackCapacity": 3}
                  ]
                }""";

        PizzaLoader loader = PizzaLoader.load(new StringReader(json));

        assertEquals(20, loader.warehouseCapacity);

        assertNotNull(loader.bakers);
        assertEquals(2, loader.bakers.size());
        assertEquals(1, loader.bakers.get(0).id);
        assertEquals(500, loader.bakers.get(0).speed);

        assertNotNull(loader.couriers);
        assertEquals(1, loader.couriers.size());
        assertEquals(1, loader.couriers.get(0).id);
        assertEquals(3, loader.couriers.get(0).backpackCapacity);
    }


    @Test
    void testLoadInvalid() {
        String invalidJson = "{ \"warehouseCapacity\": 20, \"bakers\": [ ";
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> PizzaLoader.load(new StringReader(invalidJson))
        );
        assertTrue(thrown.getMessage().contains("Problem with config"));
    }
}