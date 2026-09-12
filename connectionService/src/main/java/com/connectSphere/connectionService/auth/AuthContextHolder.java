package com.connectSphere.connectionService.auth;

/**
 * Uses {@code ThreadLocal} to maintain user-specific data, ensuring
 * that the state is isolated to the current thread.
 */
public class AuthContextHolder {
    /**
     * Store the ID of the currently authenticated user for the current thread.
     * This ensures that the user-specific data remains isolated within the
     * thread's scope, avoiding interference between threads in a multithreaded
     * environment.
     */
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    /**
     * Retrieves the ID of the current user associated with the thread.
     *
     * @return The user ID of the currently authenticated user.
     */
    public static Long getCurrentUserId() {
        return currentUserId.get();
    }

    /**
     * Sets the ID of the current user in the thread-local storage.
     *
     * @param userId The ID of the user to set for the current thread.
     */
    static void setCurrentUserId(final Long userId) {
        currentUserId.set(userId);
    }

    /**
     * Clears the thread-local storage for the current user ID.
     */
    static void clear() {
        currentUserId.remove();
    }
}
