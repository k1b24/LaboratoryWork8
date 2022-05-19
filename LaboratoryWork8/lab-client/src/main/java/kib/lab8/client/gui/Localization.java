package kib.lab8.client.gui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {
    private final Locale currentLocale = Locale.getDefault();
    private final Locale csCzLocale = new Locale("cs", "CZ");
    private final Locale ruRuLocale = new Locale("ru", "RU");
    private final Locale bgBgLocale = new Locale("bg", "BG");
    private final Locale enGbLocale = new Locale("en", "GB");
    private ResourceBundle resourceBundle;

    public Localization() {
        try {
            resourceBundle = ResourceBundle.getBundle("text", enGbLocale);
        } catch (MissingResourceException e) {
            resourceBundle = ResourceBundle.getBundle("text", ruRuLocale);
        }
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
