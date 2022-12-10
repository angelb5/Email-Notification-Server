package com.example.xd.Service.Impl;
import com.example.xd.DTO.ReservaDTO;
import com.example.xd.DTO.UserDTO;
import com.example.xd.Firebase.FirebaseInitializer;
import com.example.xd.Service.FBService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FBServiceImpl implements FBService {
    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public UserDTO obtenerCorreo(String uid) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("users").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(UserDTO.class);
    }

    @Override
    public ReservaDTO obtenerReserva(String id) throws ExecutionException, InterruptedException {
        Firestore db = firebase.getFirestore();
        DocumentReference docRef = db.collection("reservas").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (!document.exists()) return null;
        return document.toObject(ReservaDTO.class);
    }

}
