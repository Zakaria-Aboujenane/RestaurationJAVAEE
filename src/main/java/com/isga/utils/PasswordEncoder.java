package com.isga.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
	private static final String HASHING_METHOD = "SHA";
	public static String getHashed(String input) {
		// methode utilise (si on change methode de hashage on change ici seulement)
		switch (HASHING_METHOD) {
		case "SHA":
			try {
				return toHexString(getSHA(input));
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
				return "";
			}
		case "MD5":
			return getMd5(input);
		default:
			return getMd5(input);
		}
		
	}
	public static String getMd5(String input)
    {
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            byte[] messageDigest = md.digest(input.getBytes());
 
            BigInteger no = new BigInteger(1, messageDigest);
 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
	
	public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
 
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }

}
