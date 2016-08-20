package xyz.morecraft.dev.components.summary;

import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.utils.TextUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static xyz.morecraft.dev.lang.Lang.getLang;


public class JPanelSummaryMain extends JPanel {

    private Image img1, img2, img3, img4;
    private int imgW = 48;
    private int a, b, c, o;
    private int shift = 50;
    private JLabel[] lbl1 = {null, null, null, null};
    private String[] lang1 = {getLang(3), getLang(4), getLang(5), getLang(6)};
    private JLabel[] lbl2 = {null, null, null, null, null};
    private String[] lang2 = {getLang(7), getLang(8), getLang(9), getLang(10), getLang(18)};
    private JLabel[][] lblM = {{null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null}};

    public JPanelSummaryMain() {
        setBounds(5, 12, 600, 187);
        setBorder(BorderFactory.createTitledBorder(null, "Mterials/Points", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
        setLayout(null);

        img1 = LoadFromRes.loadImageAsImage("m.png");
        img2 = LoadFromRes.loadImageAsImage("k.png");
        img3 = LoadFromRes.loadImageAsImage("d.png");
        img4 = LoadFromRes.loadImageAsImage("a_2.png");

        a = (int) ((this.getWidth() / 5.0));
        b = (int) (a - (imgW / 2.0) + shift);
        c = (int) (a - 34 + shift);
        o = (int) ((imgW + a) / 2.0);

        for (int i = 0; i < 4; i++) {
            lbl1[i] = new JLabel(lang1[i], JLabel.CENTER);
            lbl1[i].setBounds(c + (a * i), 50, 68, 20);
            lbl1[i].setFont(new Font("Tahoma", Font.BOLD, 12));
            add(lbl1[i]);
        }

        for (int i = 0; i < 5; i++) {
            lbl2[i] = new JLabel(lang2[i]);
            lbl2[i].setBounds(10, 72 + (i * 21), 100, 20);
            lbl2[i].setFont(new Font("Tahoma", Font.BOLD, 12));
            add(lbl2[i]);
        }

        for (int i1 = 0; i1 < 4; i1++) {
            for (int i2 = 0; i2 < 5; i2++) {
                lblM[i1][i2] = new JLabel("", JLabel.RIGHT);
                lblM[i1][i2].setBounds((int) (b + o + (a * (i1 - 1)) - 4), 72 + (i2 * 21), 100, 20);
                lblM[i1][i2].setFont(new Font("Tahoma", Font.PLAIN, 12));
                add(lblM[i1][i2]);
            }
        }

    }

    public void setValues(int[] wydSum) {
        int[][] wyd = {{wydSum[0], wydSum[0] * 24, wydSum[0] * 24 * 7, wydSum[0] * 24 * 30, 1000},
                {wydSum[1], wydSum[1] * 24, wydSum[1] * 24 * 7, wydSum[1] * 24 * 30, (int) Math.round((wydSum[1] / (double) (wydSum[0])) * 1000)},
                {wydSum[2], wydSum[2] * 24, wydSum[2] * 24 * 7, wydSum[2] * 24 * 30, (int) Math.round((wydSum[2] / (double) (wydSum[0])) * 1000)},
                {generateWydSumP(wydSum, 1), generateWydSumP(wydSum, 24), generateWydSumP(wydSum, 24 * 7), generateWydSumP(wydSum, 24 * 7 * 30), 0}};
        for (int i1 = 0; i1 < 4; i1++) {
            for (int i2 = 0; i2 < 5; i2++) {
                lblM[i1][i2].setText(TextUtils.customFormat("###,###.###", wyd[i1][i2]));
            }
        }
        lblM[3][4].setText("000 000 000");

    }

    private int generateWydSumP(int[] wydSum, int mult) {
        return (int) Math.round((wydSum[0] + wydSum[1] + wydSum[2]) * mult / 1000.0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image[] img = {img1, img2, img3, img4};
        for (int i = 0; i < 4; i++) {
            g.drawImage(img[i], b + (a * i), 20, this);
        }

        for (int i = -1; i < 3; i++) {
            g.drawLine((int) (b + o + (a * i)), 20, (int) (b + o + (a * i)), 176);
        }

        for (int i = 0; i < 5; i++) {
            g.drawLine(10, (int) (72 + (i * 21)), 585, (int) (72 + (i * 21)));
        }
    }

}
