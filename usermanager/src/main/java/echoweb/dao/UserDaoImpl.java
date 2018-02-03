package echoweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import echoweb.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public UserDaoImpl() {
		log.debug("************UserDaoImpl()");
	}
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	// Login method to generate SQL Injection scenario for testing purpose
	// The username will be treated as the first_name, and the password will be treated as the last_name
	public List<User> login(String username, String password) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        
        // Create SQL injection scenario: user can provide special value in name to inject SQL. e.g. 'OR 1=1--
        String sql = "select * from users where first_name='" + username + "' AND last_name='" + password + "'";
        // TODO: to fix the SQL injection issue, pass the name via parameter as below in SQL
		//String sql = "SELECT * FROM users WHERE first_name=:username and last_name=:password";
        //params.put("username", username);
		//params.put("password", password);

		List<User> result = namedParameterJdbcTemplate.query(
                    sql,
                    params,
                    new UserMapper());
                    
		log.debug("login() : [" + username + ", " + password + "]");
        return result;
        
	}
	
	public User findByName(String name) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        
		String sql = "SELECT * FROM users WHERE name=:name";
		
		User result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new UserMapper());
                    
        //new BeanPropertyRowMapper(Customer.class));
        
        return result;
        
	}
	
	public List<User> findAllByName(String name) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        //params.put("name", name);
        
        // Create SQL injection scenario: user can provide special value in name to inject SQL. e.g. 'OR 1=1--
        String sql = "select * from users where first_name='" + name + "'";
        // TODO: to fix the SQL injection issue, pass the name via parameter as below in SQL
		//String sql = "SELECT * FROM users WHERE first_name=:name";

		List<User> result = namedParameterJdbcTemplate.query(
                    sql,
                    params,
                    new UserMapper());
                    
		log.debug("findAllByName() : [" + name + "]");
        return result;
        
	}

	public List<User> findAll() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String sql = "SELECT * FROM users";
		
        List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
/*        
        List<User> newresult = this.findAllByName("'OR 1=1--alex");   
        log.debug("SCAI: newresult=" + newresult.size());
        result.addAll(newresult);
        */
        return result;
        
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	}

}