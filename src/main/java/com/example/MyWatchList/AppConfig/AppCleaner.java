package com.example.MyWatchList.AppConfig;

/**
 * An interface for cleanable components in the application.
 * Components that implement this interface can be cleaned up when no longer needed,
 * helping to release resources and improve memory management.
 */
public interface AppCleaner {
    void cleanup();

}
