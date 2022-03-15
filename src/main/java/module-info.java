module com.utcluj.travellingagencyproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.utcluj.travellingagencyproject.controller to org.hibernate.orm.core, javafx.fxml;
    opens com.utcluj.travellingagencyproject.model to org.hibernate.orm.core, javafx.base;
    opens com.utcluj.travellingagencyproject.repository to org.hibernate.orm.core;
    opens com.utcluj.travellingagencyproject.service to org.hibernate.orm.core;
    opens com.utcluj.travellingagencyproject to javafx.fxml, javafx.base;
    exports com.utcluj.travellingagencyproject;
    exports com.utcluj.travellingagencyproject.controller;
    opens com.utcluj.travellingagencyproject.exceptions to org.hibernate.orm.core;

}