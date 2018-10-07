package ua.model.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.entity.Role;
import ua.validation.annotation.PasswordsEqual;
import ua.validation.annotation.UniqueUser;
import ua.validation.flag.UserFlag;

@PasswordsEqual(message = "Password does not match", groups = {UserFlag.class})
public class RegistrationRequest {

    @UniqueUser(message = "Such an user already exists", groups = UserFlag.class)
    @NotBlank(message = "This field cannot be blank", groups = {UserFlag.class})
    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}| *$", message = "Enter valid email", groups = {
            UserFlag.class})
    private String email;

    @NotBlank(message = "This field cannot be blank", groups = {UserFlag.class})
    @Pattern(regexp = "^[\\w\\d]{4,}| *$", message = "Enter at least 4 symbols", groups = {UserFlag.class})
    private String password;

    @NotBlank(message = "This field cannot be blank", groups = {UserFlag.class})
    private String repeatPassword;

    private String photoUrl;

    private Role role;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}