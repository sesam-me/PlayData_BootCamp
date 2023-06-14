package repository;

import config.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private static BookingRepository repository;

    public static BookingRepository getRepository() {
        if(repository == null) repository = new BookingRepository();
        return repository;
    }


}

