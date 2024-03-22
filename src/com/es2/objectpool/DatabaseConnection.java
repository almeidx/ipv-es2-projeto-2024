package com.es2.objectpool;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final int DEFAULT_MAX_POOL_SIZE = 10;
    private static DatabaseConnection instance;
    private final ArrayList<DatabaseConnection> availableConnections;
    private final List<DatabaseConnection> usedConnections;
    private int maxPoolSize;

    private DatabaseConnection() {
        availableConnections = new ArrayList<>();
        usedConnections = new ArrayList<>();
        maxPoolSize = DEFAULT_MAX_POOL_SIZE;
    }

    public synchronized static DatabaseConnection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public synchronized void releaseConnection(DatabaseConnection connection) {
        usedConnections.remove(connection);
        availableConnections.add(connection);
    }

    public synchronized void setMaxPoolSize(int size) {
        maxPoolSize = size;
    }

    public synchronized void resetPool() {
        availableConnections.clear();
        usedConnections.clear();
    }
}
