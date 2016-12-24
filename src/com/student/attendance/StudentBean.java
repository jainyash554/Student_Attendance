/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.student.attendance;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author YASH KUMAR
 */
public class StudentBean {
    Connection conn;
     Vector ids;
    Vector courses;

    int sid;
    String senrollno;
    String sfirstname;
    String slastname;
    String sfname;
    String sbranch;
    int ssem;
    String smobile;
    String semail;
    String saddress;

    public StudentBean() {
        int sid;
        senrollno=new String();
        sfirstname=new String();
        slastname=new String();
        sfname=new String();
        sbranch=new String();
        int ssem;
        smobile=new String();
        semail=new String();
        saddress=new String();
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

    public StudentBean(int sid, String senrollno, String sfirstname, String slastname, String sfname, String sbranch, int ssem, String smobile, String semail, String saddress) {
        this.sid = sid;
        this.senrollno = senrollno;
        this.sfirstname = sfirstname;
        this.slastname = slastname;
        this.sfname = sfname;
        this.sbranch = sbranch;
        this.ssem = ssem;
        this.smobile = smobile;
        this.semail = semail;
        this.saddress = saddress;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSbranch() {
        return sbranch;
    }

    public void setSbranch(String sbranch) {
        this.sbranch = sbranch;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getsenrollno() {
        return senrollno;
    }

    public void setsenrollno(String senrollno) {
        this.senrollno = senrollno;
    }

    public String getSfirstname() {
        return sfirstname;
    }

    public void setSfirstname(String sfirstname) {
        this.sfirstname = sfirstname;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSlastname() {
        return slastname;
    }

    public void setSlastname(String slastname) {
        this.slastname = slastname;
    }

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public int getSsem() {
        return ssem;
    }

    public void setSsem(int ssem) {
        this.ssem = ssem;
    }



    public boolean add() {
        int x = 0;

        try {
            java.sql.Statement stmt = conn.createStatement();

            String sql = "Insert into student(senrollno,sfirstname,slastname,sfname,sbranch,ssem,smobile,semail,saddress)  values('"+senrollno+"','" + sfirstname + "','" +slastname+ "','"+sfname+"','" + sbranch + "','" +ssem+ "','"+smobile+"','" + semail + "','" +saddress+ "')";

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

  public boolean update() {
        int y = 0;

        try{
             String sql="update student set sfirstname=?,slastname=?,sfname=?,sbranch=?,ssem=?,smobile=?,semail=?,saddress=? where senrollno=?";

             PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setString(1,sfirstname);
             pstmt.setString(2,slastname);
              pstmt.setString(3,sfname);
             pstmt.setString(4,sbranch);
              pstmt.setInt(5,ssem);
             pstmt.setString(6,smobile);
              pstmt.setString(7,semail);
             pstmt.setString(8,saddress);
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
            String sql = "Select * from student where ssem="+sem+" ";
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



    public StudentBean getRecord(int num, int sem) {
        try {
            java.sql.Statement stmt = conn.createStatement();
            String sql = "Select * from student where ssem="+sem+" order by senrollno Limit " + (num - 1) + ", 1";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
//Vector p=new Vector();
                setSid(rs.getInt(1));
                setsenrollno(rs.getString(2));

                setSfirstname(rs.getString(3));
                setSlastname(rs.getString(4));
                setSfname(rs.getString(5));
            setSbranch(rs.getString(6));
                setSsem(Integer.parseInt(rs.getString(7)));
                setSmobile(rs.getString(8));
            setSemail(rs.getString(9));
                setSaddress(rs.getString(10));
                //setSfname(rs.getString(4));





            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return this;
    }







  public StudentBean getRecord(String enroll) {
        try {
         //   java.sql.Statement stmt = conn.createStatement();
            String sql = "Select * from student where senrollno=?";
                    PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setString(1,enroll);
            // pstmt.setString(2,pwd);
             ResultSet rs=(ResultSet) pstmt.executeQuery();

            if (rs.next()) {
//Vector p=new Vector();
                setSid(rs.getInt(1));
                setsenrollno(rs.getString(2));

                setSfirstname(rs.getString(3));
                setSlastname(rs.getString(4));
                setSfname(rs.getString(5));
            setSbranch(rs.getString(6));
                setSsem(Integer.parseInt(rs.getString(7)));
                setSmobile(rs.getString(8));
            setSemail(rs.getString(9));
                setSaddress(rs.getString(10));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return this;
    }





public boolean checkAvailablity(String enroll){
         int st=0;
         //ConnectionPool c=ConnectionPool.getInstance();
        // c.initialize();
      //   Connection conn=c.getConnection();
         try{
             String sql="select * from student where senrollno=? ";

             PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
             pstmt.setString(1,enroll);
            // pstmt.setString(2,pwd);
             ResultSet rs=(ResultSet) pstmt.executeQuery();

             if(rs.next()){
//                 System.out.println("Yes It is there");
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






public Vector getRecords(String name) {
        Vector rows = new Vector();
        try {
            Statement stmt = (Statement) conn.createStatement();
            String sql = "SELECT  * FROM student where  sfirstname like '%" + name + "%' or slastname like '%" + name + "%' or senrollno like '%" + name + "%' order by senrollno";
            System.out.println(sql);
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);

            while (rs.next()) {
                Vector temp = new Vector();
                temp.addElement(rs.getString(2));
               // System.out.println(rs.getString(2));
                temp.addElement(rs.getString(3));
                temp.addElement(rs.getString(4));
                temp.addElement(rs.getString(5));
                temp.addElement(rs.getString(6));
                temp.addElement(rs.getString(7));
                temp.addElement(rs.getString(8));
                temp.addElement(rs.getString(9));
                temp.addElement(rs.getString(10));
                //int i = ids.indexOf(rs.getString(2));
                AttendanceBean1 ab = new AttendanceBean1();
                ab = ab.getRecord(rs.getString(2));
               // System.out.println(i);
              //  temp.addElement(rs.getInt(2));
                temp.addElement(ab.getAttotal());
                temp.addElement(ab.getAtattended());
                temp.addElement(ab.getAtpercentage());
                temp.addElement(ab.getAptotal());
                temp.addElement(ab.getApattended());
                temp.addElement(ab.getAppercentage());
                temp.addElement(ab.getAspecial());
                temp.addElement(ab.getAfinal());
                rows.addElement(temp);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Here One");
            return null;
        }
        return rows;
    }



public Vector getColumns() {
        Vector columns = new Vector();
        columns.add("Enrollment No");
        columns.add("First Name");
        columns.add("Last Name");
        columns.add("Father`s Name");
          columns.add("Branch");
         columns.add("Sem");
        columns.add("Mobile");
        columns.add("Email");
         columns.add("Address");

        columns.add("Total theory");
        columns.add("Theory Attended");
          columns.add("Theory %");
            columns.add("Total Lab");
              columns.add("Lab Attended");
                columns.add("Lab %");
                  columns.add("Special");
                    columns.add("Final %");
        return columns;
    }








public static void main(String sr[]){
    StudentBean s=new StudentBean();
    s.getRecords("0126it131101");
  //  s.getRecord(4,6);
   //System.out.print(s.getRecord(yash));
    System.out.print(s.getSfname());

}
}
