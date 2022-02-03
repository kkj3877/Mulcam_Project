package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<X> {
	public X mapRow( ResultSet rs ) throws SQLException;
}
