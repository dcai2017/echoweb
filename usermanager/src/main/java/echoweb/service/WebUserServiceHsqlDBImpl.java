package echoweb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import echoweb.bean.WebUserBean;
import echoweb.dao.UserDao;
import echoweb.model.User;

@Service("webUserService")
@Profile("HsqlDBImpl")
public class WebUserServiceHsqlDBImpl implements WebUserService {
	@Autowired
	UserDao userDao;
	private static final Logger log = LoggerFactory.getLogger(WebUserServiceHsqlDBImpl.class);

	public List<WebUserBean> getUserList() {
		List<User> users = userDao.findAll();
		log.debug("************* getUserList(): " + users.size());
		
		List<WebUserBean> webUsers = new ArrayList<WebUserBean>();
		for(User user:users) {
			WebUserBean wb = new WebUserBean();
			wb.setEmail(user.getEmail());
			wb.setFirstName(user.getFirstName());
			wb.setLastName(user.getLastName());
			webUsers.add(wb);
		}
		return webUsers;
	}
	public void updateUser(WebUserBean userBean) {
		log.debug("**********updateUser(): " + userBean.getEmail());

	}
	public void deleteUser(String email) {
		log.debug("***********deleteUser(): " + email);

	}
}
