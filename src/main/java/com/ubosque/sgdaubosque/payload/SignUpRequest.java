package com.ubosque.sgdaubosque.payload;
import javax.validation.constraints.*;

import org.springframework.format.annotation.NumberFormat;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class SignUpRequest {

    private String name;

    @Size(min = 4)
    private String lastname;

     @NotBlank
     @Size(min = 3, max = 15)
     private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NumberFormat
    private String areaid;
    
    @NumberFormat
    private String profileid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

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

    public String getAreaid(){
        return  areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getProfileid(){
        return profileid;
    }

    public void setProfileid(String profileid) {
        this.areaid = profileid;
    }
}
