package com.ubosque.sgdaubosque.payload;
import javax.validation.constraints.*;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class SignUpRequest {
    @NotBlank
    @Size(min = 4)
    private String name;

    @Size(min = 4)
    private String lastname;

    // @NotBlank
    // @Size(min = 3, max = 15)
    // private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private long areaid;

    @NotBlank
    private long profileid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    // public String getUsername() {
    //     return username;
    // }

    // public void setUsername(String username) {
    //     this.username = username;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAreaId(){
        return areaid;
    }

    public void setAreaId(long id) {
        this.areaid = id;
    }

    public long getProfileId(){
        return profileid;
    }

    public void setProfileId(long id) {
        this.areaid = id;
    }
}
