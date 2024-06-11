module com.jobportal {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jobportal to javafx.fxml;
    opens com.jobportal.controller to javafx.fxml;
    exports com.jobportal;
    exports com.jobportal.controller;
}
