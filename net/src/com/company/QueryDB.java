package com.company;

import java.sql.*;
import java.util.ArrayList;

public class QueryDB {
    private static Connection con;
    private static Statement st=null;
    private static ResultSet rs=null;
    private static QueryDB queryDB;

    private QueryDB(){
        connectDB();
    }

    public static QueryDB getInstance(){
        if(queryDB==null){
            queryDB=new QueryDB();
        }
        return queryDB;
    }

    private static void connectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://8.130.17.4:3306/timing?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
            String user="root",password="root";
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean LoginApp(String line){
        String phoneNumber=line.split("/")[0];
        String passWord= line.split("/")[1];
        try{
            String sql="select * from account where phoneNumber="+phoneNumber+"and password="+passWord;
            st= con.prepareStatement(sql);
            rs=st.executeQuery(sql);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

//    public Account LoginApp(String phoneNumber, String passWord) {
//        try{
//            Account account=new Account();
//            account.setM_id(0);
//            String sql="select * from account where phoneNumber="+phoneNumber+"and password="+passWord;
//            st=con.prepareStatement(sql);
//            rs=st.executeQuery(sql);
//            while(rs.next()){
//                account=new Account();
//                account.setM_id(rs.getInt("id"));
//                account.setM_name(rs.getString("name"));
//                account.setM_personalSign(rs.getString("personalSign"));
//            }
//            assert account != null;
//            ArrayList<Integer> love=findRelationById("love",account.getM_id());
//            List<Integer> fan=findRelationById("fan",account.getM_id());
//            account.setM_concernAccountId(love);
//            account.setM_likeAccountId(love);
//            return account;
//        } catch (SQLException throwables) {
//            return null;
//        }
//    }
//
//    private HashMap<String,String> findAccountById(int id){
//        try{
//            HashMap<String,String> account=new HashMap<>();
//            String sql="select * from relationAccount where id="+id;
//            st=con.prepareStatement(sql);
//            rs=st.executeQuery(sql);
//            while(rs.next()){
//                account.put("id",String.valueOf(rs.getInt("id")));
//                account.put("name",rs.getString("name"));
//            }
//            return account;
//        } catch (SQLException throwables) {
//            return null;
//        }
//    }
//
//    private Account findAccountById_simple(int id){
//        try{
//            Account account=new Account();
//            String sql="select * from relationAccount where id="+id;
//            st=con.prepareStatement(sql);
//            rs=st.executeQuery(sql);
//            while(rs.next()){
//                account.setM_id(rs.getInt("id"));
//                account.setM_name(rs.getString("name"));
//            }
//            return account;
//        } catch (SQLException throwables) {
//            return null;
//        }
//    }

    private ArrayList<Integer> findRelationById(String type, int id){
        String sql;
        if(type.equals("love")){
            sql="select love_id from relationAccount where love_id="+id;
        }else{
            sql="select fan_id from relationAccount where fan_id="+id;
        }
        try{
            ArrayList<Integer> ids=new ArrayList<>();
            st= con.prepareStatement(sql);
            rs=st.executeQuery(sql);
            while(rs.next()){
                ids.add(rs.getInt("id"));
            }
            return ids;
        }catch (SQLException throwables) {
            return null;
        }
    }

    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

