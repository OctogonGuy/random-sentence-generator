module tech.octopusdragon.randomsentencegenerator {
    requires javafx.controls;
    requires javafx.fxml;


    exports tech.octopusdragon.randomsentencegenerator.gui;
    exports tech.octopusdragon.randomsentencegenerator.components;
    exports tech.octopusdragon.randomsentencegenerator.enums;
    exports tech.octopusdragon.randomsentencegenerator.util;

    opens tech.octopusdragon.randomsentencegenerator.gui to javafx.graphics, javafx.fxml;
    exports tech.octopusdragon.randomsentencegenerator;
    opens tech.octopusdragon.randomsentencegenerator to javafx.fxml, javafx.graphics;
}