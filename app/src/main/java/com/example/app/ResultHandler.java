package com.example.app;

import com.data.Protocol;
import com.data.Request;
import com.data.buffer.GameBuffer;
import com.data.buffer.ResultBuffer;
import com.ui.component.UIKeyword;
import com.ui.component.FactoryConstructor;
import com.ui.component.MainFrame;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ResultHandler implements Runnable {
    MainFrame mainFrame;
    JSONObject resultObject;

    ResultHandler(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void login() {
        if (resultObject.getBoolean(Protocol.Status.toString())) {
            SwingUtilities.invokeLater(() -> {
                mainFrame.setMatchingPanel();
                mainFrame.changePanel(mainFrame.getMatchingPanel());
            });
        } else {
            FactoryConstructor.getFactory(UIKeyword.Dialog).getDialog(mainFrame, UIKeyword.LoginDialog, null, null).setVisible(true);
        }
    }

    private void signup() {
        FactoryConstructor.getFactory(UIKeyword.Dialog)
                .getDialog(mainFrame, UIKeyword.SignupDialog, resultObject.getBoolean(Protocol.Status.toString()), null).setVisible(true);
    }

    private void updatePlayerList() {
        JSONArray jsonArray = resultObject.getJSONArray(Protocol.PlayerList.toString());
        mainFrame.getLobbyPanel().getPlayerListArea().setText("");
        for (int i = 0; i < jsonArray.length(); i++) {
            mainFrame.getLobbyPanel().getPlayerListArea().append("・" + jsonArray.getString(i) + "\n\n");
        }
    }

    private void randomMatch() {
        if (resultObject.getBoolean(Protocol.Status.toString()) && GameBuffer.getInstance().getLobbyID() == null) {
            GameBuffer.getInstance().setLobbyID(resultObject.getString(Protocol.LobbyID.toString()));
            SwingUtilities.invokeLater(() -> {
                mainFrame.setLobbyPanel(UIKeyword.RandomLobby.toString(), resultObject.getString(Protocol.LobbyID.toString()));
                mainFrame.changePanel(mainFrame.getLobbyPanel());
                updatePlayerList();
            });
        } else if (GameBuffer.getInstance().getLobbyID() != null) {
            SwingUtilities.invokeLater(this::updatePlayerList);
        }
    }

    private void privateMatch() {
        if (resultObject.getBoolean(Protocol.Status.toString()) && GameBuffer.getInstance().getLobbyID() == null) {
            GameBuffer.getInstance().setLobbyID(resultObject.getString(Protocol.LobbyID.toString()));
            SwingUtilities.invokeLater(() -> {
                mainFrame.setLobbyPanel(UIKeyword.PrivateLobby.toString(), resultObject.getString(Protocol.LobbyID.toString()));
                mainFrame.changePanel(mainFrame.getLobbyPanel());
                updatePlayerList();
            });
        } else if (GameBuffer.getInstance().getLobbyID() != null) {
            SwingUtilities.invokeLater(this::updatePlayerList);
        }
    }

    private void checkRecord() {
        String gameRecord = "あなたの対戦成績：" + resultObject.getInt(Protocol.Win.toString()) + "勝、" + resultObject.getInt(Protocol.Lose.toString()) + "敗です";
        FactoryConstructor.getFactory(UIKeyword.Dialog).getDialog(mainFrame, UIKeyword.GameRecordDialog, null, gameRecord).setVisible(true);
    }

    private void exitLobby() {
        SwingUtilities.invokeLater(this::updatePlayerList);
    }

    private void startGame() {

    }

    private void receiveChat() {
        SwingUtilities.invokeLater(() -> mainFrame.getLobbyPanel().getChatArea()
                .append("・" + resultObject.getString(Protocol.Username.toString()) + ": " + resultObject.getString(Protocol.Message.toString()) + '\n'));
    }

    @Override
    public void run() {
        while (true) {
            if (!ResultBuffer.getInstance().isEmpty()) {
                resultObject = ResultBuffer.getInstance().retrieveResult();
                switch (Request.valueOf(resultObject.getString(Protocol.Result.toString()))) {
                    case LOGIN -> login();
                    case SIGNUP -> signup();
                    case RANDOM_MATCH -> randomMatch();
                    case PRIVATE_MATCH -> privateMatch();
                    case CHECK_RECORD -> checkRecord();
                    case EXIT_LOBBY -> exitLobby();
                    case START_GAME -> startGame();
                    case SEND_CHAT -> receiveChat();
                    default -> System.err.println("Error: illegal protocol detected.");
                }
            } else {
                try {
                    TimeUnit.MICROSECONDS.sleep(100); //result読み取り処理が追いつかない場合、このタイムアウトを延長
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}