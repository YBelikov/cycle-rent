package com.belikov.valteris.cycle.bicycle.model;

public enum SortType {
    PRICE_UP("Price Up"),
    PRICE_DOWN("Price down"),
    WEIGHT_UP("Weight up"),
    WEIGHT_DOWN("Weight down");

    private final String description;

    public String getDescription() {
        return description;
    }

    public static SortType findSortTypeByDescription(String description) {
        for (SortType sortType : SortType.values()) {
            if (sortType.description.equals(description)) {
                return sortType;
            }
        }
        return SortType.PRICE_UP;
    }

    SortType(String description) {
        this.description = description;
    }
}
