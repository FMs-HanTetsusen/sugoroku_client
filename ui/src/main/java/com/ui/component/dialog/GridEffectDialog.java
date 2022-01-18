package com.ui.component.dialog;

import com.ui.component.MainFrame;

import javax.swing.*;

public class GridEffectDialog extends AppDialog{
    GridEffectDialog(MainFrame mainFrame, int effect, int value) {
        super(mainFrame);
        setTitle("マス効果");
        switch (effect) {
            case 1 -> dialogMessage.setText("効果：道具一式獲得。");
            case 2 -> dialogMessage.setText("効果：" + value + "マス前進。");
            case 3 -> dialogMessage.setText("効果：" + value + "マス後退。");
            case 5 -> dialogMessage.setText("効果：全員道具一式獲得。");
            case 6 -> dialogMessage.setText("効果：全員道具一式損失。");
            case 7 -> dialogMessage.setText("効果：出発地点に戻る。");
        }
    }

    public static JDialog getDialog(MainFrame mainFrame, int effect, int value) {
        return new GridEffectDialog(mainFrame, effect, value);
    }
}
