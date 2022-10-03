module com.example.bookingsystemfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bookingsystemfinal to javafx.fxml;
    exports com.example.bookingsystemfinal;
}