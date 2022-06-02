package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.common.entities.HumanBeing;

import java.util.List;

public class VisualizationController extends DataVisualizerController {

    @FXML
    private Canvas canvas;
    private GraphicsContext graphics;

    @FXML
    private void initialize() {
        graphics = canvas.getGraphicsContext2D();
        graphics.strokeLine(10,10,20,20);

    }

    @Override
    public void updateInfo(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {

    }

    @Override
    public void setInfo(List<HumanBeing> elementsToSet) {

    }

    @Override
    public HumanBeing getChosenHuman() {
        //TODO Возвращать выбранного в визуализации человека, для того, чтобы менюха с апдейтом красиво открывалась
        return null;
    }

    @Override
    public void setChosenHuman(HumanBeing human) {

    }
}
