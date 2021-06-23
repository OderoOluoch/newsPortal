import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao userDao;

        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();


        //End points for departments
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });

        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(departmentDao.getAll());//send it back to be displayed
        });







        //End points for users
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        get("/users", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(userDao.getAll());//send it back to be displayed
        });

        //End points for users
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        get("/news", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(newsDao.getAll());//send it back to be displayed
        });

    }
}
