import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Добавить на экран компоновщик-сетку с одним столбцом и добавить над
 * существующей кнопкой следующие компоненты в заданном порядке: JLabel
 * с заголовком «Выберите режим игры», сгруппированные в ButtonGroup
 * переключаемые JRadioButton с указанием режимов «Человек против
 * компьютера» и «Человек против человека», JLabel с заголовком «Выберите
 * размеры поля», JLabel с заголовком «Установленный размер поля:», JSlider
 * со значениями 3..10, JLabel с заголовком «Выберите длину для победы»,
 * JLabel с заголовком «Установленная длина:», JSlider со значениями 3..10
 */
public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    private int size = 3;
    private int sizeWin = 3;

    private int choice = 0;


    JButton btnStart;

    SettingWindow(GameWindow gameWindow){
        btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.startNewGame(choice, size, size, sizeWin);
            }
        });

        JPanel modePanel = createModeComponent();
        JPanel sizePanel = createSizeComponent();
        JPanel winLengthPanel = createWinLengthComponent();

        JPanel menu = new JPanel(new GridLayout(3, 1));
        menu.add(modePanel);
        menu.add(sizePanel);
        menu.add(winLengthPanel);

        add(menu);
        add(btnStart, BorderLayout.SOUTH);
    }

    private JPanel createModeComponent() {
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message = new JLabel("Выберите режим игры");

        JRadioButton choice1 = new JRadioButton("Челове против человека");
        JRadioButton choice2 = new JRadioButton("Челове против компьютера");

        choice1.setSelected(true);

        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 0;
            }
        });

        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 1;
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(choice1);
        buttonGroup.add(choice2);

        modePanel.add(message);
        modePanel.add(choice1);
        modePanel.add(choice2);

        return  modePanel;
    }

    private JPanel createSizeComponent() {
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message1 = new JLabel("Выберите размеры поля");
        JLabel message2 = new JLabel("Установленный размер поля: "+ size);

        JSlider sizer = new JSlider(3, 10, size);
        sizer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size = sizer.getValue();
                message2.setText("Установленный размер поля: "+ size);
            }
        });


        modePanel.add(message1);
        modePanel.add(message2);
        modePanel.add(sizer);


        return  modePanel;
    }

    private JPanel createWinLengthComponent() {
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message1 = new JLabel("Выберите длину для победы");
        JLabel message2 = new JLabel("Длина победы: "+ sizeWin);

        JSlider sizerWin = new JSlider(3, 10, sizeWin);
        sizerWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sizerWin.getValue() > size){
                    sizeWin = size;
                }
                else {
                    sizeWin = sizerWin.getValue();
                }
                message2.setText("Длина победы:: "+ sizeWin);
            }
        });


        modePanel.add(message1);
        modePanel.add(message2);
        modePanel.add(sizerWin);


        return  modePanel;
    }

}
