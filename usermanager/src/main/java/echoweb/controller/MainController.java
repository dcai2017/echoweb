package echoweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import echoweb.bean.LoginBean;
import echoweb.bean.LoginRspBean;
import echoweb.bean.WebUserBean;
import echoweb.dao.UserDao;
import echoweb.model.User;
import echoweb.service.WebUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController extends AbstractController{
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	WebUserService userService;
	@Autowired
	UserDao userDao;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return null;
	}
	
	@RequestMapping("/listusers")
	@ResponseBody
	public  List<WebUserBean> listUsers(){
		List<User> users = userDao.findAll();
		System.out.println("**********users found: " + users.size());
		logger.debug("**********users found: " + users.size());

		return userService.getUserList();
	}
	
	@RequestMapping("/adduser")
	@ResponseBody
	public  List<WebUserBean> addUser(@RequestBody WebUserBean userBean){
		userService.updateUser(userBean);
		return userService.getUserList();
	}

	@RequestMapping("/deleteuser")
	@ResponseBody
	public  List<WebUserBean> deleteUser(@RequestBody WebUserBean userBean){
		userService.deleteUser(userBean.getEmail());
		return userService.getUserList();
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public  LoginRspBean login(@RequestBody LoginBean loginBean){
		//userService.updateUser(userBean);
		LoginRspBean rsp = new LoginRspBean();
		List<User> users = userDao.login(loginBean.getUsername(), loginBean.getPassword());
		logger.debug("**********users found: " + users.size());
		rsp.setUsername(loginBean.getUsername());
		if(users != null && users.size() > 0) {
			rsp.setStatusMsg("Login successfully!");
			rsp.setLoginStatus(true);
			// convert the list of User to WebUserBean
			List<WebUserBean> webUsers = new ArrayList<WebUserBean>();
			for(User user:users) {
				WebUserBean wb = new WebUserBean();
				wb.setEmail(user.getEmail());
				wb.setFirstName(user.getFirstName());
				wb.setLastName(user.getLastName());
				webUsers.add(wb);
			}
			rsp.setUsers(webUsers);
		} else {
			rsp.setStatusMsg("Failed to login!");
			rsp.setLoginStatus(false);
		}
		return rsp;
	}		
}
