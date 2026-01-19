import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentGradeTracker extends Application {

    private TextArea display = new TextArea();

    @Override
    public void start(Stage stage) {

        TextField nameField = new TextField();
        nameField.setPromptText("Student Name");

        TextField m1 = new TextField();
        m1.setPromptText("Subject 1 Marks");

        TextField m2 = new TextField();
        m2.setPromptText("Subject 2 Marks");

        TextField m3 = new TextField();
        m3.setPromptText("Subject 3 Marks");

        TextField m4 = new TextField();
        m4.setPromptText("Subject 4 Marks");

        TextField m5 = new TextField();
        m5.setPromptText("Subject 5 Marks");

        Button calcBtn = new Button("Generate Report");

        VBox leftPanel = new VBox(10,
                nameField, m1, m2, m3, m4, m5, calcBtn
        );
        leftPanel.setPadding(new Insets(20));
        leftPanel.setPrefWidth(250);

        display.setEditable(false);
        display.setPrefWidth(350);

        HBox root = new HBox(15, leftPanel, display);
        root.setPadding(new Insets(15));

        calcBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();

                int s1 = Integer.parseInt(m1.getText());
                int s2 = Integer.parseInt(m2.getText());
                int s3 = Integer.parseInt(m3.getText());
                int s4 = Integer.parseInt(m4.getText());
                int s5 = Integer.parseInt(m5.getText());

                int total = s1 + s2 + s3 + s4 + s5;
                double average = total / 5.0;

                String grade;
                if (average >= 90)
                    grade = "A+";
                else if (average >= 80)
                    grade = "A";
                else if (average >= 70)
                    grade = "B";
                else if (average >= 60)
                    grade = "C";
                else
                    grade = "Fail";

                display.setText(
                        "===== STUDENT REPORT =====\n\n" +
                        "Name      : " + name + "\n" +
                        "Total     : " + total + "\n" +
                        "Average   : " + average + "\n" +
                        "Grade     : " + grade
                );

            } catch (Exception ex) {
                display.setText("❌ Please enter valid numeric marks!");
            }
        });

        Scene scene = new Scene(root, 650, 350);
        stage.setTitle("Student Grade Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

