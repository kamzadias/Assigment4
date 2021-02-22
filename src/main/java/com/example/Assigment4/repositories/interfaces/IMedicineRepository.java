package com.example.Assigment4.repositories.interfaces;

import com.example.Assigment4.entities.Medicine;

import java.util.List;

public interface IMedicineRepository {
    Medicine getMedicineById(int id);
    boolean addMedicine(Medicine medicine);
    boolean removeMedicineById(int id);
    List<Medicine> searchMedicineByName(String name);
}
