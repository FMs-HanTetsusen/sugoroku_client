package com.ui.component.label;

import com.ui.component.ComponentFactory;

import javax.swing.*;

/**
 * ラベルを生成するためのラベルファクトリークラス。
 * ラベルの配属先によって分類、
 * トップ、マッチング、ロビー、ゲームパネルや、ダイアログなど、
 * 必要に応じて追加可能。
 */

public class LabelFactory implements ComponentFactory {
    @Override
    public JLabel getLabel(String panel, String label) {
        return switch (panel) {
            case "top" -> new TopPanelLabel(label);
            case "matching" -> new MatchingPanelLabel(label);
            case "lobby" -> new LobbyPanelLabel();
            case "game" -> null; //TODO: ゲームパネルラベルクラスは要作成
            default -> null;
        };
    }

    //ラベルファクトリーであるため、ラベル以外の生成メソッドはnullのまま
    @Override
    public JButton getButton(String panel, String label) {
        return null;
    }
}
