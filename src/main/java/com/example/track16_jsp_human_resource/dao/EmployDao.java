package com.example.track16_jsp_human_resource.dao;

import com.example.track16_jsp_human_resource.common.JDBConnection;
import com.example.track16_jsp_human_resource.dto.EmployDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Employ를 등록할 때 사원번호를 생성해 주는 메소드
     * @return 생성된 사원번호
     */
    public String generateEmployIdentifier() {

        String sql = "SELECT NVL(TO_NUMBER(MAX(NO)) + 1, 100) AS NO FROM EMP_이승호";

        String identifierResult = null;

        JDBConnection jdbc = null;

        try {
            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()) {
                identifierResult = rs.getString("NO");
            }

            System.out.println(identifierResult);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return identifierResult;
    }

    public ArrayList<EmployDto> getEmployDtos() {

        String sql = "SELECT EM.NO, EM.NAME, GR.GRADE_NAME AS GRADE, DE.DEPART_NAME AS DEPART, EM.AGE " +
                "FROM EMP_이승호 EM, EMP_이승호_GRADE GR, EMP_이승호_DEPART DE " +
                "WHERE EM.GRADE = GR.GRADE_CODE AND EM.DEPART = DE.DEPART_CODE " +
                "ORDER BY EM.NO DESC";

        ArrayList<EmployDto> employDtos = new ArrayList<>();

        JDBConnection jdbc = null;

        try {

            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployDto employDto =
                        new EmployDto(
                                rs.getString("NO"),
                                rs.getString("NAME"),
                                rs.getString("GRADE"),
                                rs.getString("DEPART"),
                                rs.getInt("AGE"));

                employDtos.add(employDto);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return employDtos;
    }

    public ArrayList<EmployDto> getEmployDtos(String searchKey, String searchValue) {

        if(searchKey.equals("GRADE")) {
            searchKey = "GR.GRADE_NAME";
        } else if(searchKey.equals("DEPART")) {
            searchKey = "DE.DEPART_NAME";
        }

        String sql = String.format("SELECT EM.NO, EM.NAME, GR.GRADE_NAME AS GRADE, DE.DEPART_NAME AS DEPART, EM.AGE " +
                "FROM EMP_이승호 EM, EMP_이승호_GRADE GR, EMP_이승호_DEPART DE " +
                "WHERE EM.GRADE = GR.GRADE_CODE AND EM.DEPART = DE.DEPART_CODE " +
                "AND %s LIKE ?" +
                "ORDER BY %s DESC", searchKey, searchKey);

        ArrayList<EmployDto> employDtos = new ArrayList<>();

        JDBConnection jdbc = null;

        String wildCardFormatting = "%" + searchValue + "%";

        try {

            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wildCardFormatting);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployDto employDto =
                        new EmployDto(
                                rs.getString("NO"),
                                rs.getString("NAME"),
                                rs.getString("GRADE"),
                                rs.getString("DEPART"),
                                rs.getInt("AGE"));

                employDtos.add(employDto);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return employDtos;

    }

    public int saveEmploy(EmployDto employDto) {

        String sql = "INSERT INTO EMP_이승호 (NO, NAME, GRADE, DEPART, AGE) " +
                "VALUES (?, ?, ?, ?, ?)";

        int result = 0;

        JDBConnection jdbc = null;

        try {
            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, employDto.getNo());
            ps.setString(2, employDto.getName());
            ps.setString(3, employDto.getGrade());
            ps.setString(4, employDto.getDepart());
            ps.setInt(5, employDto.getAge());
            result = ps.executeUpdate();

            if(result == 1) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return result;
    }

    public Map<String, Object> getEmploy(String no) {

        String sql = "SELECT EM.NO, EM.NAME, EM.GRADE, GR.GRADE_NAME, EM.DEPART, DE.DEPART_NAME, EM.AGE " +
                "FROM EMP_이승호 EM, EMP_이승호_GRADE GR, EMP_이승호_DEPART DE " +
                "WHERE EM.GRADE = GR.GRADE_CODE " +
                "AND EM.DEPART = DE.DEPART_CODE " +
                "AND NO = ?";

        Map<String, Object> employMap = null;

        JDBConnection jdbc = null;

        try {
            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            rs = ps.executeQuery();

            if(rs.next()) {

                employMap = new HashMap<>();
                employMap.put("no", rs.getString("NO"));
                employMap.put("name", rs.getString("NAME"));
                employMap.put("grade", rs.getString("GRADE"));
                employMap.put("gradeName", rs.getString("GRADE_NAME"));
                employMap.put("depart", rs.getString("DEPART"));
                employMap.put("departName", rs.getString("DEPART_NAME"));
                employMap.put("age", rs.getString("AGE"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return employMap;
    }

    public int updateEmploy(EmployDto employDto) {

        String sql = "UPDATE EMP_이승호 SET NAME = ?, GRADE = ?, DEPART = ?, AGE = ? WHERE NO = ?";

        int result = 0;

        JDBConnection jdbc = null;

        try {
            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, employDto.getName());
            ps.setString(2, employDto.getGrade());
            ps.setString(3, employDto.getDepart());
            ps.setInt(4, employDto.getAge());
            ps.setString(5, employDto.getNo());
            result = ps.executeUpdate();

            if(result == 1) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return result;
    }

    public int deleteEmploy(String no) {

        String sql = "DELETE FROM EMP_이승호 WHERE NO = ?";

        int result = 0;

        JDBConnection jdbc = null;

        try {
            jdbc = new JDBConnection();
            conn = jdbc.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            result = ps.executeUpdate();

            if(result == 1) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            jdbc.close(conn, ps, rs);
        }

        return result;
    }

}