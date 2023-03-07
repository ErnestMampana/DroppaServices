package com.droppa.webapi.DroppaServices.bean;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.Auth.CredentialsDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;

@Stateless
@Local
public class LoginService {
	
	@EJB private UserService userService = new UserService();
	
	UserAccount extractedAccount = new UserAccount();
	private static final Logger logger = Logger.getLogger(LoginService.class.getName());

	public UserAccount userLogin(String username, String password) {
		extractedAccount = null;
		try {
			UserAccount userAcc = userService.getUserById(username);
			if (userAcc.getId().equals(username) && userAcc.getPassword().equals(password)) {
				if (userAcc.isConfirmed()) {
					if (userAcc.getStatus().equals(AccountStatus.ACTIVE)) {
						logger.info("Useraccount Logged In ================= " + userAcc.getId());
						logger.info("Useraccount Logged In ================= " + userAcc.getOwner().getUserName() + " "
								+ userAcc.getOwner().getSurname());
						
						extractedAccount = userAcc;
						
					} else {
						if (userAcc.getStatus().equals(AccountStatus.DELETED)) {
							throw new ClientException(
									"This account has been deleted, please contact Droppa Clone for re-activation.");
						} else if (userAcc.getStatus().equals(AccountStatus.AWAITING_PWD_RESET)) {
							throw new ClientException("User awaiting password reset.");
						} else {
							throw new ClientException(
									"This account has been suspended, please contact Droppa Clone for re-activation.");
						}
					}
				} else {
					throw new ClientException("Account not confirmed");
				}

			} else {
				throw new ClientException("Username and password do not match");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return extractedAccount;

	}
}
