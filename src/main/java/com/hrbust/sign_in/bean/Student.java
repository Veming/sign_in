package com.hrbust.sign_in.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    private String id;
    private String realName;
    private String idCard;
    private String className;
    private String dormNbr;
    private String flatName;
    private Integer Monitor;


    public Student(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDormNbr() {
        return dormNbr;
    }

    public void setDormNbr(String dormNbr) {
        this.dormNbr = dormNbr;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public Integer getMonitor() {
        return Monitor;
    }

    public void setMonitor(Integer monitor) {
        Monitor = monitor;
    }
}
