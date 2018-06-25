package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Student;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, String> {


    @Query(value = "select t from Student t where t.realName = ?1 and t.idCard = ?2")
    public Student getStudentByRealNameAndIdCard(String realName, String idNbr);

    @Query(value = "select t from Student t where t.sid = ?1")
    public Student getStudentBySid(String sid);

}
