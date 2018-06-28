package com.hrbust.su.sign_in.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @Getter @Setter private String sid;
    @Getter @Setter private String realName;
    @Getter @Setter private String idCard;
    @Getter @Setter private String className;
    @Getter @Setter private String dormNbr;
    @Getter @Setter private String flatName;
    @Getter @Setter private String grade;
    @Getter @Setter private String profession;
    @Getter @Setter private String phone;
    @Getter @Setter private String gender;
    @Getter @Setter private String state;


    public Student(){
    }

}
