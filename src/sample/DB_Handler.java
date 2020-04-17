package sample;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DB_Handler extends ConnectionConfig_Hosts {
    Connection dbConnection;
    public Connection getDbConnection()
            throws Exception {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        dbConnection = getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void recordCardsMysql(String kode_text, String status_text, String data_text) {
        String insert = "INSERT INTO " + DB_Constants.USER_TABLE + "(" +
                DB_Constants.CARD + "," + DB_Constants.STATUS + ","
                + DB_Constants.DATE + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, kode_text);
            prSt.setString(2, status_text);
            prSt.setString(3, String.valueOf(data_text));
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
