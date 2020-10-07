package com.anton.web_project.model.entity.type;

public enum LanguageType {
    RU_RU(1),
    EN_US(2);

    private int languageId;

    LanguageType(int languageId) {
        this.languageId = languageId;
    }

    public int getLanguageId() {
        return languageId;
    }
}
