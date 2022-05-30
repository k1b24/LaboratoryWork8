package kib.lab8.client.gui.controllers;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.client.utils.ChildUIType;
import kib.lab8.common.entities.HumanBeing;

import java.text.SimpleDateFormat;
import java.util.List;

public class TableViewController extends DataVisualizerController {

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
    private TableColumn<HumanBeing, Integer> impactSpeed;

    @FXML
    private TableColumn<HumanBeing, String> weapon;

    @FXML
    private TableColumn<HumanBeing, String> mood;

    @FXML
    private TableColumn<HumanBeing, String> carCoolness;

    @FXML
    private TableColumn<HumanBeing, String> carSpeed;
    private final ObservableList<HumanBeing> observableHumanBeingList = FXCollections.observableArrayList();
    private HumanBeing chosenHuman;

    @FXML
    private void initialize() {
        humanTable.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                chosenHuman = humanTable.getSelectionModel().getSelectedItem();
                getParentController().loadUI(GUIConfig.PROFILE_WINDOW_PATH, null, ChildUIType.HUMAN_PROFILE_CHILD_WINDOW);
            }
        });
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        x.setCellValueFactory(humanBeing -> new SimpleLongProperty(humanBeing.getValue().getCoordinates().getX()).asObject());
        y.setCellValueFactory(humanBeing -> new SimpleFloatProperty(humanBeing.getValue().getCoordinates().getY()).asObject());
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        realHero.setCellValueFactory(new PropertyValueFactory<>("realHero"));
        popularity.setCellValueFactory(new PropertyValueFactory<>("hasToothpick"));
        impactSpeed.setCellValueFactory(humanBeing -> new SimpleObjectProperty<>(humanBeing.getValue().getImpactSpeed()));
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
        carSpeed.setCellValueFactory(humanBeing -> new SimpleObjectProperty<>(humanBeing.getValue().getCar() != null
                ? String.valueOf(humanBeing.getValue().getCar().getCarSpeed())
                : "-"));
    }

    public HumanBeing getChosenHuman() {
        return chosenHuman;
    }

    @Override
    public void setChosenHuman(HumanBeing human) {
        this.chosenHuman = human;
    }

    @Override
    public void updateInfo(List<HumanBeing> humanBeingList) {
        observableHumanBeingList.clear();
        observableHumanBeingList.addAll(humanBeingList);
        humanTable.setItems(observableHumanBeingList);
    }
}
