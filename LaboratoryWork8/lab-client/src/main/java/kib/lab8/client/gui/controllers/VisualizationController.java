package kib.lab8.client.gui.controllers;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.client.utils.ChildUIType;
import kib.lab8.common.entities.HumanBeing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class VisualizationController extends DataVisualizerController {

    @FXML
    private AnchorPane visualizationPane;
    private HumanBeing chosenHuman;
    private final Map<HumanBeing, Canvas> people = new HashMap<>();
    private static final Map<String, Color> colors = new HashMap<>();

    private Canvas generateCanvas(HumanBeing human) {
        checkAuthorColor(human);
        Canvas humanCanvas = new Canvas(50, 50);
        humanCanvas.setOnMouseEntered(event -> {
            humanCanvas.setScaleX(1.13);
            humanCanvas.setScaleY(1.13);
        });
        humanCanvas.setOnMouseExited(event -> {
            humanCanvas.setScaleX(1);
            humanCanvas.setScaleY(1);
        });
        humanCanvas.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                chosenHuman = human;
                getParentController().loadUI(GUIConfig.PROFILE_WINDOW_PATH, null, ChildUIType.HUMAN_PROFILE_CHILD_WINDOW);
            }
        });
        humanCanvas.setLayoutX(525 + human.getCoordinates().getX()*1.05);
        humanCanvas.setLayoutY(225 - (human.getCoordinates().getY() * 0.9));
        if (human.getCar() == null) {
            drawHumanWithoutCar(human, humanCanvas);
        } else if (human.getCar().getCarCoolness()) {
            drawHumanWithCoolCar(human, humanCanvas);
        } else {
            drawHumanWithShitCar(human, humanCanvas);
        }
        return humanCanvas;
    }

    private void drawHumanWithoutCar(HumanBeing human, Canvas humanCanvas) {
        checkAuthorColor(human);
        GraphicsContext graphicsContext = humanCanvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(2);
        graphicsContext.setFill(colors.get(human.getAuthor()));
        graphicsContext.setStroke(colors.get(human.getAuthor()));
        graphicsContext.strokeLine(7, 15, 7, 30);
        //legs
        graphicsContext.strokeLine( 0, 35, 7, 30);
        graphicsContext.strokeLine(14,35, 7, 30);

        //hands
        graphicsContext.strokeLine(0, 13, 7,20);
        graphicsContext.strokeLine( 14, 13, 7,20);

        //head
        graphicsContext.strokeOval(2, 2, 10, 10);
    }

    private void drawHumanWithShitCar(HumanBeing human, Canvas humanCanvas) {
        checkAuthorColor(human);
        GraphicsContext graphicsContext = humanCanvas.getGraphicsContext2D();
        graphicsContext.setFill(colors.get(human.getAuthor()));
        graphicsContext.setStroke(colors.get(human.getAuthor()));
        graphicsContext.setLineWidth(2);
        graphicsContext.strokeOval(10, 30, 10, 10);
        graphicsContext.strokeOval(30, 30, 10, 10);
        graphicsContext.strokeLine(15, 35, 25, 35);
        graphicsContext.strokeLine(15, 35, 20, 28);
        graphicsContext.strokeLine(32, 25, 20, 28);
        graphicsContext.strokeLine(32, 25, 25, 35);
        graphicsContext.strokeLine(32, 25, 35, 35);
        graphicsContext.strokeLine(32, 25, 32, 20);
        graphicsContext.strokeLine(36, 22, 29, 18);


        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.setStroke(Color.YELLOW);
        graphicsContext.fillOval(16, 8, 8,8);
        graphicsContext.strokeLine(20, 24, 25, 37);
        graphicsContext.strokeLine(20, 24, 22, 37);
        graphicsContext.strokeLine(20, 24, 20, 15);
        graphicsContext.strokeLine(25, 23, 20, 15);
    }

    private void drawHumanWithCoolCar(HumanBeing human, Canvas humanCanvas) {
        checkAuthorColor(human);
        GraphicsContext graphicsContext = humanCanvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(2);
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.setStroke(Color.YELLOW);
        graphicsContext.fillOval(16, 16, 8,8);
        graphicsContext.strokeLine(20, 32, 20, 23);
        graphicsContext.strokeLine(25, 31, 20, 23);

        graphicsContext.setFill(colors.get(human.getAuthor()));
        graphicsContext.setStroke(colors.get(human.getAuthor()));
        //cool car
        graphicsContext.fillRect(5, 33, 25, 8);
        graphicsContext.fillRect(30, 38, 15, 3);
        graphicsContext.fillPolygon(new double[] {30, 30, 45}, new double[] {33, 38, 38}, 3);
        graphicsContext.strokeLine(25, 30, 30, 33);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(12, 37, 7, 7);
        graphicsContext.fillOval(23, 37, 7, 7);
    }

    private void checkAuthorColor(HumanBeing humanBeing) {
        if (!colors.containsKey(humanBeing.getAuthor())) {
            colors.put(humanBeing.getAuthor(), Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

    @Override
    public void updateInfo(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {
        elementsToRemove.forEach(this::removeFromVisualization);
        elementsToAdd.forEach(this::addToVisualization);
        List<Long> idsToUpdate = elementsToUpdate.stream().map(HumanBeing::getId).collect(Collectors.toList());
        people.keySet().removeIf(human -> idsToUpdate.contains(human.getId()));
        elementsToUpdate.forEach(human -> people.put(human, generateCanvas(human)));

    }

    @Override
    public void setInfo(List<HumanBeing> elementsToSet) {
        elementsToSet.forEach(this::addToVisualization);
    }

    private void addToVisualization(HumanBeing human) {
        Canvas canvas = generateCanvas(human);
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(canvas);
        transition.setDuration(Duration.millis(1000));
        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(1);
        transition.setToY(1);
        people.put(human, canvas);
        transition.play();
        visualizationPane.getChildren().add(canvas);
    }

    private void removeFromVisualization(HumanBeing human) {
        visualizationPane.getChildren().remove(people.get(human));
        people.remove(human);
    }

    @Override
    public HumanBeing getChosenHuman() {
        return chosenHuman;
    }

    @Override
    public void setChosenHuman(HumanBeing human) {
        this.chosenHuman = human;
    }
}
