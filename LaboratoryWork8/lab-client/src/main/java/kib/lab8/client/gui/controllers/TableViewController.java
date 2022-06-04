package kib.lab8.client.gui.controllers;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kib.lab8.client.gui.GUIConfig;
import kib.lab8.client.gui.abstractions.DataVisualizerController;
import kib.lab8.client.utils.ChildUIType;
import kib.lab8.common.entities.HumanBeing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
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
    private Pagination pagination = new Pagination();
    private final ObservableList<HumanBeing> observableHumanBeingList = FXCollections.observableArrayList();
    private HumanBeing chosenHuman;
    private final List<HumanBeing> fullCollection = new ArrayList<>();
    private final List<HumanBeing> collectionToShow = new ArrayList<>();
    private int currentPage = 0;
    private Predicate<HumanBeing> currentFiltrationPredicate = null;
    private final ChangeListener<String> idFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.getId()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> nameFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> humanBeing.getName().toLowerCase().contains(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> xFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.getCoordinates().getX()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> yFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.getCoordinates().getY()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> creationDateFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.getCreationDate()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> realHeroFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.isRealHero()).toLowerCase().startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> popularityFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> String.valueOf(humanBeing.isHasToothpick()).toLowerCase().startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> impactSpeedFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> (humanBeing.getImpactSpeed() == null ? "-" : String.valueOf(humanBeing.getImpactSpeed())).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> weaponFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> (humanBeing.getWeaponType() == null ? "-" : humanBeing.getWeaponType().toString().toLowerCase()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> moodFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> (humanBeing.getMood() == null ? "-" : humanBeing.getMood().toString().toLowerCase()).startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> carCoolnessFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> ((humanBeing.getCar() == null ? "-" : String.valueOf(humanBeing.getCar().getCarCoolness()).toLowerCase()).startsWith(newValue.toLowerCase()));
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> carSpeedFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> ((humanBeing.getCar() == null ? "-" : String.valueOf(humanBeing.getCar().getCarSpeed())).startsWith(newValue.toLowerCase()));
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };
    private final ChangeListener<String> authorFieldChangeListener = (observableValue, oldValue, newValue) -> {
        if (newValue.equals("")) {
            currentFiltrationPredicate = null;
            collectionToShow.clear();
            collectionToShow.addAll(fullCollection);
        } else {
            collectionToShow.clear();
            Predicate<HumanBeing> filtrationPredicate = humanBeing -> humanBeing.getAuthor().toLowerCase().startsWith(newValue.toLowerCase());
            collectionToShow.addAll(fullCollection.stream().filter(filtrationPredicate).collect(Collectors.toList()));
            currentFiltrationPredicate = filtrationPredicate;
        }
        currentPage = 0;
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    };

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
        humanTable.setItems(observableHumanBeingList);
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            currentPage = newValue.intValue();
            updateTable();
        });
        setUpFiltrationListeners();
    }

    private void updateTable() {
        int from = currentPage * ROWS_PER_PAGE;
        int to = from + ROWS_PER_PAGE;
        List<HumanBeing> subList = new ArrayList<>();
        for (int i = 0; i < collectionToShow.size(); i++) {
            if (i >= from && i < to) {
                subList.add(collectionToShow.get(i));
            }
        }
        observableHumanBeingList.clear();
        observableHumanBeingList.addAll(subList);
    }

    @Override
    public void updateInfo(List<HumanBeing> elementsToRemove, List<HumanBeing> elementsToAdd, List<HumanBeing> elementsToUpdate) {
        fullCollection.removeAll(elementsToRemove);
        fullCollection.addAll(elementsToAdd);
        List<Long> updatedIds = elementsToUpdate.stream().map(HumanBeing::getId).collect(Collectors.toList());
        fullCollection.removeIf(human -> updatedIds.contains(human.getId()));
        fullCollection.addAll(elementsToUpdate);
        fullCollection.sort(Comparator.comparing(HumanBeing::getId));
        collectionToShow.clear();
        if (currentFiltrationPredicate != null) {
            collectionToShow.addAll(fullCollection.stream().filter(currentFiltrationPredicate).collect(Collectors.toList()));
        } else {
            collectionToShow.addAll(fullCollection);
        }
        pagination.setPageCount((int) Math.ceil(collectionToShow.size() / (double) ROWS_PER_PAGE));
        updateTable();
    }

    @Override
    public void setInfo(List<HumanBeing> elementsToSet) {
        fullCollection.clear();
        fullCollection.addAll(elementsToSet);
        fullCollection.sort(Comparator.comparing(HumanBeing::getId));
        pagination.setPageCount((int) Math.ceil(fullCollection.size() / (double) ROWS_PER_PAGE));
        collectionToShow.addAll(fullCollection);
        updateTable();
    }


    @Override
    public HumanBeing getChosenHuman() {
        return chosenHuman;
    }

    @Override
    public void setChosenHuman(HumanBeing human) {
        this.chosenHuman = human;
    }

    private void setUpFiltrationListeners() {
        idFilter.textProperty().addListener(idFieldChangeListener);
        nameFilter.textProperty().addListener(nameFieldChangeListener);
        xFilter.textProperty().addListener(xFieldChangeListener);
        yFilter.textProperty().addListener(yFieldChangeListener);
        creationDateFilter.textProperty().addListener(creationDateFieldChangeListener);
        realHeroFilter.textProperty().addListener(realHeroFieldChangeListener);
        popularityFilter.textProperty().addListener(popularityFieldChangeListener);
        impactSpeedFilter.textProperty().addListener(impactSpeedFieldChangeListener);
        weaponFilter.textProperty().addListener(weaponFieldChangeListener);
        moodFilter.textProperty().addListener(moodFieldChangeListener);
        carCoolnessFilter.textProperty().addListener(carCoolnessFieldChangeListener);
        carSpeedFilter.textProperty().addListener(carSpeedFieldChangeListener);
        authorFilter.textProperty().addListener(authorFieldChangeListener);
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
}
