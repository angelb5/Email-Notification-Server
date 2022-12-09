package com.example.xd.Service;

import com.example.xd.DTO.DeviceDTO;
import com.example.xd.DTO.MarcaPrestamoDTO;
import com.example.xd.DTO.UserDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FBService {
    List<DeviceDTO> devices();
    UserDTO obtenerCorreo(String correo);

    List<String> marcasList();

    List<MarcaPrestamoDTO> marcaPrestamoList() throws ExecutionException, InterruptedException;
}
