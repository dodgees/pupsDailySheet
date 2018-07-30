package com.mindcanary.domain.activities;

import com.mindcanary.exceptions.EnumerationException;

public enum ActivityType {
    MEAL(1, "Meal"), NAP(2, "Nap/Sleep"), BATHROOM(3, "Bathroom"), MEDICATION(4, "Medication");

    private final long id;

    private final String name;

    ActivityType(long id, String name){
        this.id = id;
        this.name= name;
    }

    public static ActivityType fromName(String name) {
        for (ActivityType type : ActivityType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new EnumerationException(name, ActivityType.class.toString());
    }

    public static ActivityType fromId(long id) {
        for (ActivityType type : ActivityType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new EnumerationException(id, ActivityType.class.toString());
    }
    }
