package com.project.abc.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = false)
public class UserDAO extends JdbcDaoSupport {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }

	public Map<String, Object> getUsers(String searchQuery, Integer pageNumber, String sortBy, String order, Integer limit) {
		Integer offset = pageNumber <= 0 ? 0 : (pageNumber - 1) * limit;

		String sql = UserSQL.RETRIEVE_FILTERED_USER;
		sql = String.format(sql, sortBy, order);
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), "%" + searchQuery + "%", offset, limit);

		sql = UserSQL.RETRIEVE_FILTERED_USER_COUNT;
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, "%" + searchQuery + "%");

		Map<String, Object> result = new HashMap<>();
		result.put("Sites", users);
		result.put("TotalRows", count);
		return result;
	}

	public User retrieveUserInfo(String userName, String Password) throws Exception {
        User user = null;
        try {
            String sql = UserSQL.RETRIEVE_USER_INFO;
            user = jdbcTemplate.queryForObject(sql, new Object[] { userName }, new BeanPropertyRowMapper<User>(User.class));
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

	public User retrieveUserInfo(Integer userId) throws Exception {
		User user = null;
		try {
			String sql = UserSQL.RETRIEVE_USER_INFO_BY_ID;
			user = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new BeanPropertyRowMapper<User>(User.class));
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean addNewUser(User user) throws Exception {
		boolean isSuccess = false;
		try {
			String sql = UserSQL.ADD_NEW_USER;
			int returnValue = jdbcTemplate.update(sql,
					new Object[] { user.getUserName(), user.getUserLastName(), user.getUserFirstName(), 
					user.getUserPassword() });

			if (returnValue > 0) {
				isSuccess = true;
			}
			return isSuccess;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean deleteUser(Integer userId) throws Exception {
		boolean isSuccess = false;
		try {
			String sql = UserSQL.DELETE_USER;
			int returnValue = jdbcTemplate.update(sql, new Object[] { userId });

			if (returnValue > 0) {
				isSuccess = true;
			}

			return isSuccess;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean updateUserInfo(User user) throws Exception {
		boolean isSuccess = false;
		try {
			String sql = UserSQL.UPDATE_USER_INFO;
			int returnValue = jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getUserLastName(), user.getUserFirstName(), user.getUserID() });
			
			if (returnValue > 0) {
				isSuccess = true;
			}

			return isSuccess;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean isUsernameExists(User user) {
		boolean exist = false;
		try {
			String sql = UserSQL.IS_USERNAME_EXISTS;
			int  recordCount = jdbcTemplate.queryForObject(sql, new Object[] { user.getUserName(), user.getUserID() }, Integer.class);
			
			if (recordCount > 0) {
				exist = true;
			}
			
			return exist;
		} catch (Exception e) {
			throw e;
		}
	}
}
