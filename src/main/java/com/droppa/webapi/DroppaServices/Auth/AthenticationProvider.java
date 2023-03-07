/**
 * 
 */
package com.droppa.webapi.DroppaServices.Auth;

import java.util.regex.Pattern;

import org.glassfish.jersey.process.internal.RequestScoped;

import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Ernest Mampana
 *
 */
@RequestScoped
public class AthenticationProvider {

	private void validatePassword(char[] candidate) {
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
		boolean matches = Pattern.compile(pattern).matcher(String.valueOf(candidate)).matches();
		if (!matches)
			throw new ClientException("Password should contain special characters,numbers and letters");
	}
	
	
//	public String generateToken(UserAccount account) {
//		return Jwts.builder().setIssuer(account.getId())
//				.setExpiration(expirationDate()).signWith(SignatureAlgorithm.HS256, secretKey()).compact();
//	}

}
