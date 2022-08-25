package com.duybinh.qlsv;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static String DB_URL = "jdbc:mysql://localhost:3306/db01";
    private static String USER_NAME = "root";
    private static String PASSWORD = "duybinh03";
    public static ArrayList <Employee> EmpList = new ArrayList<>();

    public static void main(String[] args) throws Exception {


    //Create Employee Object:
        Employee empObj1 = new Employee("Binh01", 001, "C3", 8 );
        Employee empObj2 = new Employee("Binh02", 002, "C2", 9 );
        Employee empObj3 = new Employee("Binh03", 003, "C1", 10 );
        Employee empObj4 = new Employee("Binh04", 004, "C0", 11 );
        Employee empObj5 = new Employee("Binh05", 005, "C4", 12 );
        Employee empObj6 = new Employee("Binh06", 005, "C5", 13 );
        Employee empObj7 = new Employee("Binh07", 005, "C6", 14 );
        Employee empObj8 = new Employee("Binh08", 005, "C7", 15 );

    //Create EmpList List:
        EmpList.add(empObj1);
        EmpList.add(empObj2);
        EmpList.add(empObj3);
        EmpList.add(empObj4);
        EmpList.add(empObj5);





    //Create InsertManyEmp List:
        ArrayList<Employee> InsertManyEmp = new ArrayList<>();
        InsertManyEmp.add(empObj6);
        InsertManyEmp.add(empObj7);
        InsertManyEmp.add(empObj8);

    //Create DeleteManyEmp List:
        ArrayList<Employee> DeleteManyEmp = new ArrayList<>();
        DeleteManyEmp.add(empObj1);
        DeleteManyEmp.add(empObj2);
        DeleteManyEmp.add(empObj3);


    //Call Method:
//        CreateEmployeeTable();
//        InsertEmployee(EmpList);
//        UpdateEmployee(2);
//        DeleteEmployee(DeleteManyEmp);
//        searchById(4);
//        searchAll("Binh05");


    }


    //Create CreateEmployeeTable method:
    public static void CreateEmployeeTable() throws Exception {
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            PreparedStatement pstmt = conn.prepareStatement("CREATE table IF NOT EXISTS Employee(id INT(20) not null, name varchar(200) not null, level varchar(200), salary INT(20),   primary key (id)); ");
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function Create Completed.");
        }
    }



    //Create InsertEmployee method:

    public static void InsertEmployee(ArrayList <Employee> EmpList) throws Exception {
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sqlInsert = "Insert into Employee(name,id,level,salary)  values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
        int count = 0;
        for (Employee emp : EmpList)
        {
            pstmt.setString(1,emp.getName());
            pstmt.setInt(2,emp.getId());
            pstmt.setString(3,emp.getLevel());
            pstmt.setInt(4,emp.getSalary());
            pstmt.addBatch();
            count++;
        }
            if (count % 100 == 0 || count == EmpList.size()) {
                pstmt.executeBatch();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function Insert Completed.");
        }
    }



    //Create UpdateEmployee method:
    public static void UpdateEmployee(int number) throws Exception {
        try {

            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sqlSelect = "Select * from Employee where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            pstmt.setInt(1,number);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("Record is not exists");
            }else{
                Connection connAfter = getConnection(DB_URL, USER_NAME, PASSWORD);
                String sqlUpdate = "Update Employee set name = ?, id = ?,  level = ?, salary = ? where id = ?";
                PreparedStatement pstmtAfter = connAfter.prepareStatement(sqlUpdate);
                pstmtAfter.setString(1,"Dat");
                pstmtAfter.setInt(2,9);
                pstmtAfter.setString(3,"ProVjp");
                pstmtAfter.setInt(4,100);
                pstmtAfter.setInt(5,number);

                pstmtAfter.executeUpdate();
                pstmtAfter.close();
                System.out.println("1 row updated");

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function Insert Completed.");
        }
    }


    //Create DeleteEmployee method:
    public static void DeleteEmployee(ArrayList<Employee> DeleteManyEmp ) {
        try {

            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String sqlSelect = "Delete from Employee where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);


            int count = 0;
            for (Employee empEmp: EmpList){
                for (Employee empDelete: DeleteManyEmp){
                    pstmt.setInt(1,empDelete.getId());
                    if (empEmp.getId().equals(empDelete.getId())){
                        pstmt.addBatch();
                        count++;
                    }
                }if (count % 100 == 0 || count == DeleteManyEmp.size()){
//
                    pstmt.executeBatch();
//
                }else{
                    System.out.println("Records were deleted or weren't exists");
                }
            }




        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function Delete Complete");
        }
    }




    //Create searchById method:

    public static void searchById(int number) {

        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            String selectSql = "Select *  from Employee where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(selectSql);
            pstmt.setInt(1,number);
            pstmt.executeQuery();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " +  rs.getInt(4));
            }
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function selectByName Complete");
        }
    }


    //Create searchAll method:

    public static void searchAll(String name){
        try{
            Connection conn = getConnection(DB_URL,USER_NAME,PASSWORD);
            String sqlSearchAll = "Select * from Employee ";
            PreparedStatement pstmt = conn.prepareStatement(sqlSearchAll);
            if (name == null){
                pstmt.executeQuery();
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " +  rs.getInt(4));
                }


            }else{
                String sqlSearchByName = "Select * from Employee where name = ?";
                PreparedStatement pstmtName = conn.prepareStatement(sqlSearchByName);
                pstmtName.setString(1,name);
                pstmtName.executeQuery();
                ResultSet rs = pstmtName.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2)+ " " + rs.getString(3)+ " " +  rs.getInt(4));
                }
                pstmtName.close();
            }
            pstmt.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }



    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }


}
