package kib.lab8.client.gui.controllers;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kib.lab8.client.gui.abstractions.AbstractController;
import kib.lab8.client.utils.MenuModel;
import kib.lab8.common.abstractions.DataVisualizerController;
import kib.lab8.common.entities.HumanBeing;
import kib.lab8.common.entities.enums.Mood;
import kib.lab8.common.entities.enums.WeaponType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableViewController extends DataVisualizerController {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
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
    private TableColumn<HumanBeing, String> creationDate;

    @FXML
    private TableColumn<HumanBeing, Boolean> realHero;

    @FXML
    private TableColumn<HumanBeing, Boolean> popularity;

    @FXML
    private TableColumn<HumanBeing, String> impactSpeed;

    @FXML
    private TableColumn<HumanBeing, String> weapon;

    @FXML
    private TableColumn<HumanBeing, String> mood;

    @FXML
    private TableColumn<HumanBeing, String> carCoolness;

    @FXML
    private TableColumn<HumanBeing, String> carSpeed;
    private final ObservableList<HumanBeing> observableHumanBeingList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        x.setCellValueFactory(humanBeing -> new SimpleLongProperty(humanBeing.getValue().getCoordinates().getX()).asObject());
        y.setCellValueFactory(humanBeing -> new SimpleFloatProperty(humanBeing.getValue().getCoordinates().getY()).asObject());
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        realHero.setCellValueFactory(new PropertyValueFactory<>("realHero"));
        popularity.setCellValueFactory(new PropertyValueFactory<>("hasToothpick"));
        impactSpeed.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getImpactSpeed() != null
                ? humanBeing.getValue().getImpactSpeed().toString()
                : "-"));
        weapon.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getWeaponType() != null
                ? humanBeing.getValue().getWeaponType().toString()
                : "-"));
        mood.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getMood() != null
                ? humanBeing.getValue().getMood().toString()
                : "-"));
        carCoolness.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getCar() != null
                ? humanBeing.getValue().getCar().getCarCoolness() != null
                ? humanBeing.getValue().getCar().getCarCoolness().toString()
                : "-"
                : "-"));
        carSpeed.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getCar() != null
                ? String.valueOf(humanBeing.getValue().getCar().getCarSpeed())
                : "-"));
    }

    @Override
    public void updateInfo(List<HumanBeing> humanBeingList) {
        observableHumanBeingList.clear();
        observableHumanBeingList.addAll(humanBeingList);
        humanTable.setItems(observableHumanBeingList);
    }
}
