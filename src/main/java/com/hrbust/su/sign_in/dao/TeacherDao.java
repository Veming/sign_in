package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherDao extends JpaRepository<Teacher, String> {
    @Query(value = "select t from Teacher t where t.tid = ?1")
    public Teacher getTeacherByTid(String tid);
}
