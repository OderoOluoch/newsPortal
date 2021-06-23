package dao;

import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);
    void addDepartmentToNews(Department department,News news);

    //read
    List<Department> getAll();
    List<Department> getAllDepartmentsForANews(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
