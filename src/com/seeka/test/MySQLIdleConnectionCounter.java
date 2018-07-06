package com.seeka.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLIdleConnectionCounter implements IdleConnectionCounter {
	 
    public static final IdleConnectionCounter INSTANCE = 
        new MySQLIdleConnectionCounter();
 
/*    @Override
    public boolean appliesTo(Class<? extends Dialect> dialect) {
        return MySQL5Dialect.class.isAssignableFrom( dialect );
    }*/
 
    @Override
    public int count(Connection connection) {
        try ( Statement statement = connection.createStatement() ) {
            try ( ResultSet resultSet = statement.executeQuery(
                    "SHOW PROCESSLIST" ) ) {
                int count = 0;
                while ( resultSet.next() ) {
                    String state = resultSet.getString( "command" );
                    if ( "sleep".equalsIgnoreCase( state ) ) {
                        count++;
                    }
                }
                return count;
            }
        }
        catch ( SQLException e ) {
            throw new IllegalStateException( e );
        }
    }
}