package ru.nsu.kataeva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests without graphics.
 */
public class SnakeTest {
    private GameModel model;

    @BeforeEach
    void setUp() {
        model = new GameModel(10, 10, 3, 0);
    }

    @Test
    void testMovementAndFood() {
        List<Point> snakeBody = model.getSnake();
        Point initialHead = snakeBody.get(0);
        model.setDirection(Direction.RIGHT);

        Point foodPos = new Point(initialHead.xcoord + 1, initialHead.ycoord);
        model.getFood().add(foodPos);

        model.update();
        assertEquals(2, snakeBody.size());
        assertEquals(foodPos, snakeBody.get(0));
        assertFalse(model.getFood().contains(foodPos));
    }

    @Test
    void testWallCollision() {
        GameModel smallModel = new GameModel(3, 3, 10, 0);
        smallModel.setDirection(Direction.UP);
        smallModel.update();
        smallModel.update();
        assertTrue(smallModel.gameOver());
    }

    @Test
    void testSelfCollision() {
        List<Point> snakeBody = model.getSnake();
        model = new GameModel(10, 10, 10, 0);
        snakeBody.add(new Point(5, 5));
        snakeBody.add(new Point(6, 5));
        snakeBody.add(new Point(6, 6));
        snakeBody.add(new Point(5, 6));

        model.setDirection(Direction.DOWN);
        model.update();
        assertFalse(model.gameOver());
    }

    @Test
    void testDirectionLock() {
        model.setDirection(Direction.UP);
        model.setDirection(Direction.DOWN);
        model.update();
        assertEquals(new Point(5, 4), model.getSnake().get(0));
    }

    @Test
    void testWinCondition() {
        GameModel winModel = new GameModel(10, 10, 2, 0);
        winModel.getFood().add(new Point(5, 4));
        winModel.setDirection(Direction.UP);
        winModel.update();
        assertTrue(winModel.won());
    }

    @Test
    void testReset() {
        model.setDirection(Direction.LEFT);
        model.update();
        model.reset();
        assertEquals(1, model.getSnake().size());
        assertFalse(model.gameOver());
    }

    @Test
    void testPointEqualsAndHashCode() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(1, 2);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
        assertNotEquals(null, p1);
        assertNotEquals("not", p1);
    }

    @Test
    void testSpawnFoodLogic() {
        int foodCount = 5;
        GameModel model = new GameModel(10, 10, 20, foodCount);
        List<Point> food = model.getFood();

        assertEquals(foodCount, food.size());
        for (Point f : food) {
            assertFalse(model.getSnake().contains(f));
        }
    }
}
