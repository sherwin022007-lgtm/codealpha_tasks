import java.sql.*;

public class BookingDAO {

    public static void saveBooking(String name, int roomId, int days, double bill)
            throws Exception {

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO bookings(customer_name,room_id,days,total_bill) VALUES(?,?,?,?)");

        ps.setString(1, name);
        ps.setInt(2, roomId);
        ps.setInt(3, days);
        ps.setDouble(4, bill);
        ps.executeUpdate();

        RoomDAO.updateAvailability(roomId);
        FileLogger.log("BOOKED → Room " + roomId + " | " + name + " | ₹" + bill);
    }
}
