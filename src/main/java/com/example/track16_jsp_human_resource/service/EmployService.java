package com.example.track16_jsp_human_resource.service;

import com.example.track16_jsp_human_resource.dao.DepartDao;
import com.example.track16_jsp_human_resource.dao.EmployDao;
import com.example.track16_jsp_human_resource.dao.GradeDao;
import com.example.track16_jsp_human_resource.dto.DepartDto;
import com.example.track16_jsp_human_resource.dto.EmployDto;
import com.example.track16_jsp_human_resource.dto.GradeDto;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class EmployService {

    EmployDao employDao;
    DepartDao departDao;
    GradeDao gradeDao;

    public ArrayList<EmployDto> getEmployDtos() {
        employDao = new EmployDao();

        return employDao.getEmployDtos();
    }

    /**
     * 회원가입, 수정시에 사용되는 부서목록으로, GROUP BY 문을 통한 쿼리로 인원을 확인할 수 있음
     * @return
     */
    public ArrayList<DepartDto> getDepartList() {

        departDao = new DepartDao();

        return departDao.getDepartList();
    }

    public ArrayList<GradeDto> getGradeList() {

        gradeDao = new GradeDao();

        return gradeDao.getGradeList();
    }

    public int saveEmployDto(String name, String grade, String depart, int age) {

        employDao = new EmployDao();

        EmployDto saveEmployDto = null;

        int result = 0;

        Optional<String> identifier = Optional.ofNullable(employDao.generateEmployIdentifier());

        if(identifier.isPresent()) {
            saveEmployDto =
                    new EmployDto(
                            identifier.get(),
                            name,
                            grade,
                            depart,
                            age);

            result = employDao.saveEmploy(saveEmployDto);

        } else {
            return result;
        }

        return result;
    }

    public Map<String, Object> getEmployMap(String no) {
        employDao = new EmployDao();
        Map<String, Object> employMap = employDao.getEmploy(no);
        return employMap;
    }

    public int updateEmploy(EmployDto employDto) {
        employDao = new EmployDao();
        int result = employDao.updateEmploy(employDto);
        return result;
    }

    public int deleteEmploy(String no) {
        employDao = new EmployDao();
        int result = employDao.deleteEmploy(no);
        return result;
    }
}
