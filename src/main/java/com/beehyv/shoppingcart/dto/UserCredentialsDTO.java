package com.beehyv.shoppingcart.dto;

public class UserCredentialsDTO {
    private String email;
    private String password;
    private UserProfileDTO userProfileDTO;
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

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
    }

    @Override
    public String toString() {
        return "UserCredentialsDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userProfileDTO=" + userProfileDTO +
                '}';
    }
}
