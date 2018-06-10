package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, String> {


    @Query(value = "select t from t_student t where t.real_name = ?1 and t.id_card = ?2")
    public List<Student> getUserInfo(String realName, String idNbr);
}
