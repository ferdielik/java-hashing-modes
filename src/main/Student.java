package main;

public class Student {
    private Integer id;
    private String name;
    private String surname;

    public Student() {

    }

    public Student(String text) {
        String[] parts = text.split(";");

        this.id = Integer.valueOf(parts[2]);
        this.name = parts[0];
        this.surname = parts[1];
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return id + ";" + name + ";" + surname;
    }
}
