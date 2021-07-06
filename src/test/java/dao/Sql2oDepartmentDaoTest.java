package dao;

import models.Department;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
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

    @Test
    public void addDepartmentTypeToNewsTypeCorrectly() throws Exception {

        News testNews = setupNews();
        News altNews = setupAltNews();

        newsDao.add(testNews);
        newsDao.add(altNews);

        Department testDepartment = setupDepartment();

        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToNews(testDepartment,testNews);
        departmentDao.addDepartmentToNews(testDepartment,altNews);


        assertEquals(2, departmentDao.getAllNewsForADepartment(testDepartment.getId()).size());
    }



    @Test
    public void deleteingNewsAlsoUpdatesJoinTable() throws Exception {
        Department testDepartment  = new Department("Marketing");
        departmentDao.add(testDepartment);

        News testNews = setupNews();
        newsDao.add(testNews);

        News altNews = setupAltNews();
        newsDao.add(altNews);
        newsDao.addNewsToADepartment(testNews,testDepartment);
        newsDao.addNewsToADepartment(altNews,testDepartment);

        newsDao.deleteById(testNews.getId());

        assertEquals(0, newsDao.getAllDepartmentsForNews(testNews.getId()).size());
    }


    //helpers
    public Department setupDepartment() {
        Department department = new Department("Marketing");
        departmentDao.add(department);
        return department;
    }

    public News setupNews() {
        News news = new News("Morning Show","We are great to be here today",3);
        newsDao.add(news);
        return news;
    }
    public News setupAltNews() {
        News news = new News("Evening show","We are great to be out",3);
        newsDao.add(news);
        return news;
    }

}