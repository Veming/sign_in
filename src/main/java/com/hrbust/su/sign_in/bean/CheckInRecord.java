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

    @Getter @Setter private String classCode;
    @Getter @Setter private String longitude;
    @Getter @Setter private String dimensions;
    @Getter @Setter private String sTime;
    @Getter @Setter private String stuId;

    public CheckInRecord() {
    }
}
