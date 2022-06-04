package kib.lab8.client.gui.localization;

public enum LanguagesEnum {
    ENGLISH("EN", "en"),
    BULGARIAN("BG", "bg"),
    RUSSIAN("RU", "ru"),
    CZECH("CZ", "cz");

    private final String name;
    private final String languageName;

    LanguagesEnum(String name, String languageName) {
        this.name = name;
        this.languageName = languageName;
    }

    public String getName() {
        return name;
    }

    public String getLanguageName() {
        return languageName;
    }

    @Override
    public String toString() {
        return name;
    }
}
