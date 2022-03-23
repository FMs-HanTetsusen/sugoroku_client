package com.example.app;

import com.ui.component.MainFrame;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Application main class
 */

public class App {
    private static final String appTitle = "すごろくゲーム";

    public static void main(String[] args) {
        //using system anti-aliasing text settings
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        //using Nimbus look and feel as default
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("INFO: Nimbus look and feel not found, using system default look and feel instead.");
        }

        MainFrame mainFrame = MainFrame.getMainFrame(appTitle);
        RequestHandler requestHandler = new RequestHandler();
        ResultHandler resultHandler = new ResultHandler(mainFrame);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.submit(requestHandler);
        executor.submit(resultHandler);
        executor.shutdown();
    }
}
