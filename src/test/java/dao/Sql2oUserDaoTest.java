package dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;


public class Sql2oUserDaoTest {
    private Connection conn;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingUserSetsId() throws Exception {
        User testUser = setupUser();
        assertEquals(1, testUser.getId());
    }

    @Test
    public void getAll() throws Exception {
        User userOne = setupUser();
        User userTwo = setupUser();
        assertEquals(2, userDao.getAll().size());
    }



    @Test
    public void deleteById() throws Exception {
        User userOne = setupUser();
        User userTwo = setupUser();
        assertEquals(2, userDao.getAll().size());
        userDao.deleteById(userOne.getId());
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        User userOne = setupUser();
        User userTwo = setupUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    //helpers

    public User setupUser() {
        User user = new User("Odero", 4);
        userDao.add(user);
        return user;
    }

}