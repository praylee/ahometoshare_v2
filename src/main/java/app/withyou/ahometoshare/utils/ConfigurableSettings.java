package app.withyou.ahometoshare.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("globalsettings")
public class ConfigurableSettings {

    private String emailAddress;

    private String emailPassword;

    private String adminEmailAddress;

    private Integer emailThreadPoolSize;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getAdminEmailAddress() {
        return adminEmailAddress;
    }

    public void setAdminEmailAddress(String adminEmailAddress) {
        this.adminEmailAddress = adminEmailAddress;
    }

    public Integer getEmailThreadPoolSize() {
        return emailThreadPoolSize;
    }

    public void setEmailThreadPoolSize(Integer emailThreadPoolSize) {
        this.emailThreadPoolSize = emailThreadPoolSize;
    }
}
