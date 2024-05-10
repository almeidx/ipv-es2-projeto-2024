package courses;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final int POOL_SIZE = 10;
    private final static List<DatabaseConnection> pool;
    private final static List<DatabaseConnection> inUse;

    private DatabaseConnection() {
    }

    static {
        pool = new ArrayList<>(POOL_SIZE);
        inUse = new ArrayList<>(POOL_SIZE);
     }

    public static DatabaseConnection getConnection() throws PoolExhaustedException {
        DatabaseConnection connection;

        if (pool.isEmpty()) {
            if (inUse.size() >= POOL_SIZE) {
                throw new PoolExhaustedException("No connections available");
            }

            connection = new DatabaseConnection();
        } else {
            connection = pool.removeFirst();
        }

        inUse.add(connection);
        return connection;
    }

    public void releaseConnection(DatabaseConnection connection) throws ObjectNotFoundException {
        if (!inUse.contains(connection)) {
            throw new ObjectNotFoundException("Connection not found in pool");
        }

        pool.add(connection);
        inUse.remove(connection);
    }
}
