package com.project.TicketingMaster.Data;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum Section {
    SECTION_A("Section A"),
    SECTION_B("Section B");

    public final String sectionName;

    private static final Map<String, Section> map = new HashMap<>(values().length, 1);

    static {
        for (Section c : values()) map.put(c.sectionName, c);
    }

    public static Section of(String name) {
        Section result = map.get(name);
        if (result == null) {
            throw new IllegalArgumentException("Invalid section: " + name);
        }
        return result;
    }
}
