import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class SimpleJavaAppRunnerFormController {

    public AnchorPane root;
    public TextArea txtCode;
    public TextArea txtOutput;
    public Button btnRun;

    public void btnRunOnAction(ActionEvent actionEvent) {

        String data =   "public class App{" +
                            "public static void main(String[] args{" +
                                txtCode.getText()+
                            "})"+
                        "}";

    }

}
