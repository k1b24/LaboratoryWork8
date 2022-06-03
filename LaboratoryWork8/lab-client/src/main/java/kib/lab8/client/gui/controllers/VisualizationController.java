package kib.lab8.client.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.common.entities.HumanBeing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VisualizationController extends DataVisualizerController {

    @FXML
    private AnchorPane visualizationPane;

    private final ObservableList<HumanBeing> observableHumanBeingList = FXCollections.observableArrayList();
    private HumanBeing chosenHuman;
    private final List<Canvas> humans = new ArrayList<>();
    private static final Map<String, Color> colors = new HashMap<>();

    @FXML
    private void initialize() {


    }

    private void drawHuman(HumanBeing human) {
        checkAuthorColor(human);
        Canvas humanCanvas = new Canvas(50, 50);
        humanCanvas.setLayoutX(550 + (float) (human.getCoordinates().getX()) / 3);
        humanCanvas.setLayoutY(250 - (human.getCoordinates().getY()) / 3);
        if (human.getCar() == null) {
            drawHumanWithoutCar(human, humanCanvas);
        } else if (human.getCar().getCarCoolness()) {
            drawHumanWithCoolCar(human, humanCanvas);
        } else {
            drawHumanWithShitCar(human, humanCanvas);
        }

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
        visualizationPane.getChildren().add(humanCanvas);
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


        graphicsContext.setFill(Color.BEIGE);
        graphicsContext.setStroke(Color.BEIGE);
        graphicsContext.fillOval(16, 8, 8,8);
        graphicsContext.strokeLine(20, 24, 25, 37);
        graphicsContext.strokeLine(20, 24, 22, 37);
        graphicsContext.strokeLine(20, 24, 20, 15);
        graphicsContext.strokeLine(25, 23, 20, 15);

        visualizationPane.getChildren().add(humanCanvas);
    }

    private void drawHumanWithCoolCar(HumanBeing human, Canvas humanCanvas) {
        checkAuthorColor(human);
        GraphicsContext graphicsContext = humanCanvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(2);
        graphicsContext.setFill(Color.BEIGE);
        graphicsContext.setStroke(Color.BEIGE);
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
        visualizationPane.getChildren().add(humanCanvas);
    }

    private void checkAuthorColor(HumanBeing humanBeing) {
        if (!colors.containsKey(humanBeing.getAuthor())) {
            colors.put(humanBeing.getAuthor(), Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

    @Override
    public void updateInfo(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {
        observableHumanBeingList.removeAll(elementsToRemove);
        observableHumanBeingList.addAll(elementsToAdd);
        List<Long> updatedIds = elementsToUpdate.stream().map(HumanBeing::getId).collect(Collectors.toList());
        observableHumanBeingList.removeIf(human -> updatedIds.contains(human.getId()));
        observableHumanBeingList.addAll(elementsToUpdate);
    }

    @Override
    public void setInfo(List<HumanBeing> elementsToSet) {
        observableHumanBeingList.clear();
        observableHumanBeingList.addAll(elementsToSet);
        observableHumanBeingList.forEach(this::drawHuman);
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
