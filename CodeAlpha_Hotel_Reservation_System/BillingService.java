public class BillingService {

    private static final double GST_RATE = 0.12;

    public static double calculateTotal(double price, int days) {
        double base = price * days;
        double gst = base * GST_RATE;
        return base + gst;
    }
}
