package com.hrbust.su.sign_in.bean;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @Getter @Setter private String tid;

    @Getter @Setter private String name;

    @Getter @Setter private String username;
    @Getter @Setter private String password;

    public Teacher() {
    }


}
