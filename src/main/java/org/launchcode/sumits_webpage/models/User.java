package org.launchcode.sumits_webpage.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//tells us what java class is converted into a persistent data store in mySQL
@Entity
public class User extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @Email
    private String email;

    @NotNull
    private String pwHash;

    private String verificationCode;

    private boolean isVerified;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;

        this.pwHash = encoder.encode(password);
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

    public void setEmail(String email) {this.email = email;}

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }


}
