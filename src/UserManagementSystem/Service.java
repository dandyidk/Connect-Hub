/*
 * Service.java
 * Interface defining authentication operations for the Connect-Hub application.
 */
package UserManagementSystem;

/**
 * Defines the contract for user authentication services.
 * Implemented by {@link User} to provide login and signup functionality.
 *
 * @author ahmed
 * @see User
 */
public interface Service {

    /** Authenticates the user and updates their online status. */
    public void login();

    /** Registers a new user account and persists it to the database. */
    public void signup();
}
