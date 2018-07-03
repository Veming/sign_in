package com.hrbust.su.sign_in.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_class_record")
public class ClassRecord {

    @Id
    @Getter @Setter private String rid;

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