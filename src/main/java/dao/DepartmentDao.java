package dao;

import models.User;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(User user);

    //void addUserToADepartment(User user, Department department);

    //read
    List<User> getAll();

    // List<Department> getAllDepartmentsForAUser(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
