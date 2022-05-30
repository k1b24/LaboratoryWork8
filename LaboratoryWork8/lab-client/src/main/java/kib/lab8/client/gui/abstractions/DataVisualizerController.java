package kib.lab8.client.gui.abstractions;

import kib.lab8.client.gui.controllers.MenuController;
import kib.lab8.common.entities.HumanBeing;

import java.util.List;

public abstract class DataVisualizerController {

    private MenuController parentController;

    public abstract void updateInfo(List<HumanBeing> humanBeingList);

    public MenuController getParentController() {
        return parentController;
    }

    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }
}
