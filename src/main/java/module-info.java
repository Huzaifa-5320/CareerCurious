module com.jobportal{
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jobportal to javafx.fxml;
    exports com.jobportal;
}