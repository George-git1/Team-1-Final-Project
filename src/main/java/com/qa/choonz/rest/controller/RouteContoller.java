package com.qa.choonz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin("http://127.0.0.1:5500")
@Controller
public class RouteContoller {
    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "index.html";
    }

    @GetMapping(value = "/tracks")
    public String tracks() {
        return "tracks.html";
    }
    
    @GetMapping(value = "/albums")
    public String albums() {
        return "album.html";
    }
    
    @GetMapping(value = "/albums/view/{albumId}")
    public String albumview(@PathVariable Long albumId) {
    	
    	return "http://localhost:81/album.html?id"+albumId;

    }

}
