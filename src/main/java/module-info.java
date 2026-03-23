module dk.easv.w13_workshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.w13_workshop to javafx.fxml;
    exports dk.easv.w13_workshop;
}