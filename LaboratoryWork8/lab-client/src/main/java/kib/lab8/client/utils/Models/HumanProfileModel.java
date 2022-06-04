package kib.lab8.client.utils.Models;

import kib.lab8.client.gui.controllers.HumanProfileController;
import kib.lab8.client.utils.ExecutableCommand;
import kib.lab8.client.utils.Exceptions.UserException;
import kib.lab8.common.entities.HumanBeing;

import java.net.SocketTimeoutException;
import java.time.LocalDate;

public class HumanProfileModel {

    private final HumanProfileController controller;
    private final HumanBeing currentHuman;

    public HumanProfileModel(HumanProfileController controller, HumanBeing human) {
        this.controller = controller;
        this.currentHuman = human;
    }

    public void updateHuman() throws UserException {
        HumanBeing newHuman = new HumanBeing();
        try {
            if (!controller.getNameField().getText().equals("")) {
                newHuman.setName(controller.getNameField().getText());
            } else {
                newHuman.setName(currentHuman.getName());
            }
            if (!controller.getxField().getText().equals("")) {
                newHuman.getCoordinates().setX(Long.parseLong(controller.getxField().getText()));
            } else {
                newHuman.getCoordinates().setX(currentHuman.getCoordinates().getX());
            }
            if (!controller.getyField().getText().equals("")) {
                newHuman.getCoordinates().setY(Float.parseFloat(controller.getyField().getText()));
            } else {
                newHuman.getCoordinates().setY(currentHuman.getCoordinates().getY());
            }
            newHuman.setRealHero(controller.getRealHeroCheckBox().isSelected());
            newHuman.setHasToothpick(controller.getPopularityCheckBox().isSelected());
            if (!controller.getImpactSpeedField().getText().equals("")) {
                newHuman.setImpactSpeed(Integer.parseInt(controller.getImpactSpeedField().getText()));
            } else {
                newHuman.setImpactSpeed(currentHuman.getImpactSpeed());
            }
            if (controller.getWeaponChoiceBox().getValue() != currentHuman.getWeaponType()) {
                newHuman.setWeaponType(controller.getWeaponChoiceBox().getValue());
            } else {
                newHuman.setWeaponType(currentHuman.getWeaponType());
            }
            if (controller.getMoodChoiceBox().getValue() != currentHuman.getMood()) {
                newHuman.setMood(controller.getMoodChoiceBox().getValue());
            } else {
                newHuman.setMood(currentHuman.getMood());
            }
            if (controller.getCarCheckBox().isSelected() && currentHuman.getCar() == null) {
                newHuman.getCar().setCarSpeed(Integer.parseInt(controller.getCarSpeedField().getText()));
                newHuman.getCar().setCarCoolness(controller.getCarCoolnessCheckBox().isSelected());
            } else if (!controller.getCarCheckBox().isSelected() && currentHuman.getCar() != null) {
                newHuman.setCar(null);
            } else {
                if (!controller.getCarSpeedField().getText().equals("")) {
                    newHuman.getCar().setCarSpeed(Integer.parseInt(controller.getCarSpeedField().getText()));
                }
                newHuman.getCar().setCarCoolness(controller.getCarCoolnessCheckBox().isSelected());
            }
        } catch (NumberFormatException e) {
            throw new UserException("Введите скорость машины");
        }
        validateHuman(newHuman);
        newHuman.setAuthor(controller.getParentModel().getUserLogin());
        newHuman.setCreationDate(LocalDate.now());
        ExecutableCommand update = ExecutableCommand.UPDATE_BY_ID_COMMAND;
        try {
            update.action(controller.getParentModel().getConnectionHandler(), controller.getParentModel().getUserLogin(), controller.getParentModel().getUserPassword(), currentHuman.getId(), newHuman);
        } catch (SocketTimeoutException e) {
            throw new UserException("От сервера не был получен ответ...");
        }
    }

    private void validateHuman(HumanBeing human) throws UserException {
        if (!(human.getName().length() > 2 && human.getName().length() < 50)) {
            throw new UserException("Длина имени человека должна быть больше 2 и меньше 50");
        }
        if (!(human.getCoordinates().getX() > -500)) {
            throw new UserException("Координата X должна быть больше -500");
        }
        if (!(human.getCoordinates().getX() < 500)) {
            throw new UserException("Координата по X должна быть меньше 500");
        }
        if (!(human.getCoordinates().getY() < 250)) {
            throw new UserException("Координата по Y должна быть меньше 250");
        }
        if (!(human.getCoordinates().getY() > -250)) {
            throw new UserException("Координата по Y должна быть больше 250");
        }
        if (!(human.getImpactSpeed() < 712)) {
            throw new UserException("Скорость удара должна быть меньше 712");
        }
    }

    public HumanBeing getCurrentHuman() {
        return currentHuman;
    }
}
