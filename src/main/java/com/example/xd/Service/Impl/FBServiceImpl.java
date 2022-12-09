package com.example.xd.Service.Impl;
import com.example.xd.DTO.DeviceDTO;
import com.example.xd.DTO.MarcaPrestamoDTO;
import com.example.xd.DTO.UserDTO;
import com.example.xd.Firebase.FirebaseInitializer;
import com.example.xd.Service.FBService;
import com.google.api.client.json.Json;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FBServiceImpl implements FBService {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<DeviceDTO> devices(){
        List<DeviceDTO> response = new ArrayList<>();
        DeviceDTO device;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

        try {
            for(DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()){
                device = doc.toObject(DeviceDTO.class);
                device.setId(doc.getId());
                response.add(device);
            }

            return response;
        } catch (Exception e){
            return null;
        }
    }

    public UserDTO obtenerCorreo(String codigo){
        List<UserDTO> user = new ArrayList<>();
        //Buscar datos del codigo
        CollectionReference userx = firebase.getFirestore().collection("users");
        // Create a query against the collection.
        Query query = userx.whereEqualTo("codigo",codigo);
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try{
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                user.add(document.toObject(UserDTO.class));
                System.out.printf(document.toObject(UserDTO.class).getCodigo());
            }
            return user.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> marcasList() {
        List<String> listMarcas = new ArrayList<>();
        Map<String, Object> marcaNumber = new HashMap<>();

        DocumentReference docRef = firebase.getFirestore().collection("others").document("marcas");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
            marcaNumber = document.getData();
            marcaNumber.forEach((key,value)->{
                System.out.println(key+"-"+value);
                if((long) value > 0){
                    listMarcas.add(key);
                }
            });
            return listMarcas;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<String>();
        }
    }

    public List<MarcaPrestamoDTO> marcaPrestamoList() throws ExecutionException, InterruptedException {
        List<MarcaPrestamoDTO> marcaPrestamoList = new ArrayList<>();
        ApiFuture<QuerySnapshot> futureQuery = firebase.getFirestore()
                .collection("reservas").whereEqualTo("estado", "Solicitud aceptada").get();
        List<QueryDocumentSnapshot> documents = futureQuery.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            String marca = document.getString("device.marca");
            boolean inList = false;
            for (MarcaPrestamoDTO marcaPrestamoDTO : marcaPrestamoList) {
                if (marcaPrestamoDTO.getMarca().equalsIgnoreCase(marca)) {
                    marcaPrestamoDTO.setPrestamos(marcaPrestamoDTO.getPrestamos() + 1);
                    inList = true;
                    break;
                }
            }
            if (!inList) {
                marcaPrestamoList.add(new MarcaPrestamoDTO(marca, 1));
            }
        }
        return marcaPrestamoList;
    }




    private CollectionReference getCollection(){
        return firebase.getFirestore().collection("devices");
    }

}
