package com.example.track16_jsp_human_resource.dao;

import com.example.track16_jsp_human_resource.common.JDBConnection;
import com.example.track16_jsp_human_resource.dto.DepartDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
