package echoweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class ServiceConfig {
	private static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);


	@Profile("NoDBImpl")
	@Bean(name="webUserService")
	public WebUserService webUserServiceNoDBImpl() {
		
		logger.debug("*****************webUserServiceNoDBImpl()");
		//return new WebUserServiceDBImpl();
		//return new WebUserServiceImpl();
		return new WebUserServiceNoDBImpl();
	}
	
	@Profile("DynamoDBImpl")
	@Bean(name="webUserService")
	public WebUserService webUserService() {
		
		logger.debug("*****************webUserService()");
		//return new WebUserServiceDBImpl();
		return new WebUserServiceImpl();
		//return new WebUserServiceNoDBImpl();
	}
/*	
	@Profile("HsqlDBImpl")
	@Bean(name="webUserService")
	public WebUserService webUserServiceHsqlDBImpl() {
		
		logger.debug("*****************webUserServiceHsqlDBImpl()");
		//return new WebUserServiceDBImpl();
		//return new WebUserServiceImpl();
		WebUserServiceHsqlDBImpl ws = new WebUserServiceHsqlDBImpl();

		return new WebUserServiceHsqlDBImpl();
	}
*/

}
