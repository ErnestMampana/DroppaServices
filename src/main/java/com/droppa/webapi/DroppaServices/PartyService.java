package com.droppa.webapi.DroppaServices;

import java.util.Random;
import java.util.logging.Logger;

public class PartyService {
	private static final Logger logger = Logger.getLogger(PartyService.class.getName());
	

	public int generateOTP(String mobileNumber) {
		Random random = new Random();
		int otp = random.nextInt(99999);
		logger.info("==================== OTP "+ otp +" sent to mobile number " + mobileNumber);
		return otp;		
	}

}
