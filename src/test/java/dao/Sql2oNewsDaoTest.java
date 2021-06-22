package dao;

import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
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

    //helpers
    public News setupNews() {
        News news = new News("Morning Show","We are great to be here today",3,4);
        newsDao.add(news);
        return news;
    }

}