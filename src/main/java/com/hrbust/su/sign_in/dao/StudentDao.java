package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentDao extends JpaRepository<Student, String> {


    @Query("select t from t_student t where real_name =?1 and id_card=?2")
    public Student getUserInfo(String realName, String idNbr);
}
