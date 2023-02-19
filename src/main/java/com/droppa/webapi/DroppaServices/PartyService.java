package com.droppa.webapi.DroppaServices;

import java.util.Random;
import java.util.logging.Logger;

public class PartyService {
	private static final Logger logger = Logger.getLogger(PartyService.class.getName());
	

	public int generateOTP(String mobileNumber) {
		final int max = 99999;
		final int min = 10000;
		Random random = new Random();
		int otp = random.nextInt((max - min) + 1) + min;
		logger.info("==================== OTP "+ otp +" sent to mobile number " + mobileNumber);
		return otp;		
	}

}
