package com.example.track16_jsp_human_resource.dao;

import com.example.track16_jsp_human_resource.common.JDBConnection;
import com.example.track16_jsp_human_resource.dto.DepartDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<DepartDto> getDepartList() {

        String sql = "SELECT DE.DEPART_CODE, DE.DEPART_NAME, COUNT(EM.NO) DEPART_PERSONNEL " +
                "FROM EMP_이승호 EM RIGHT JOIN EMP_이승호_DEPART DE ON EM.DEPART = DE.DEPART_CODE " +
                "GROUP BY DE.DEPART_CODE, DE.DEPART_NAME " +
                "ORDER BY DEPART_PERSONNEL ASC";

        ArrayList<DepartDto> departList = new ArrayList<DepartDto>();

        JDBConnection jdbc = null;

        try {

            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                DepartDto departDto =
                        new DepartDto(
                                rs.getString("DEPART_CODE"),
                                rs.getString("DEPART_NAME"),
                                rs.getString("DEPART_PERSONNEL"));

                departList.add(departDto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return departList;
    }

    public int saveDepart(String departCode, String departName) {

        String sql = "INSERT INTO EMP_이승호_DEPART (DEPART_CODE, DEPART_NAME) VALUES (?, ?)";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, departCode);
            ps.setString(2, departName);
            result = ps.executeUpdate();

            if(result == 1)  {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int updateDepartName(String existCode, String updateName) {

        String sql = "UPDATE EMP_이승호_DEPART " +
                "SET DEPART_NAME = ? " +
                "WHERE DEPART_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql) ;
            ps.setString(1, updateName);
            ps.setString(2, existCode);
            result = ps.executeUpdate();

            if(result == 1)  {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int codeDuplication(String departCode) {
        String sql = "SELECT DEPART_CODE " +
                "FROM EMP_이승호_DEPART " +
                "WHERE DEPART_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, departCode);
            rs = ps.executeQuery();

            if(rs.next()) {
                result = 1;
            } else {
                result = 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int nameDuplication(String departName) {

        String sql = "SELECT DEPART_NAME " +
                "FROM EMP_이승호_DEPART " +
                "WHERE DEPART_NAME = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, departName);
            rs = ps.executeQuery();

            if(rs.next()) {
                result = 1;
            } else {
                result = 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    public int departPersonnel(String mergeFrom) {

        String sql = "SELECT COUNT(EM.NO) PERSONNEL " +
                "FROM EMP_이승호 EM RIGHT JOIN EMP_이승호_DEPART DE ON EM.DEPART = DE.DEPART_CODE " +
                "WHERE DEPART = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mergeFrom);
            rs = ps.executeQuery();

            if(rs.next()) {
                if(rs.getString("PERSONNEL").equals("0")) {
                    result = 0;
                } else {
                    result = 1;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return result;
    }

    public int mergeDepart(String mergeFrom, String mergeTo) {

        String sql = "UPDATE EMP_이승호 SET DEPART = ? WHERE DEPART = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        System.out.println(mergeTo);
        System.out.println(mergeFrom);

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, mergeTo);
            ps.setString(2, mergeFrom);
            result = ps.executeUpdate();

            if(result > 0)  {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int deleteDepart(String departCode) {

        String sql = "DELETE FROM EMP_이승호_DEPART WHERE DEPART_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, departCode);
            result = ps.executeUpdate();

            if(result == 1)  {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
