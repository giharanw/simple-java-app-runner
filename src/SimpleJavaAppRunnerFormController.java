import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleJavaAppRunnerFormController {

    public AnchorPane root;
    public TextArea txtCode;
    public TextArea txtOutput;
    public Button btnRun;

    public void btnRunOnAction(ActionEvent actionEvent) throws IOException {

        String data =   "public class App{" +
                            "public static void main(String[] args{" +
                                txtCode.getText()+
                            "})"+
                        "}";

        String tmpDir = System.getProperty("java.io.tmpdir");
        Path tmpFilePath = Paths.get(tmpDir,"App.java");
        Files.write(tmpFilePath,data.getBytes());

    }

}
