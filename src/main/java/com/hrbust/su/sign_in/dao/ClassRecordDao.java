package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.ClassRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRecordDao extends JpaRepository<ClassRecord, String> {
}
