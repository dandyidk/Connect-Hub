/*
 * Main.java
 * Application entry point for Connect-Hub — a Java Swing social networking platform.
 */

import UserManagementSystem.Firstpage;

/**
 * Main entry point for the Connect-Hub application.
 * Launches the initial landing page where users can choose to Sign Up or Log In.
 */
public class Main {
    public static void main(String[] args) {
        Firstpage fp = new Firstpage();
        fp.setTitle("Connect-Hub");
        fp.setVisible(true);
    }
}
