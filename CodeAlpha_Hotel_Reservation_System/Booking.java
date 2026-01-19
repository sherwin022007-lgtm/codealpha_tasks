public class Booking {
    private int bookingId;
    private String customerName;
    private int roomId;
    private int days;
    private double totalBill;

    public Booking(int bookingId, String customerName, int roomId, int days, double totalBill) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.days = days;
        this.totalBill = totalBill;
    }
}
