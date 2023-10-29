package com.nishant.ScribbleSpace.util;

public class MailingUtil {

    public static String getUserVerificationEmailMessage(String host, String recipient, String token) {
        return "Please click below link to verify account: \n\n"+
                host + "/guest/verification/user?token=" +token+"&email="+recipient;
    }
}