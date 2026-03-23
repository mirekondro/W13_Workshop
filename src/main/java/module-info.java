module dk.easv.w13_workshop {
    requires javafx.controls;
    requires javafx.fxml;

    opens dk.easv.w13_workshop to javafx.fxml;
    opens dk.easv.w13_workshop.foxconfig.model to javafx.base;

    exports dk.easv.w13_workshop;
    exports dk.easv.w13_workshop.foxconfig.model;
    exports dk.easv.w13_workshop.foxconfig.command;
    exports dk.easv.w13_workshop.foxconfig.repository;
}