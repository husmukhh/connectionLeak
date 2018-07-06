package com.seeka.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ConnectionLeakUtil {
	 
 
    private List<IdleConnectionCounter> idleConnectionCounters =   Arrays.asList( MySQLIdleConnectionCounter.INSTANCE );
 
    private IdleConnectionCounter connectionCounter;
 
    private int connectionLeakCount;
 
    public ConnectionLeakUtil() {
        for ( IdleConnectionCounter connectionCounter : idleConnectionCounters ) {
            if ( true ) {
                this.connectionCounter = connectionCounter;
                break;
            }
        }
        if ( connectionCounter != null ) {
            connectionLeakCount = countConnectionLeaks();
        }
    }
 
    public void assertNoLeaks() throws ConnectionLeakException {
        if ( connectionCounter != null ) {
            int currentConnectionLeakCount = countConnectionLeaks();
            int diff = currentConnectionLeakCount - connectionLeakCount;
            if ( diff > 0 ) {
                throw new ConnectionLeakException( "Connection Leak count :"+ diff);
            }
        }
    }
 
    private int countConnectionLeaks() {
        try ( Connection connection = newConnection() ) {
            return connectionCounter.count( connection );
        }
        catch ( SQLException e ) {
            throw new IllegalStateException( e );
        }
    }
 
    private Connection newConnection() {
        try {
            return DriverManager.getConnection("jdbcurl",  "username",  "password"    );
        }
        catch ( SQLException e ) {
            throw new IllegalStateException( e );
        }
    }
}