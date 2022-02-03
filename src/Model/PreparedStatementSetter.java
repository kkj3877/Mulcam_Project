package Model;

import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
	public void setValues(PreparedStatement stmt) throws Exception;
}
