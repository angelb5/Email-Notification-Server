package com.example.xd.Service;

import com.example.xd.DTO.ReservaDTO;
import com.example.xd.DTO.UserDTO;

import java.util.concurrent.ExecutionException;

public interface FBService {

    UserDTO obtenerCorreo(String id) throws ExecutionException, InterruptedException;

    ReservaDTO obtenerReserva(String id) throws ExecutionException, InterruptedException;
}
