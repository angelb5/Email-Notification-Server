package com.example.xd.DTO;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO {
    private ClienteUser clienteUser;
    private TIUser tiUser;
    private Device device;
    private String motivoReserva;
    private String curso;
    private Integer tiempoReserva;
    private List<String> Programas;
    private String dni;
    private String otros;
    private GeoPoint lugarRecojo;
    private String nombreLugarRecojo;
    private String motivoRechazo;
    private String estado;
    private Timestamp horaReserva;
    private Timestamp horaFinReserva;
    private Timestamp horaRespuesta;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ClienteUser{
        private String nombre;
        private String uid;
        private String avatarUrl;
        private String rol;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class TIUser {
        private String nombre;
        private String uid;
        private String avatarUrl;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Device {
        private String modelo;
        private String marca;
        private String fotoPrincipal;
        private String categoria;
        private String uid;
    }
}
