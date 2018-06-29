package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, String> {


    @Query(value = "select t from Student t where t.realName = ?1 and t.idCard = ?2")
    public Student getStudentByRealNameAndIdCard(String realName, String idNbr);

    @Query(value = "select t from Student t where t.sid = ?1")
    public Student getStudentBySid(String sid);

    @Query(value = "select t from Student t where t.profession = ?1 and t.grade = ?2 and t.className = ?3 and t.sid <> ?4")
    public List<Student> getStudentsByProfessionAndGradeAndClassName(String profession, String grade, String className, String sid);

    @Transactional
    @Modifying
    @Query(value = "update Student t set t.state = \'active\'  where t.sid = ?1")
    public void setState(String sid);
}
