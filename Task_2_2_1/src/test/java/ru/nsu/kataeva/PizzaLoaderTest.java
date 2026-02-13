package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Pizza loader tests.
 */
class PizzaLoaderTest {

    @Test
    void testLoad() {
        String json = "{\n" +
                "  \"warehouseCapacity\": 20,\n" +
                "  \"bakers\": [\n" +
                "    {\"id\": 1, \"speed\": 500},\n" +
                "    {\"id\": 2, \"speed\": 600}\n" +
                "  ],\n" +
                "  \"couriers\": [\n" +
                "    {\"id\": 1, \"backpackCapacity\": 3}\n" +
                "  ]\n" +
                "}";

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
}