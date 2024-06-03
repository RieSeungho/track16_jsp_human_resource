package com.example.track16_jsp_human_resource.dao;

import com.example.track16_jsp_human_resource.common.JDBConnection;
import com.example.track16_jsp_human_resource.dto.DepartDto;
import com.example.track16_jsp_human_resource.dto.GradeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<GradeDto> getGradeList() {

        String sql = "SELECT GR.GRADE_CODE, GR.GRADE_NAME, COUNT(EM.NO) GRADE_PERSONNEL " +
                "FROM EMP_이승호 EM " +
                "RIGHT JOIN EMP_이승호_GRADE GR ON EM.GRADE = GR.GRADE_CODE " +
                "GROUP BY GR.GRADE_CODE, GR.GRADE_NAME " +
                "ORDER BY GRADE_PERSONNEL ASC";

        ArrayList<GradeDto> gradeList = new ArrayList<GradeDto>();

        JDBConnection jdbc = null;

        try {

            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                GradeDto gradeDto =
                        new GradeDto(
                                rs.getString("GRADE_CODE"),
                                rs.getString("GRADE_NAME"),
                                rs.getString("GRADE_PERSONNEL"));

                gradeList.add(gradeDto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return gradeList;
    }

    public int codeDuplication(String gradeCode) {

        String sql = "SELECT GRADE_CODE FROM EMP_이승호_GRADE WHERE GRADE_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, gradeCode);
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

        return 0;
    }

    public int nameDuplication(String updateName) {

        String sql = "SELECT GRADE_NAME " +
                "FROM EMP_이승호_GRADE " +
                "WHERE GRADE_NAME = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, updateName);
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

    public int saveGrade(String gradeCode, String gradeName) {

        String sql = "INSERT INTO EMP_이승호_GRADE (GRADE_CODE, GRADE_NAME) VALUES (?, ?)";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, gradeCode);
            ps.setString(2, gradeName);
            result = ps.executeUpdate();

            if(result == 1) {
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

    public int updateGradeName(String existCode, String updateName) {

        String sql = "UPDATE EMP_이승호_GRADE SET GRADE_NAME = ? WHERE GRADE_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, updateName);
            ps.setString(2, existCode);
            result = ps.executeUpdate();

            if(result == 1) {
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

    public int gradePersonnel(String mergeFrom) {

        String sql = "SELECT COUNT(EM.NO) PERSONNEL " +
                "FROM EMP_이승호 EM RIGHT JOIN EMP_이승호_GRADE GR ON EM.GRADE = GR.GRADE_CODE " +
                "WHERE GRADE_CODE = ?";

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

    public int mergeGrade(String mergeFrom, String mergeTo) {

        String sql = "UPDATE EMP_이승호 SET GRADE = ? WHERE GRADE = ?";

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

    public int deleteGrade(String gradeCode) {

        String sql = "DELETE FROM EMP_이승호_GRADE WHERE GRADE_CODE = ?";

        int result = 0;

        JDBConnection jdbc = new JDBConnection();

        try {
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, gradeCode);
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
