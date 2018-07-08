package com.hrbust.su.sign_in.bean;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_check_in_record")
public class CheckInRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;

    // 课程码
    @Getter @Setter private String sourceCode;
    // 经纬度
    @Getter @Setter private String longitude;
    @Getter @Setter private String latitude;
    // 距离
    @Getter @Setter private String distance;
    // 签到时间
    @Getter @Setter private String checkInTime;
    // 学生id
    @Getter @Setter private String stuId;
    // 对应上课日志 ID
    @Getter @Setter private String crid;

    public CheckInRecord() {
    }
}
