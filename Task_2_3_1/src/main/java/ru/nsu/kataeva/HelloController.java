package ru.nsu.kataeva;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

@Generated
public class HelloController {
    @FXML
    private Canvas gameCanvas;
    @FXML
    private Label statusLabel;

    private GameModel gameModel;
    private Timeline timeline;
    private static final int CELL_SIZE = 20;

    public void initialize() {
        int gridW = (int) gameCanvas.getWidth() / CELL_SIZE;
        int gridH = (int) gameCanvas.getHeight() / CELL_SIZE;
        gameModel = new GameModel(gridW, gridH, 10, 3);

        gameCanvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleInput);
            }
        });

        timeline = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            gameModel.update();
            draw();
            checkGameStatus();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        draw();
    }

    private void handleInput(KeyEvent event) {
        KeyCode code = event.getCode();
        System.out.println("KeyCode: " + code);
        if (code == KeyCode.W || code == KeyCode.UP) gameModel.directionCheck(Direction.UP);
        else if (code == KeyCode.S || code == KeyCode.DOWN) gameModel.directionCheck(Direction.DOWN);
        else if (code == KeyCode.A || code == KeyCode.LEFT) gameModel.directionCheck(Direction.LEFT);
        else if (code == KeyCode.D || code == KeyCode.RIGHT) gameModel.directionCheck(Direction.RIGHT);

        if (code == KeyCode.R && (gameModel.gameOver() || gameModel.won())) {
            gameModel.reset();
            timeline.play();
            statusLabel.setText("Score: 0");
        }
    }

    private void checkGameStatus() {
        statusLabel.setText("Length: " + gameModel.getSnake().size());

        if (gameModel.gameOver()) {
            timeline.stop();
            statusLabel.setText("Game Over! Press 'R'");
        } else if (gameModel.won()) {
            timeline.stop();
            statusLabel.setText("You Won! Press 'R'");
        }
    }

    private void draw() {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gc.setFill(Color.RED);
        for (Point food : gameModel.getFood()) {
            gc.fillOval(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        gc.setFill(Color.LIMEGREEN);
        for (Point part : gameModel.getSnake()) {
            gc.fillRect(part.x * CELL_SIZE, part.y * CELL_SIZE, CELL_SIZE - 2, CELL_SIZE - 2);
        }
    }
}

