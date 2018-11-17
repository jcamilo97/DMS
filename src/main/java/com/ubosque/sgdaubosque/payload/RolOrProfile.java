package com.ubosque.sgdaubosque.payload;

import com.ubosque.sgdaubosque.model.Profile;

public class RolOrProfile {
    private String roleorperfil;
    private String[] actions;

    public RolOrProfile(String nameRol, String[] actions) {
        this.roleorperfil = nameRol;
        this.actions = actions;
    }

    public RolOrProfile(Profile profile) {
        this.roleorperfil = profile.getName();
        this.actions = profile.getActions().substring(2, profile.getActions().length() - 2).replace("\"","").split(",");
    }


    public String getRoleorperfil() {
        return roleorperfil;
    }

    public void setRoleorperfil(String roleorperfil) {
        this.roleorperfil = roleorperfil;
    }

    public String[] getActions() {
        return actions;
    }

    public void setActions(String[] actions) {
        this.actions = actions;
    }
}
