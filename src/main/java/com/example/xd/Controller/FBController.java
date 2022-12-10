package com.example.xd.Controller;

import com.example.xd.DTO.ReservaDTO;
import com.example.xd.DTO.UserDTO;
import com.example.xd.Service.EmailService;
import com.example.xd.Service.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "")

public class FBController {
    @Autowired
    private FBService fbService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/enviarCorreo/{reservaId}")
    public HashMap<String, Object> marcasPrestamoList(@PathVariable(value = "reservaId") String reservaId){
        HashMap<String, Object> map = new HashMap<>();
        try {
            ReservaDTO reserva = fbService.obtenerReserva(reservaId);
            if (reserva == null) {
                map.put("status", "error");
                throw new Exception();
            }
            UserDTO userDTO = fbService.obtenerCorreo(reserva.getClienteUser().getUid());
            emailService.correoSolicitud(reserva, userDTO.getCorreo());
            map.put("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "error");
        }
        return map;
    }


}