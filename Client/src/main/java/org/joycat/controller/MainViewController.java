package org.joycat.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.NoArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.joycat.entity.Message;
import org.joycat.service.MessageService;
import org.joycat.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@NoArgsConstructor
@FxmlView("main-view.fxml")
public class MainViewController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private OnlineService onlineService;

    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfRecipient;
    @FXML
    private TextArea taMessage;
    @FXML
    private Label lblMessage;

    @FXML
    private Button btnSend;
    @FXML
    private Button btnOnline;

    public void sendMessage(ActionEvent event) {
        Message message = new Message();
        message.setSender(tfLogin.getText());
        message.setRecipient(tfRecipient.getText());
        message.setText(taMessage.getText());
        message.setSendTime(LocalDateTime.now());
        messageService.sendMessage(message);
    }

    public void turnOnline(ActionEvent event) {
        onlineService.turnOnline();
    }

    @ResponseBody
    @PostMapping("/msg")
    public void receiveMessage(@RequestBody Message message) {
        Platform.runLater(() -> lblMessage.setText(message.getText()));
    }

}
