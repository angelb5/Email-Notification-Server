package com.example.xd.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Data
public class DeviceDTO {
    private String id;
    private String accesorios;
    private String categoria;
    private String descripcion;
    private int disponibles;
    private int enPrestamo;
    private ArrayList<String> fotosUrl;
    private String marca;
    private String modelo;
    private ArrayList<String> searchKeywords;
    private String searchMarca;
    private int stock;
}
