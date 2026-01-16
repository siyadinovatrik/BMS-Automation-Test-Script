package testdata;

import config.ConfigReader;

public class TestData {
    
    public static class User {
        public static final String MOBILE_NUMBER = 
            ConfigReader.getTestMobileNumber();
        public static final String OTP = ConfigReader.getTestOTP();
        public static final String EMAIL = ConfigReader.getTestEmail();
        public static final String NAME = ConfigReader.getTestName();
    }
    
    public static class Address {
        public static final String FLAT = "KATHAR TDPA KOCHI Ernakulam";
        public static final String CITY = "Cochin";
        public static final String PINCODE = "685584";
        public static final String STATE = "Kerala";
    }
}