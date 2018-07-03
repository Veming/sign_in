package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRecordDao extends JpaRepository<CheckInRecord, String> {
}
