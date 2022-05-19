package org.launchcode.codingevents.models;

public enum EventType {
    CONFERENCE("conference"),
    MEETUP("meetup"),
    WORKSHOP("workshop"),
    SOCIAL("social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
