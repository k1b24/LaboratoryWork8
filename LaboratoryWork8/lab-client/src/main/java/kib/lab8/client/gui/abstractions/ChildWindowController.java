package kib.lab8.client.gui.abstractions;

import kib.lab8.client.utils.MenuModel;

public abstract class ChildWindowController {

    private MenuModel parentModel;

    public void setMenuModel(MenuModel model) {
        this.parentModel = model;
    }

    public MenuModel getParentModel() {
        return parentModel;
    }
}
