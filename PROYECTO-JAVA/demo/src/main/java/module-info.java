module sambjavax {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
   

    
 
    opens sambjavax to javafx.fxml;
    
    exports sambjavax;
}
