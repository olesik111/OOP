package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Game model building.
 */
public class GameModel {
    private final int width;
    private final int height;
    private final int target;
    private final int foodCount;

    private Snake snake;
    private final List<Point> food = new ArrayList<>();

    private Direction direction = Direction.UP;
    private boolean gameOver = false;
    private boolean won = false;
    private final Random random = new Random();
    private final IntegerProperty score = new SimpleIntegerProperty(1);

    /**
     * Constructor of game model.
     *
     * @param width     width.
     * @param height    height.
     * @param target    length to win.
     * @param foodCount apples at the screen in a moment.
     */
    public GameModel(int width, int height, int target, int foodCount) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive.");
        }
        int totalCells = width * height;
        if (target <= 0 || target > totalCells) {
            throw new IllegalArgumentException("Target must be positive and <= width * height.");
        }
        if (foodCount < 0 || foodCount > (totalCells - target + 1)) {
            throw new IllegalArgumentException("Not enough free cells to spawn foodCount.");
        }
        this.width = width;
        this.height = height;
        this.target = target;
        this.foodCount = foodCount;
        reset();
    }

    /**
     * Restart the game.
     */
    public void reset() {
        food.clear();
        direction = Direction.UP;
        gameOver = false;
        won = false;

        snake = new Snake(new Point(width / 2, height / 2));
        score.set(1);
        spawnFood();
    }

    /**
     * Spawn the needed value of apples.
     */
    private void spawnFood() {
        List<Point> freeCells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point p = new Point(x, y);
                if (!snake.contains(p) && !food.contains(p)) {
                    freeCells.add(p);
                }
            }
        }

        while (food.size() < foodCount && !freeCells.isEmpty()) {
            int index = random.nextInt(freeCells.size());
            food.add(freeCells.remove(index));
        }
    }

    /**
     * Your direction.
     *
     * @param directionNew to check if legal and to return.
     */
    public boolean directionCheck(Direction directionNew) {
        if (direction == Direction.UP && directionNew == Direction.DOWN) {
            return false;
        }
        if (direction == Direction.DOWN && directionNew == Direction.UP) {
            return false;
        }
        if (direction == Direction.LEFT && directionNew == Direction.RIGHT) {
            return false;
        }
        if (direction == Direction.RIGHT && directionNew == Direction.LEFT) {
            return false;
        }
        return true;
    }

    /**
     * Set new direction if legal.
     *
     * @param directionNew new direction.
     */
    public void setDirection(Direction directionNew) {
        if (directionCheck(directionNew)) {
            this.direction = directionNew;
        }
    }

    /**
     * To check the newest state of screen.
     */
    public void update() {
        if (gameOver || won) {
            return;
        }

        Point head = snake.getHead();
        Point newHead = null;

        switch (direction) {
            case UP: {
                newHead = new Point(head.xcoord, head.ycoord - 1);
                break;
            }
            case DOWN: {
                newHead = new Point(head.xcoord, head.ycoord + 1);
                break;
            }
            case LEFT: {
                newHead = new Point(head.xcoord - 1, head.ycoord);
                break;
            }
            case RIGHT: {
                newHead = new Point(head.xcoord + 1, head.ycoord);
                break;
            }
            default: {
            }
        }

        if (newHead.xcoord < 0 || newHead.xcoord >= width
                || newHead.ycoord < 0 || newHead.ycoord >= height) {
            gameOver = true;
            return;
        }

        if (snake.collision(newHead)) {
            gameOver = true;
            return;
        }

        if (food.contains(newHead)) {
            food.remove(newHead);
            snake.grow(newHead);
            score.set(snake.getBody().size());
            spawnFood();
        } else {
            snake.move(newHead);
        }

        if (snake.getBody().size() == target) {
            won = true;
        }
    }

    /**
     * Get for binding.
     *
     * @return score.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Get snake body.
     *
     * @return body.
     */
    public List<Point> getSnake() {
        return snake.getBody();
    }

    /**
     * Get food coordinates.
     *
     * @return food.
     */
    public List<Point> getFood() {
        return food;
    }

    /**
     * If game over.
     *
     * @return game over.
     */
    public boolean gameOver() {
        return gameOver;
    }

    /**
     * If you won.
     *
     * @return won.
     */
    public boolean won() {
        return won;
    }

}
