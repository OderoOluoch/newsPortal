package models;

import java.util.Objects;

public class News {
    private int id;
    private String name;
    private int deptId;

    public News(String name, int deptId){
        this.name = name;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && deptId == news.deptId && name.equals(news.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deptId);
    }
}
