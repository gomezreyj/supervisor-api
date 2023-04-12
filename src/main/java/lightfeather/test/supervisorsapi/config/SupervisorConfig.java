package lightfeather.test.supervisorsapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="app")
@Configuration("appConfig")
public class SupervisorConfig {

    private String getSupervisorsURL;
    private String missingFirstName;
    private String missingLastName;
    private String missingSupervisor;
    private String successfully;

    public String getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(String successfully) {
        this.successfully = successfully;
    }

    public String getSuccess() {
        return successfully;
    }

    public void setSuccess(String success) {
        this.successfully = success;
    }

    public String getMissingLastName() {
        return missingLastName;
    }

    public void setMissingLastName(String missingLastName) {
        this.missingLastName = missingLastName;
    }

    public String getMissingSupervisor() {
        return missingSupervisor;
    }

    public void setMissingSupervisor(String missingSupervisor) {
        this.missingSupervisor = missingSupervisor;
    }

    public String getMissingFirstName() {
        return missingFirstName;
    }

    public void setMissingFirstName(String missingFirstName) {
        this.missingFirstName = missingFirstName;
    }

    public String getGetSupervisorsURL() {
        return getSupervisorsURL;
    }

    public void setGetSupervisorsURL(String getSupervisorsURL) {
        this.getSupervisorsURL = getSupervisorsURL;
    }

}
