package app.withyou.ahometoshare.model;

import java.util.List;

public class HostDetail {

    private Host host;

    private List<Property> propertyList;

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
