package dao;


import models.News;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);

    //void addUserToADepartment(User user, Department department);

    //read
    List<News> getAll();

    // List<Department> getAllDepartmentsForAUser(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
