package com.example.Assigment4.controllers;

import com.example.Assigment4.entities.Medicine;
import com.example.Assigment4.repositories.interfaces.IMedicineRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("medicines")
public class MedicineController {
    @Inject
    private IMedicineRepository repo;
    @GET
    public String sayHello(){
        return "Hello World!";
    }
    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchMedicineByName(@PathParam("name") String name) {
        List<Medicine> medicines;

        try {
            medicines = repo.searchMedicineByName(name);
        } catch (ServerErrorException ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getMedicineById(@PathParam("id") int id) {
//        Medicine medicine;
//        try {
//            medicine = repo.getMedicineById(id);
//        } catch (ServerErrorException ex) {
//            return Response
//                    .status(500).entity(ex.getMessage()).build();
//        }
//
//        if (medicine == null) {
//            return Response
//                    .status(Response.Status.NOT_FOUND)
//                    .entity("Medicine does not exist!")
//                    .build();
//        }
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(medicine)
//                .build();
//    }
//
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicine(Medicine medicine) {
        LocalDate expirationDate = LocalDate.now();
        boolean created;
        try {
            created = repo.addMedicine(medicine);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Medicine cannot be created!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Medicine created successfully!")
                .build();

}
//    @DELETE
//    @Path("/{removebyid}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response removeMedicineById(@PathParam("removebyid") int id) {
//        boolean removedByID;
//        try {
//            removedByID = repo.removeMedicineById(id);
//        } catch (ServerErrorException ex) {
//            return Response.serverError().entity(ex.getMessage()).build();
//        }
//
//        if (!removedByID) {
//            return Response
//                    .status(Response.Status.BAD_REQUEST)
//                    .entity("Medicine not removed.Please try again by following the instructions")
//                    .build();
//        }
//
//        return Response
//                .status(Response.Status.CREATED)
//                .entity("Medicine successfully removed!")
//                .build();
//    }
}
