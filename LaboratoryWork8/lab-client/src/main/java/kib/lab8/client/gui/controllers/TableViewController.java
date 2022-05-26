package kib.lab8.client.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.entities.enums.Mood;
import kib.lab8.common.entities.enums.WeaponType;

import java.util.Date;

public class TableViewController extends AbstractController {

    @FXML
    private TableView<HumanBeing> humanTable;

    @FXML
    private TableColumn<HumanBeing, Long> id;

    @FXML
    private TableColumn<HumanBeing, String> name;

    @FXML
    private TableColumn<HumanBeing, Long> x;

    @FXML
    private TableColumn<HumanBeing, Float> y;

    @FXML
    private TableColumn<HumanBeing, Date> creationDate;

    @FXML
    private TableColumn<HumanBeing, Boolean> realHero;

    @FXML
    private TableColumn<HumanBeing, Boolean> popularity;

    @FXML
    private TableColumn<HumanBeing, Integer> impactSpeed;

    @FXML
    private TableColumn<HumanBeing, WeaponType> weapon;

    @FXML
    private TableColumn<HumanBeing, Mood> mood;

    @FXML
    private TableColumn<HumanBeing, Boolean> carCoolness;

    @FXML
    private TableColumn<HumanBeing, Boolean> carSpeed;

}
