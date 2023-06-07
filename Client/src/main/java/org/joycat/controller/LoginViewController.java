package org.joycat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.joycat.entity.User;
import org.joycat.entity.UserShort;
import org.joycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@FxmlView("login-view.fxml")
@Slf4j
public class LoginViewController {

    @Autowired
    private static ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    @FXML
    private TextField tfLoginSignIn;
    @FXML
    private TextField tfLoginSignUp;
    @FXML
    private PasswordField pfPasswordSignIn;
    @FXML
    private PasswordField pfPasswordSignUp;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;


    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;

    @Autowired
    private MainViewController mainViewController;

    public void switchToMain() {
        Window window = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        log.info("ApplicationContext is: {}", applicationContext);
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainViewController.class);
        Stage stage = (Stage) window;
        try {
            stage.setScene(new Scene(root));
            stage.show();
        } catch (NullPointerException e) {
            log.error("No windows are in status <isSHOWING>. Error message: {}", e.getMessage());
        }


    }

    public void signIn(ActionEvent event) {
        userService.validateUser(new UserShort(tfLoginSignIn.getText(), pfPasswordSignIn.getText()));
    }

    public void signUp(ActionEvent event) {

        User user = new User();
        user.setLogin(tfLoginSignUp.getText());
        user.setPassword(pfPasswordSignUp.getText());
        user.setEmail(tfEmail.getText());
        user.setName(tfName.getText());
        user.setPhone(tfPhone.getText());
        userService.createNewUser(user);

        switchToMain();
    }

}
