package com.example.Assigment4;


import com.example.Assigment4.data.PostgresDB;
import com.example.Assigment4.data.interfaces.IDB;
import com.example.Assigment4.repositories.MedicineRepository;
import com.example.Assigment4.repositories.interfaces.IMedicineRepository;
import org.glassfish.jersey.internal.inject.AbstractBinder;


public class MyApplicationBinder extends AbstractBinder{
    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDB.class);
        bind(MedicineRepository.class).to(IMedicineRepository.class);
    }
}
