package com.hrbust.sign_in.dao;

import com.hrbust.sign_in.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, String > {


}
