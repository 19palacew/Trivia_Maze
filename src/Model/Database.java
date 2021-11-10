package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static Question genQuestion(){
        return new Question("SQLite Coming Soon", Answer.A);
    }
}
