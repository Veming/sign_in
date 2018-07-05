package com.hrbust.su.sign_in.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Getter @Setter private String openId;
    @Getter @Setter private String sessionKey;
    @Getter @Setter private Integer type;
    @Getter @Setter private String id;

    public User(){

    }


}
