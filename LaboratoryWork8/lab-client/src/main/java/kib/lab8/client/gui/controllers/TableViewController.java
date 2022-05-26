package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.common.entities.HumanBeing;

public class TableViewController extends AbstractController {

    @FXML
    private TableView<HumanBeing> humanTable;

    @FXML
    private TableColumn<HumanBeing, Long> id;

    @FXML
    private TableColumn<HumanBeing, String> name;

}
