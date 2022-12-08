package com.example.xd.Service;

import com.example.xd.DTO.DeviceDTO;
import com.example.xd.DTO.UserDTO;

import java.util.List;

public interface FBService {
    List<DeviceDTO> devices();
    UserDTO obtenerCorreo(String correo);

    List<String> marcasList();
}
