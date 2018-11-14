package org.o7planning.sbsecurity.dao;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.mapper.AppUserMapper;
import org.o7planning.sbsecurity.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public AppUser findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.BASE_SQL + " where USER_NAME = ? ";

        Object[] params = new Object[] { userName };
        AppUserMapper mapper = new AppUserMapper();


        try {
            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<AppUser> getAllUsers(){
        AppUserMapper mapper=new AppUserMapper();
        return this.getJdbcTemplate().query("select * from App_User",mapper);

    }

}