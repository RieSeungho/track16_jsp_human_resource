package com.example.track16_jsp_human_resource.dao;

import com.example.track16_jsp_human_resource.common.JDBConnection;
import com.example.track16_jsp_human_resource.dto.DepartDto;
import com.example.track16_jsp_human_resource.dto.GradeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GradeDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<GradeDto> getGradeList() {

        String sql = "SELECT GR.GRADE_CODE, GR.GRADE_NAME, COUNT(EM.NO) GRADE_PERSONNEL " +
                "FROM EMP_이승호 EM, EMP_이승호_GRADE GR " +
                "WHERE EM.GRADE = GR.GRADE_CODE " +
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
}
