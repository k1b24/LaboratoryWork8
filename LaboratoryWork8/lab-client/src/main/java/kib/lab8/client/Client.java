package kib.lab8.client;


import kib.lab8.client.gui.GUIApplication;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        GUIApplication.main(args);
    }
}
