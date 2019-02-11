package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.utils.RestJson;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminRestController {
    @Autowired
    HostService hostService;


    @RequestMapping(value = "/admin/getAllHosts" ,method = RequestMethod.GET)
    public String getAllHosts(){
        RestJson restJson = new RestJson();
        List<Host> hosts= hostService.getAllHosts();
        restJson.setData(hosts);
        return JSONObject.toJSONString(restJson);
    }


}
