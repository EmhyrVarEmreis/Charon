package xyz.morecraft.dev.components.tabs;

import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Mine;
import xyz.morecraft.dev.types.mines.CrystalMine;
import xyz.morecraft.dev.types.mines.DeuteriumExtractor;
import xyz.morecraft.dev.types.mines.MetalMine;
import xyz.morecraft.dev.utils.TextUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static xyz.morecraft.dev.lang.Lang.getLang;

public class JPanelPlanetOnTabMines extends JPanel {

    private JLabel l1, l2, l3, l4;
    private JLabel[] ll = new JLabel[]{l1, l2, l3, l4};

    private JLabel lH, lD, lW, lM;
    private JLabel[] lK = new JLabel[]{lH, lD, lW, lM};

    private JLabel lProc, lLvl, lDop;
    private JLabel[] lR = new JLabel[]{lProc, lLvl, lDop};

    private JPanelPlanetOnTabButtons pDop1, pDop2, pPoz1, pPoz2, pProc1, pProc2;

    private int[] wyd;

    private Image img1, img2, img3, img4;
    private Mine mine;

    private String[] lblS = new String[]{getLang(0), getLang(1), getLang(2)};

    public JPanelPlanetOnTabMines(Mine mine, Coordinates coordinates) {
        int type = -1;
        this.setLayout(null);
        if (mine instanceof MetalMine) {
            img1 = LoadFromRes.loadImageAsImage("kop_m.png");
            type = 0;
        } else if (mine instanceof CrystalMine) {
            img1 = LoadFromRes.loadImageAsImage("kop_k.png");
            type = 1;
        } else if (mine instanceof DeuteriumExtractor) {
            img1 = LoadFromRes.loadImageAsImage("kop_d.png");
            type = 2;
        }

        this.setBorder(BorderFactory.createTitledBorder(null, lblS[type], TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));

        img2 = LoadFromRes.loadImageAsImage("b_004.png");
        img3 = LoadFromRes.loadImageAsImage("b_002.png");
        img4 = LoadFromRes.loadImageAsImage("b_003.png");

        for (int i = 0; i < 4; i++) {
            ll[i] = new JLabel(getLang(7 + i));
            ll[i].setBounds(35, 180 + (i * 20), 80, 20);
            ll[i].setFont(new Font("Tahoma", Font.BOLD, 12));
            add(ll[i]);
        }

        generateProduction();

        for (int i = 0; i < 4; i++) {
            lK[i] = new JLabel("", JLabel.RIGHT);
            lK[i].setBounds(140, 180 + (i * 20), 80, 20);
            lK[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
            add(lK[i]);
        }

        lR[0] = new JLabel("", JLabel.CENTER);
        lR[0].setBounds(81, 132, 100, 20);
        lR[0].setForeground(Color.WHITE);
        lR[0].setFont(new Font("Tahoma", Font.PLAIN, 18));
        lR[0].setToolTipText(getLang(16));
        add(lR[0]);

        lR[1] = new JLabel("", JLabel.CENTER);
        lR[1].setBounds(180, 94, 53, 20);
        lR[1].setForeground(Color.WHITE);
        lR[1].setFont(new Font("Tahoma", Font.PLAIN, 18));
        lR[1].setToolTipText(getLang(17));
        add(lR[1]);

        lR[2] = new JLabel("", JLabel.CENTER);
        lR[2].setBounds(27, 94, 53, 20);
        lR[2].setForeground(Color.WHITE);
        lR[2].setFont(new Font("Tahoma", Font.PLAIN, 18));
        lR[2].setToolTipText(getLang(15));
        add(lR[2]);


        setText(mine);

        pDop1 = new JPanelPlanetOnTabButtons(false, type, 1, coordinates);
        pDop1.setLocation(30, 65);
        add(pDop1);

        pDop2 = new JPanelPlanetOnTabButtons(true, type, 1, coordinates);
        pDop2.setLocation(51, 65);
        add(pDop2);

        pPoz1 = new JPanelPlanetOnTabButtons(false, type, 0, coordinates);
        pPoz1.setLocation(185, 65);
        add(pPoz1);

        pPoz2 = new JPanelPlanetOnTabButtons(true, type, 0, coordinates);
        pPoz2.setLocation(206, 65);
        add(pPoz2);

        pProc1 = new JPanelPlanetOnTabButtons(false, type, 2, coordinates);
        pProc1.setLocation(57, 133);
        add(pProc1);

        pProc2 = new JPanelPlanetOnTabButtons(true, type, 2, coordinates);
        pProc2.setLocation(179, 133);
        add(pProc2);

    }

    private void setText(Mine mine) {
        lR[0].setText(String.valueOf(mine.getPercent()) + "%");
        lR[1].setText(String.valueOf(mine.getLevel()));
        lR[2].setText(String.valueOf(mine.getSpeeder()) + "%");

        for (int i = 0; i < 4; i++) {
            lK[i].setText(TextUtils.customFormat("###,###.###", wyd[i]));
        }
    }

    public void refresh(Mine mine) {
        this.mine = mine;
        generateProduction();
        setText(mine);
    }


    private void generateProduction() {
        wyd = new int[]{
                mine.getProduction(), mine.getProduction() * 24, mine.getProduction() * 24 * 7, mine.getProduction() * 24 * 30
        };
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img1, (this.getWidth() / 2) - 50, 25, this);
        g.drawImage(img2, (this.getWidth() / 2) - 105, 87, this);
        g.drawImage(img3, (this.getWidth() / 2) + 50, 87, this);
        g.drawImage(img4, (this.getWidth() / 2) - 50, 125, this);
    }

}
