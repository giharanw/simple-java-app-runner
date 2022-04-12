import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleJavaAppRunnerFormController {

    public AnchorPane root;
    public TextArea txtCode;
    public TextArea txtOutput;
    public Button btnRun;

    public void btnRunOnAction(ActionEvent actionEvent) throws IOException {

        try {
            String data =   "public class App{" +
                                "public static void main(String[] args){" +
                                    txtCode.getText() +
                                "}" +
                            "}";

            String tmpDir = System.getProperty("java.io.tmpdir");
            Path tmpFilePath = Paths.get(tmpDir, "App.java");
            Files.write(tmpFilePath, data.getBytes());

            Process javac = Runtime.getRuntime().exec("javac " + tmpFilePath);
            int exitCode = javac.waitFor();

            if (exitCode == 0) {
                Process java = Runtime.getRuntime().exec("java -cp " + tmpDir + " App");
                exitCode = java.waitFor();

                if (exitCode == 0) {
                    readStream(java.getInputStream());
                } else {
                    readStream(java.getErrorStream());
                }
            } else {
                readStream(javac.getErrorStream());
            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        } finally {
            /* Deleting java and class files from temp directory */
            Files.deleteIfExists(Paths.get(System.getProperty("java.io.tmpdir") ,"App.java"));
            Files.deleteIfExists(Paths.get(System.getProperty("java.io.tmpdir") , "App.class"));
        }
    }

    private void readStream(InputStream is) throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        txtOutput.setText(new String(buffer));
        is.close();
    }

}
