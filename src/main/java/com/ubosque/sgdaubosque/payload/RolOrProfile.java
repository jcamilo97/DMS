package com.ubosque.sgdaubosque.payload;

import com.ubosque.sgdaubosque.model.Profile;

public class RolOrProfile {
    private String name;
    private String[] actions;

    public RolOrProfile(String nameRol, String[] actions) {
        this.name = nameRol;
        this.actions = actions;
    }

    public RolOrProfile(Profile profile) {
        this.name = profile.getName();
        String act = profile.getActions().substring(1, profile.getActions().length() - 1);
        this.actions = profile.getActions().substring(1, profile.getActions().length() - 2).replace("\"","").split(",");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getActions() {
        return actions;
    }

    public void setActions(String[] actions) {
        this.actions = actions;
    }
}
