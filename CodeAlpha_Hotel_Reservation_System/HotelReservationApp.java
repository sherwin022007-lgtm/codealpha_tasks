import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.ResultSet;

public class HotelReservationApp extends Application {

    TextArea display = new TextArea();

    @Override
    public void start(Stage stage) {

        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");

        TextField roomField = new TextField();
        roomField.setPromptText("Room ID");

        TextField daysField = new TextField();
        daysField.setPromptText("No. of Days");

        Button searchBtn = new Button("Search Rooms");
        Button bookBtn = new Button("Book Room");

        VBox left = new VBox(10, nameField, roomField, daysField, searchBtn, bookBtn);
        left.setPadding(new Insets(20));
        left.setPrefWidth(250);

        display.setEditable(false);

        HBox root = new HBox(left, display);
        root.setPadding(new Insets(10));

        searchBtn.setOnAction(e -> {
            try {
                display.clear();
                ResultSet rs = RoomDAO.getAvailableRooms();
                while (rs.next()) {
                    display.appendText(
                        "Room " + rs.getInt(1) + " | " +
                        rs.getString(2) + " | ₹" +
                        rs.getDouble(3) + "\n");
                }
            } catch (Exception ex) {
                display.setText("Error loading rooms");
            }
        });

        bookBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int roomId = Integer.parseInt(roomField.getText());
                int days = Integer.parseInt(daysField.getText());

                double price = RoomDAO.getRoomPrice(roomId);
                double bill = BillingService.calculateTotal(price, days);

                BookingDAO.saveBooking(name, roomId, days, bill);
                display.setText("Booking Successful!\nTotal Bill: ₹" + bill);

            } catch (Exception ex) {
                display.setText("Booking Failed!");
            }
        });

        stage.setScene(new Scene(root, 700, 400));
        stage.setTitle("Hotel Reservation System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
