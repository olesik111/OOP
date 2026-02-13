package ru.nsu.kataeva;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Pizza loader class.
 */
public class PizzaLoader {
    public int warehouseCapacity;
    public List<BakerConfig> bakers;
    public List<CourierConfig> couriers;

    /**
     * Baker from json.
     */
    public static class BakerConfig {
        public int id;
        public int speed;
    }

    /**
     * Courier from json.
     */
    public static class CourierConfig {
        public int id;
        public int backpackCapacity;
    }

    /**
     * To construct pizzeria.
     *
     * @param reader to read config.
     * @return the pizzeria.
     */
    public static PizzaLoader load(Reader reader) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(reader, PizzaLoader.class);
        } catch (IOException e) {
            throw new RuntimeException("Problem with config", e);
        }
    }
}
