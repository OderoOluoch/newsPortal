package dao;

import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        Department departmentOne = setupDepartment();
        assertEquals(1, departmentOne.getId());
    }

    @Test
    public void getAll() throws Exception {
        Department departmentOne = setupDepartment();
        Department departmentTwo = setupDepartment();
        assertEquals(2, departmentDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        Department departmentOne = setupDepartment();
        Department departmentTwo = setupDepartment();
        assertEquals(2, departmentDao.getAll().size());
        departmentDao.deleteById(departmentOne.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Department departmentOne = setupDepartment();
        Department departmentTwo = setupDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

    //helpers
    public Department setupDepartment() {
        Department department = new Department("Marketing");
        departmentDao.add(department);
        return department;
    }

}