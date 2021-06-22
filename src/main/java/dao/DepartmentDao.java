package dao;

import models.Department;


import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);

    //void addUserToADepartment(User user, Department department);

    //read
    List<Department> getAll();

    // List<Department> getAllDepartmentsForAUser(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
