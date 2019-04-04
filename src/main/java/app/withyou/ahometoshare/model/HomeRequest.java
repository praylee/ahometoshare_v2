package app.withyou.ahometoshare.model;

import java.util.Date;

public class HomeRequest {
    private Integer id;

    private String renter;

    private String renterName;

    private String host;

    private String hostName;

    private String propertyAddress;

    private Date requestTime;

    public HomeRequest(Integer id, String renter, String renterName, String host, String hostName, String propertyAddress, Date requestTime) {
        this.id = id;
        this.renter = renter;
        this.renterName = renterName;
        this.host = host;
        this.hostName = hostName;
        this.propertyAddress = propertyAddress;
        this.requestTime = requestTime;
    }

    public HomeRequest() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter == null ? null : renter.trim();
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName == null ? null : renterName.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress == null ? null : propertyAddress.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}