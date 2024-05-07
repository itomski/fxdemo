module de.lubowiecki.fxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens de.lubowiecki.fxdemo to javafx.fxml;
    exports de.lubowiecki.fxdemo;
}