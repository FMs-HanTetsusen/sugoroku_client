package com.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * クライアントの画面を管理するクラス：メインフレーム、
 * デスクトップアプリケーションのウィンドウに相当、
 * メインフレームを閉じる時点でプログラムは終了。
 * すべてのパネルや、ダイアログはメインフレームに依存。
 */

public class MainFrame extends JFrame {
    Container contentPane;
    TopPanel topPanel;
    MatchingPanel matchingPanel;

    MainFrame(String title) {
        super(title);
        setMinimumSize(new Dimension(1280, 960)); //画面の最小サイズを制限、画面内のコンポーネントが崩れた場合ここを拡大
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane = getContentPane();
        topPanel = new TopPanel(this);
        matchingPanel = new MatchingPanel(this);

        init();
    }

    public void init() {
        contentPane.add(topPanel);
        pack();
        setVisible(true);
    }

    public MatchingPanel getMatchingPanel() {
        return matchingPanel;
    }

    public LobbyPanel createLobbyPanel(String lobbyType, String lobbyID) {
        return new LobbyPanel(this, lobbyType, lobbyID);
    }

    public void changePanel(JPanel nextPanel) {
        contentPane.removeAll();
        contentPane.add(nextPanel);
        contentPane.validate(); //repaint()の前は必ずvalidate()
        contentPane.repaint();
    }

    public static MainFrame createFrame(String title) {
        return new MainFrame(title);
    }
}
