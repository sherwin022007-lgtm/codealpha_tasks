import java.sql.*;

public class RoomDAO {

    public static ResultSet getAvailableRooms() throws Exception {
        Connection con = DBConnection.getConnection();
        return con.prepareStatement(
            "SELECT * FROM rooms WHERE available=true"
        ).executeQuery();
    }

    public static double getRoomPrice(int roomId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT price FROM rooms WHERE room_id=? AND available=true");
        ps.setInt(1, roomId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getDouble(1);
        return -1;
    }

    public static void updateAvailability(int roomId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE rooms SET available=false WHERE room_id=?");
        ps.setInt(1, roomId);
        ps.executeUpdate();
    }
}
