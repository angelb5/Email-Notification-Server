package com.example.xd.Controller;

import com.example.xd.DTO.DeviceDTO;
import com.example.xd.DTO.UserDTO;
import com.example.xd.Service.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "")

public class FBController {
    @Autowired
    private FBService fbService;

    @GetMapping(value = "/devices")
    public List<DeviceDTO> devicesList(){
        return fbService.devices();

    }

    @GetMapping(value = "/user/{codigo}",produces= MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,String> usersList(@PathVariable(value = "codigo") String codigo){
        UserDTO user;
        HashMap<String,String> map = new HashMap<>();
        if((user = fbService.obtenerCorreo(codigo))!=null){
            map.put("correo",user.getCorreo());
            map.put("found","true");
            return map;
        }else{
            map.put("msg","No se encuentra registrado.");
            map.put("found","false");
            return map;
        }
    }

    @GetMapping(value = "/marcas")
    public HashMap<String, List<String>> marcasList(){
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("marcas",fbService.marcasList());
        return map;
    }
}