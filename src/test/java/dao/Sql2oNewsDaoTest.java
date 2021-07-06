package dao;

import models.Department;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News newsOne = setupNews();
        assertEquals(1, newsOne.getId());
    }

    @Test
    public void getAll() throws Exception {
        News newsOne = setupNews();
        News newsTwo = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }


    @Test
    public void deleteById() throws Exception {
        News newsOne = setupNews();
        News newsTwo = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(newsOne.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News newsOne = setupNews();
        News newsTwo = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }


    @Test
    public void NewsReturnsDepartmentCorrectly() throws Exception {
        Department testDepartment  = new Department("Marketing");
        Department altDepartment = new Department("Senior Management");
        departmentDao.add(testDepartment);
        departmentDao.add(altDepartment);

        News testNews = setupNews();

        newsDao.add(testNews);

        newsDao.addNewsToADepartment(testNews,testDepartment);
        newsDao.addNewsToADepartment(testNews,altDepartment);

        Department[] departments = {testDepartment,altDepartment}; //oh hi what is this? Observe how we use its assertion below.

        assertEquals(Arrays.asList(departments), newsDao.getAllDepartmentsForNews(testNews.getId()));
    }

    //helpers
    public News setupNews() {
        News news = new News("Morning Show","We are great to be here today",3);
        newsDao.add(news);
        return news;
    }
    public News setupAltNews() {
        News news = new News("Evening show","We are great to be out",1);
        newsDao.add(news);
        return news;
    }

}