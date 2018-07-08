package com.hrbust.su.sign_in.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_class_record")
public class ClassRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer rid;

    @Getter @Setter private String courseCode;
    // 经纬
    @Getter @Setter private String longitude;
    // 纬度
    @Getter @Setter private String latitude;
    // 创建时间
    @Getter @Setter private String createTime;
    // 教师id
    @Getter @Setter private String tid;

    public ClassRecord() {
    }
}