module com.example.gestionbiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires java.sql;
    requires java.desktop;

    opens com.example.gestionbiblioteca to javafx.fxml;
    opens com.example.gestionbiblioteca.controller to javafx.fxml; // Agregado para permitir el acceso al controlador

    exports com.example.gestionbiblioteca;
    exports com.example.gestionbiblioteca.controller; // Exportando el paquete controller
}
