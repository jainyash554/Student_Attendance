/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.student.attendance;

import com.student.attendance.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author YASH KUMAR
 */
public class AttendanceBean1 {
    static Connection conn;
    int aid;
    String senrollno;
    int attotal;
    int atattended;
    double atpercentage;
    int aptotal;
    int apattended;
    double appercentage;
    int aspecial;
    double afinal;

    public AttendanceBean1() {
         try {
            Properties pro = new Properties();
            InputStream in = getClass().getResourceAsStream("Message.properties");
            pro.load(in);
            String userName = pro.getProperty("UserName");
            String password = pro.getProperty("Password");
            String url = pro.getProperty("url");
            Class.forName(pro.getProperty("drivername")).newInstance();
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            System.out.println("Connection Stabished");
//            populate();
        } catch (Exception e) {
            System.out.println("Unable to connect to database.");
        }

    }

    public AttendanceBean1(int aid, String senrollno, int attotal, int atattended, double atpercentage, int aptotal, int apattended, double appercentage, int aspecial, double afinal) {
        this.aid = aid;
        this.senrollno = senrollno;
        this.attotal = attotal;
        this.atattended = atattended;
        this.atpercentage = atpercentage;
        this.aptotal = aptotal;
        this.apattended = apattended;
        this.appercentage = appercentage;
        this.aspecial = aspecial;
        this.afinal = afinal;
    }

    public double getAfinal() {
        return afinal;
    }

    public void setAfinal(double afinal) {
        this.afinal = afinal;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getApattended() {
        return apattended;
    }

    public void setApattended(int apattended) {
        this.apattended = apattended;
    }

    public double getAppercentage() {
        return appercentage;
    }

    public void setAppercentage(double appercentage) {
        this.appercentage = appercentage;
    }

    public int getAptotal() {
        return aptotal;
    }

    public void setAptotal(int aptotal) {
        this.aptotal = aptotal;
    }

    public int getAspecial() {
        return aspecial;
    }

    public void setAspecial(int aspecial) {
        this.aspecial = aspecial;
    }

    public int getAtattended() {
        return atattended;
    }

    public void setAtattended(int atattended) {
        this.atattended = atattended;
    }

    public double getAtpercentage() {
        return atpercentage;
    }

    public void setAtpercentage(double atpercentage) {
        this.atpercentage = atpercentage;
    }

    public int getAttotal() {
        return attotal;
    }

    public void setAttotal(int attotal) {
        this.attotal = attotal;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        AttendanceBean1.conn = conn;
    }

    public String getSenrollno() {
        return senrollno;
    }

    public void setSenrollno(String senrollno) {
        this.senrollno = senrollno;
    }
     public boolean add() {
        int x = 0;

        try {
            java.sql.Statement stmt = conn.createStatement();

            String sql = "Insert into attendance(senrollno,attotal,atattended,atpercentage,aptotal,apattended,appercentage,aspecial,afinalpercentage)  values('"+senrollno+"','" + attotal + "','" +atattended+ "','"+atpercentage+"','" + aptotal + "','" +apattended+ "','"+appercentage+"','" + aspecial + "','" +afinal+ "')";

            int y = 0;
            y = stmt.executeUpdate(sql);
            if (y == 0) {

                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

  public AttendanceBean1 getRecord(String enroll) {
        try {
         //   java.sql.Statement stmt = conn.createStatement();
            String sql = "Select * from attendance where senrollno=?";
                    PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setString(1,enroll);

             ResultSet rs=(ResultSet) pstmt.executeQuery();

            if (rs.next()) {

                setAid(rs.getInt(1));
                setSenrollno(rs.getString(2));
                setAttotal(rs.getInt(3));
                setAtattended(rs.getInt(4));
                setAtpercentage(rs.getDouble(5));
                setAptotal(rs.getInt(6));
                setApattended(rs.getInt(7));
                setAppercentage(rs.getInt(8));
                setAspecial(rs.getInt(9));
                setAfinal(rs.getInt(10));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return this;
    }


    public boolean update() {
        int y = 0;

        try{
             String sql="update attendance set attotal=?,atattended=?,atpercentage=?,aptotal=?,apattended=?,appercentage=?,aspecial=?,afinalpercentage=? where senrollno=?";

             PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setInt(1,attotal);
             pstmt.setInt(2,atattended);
              pstmt.setDouble(3,atpercentage);
             pstmt.setInt(4,aptotal);
              pstmt.setInt(5,apattended);
             pstmt.setDouble(6,appercentage);
              pstmt.setInt(7,aspecial);
             pstmt.setDouble(8,afinal);
             pstmt.setString(9,senrollno);
             int rs=pstmt.executeUpdate();

             if(rs!=0){
                 System.out.println("Yes It is there");
                 return true;
             }
         }
             catch(Exception e){
                 System.out.println("Exception"+e);
               }

        // finally{
          //   c.putConnection(conn);
   return false;
   }

   public int getTotal(int sem) {
        try {
            java.sql.Statement stmt = conn.createStatement();
            String sql = "Select * from attendance where ssem="+sem+" ";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            boolean b = rs.last();
            if (b) {
                return rs.getRow();
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to Fetch total number of rows.");
        }
        return 0;
    }


    










public static void main(String ar[]){
    AttendanceBean1 ab=new AttendanceBean1();

    ab.getRecord("0126it131101");
    //System.out.println(ab.getRecord("0126it131101"));
    //System.out.println(ab.getAttotal());
System.out.print(ab.getAtattended());
}
}
