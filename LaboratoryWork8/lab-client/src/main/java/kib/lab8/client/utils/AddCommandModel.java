package kib.lab8.client.utils;

import kib.lab8.client.gui.controllers.AddCommandController;
import kib.lab8.common.entities.HumanBeing;

import java.time.LocalDate;

public class AddCommandModel {

    private final AddCommandController controller;

    public AddCommandModel(AddCommandController controller) {
        this.controller = controller;
    }

    public HumanBeing createHuman() throws UserException {
        try {
            HumanBeing newHuman = new HumanBeing();
            newHuman.setName(controller.getName().getText());
            newHuman.getCoordinates().setX(Long.parseLong(controller.getX().getText()));
            newHuman.getCoordinates().setY(Long.parseLong(controller.getY().getText()));
            newHuman.setCreationDate(LocalDate.now());
            newHuman.setRealHero(controller.getRealHero().isSelected());
            newHuman.setHasToothpick(controller.getPopularity().isSelected());
            newHuman.setImpactSpeed(Integer.parseInt(controller.getImpactSpeed().getText()));
            newHuman.setWeaponType(controller.getWeapon().getValue());
            newHuman.setMood(controller.getMood().getValue());
            if (!controller.getCarCheckBox().isSelected()) {
                newHuman.setCar(null);
            } else {
                newHuman.getCar().setCarCoolness(controller.getCarCoolness().isSelected());
                newHuman.getCar().setCarSpeed(Integer.parseInt(controller.getCarSpeed().getText()));
            }
            validateHuman(newHuman);
            return newHuman;
        } catch (NumberFormatException e) {
            throw new UserException("Поля с числовыми значениями не могут быть пустыми");
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
}
