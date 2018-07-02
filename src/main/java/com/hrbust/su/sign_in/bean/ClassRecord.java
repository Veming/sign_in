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
    @Getter @Setter private String courseCode;

    @Getter @Setter private String classCode;
    @Getter @Setter private String longitude;
    @Getter @Setter private String createTime;
    @Getter @Setter private String tid;

    public ClassRecord() {
    }
}