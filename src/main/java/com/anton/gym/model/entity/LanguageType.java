package com.anton.gym.model.entity;

/**
 * The {@code LanguageType} class represents language type.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public enum LanguageType {
    RU_RU(1),
    EN_US(2);

    private int languageId;

    LanguageType(int languageId) {
        this.languageId = languageId;
    }

    /**
     * get language id
     *
     * @return the id
     */
    public int getLanguageId() {
        return languageId;
    }
}
