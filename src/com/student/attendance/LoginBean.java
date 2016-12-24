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
public class LoginBean {
    static Connection conn;
    ResultSet rs=null;
    PreparedStatement psmt;
    int lid;
    String username;
    String password;




    public LoginBean() {
        int lid=0;
        username=new String();
        password=new String();
        try{
            Properties pro=new Properties();
            InputStream in=getClass().getResourceAsStream("Message.properties");
            pro.load(in);
            String username=pro.getProperty("UserName");
            String password=pro.getProperty("Password");
            String url=pro.getProperty("url");
            Class.forName(pro.getProperty("drivername")).newInstance();
            conn=DriverManager.getConnection(url,username,password);
             System.out.println("Connection Stabished");
//            populate();
        } catch (Exception e) {
            System.out.println("Unable to connect to database.");
        }

    }

    public LoginBean(int lid, String username, String password) {
        this.lid = lid;
        this.username = username;
        this.password = password;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
/*public boolean add(String username,String password) {
        int x = 0;


        try {
            java.sql.Statement stmt = conn.createStatement();
           // int cid = ((Integer) ids.elementAt(courseid)).intValue();
                   String sql="select * From login where username=? and password=?";
        PreparedStatement psmt=new PreparedStatement(sql);
            stmt=conn.prepareStatement(sql);
           // stmt.setString(1,username);
            //stmt.setString(2,getPassword());
            rs=stmt.executeQuery();
 }
   .0      catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
 */
   public boolean update() {
        int y = 0;

        try{
             String sql="update login set username=?,password=? where lid=?";

             PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setString(1,username);
             pstmt.setString(2,password);
             pstmt.setInt(3,lid);

             int rs=pstmt.executeUpdate();

             if(rs!=0){
                 System.out.println("Yes It is there");
                 return true;
             }
         }
             catch(Exception e){
                 System.out.println("Exception"+e);
               }
        return false;

    }


 public boolean checkAvailability(String uname,String pwd){
         int st=0;
         //ConnectionPool c=ConnectionPool.getInstance();
        // c.initialize();
      //   Connection conn=c.getConnection();
         try{
             String sql="select * from login where username=? and password=?";

             PreparedStatement pstmt=conn.prepareStatement(sql);
             pstmt.setString(1,uname);
             pstmt.setString(2,pwd);
             ResultSet rs=pstmt.executeQuery();

             if(rs.next()){
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


public LoginBean getRecord() {
        try {
            java.sql.Statement stmt = conn.createStatement();
            String sql = "Select * from login ";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
//Vector p=new Vector();
               // Sid(rs.getInt(1));

                setLid(Integer.parseInt(rs.getString(1)));
                setUsername(rs.getString(2));
                setPassword(rs.getString(3));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return this;
    }














    public static void main(String ar[]){
    
    }

}
