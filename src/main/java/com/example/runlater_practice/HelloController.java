package com.example.runlater_practice;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML Label lb_time;
    @FXML Button btn_start, btn_stop;

    private boolean stop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stop = false;
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        while (!stop){
                            String time = sdf.format(new Date());

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    lb_time.setText(time);
                                }
                            });

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };
                thread.setDaemon(true);
                thread.start();
            }
        });

        btn_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stop = true;
            }
        });
    }
}