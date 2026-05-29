/*
 * FILELOCATION.java
 * Centralized configuration for all JSON database file paths.
 * Uses relative paths resolved from the project root directory for cross-machine compatibility.
 */
package ContentCreation;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Centralized interface defining the file paths for the application's JSON database.
 * All file paths are resolved relative to the project's working directory,
 * ensuring the application runs correctly on any machine without hardcoded paths.
 */
public interface FILELOCATION {

    /** Base directory for all data files, relative to the project root. */
    String DATA_DIR = Paths.get(System.getProperty("user.dir"), "data").toString();

    /** Path to the main user/group/content database file. */
    String DATABASE = Paths.get(DATA_DIR, "users.json").toString();

    /** Path to the chat messages database file. */
    String CHATS = Paths.get(DATA_DIR, "chats.json").toString();
}
