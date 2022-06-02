package kib.lab8.client.gui.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.client.utils.ChildUIType;
import kib.lab8.common.entities.HumanBeing;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TableViewController extends DataVisualizerController {

    private static final int ROWS_PER_PAGE = 16;
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

    @FXML
    private TableColumn<HumanBeing, String> author;

    @FXML
    private TextField idFilter;

    @FXML
    private TextField nameFilter;

    @FXML
    private TextField xFilter;

    @FXML
    private TextField yFilter;

    @FXML
    private TextField creationDateFilter;

    @FXML
    private TextField realHeroFilter;

    @FXML
    private TextField popularityFilter;

    @FXML
    private TextField impactSpeedFilter;

    @FXML
    private TextField weaponFilter;

    @FXML
    private TextField moodFilter;

    @FXML
    private TextField carCoolnessFilter;

    @FXML
    private TextField carSpeedFilter;

    @FXML
    private TextField authorFilter;

    @FXML
    private Pagination pagination;
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
        impactSpeed.setCellValueFactory(humanBeing -> new SimpleIntegerProperty(humanBeing.getValue().getImpactSpeed()).asObject());
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
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        setToolTip(name);
        setToolTip(weapon);
        setToolTip(author);
        humanTable.getSortOrder().add(id);
        setUpFiltration(observableHumanBeingList);
    }

    public void setToolTip(TableColumn<HumanBeing, String> tableColumn) {
        tableColumn.setCellFactory
                (column -> new TableCell<HumanBeing, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item != null && item.length() >= 10) {
                            setTooltip(new Tooltip(item));
                        }
                    }
                });
    }

    public HumanBeing getChosenHuman() {
        return chosenHuman;
    }

    @Override
    public void setChosenHuman(HumanBeing human) {
        this.chosenHuman = human;
    }

    @Override
    public void updateInfo(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {
        observableHumanBeingList.removeAll(elementsToRemove);
        observableHumanBeingList.addAll(elementsToAdd);
        List<Long> updatedIds = elementsToUpdate.stream().map(HumanBeing::getId).collect(Collectors.toList());
        observableHumanBeingList.removeIf(human -> updatedIds.contains(human.getId()));
        observableHumanBeingList.addAll(elementsToUpdate);
        updatePagination();
    }

    @Override
    public void setInfo(List<HumanBeing> elementsToSet) {
        observableHumanBeingList.clear();
        observableHumanBeingList.addAll(elementsToSet);
    }

    private void updatePagination() {
        Platform.runLater(new Runnable() {
            int currentPage;
            @Override
            public void run() {
                currentPage = pagination.getCurrentPageIndex();
                pagination.setPageCount(observableHumanBeingList.size() / ROWS_PER_PAGE + 1);
                pagination.setPageFactory(this::createPage);
                pagination.setCurrentPageIndex(currentPage);
            }

            private Node createPage(int pageIndex) {
                int from = pageIndex * ROWS_PER_PAGE;
                int to = Math.min(from + ROWS_PER_PAGE, observableHumanBeingList.size());
                ObservableList<TableColumn<HumanBeing, ?>> sortOrder = FXCollections.observableArrayList(humanTable.getSortOrder());
                ObservableList<HumanBeing> pageHumansList = FXCollections.observableArrayList(observableHumanBeingList.subList(from, to));
                humanTable.setItems(pageHumansList);
                setUpFiltration(pageHumansList);
                humanTable.getSortOrder().addAll(sortOrder);
                humanTable.sort();
                return humanTable;
            }
        });
    }

    private void setUpFiltration(ObservableList<HumanBeing> humanBeingObservableList) {
        FilteredList<HumanBeing> filteredList = new FilteredList<>(humanBeingObservableList, t -> true);
        idFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getId()).startsWith(newValue)));
        nameFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> humanBeing.getName().toLowerCase().contains(newValue.toLowerCase())));
        xFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getCoordinates().getX()).startsWith(newValue)));
        yFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getCoordinates().getY()).startsWith(newValue)));
        creationDateFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getCreationDate()).startsWith(newValue)));
        realHeroFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.isRealHero()).startsWith(newValue)));
        popularityFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.isHasToothpick()).startsWith(newValue)));
        impactSpeedFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getImpactSpeed()).toLowerCase().startsWith(newValue.toLowerCase())));
        weaponFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getWeaponType()).toLowerCase().startsWith(newValue.toLowerCase())));
        moodFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> String.valueOf(humanBeing.getMood()).startsWith(newValue)));
        carCoolnessFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> (humanBeing.getCar() == null ? "-" : String.valueOf(humanBeing.getCar().getCarCoolness())).startsWith(newValue)));
        carSpeedFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> (humanBeing.getCar() == null ? "-" : String.valueOf(humanBeing.getCar().getCarSpeed())).startsWith(newValue)));
        authorFilter.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(humanBeing -> (humanBeing.getAuthor().toLowerCase()).startsWith(newValue.toLowerCase())));
        SortedList<HumanBeing> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(humanTable.comparatorProperty());
        humanTable.setItems(sortedList);
    }
}
