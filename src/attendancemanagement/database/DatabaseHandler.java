package attendancemanagement.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseHandler 
{
    private static DatabaseHandler handler;
    
    public static final String DB_URL = "jdbc:derby:database/attendance; create = true";
    private static Connection conn = null;
    private static Statement stmt = null;
 
    public DatabaseHandler()
    {
        createConnection();
        setupFacultyTable();
        setupStudentTable();
        setupAttendanceTable();
    }
    
    void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        }
        catch(Exception e)
        {
            System.out.println("Exception caught at createConnection()");
        }
    }
    
    void setupFacultyTable()
    {
        String Faculty_Table = "FACULTY";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, Faculty_Table, null);
            if(tables.next())
            {
                System.out.println("Table " + Faculty_Table + "already exists. Ready to go!");
            }
            else
            {
                stmt.execute("CREATE TABLE "+ Faculty_Table + "("
                        + "username varchar(20) primary key,\n"
                        + "password varchar(20) ,\n"
                        + "firstName varchar(20),\n"
                        + "lastName varchar(20),\n"
                        + "number varchar(13),\n"
                        + "email varchar(30),\n"
                        + "gender varchar(20),\n"
                        + "address varchar(50),\n"
                        + "dob varchar(20),\n"
                        + "subject varchar(200)"
                        + " )");
                System.out.println("successfully created faculty database");
                stmt.execute("INSERT INTO FACULTY VALUES('admin','admin','null','null','null','null','null','null','null','null')");
            }            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"....could not create database");
        }
    }
    
    public ResultSet execQuery(String qu)
    {
        ResultSet rs;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(qu);
        }
        catch(SQLException e)
        {
            System.out.println("Exception at execQuery: databaseHandler "+ e.getLocalizedMessage());
            return null;
        }
        return rs;
    }
    
    public void execUpdate(String qu)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(qu);
        }
        catch(SQLException e)
        {
            System.out.println("Exception at execQuery: databaseHandler "+ e.getLocalizedMessage());
        }
    }
    
    public boolean exec(String qu)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Exception at insertData: databaseHandler "+ e.getLocalizedMessage());
            return false;
        }
    }
    
    void setupStudentTable()
    {
        String Student_Table = "STUDENT";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, Student_Table, null);
            if(tables.next())
            {
                System.out.println("Table " + Student_Table + "already exists. Ready to go!");
            }
            else
            {
                stmt.execute("CREATE TABLE "+ Student_Table + "("
                        + "grno varchar(10) primary key,\n"
                        + "roll int,\n"
                        + "firstName varchar(20),\n"
                        + "lastName varchar(20),\n"
                        + "father varchar(30),\n"
                        + "mother varchar(30),\n"
                        + "number varchar(13),\n"
                        + "email varchar(50),\n"
                        + "gender varchar(10),\n"
                        + "address varchar(50),\n"
                        + "class varchar(2),\n"
                        + "branch varchar(20),\n"
                        + "dob varchar(30)"
                        + " )");
                System.out.println("Succesfully created student table");
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"....Could not create database");
        }
    }
    
    void setupAttendanceTable()
    {
        //long millis = System.currentTimeMillis();
        //java.sql.Date date = new java.sql.Date(millis);
        //String AttendanceTable = date.toString().toUpperCase();
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, "ATTENDANCE", null);
            if(tables.next())
            {
                System.out.println("Table ATTENDANCE already exists. Ready to go!");
            }
            else
            {
                stmt.execute("CREATE TABLE ATTENDANCE("
                                            + "rollNo int,\n"
                                            + "date varchar(10),\n"
                                            + "day varchar(9),\n"
                                            + "slot1 boolean default TRUE ,\n"
                                            + "slot2 boolean default TRUE,\n"
                                            + "slot3 boolean default TRUE,\n"
                                            + "slot4 boolean default TRUE,\n"
                                            + "slot5 boolean default TRUE,\n"
                                            + "slot6 boolean default TRUE,\n"
                                            + "total int"
                                            +")");
                System.out.println("Succesfully created attendance table for ATTENDANCE");
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"....Could not create database");
        }
    }
}
