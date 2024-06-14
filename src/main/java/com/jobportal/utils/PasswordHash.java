package com.jobportal.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHash {

    /**
     * Hashes a password using SHA-256 with a random salt.
     *
     * @param password The plain password to hash.
     * @return The Base64 encoded salt and hashed password.
     */
    public static String hashPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

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
            throw new RuntimeException("Hashing algorithm not found");
        }
    }

    /**
     * Verifies a password against a stored hashed password.
     *
     * @param password       The plain password to verify.
     * @param hashedPassword The Base64 encoded salt and hashed password to verify against.
     * @return true if the password matches the hash, false otherwise.
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) {
            throw new IllegalArgumentException("Password and hashedPassword cannot be null");
        }

        try {
            // Decode the hashed password from Base64
            byte[] combined = Base64.getDecoder().decode(hashedPassword);

            // Extract the salt and hashed password
            if (combined.length < 16) {
                throw new IllegalArgumentException("Invalid hashed password length");
            }

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
            throw new RuntimeException("Hashing algorithm not found");
        }
    }
}
