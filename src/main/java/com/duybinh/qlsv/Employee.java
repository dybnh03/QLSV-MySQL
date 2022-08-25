package com.duybinh.qlsv;

public class Employee {
    private String name;
    private Integer id;
    private String level;
    private int salary;

    public Employee(String newName, Integer newId, String newLevel, int newSalary) {
        this.name = newName;
        this.id = newId;
        this.level = newLevel;
        this.salary = newSalary;
    }

    ;

    public void setName(String newName) {

        this.name = newName;

    }

    public void setId(Integer newId) {
        this.id = newId;
    }

    public void setLevel(String newLevel) {
        this.level = newLevel;
    }

    public void setSalary(int newSalary) {
        this.salary = newSalary;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }


}
