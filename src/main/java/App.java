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

        String connectionString = "jdbc:h2:~/jadle1.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        //Home url
        get("/", "application/json", (req, res) -> {
            String welcome = "Welcome to the news portal";
            res.status(201);
            res.type("application/json");
            return gson.toJson(welcome);
        });


        //End points for departments

        //Create Department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });

        //Read all departments
        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(departmentDao.getAll());//send it back to be displayed
        });

        //Get department by Id
        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });



        //End points for users

        //Create User
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        //Get All existing Users
        get("/users", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(userDao.getAll());//send it back to be displayed
        });

        //Get user by Id.
        get("/users/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(userDao.findById(userId));
        });


        //End points for news

        //Create news
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        //Get all news
        get("/news", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(newsDao.getAll());//send it back to be displayed
        });

        //Get News by Id
        get("/news/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int newsId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(newsDao.findById(newsId));
        });

    }
}
