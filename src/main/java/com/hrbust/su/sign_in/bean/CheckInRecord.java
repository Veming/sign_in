package com.hrbust.su.sign_in.bean;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_check_in_record")
public class CheckInRecord {

    @Id
    @GeneratedValue
    @Getter @Setter private Integer id;

    // 课程码
    @Getter @Setter private String classCode;
    // 经纬度
    @Getter @Setter private String longitude;
    @Getter @Setter private String latitude;
    // 距离
    @Getter @Setter private String distance;
    // 签到时间
    @Getter @Setter private String checkInTime;
    // 学生id
    @Getter @Setter private String stuId;

    public CheckInRecord() {
    }
}
