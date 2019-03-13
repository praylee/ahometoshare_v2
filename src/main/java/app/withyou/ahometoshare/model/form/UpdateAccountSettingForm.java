package app.withyou.ahometoshare.model.form;

import javax.validation.constraints.*;

public class UpdateAccountSettingForm {

    @NotNull
    @Email
    @NotBlank
    private String email;
    @NotNull
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Must not be less than 6 characters and have at least 1 upper case, 1 lowercase letter")
    private String oldPassword;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Must not be less than 6 characters and have at least 1 upper case, 1 lowercase letter")
    private String newPassword;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Must not be less than 6 characters and have at least 1 upper case, 1 lowercase letter")
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue
    public boolean passwordEqual(){
        return this.newPassword.equals(this.confirmPassword);
    }

    @Override
    public String toString() {
        return "UpdateAccountSettingForm{" +
                "email='" + email + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
