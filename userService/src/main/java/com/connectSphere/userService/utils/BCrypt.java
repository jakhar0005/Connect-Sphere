package com.connectSphere.userService.utils;

import static org.mindrot.jbcrypt.BCrypt.*;

/**
 * Utility class for hashing and checking passwords using BCrypt.
 */
public class BCrypt {
    /**
     * Hashes the provided password using BCrypt.
     *
     * @param password The plain text password to hash.
     *
     * @return The hashed password.
     */
    public static String hashPassword(final String password) {
        return hashpw(password, gensalt());
    }

    /**
     * Checks if the provided password matches the hashed password.
     *
     * @param password The plain text password to check.
     * @param hashed The hashed password to compare against.
     *
     * @return {@code true} if the password matches the hashed password
     * , {@code false} otherwise.
     */
    public static boolean checkPassword(final String password, final String hashed) {
        return checkpw(password, hashed);
    }
}
