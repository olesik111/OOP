package ru.nsu.kataeva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Game model building.
 */
public class GameModel {
    private final int width;
    private final int height;
    private final int target;
    private final int foodCount;

    private final LinkedList<Point> snake = new LinkedList<>();
    private final List<Point> food = new ArrayList<>();

    private Direction direction = Direction.DOWN;
    private boolean gameOver = false;
    private boolean won = false;
    private final Random random = new Random();

    /**
     * Constructor of game model.
     *
     * @param width     width.
     * @param height    height.
     * @param target    length to win.
     * @param foodCount apples at the screen in a moment.
     */
    public GameModel(int width, int height, int target, int foodCount) {
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
        snake.clear();
        food.clear();
        direction = Direction.UP;
        gameOver = false;
        won = false;

        snake.add(new Point(width / 2, height / 2));
        spawnFood();
    }

    /**
     * Spawn the needed value of apples.
     */
    private void spawnFood() {
        while (food.size() < foodCount) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Point p = new Point(x, y);

            if (!snake.contains(p) && !food.contains(p)) {
                food.add(p);
            }
        }
    }

    /**
     * Your direction.
     *
     * @param directionNew to check if legal and to return.
     */
    public void directionCheck(Direction directionNew) {
        if (direction == Direction.UP && directionNew == Direction.DOWN) {
            return;
        }
        if (direction == Direction.DOWN && directionNew == Direction.UP) {
            return;
        }
        if (direction == Direction.LEFT && directionNew == Direction.RIGHT) {
            return;
        }
        if (direction == Direction.RIGHT && directionNew == Direction.LEFT) {
            return;
        }
        this.direction = directionNew;
    }

    /**
     * To check the newest state of screen.
     */
    public void update() {
        if (gameOver || won) {
            return;
        }

        Point head = snake.getFirst();
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

        for (int i = 0; i < snake.size() - 1; i++) {
            if (snake.get(i).xcoord == newHead.xcoord && snake.get(i).ycoord == newHead.ycoord) {
                gameOver = true;
                return;
            }
        }
        snake.addFirst(newHead);

        if (food.contains(newHead)) {
            food.remove(newHead);
            spawnFood();
        } else {
            snake.removeLast();
        }

        if (snake.size() == target) {
            won = true;
        }
    }

    /**
     * Get snake body.
     *
     * @return body.
     */
    public List<Point> getSnake() {
        return snake;
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
