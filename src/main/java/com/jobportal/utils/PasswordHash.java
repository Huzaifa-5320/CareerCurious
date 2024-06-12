package com.jobportal.utils ;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHash{

    public static String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Add salt to the digest
            digest.update(salt);

            // Hash the password
            byte[] hashedPassword = digest.digest(password.getBytes());

            // Combine salt and hashed password
            byte[] combined = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

            // Convert the combined bytes to Base64
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            // Decode the hashed password from Base64
            byte[] combined = Base64.getDecoder().decode(hashedPassword);

            // Extract the salt and hashed password
            byte[] salt = new byte[16];
            byte[] storedHash = new byte[combined.length - salt.length];
            System.arraycopy(combined, 0, salt, 0, salt.length);
            System.arraycopy(combined, salt.length, storedHash, 0, storedHash.length);

            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Add salt to the digest
            digest.update(salt);

            // Hash the provided password with the same salt
            byte[] hashedInput = digest.digest(password.getBytes());

            // Compare the stored hash with the hash of the provided password
            return MessageDigest.isEqual(storedHash, hashedInput);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

}
