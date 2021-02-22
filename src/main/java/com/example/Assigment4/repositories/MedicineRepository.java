package com.example.Assigment4.repositories;

import com.example.Assigment4.data.interfaces.IDB;
import com.example.Assigment4.entities.Medicine;
import com.example.Assigment4.repositories.interfaces.IMedicineRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class MedicineRepository implements IMedicineRepository{
    @Inject
    private IDB db;

    @Override
    public List<Medicine> searchMedicineByName(String name) {
        Connection connection = null;
        name = "%"+name+"%";
        try {
            connection = db.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM public.medicines WHERE name LIKE ?");
            sql.setString(1,name);
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(String.valueOf(sql));

            List<Medicine> medicines = new LinkedList<>();

            while (resultSet.next()) {
                Medicine medicine = new Medicine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("manufacturer"),
                        resultSet.getString("restrictions"));

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Medicine getMedicineById(int id) {
        Connection connection = null;

        try {
            connection = db.getConnection();

            String sql = "SELECT * FROM medicines WHERE id=?";

            PreparedStatement st=connection.prepareStatement(sql);

            st.setInt(1,id);
            ResultSet resultSet = st.executeQuery();

            Medicine medicine = new Medicine();

            if (resultSet.next()) {
                medicine.setId(resultSet.getInt("id"));
                medicine.setName(resultSet.getString("name"));
                medicine.setPrice(resultSet.getDouble("price"));
                medicine.setExpirationDate(resultSet.getDate("expirationDate").toLocalDate());
                medicine.setManufacturer(resultSet.getString("manufacturer"));
                medicine.setRestrictions(resultSet.getString("restrictions"));
            }

            return medicine;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        Connection connection = null;

        try {
            connection = db.getConnection();

            String sql = "INSERT INTO medicines (name, price, expirationdate, manufacturer, restrictions) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, medicine.getName());
            preparedStatement.setDouble(2, medicine.getPrice());
            preparedStatement.setDate(3, Date.valueOf(medicine.getExpirationDate()));
            preparedStatement.setString(4, medicine.getManufacturer());
            preparedStatement.setString(5,medicine.getRestrictions());
            preparedStatement.execute();

            return true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean removeMedicineById(int id) {
        Connection connection = null;

        try {
            connection = db.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medicines WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            return true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }


}
