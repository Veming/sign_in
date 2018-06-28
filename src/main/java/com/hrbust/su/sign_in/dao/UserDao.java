package com.hrbust.su.sign_in.dao;

import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, String> {
    @Query(value = "select t from User t where t.sessionKey = ?1")
    public User getUserInfoBySessionKey(String sessionKey);

    @Query(value = "select t.id from User t where t.sessionKey = ?1")
    public String getIdBySessionKey(String sessionKey);

    @Query(value = "select t from User t where t.openId = ?1")
    public User getUserInfoByOpenId(String openid);
}




