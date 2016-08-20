package xyz.morecraft.dev.components;

import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.main.Main;
import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Planet;
import xyz.morecraft.dev.types.Tech;
import xyz.morecraft.dev.types.Universe;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class JPanelNewDatabase extends JPanel implements ActionListener {

    private JLabel lGracz, lNazwa, lTech1, lTech2, lTech3, lTech4, lWsp1, lWsp2, lWsp3, lKop1, lKop2, lKop3, lDop1, lDop2, lDop3, lProc1, lProc2,
            lProc3, lTemp, lUniName, lUniSpeedEco;
    private JTextField tfGracz, tfNazwa, tfTech1, tfTech2, tfTech3, tfTech4, tfWsp1, tfWsp2, tfWsp3, tfKop1, tfKop2, tfKop3, tfDop1, tfDop2, tfDop3,
            tfProc1, tfProc2, tfProc3, tfTemp, tfUniName, tfUniSpeedEco;
    private JPanel p1, p2, p3, p4, spP;
    private JButton bAdd, bAccept, bCancel;

    private JLabel[][] lKDP = new JLabel[][]{{lKop1, lKop2, lKop3}, {lDop1, lDop2, lDop3}, {lProc1, lProc2, lProc3}};
    private JTextField[][] tfKDP = new JTextField[][]{{tfKop1, tfKop2, tfKop3}, {tfDop1, tfDop2, tfDop3}, {tfProc1, tfProc2, tfProc3}};

    private JScrollPane sp;
    private List<Planet> planety;

    private boolean colorizePlanetOnListB = true;
    private boolean isEditing = false;

    public JPanelNewDatabase(List<Planet> arp, Tech tre, String nm, Universe uni) {
        this();
        planety = arp;
        tfTech1.setText(String.valueOf(tre.getArmourTech()));
        tfTech2.setText(String.valueOf(tre.getBattleTech()));
        tfTech3.setText(String.valueOf(tre.getProtectionTech()));
        tfTech4.setText(String.valueOf(tre.getPlasmaTech()));
        tfGracz.setText(nm);
        tfUniName.setText(uni.getNazwa());
        tfUniSpeedEco.setText(String.valueOf(uni.getSpeed()));

        reListPlanets();

        this.isEditing = true;
    }

    public JPanelNewDatabase() {
        String[][] sKDP = {{"Kop. M.:", "Kop. K.:", "Kop. D.:"}, {"Dop. M.:", "Dop. K.:", "Dop. D.:"}, {"Proc. M.:", "Proc. K.:", "Proc. D.:"}};

        setLayout(null);

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder(null, "Main", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
        p1.setBounds(0, 0, 150, 122);

        lGracz = new JLabel("Player:");
        lGracz.setBounds(10, 15, 50, 20);
        p1.add(lGracz);

        tfGracz = new JTextField("");
        tfGracz.setBounds(60, 15, 80, 20);
        tfGracz.setDocument(new JTextFieldLimit(30));
        p1.add(tfGracz);

        lTech1 = new JLabel("Armor:");
        lTech1.setBounds(10, 35, 100, 20);
        p1.add(lTech1);

        tfTech1 = new JTextField("");
        tfTech1.setBounds(120, 35, 20, 20);
        tfTech1.setDocument(new JTextFieldLimit(2, true));
        p1.add(tfTech1);

        lTech2 = new JLabel("Battle tech.:");
        lTech2.setBounds(10, 55, 100, 20);
        p1.add(lTech2);

        tfTech2 = new JTextField("");
        tfTech2.setBounds(120, 55, 20, 20);
        tfTech2.setDocument(new JTextFieldLimit(2, true));
        p1.add(tfTech2);

        lTech3 = new JLabel("Protection tech.:");
        lTech3.setBounds(10, 75, 100, 20);
        p1.add(lTech3);

        tfTech3 = new JTextField("");
        tfTech3.setBounds(120, 75, 20, 20);
        tfTech3.setDocument(new JTextFieldLimit(2, true));
        p1.add(tfTech3);

        lTech4 = new JLabel("Plasma tech.:");
        lTech4.setBounds(10, 95, 100, 20);
        p1.add(lTech4);

        tfTech4 = new JTextField("");
        tfTech4.setBounds(120, 95, 20, 20);
        tfTech4.setDocument(new JTextFieldLimit(2, true));
        p1.add(tfTech4);

        add(p1);

        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBorder(BorderFactory.createTitledBorder(null, "Planet", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
        p2.setBounds(150, 0, 333, 122);

        lWsp1 = new JLabel("Coordinates:");
        lWsp1.setBounds(10, 15, 75, 20);
        p2.add(lWsp1);

        tfWsp1 = new JTextField();
        tfWsp1.setBounds(90, 15, 13, 20);
        tfWsp1.setDocument(new JTextFieldLimit(1, true));
        p2.add(tfWsp1);

        lWsp2 = new JLabel(":");
        lWsp2.setBounds(106, 15, 4, 20);
        p2.add(lWsp2);

        tfWsp2 = new JTextField();
        tfWsp2.setBounds(112, 15, 25, 20);
        tfWsp2.setDocument(new JTextFieldLimit(3, true));
        p2.add(tfWsp2);

        lWsp3 = new JLabel(":");
        lWsp3.setBounds(140, 15, 4, 20);
        p2.add(lWsp3);

        tfWsp3 = new JTextField();
        tfWsp3.setBounds(146, 15, 19, 20);
        tfWsp3.setDocument(new JTextFieldLimit(2, true));
        p2.add(tfWsp3);

        for (int ii1 = 0; ii1 < 3; ii1++) {
            for (int ii2 = 0; ii2 < 3; ii2++) {
                lKDP[ii1][ii2] = new JLabel(sKDP[ii1][ii2]);
                lKDP[ii1][ii2].setBounds(10 + (ii1 * 110), 35 + (ii2 * 20), 60, 20);
                p2.add(lKDP[ii1][ii2]);
            }
        }

        for (int ii1 = 0; ii1 < 3; ii1++) {
            for (int ii2 = 0; ii2 < 3; ii2++) {
                tfKDP[ii1][ii2] = new JTextField();
                tfKDP[ii1][ii2].setBounds(70 + (ii1 * 110), 35 + (ii2 * 20), (ii1 == 2) ? 25 : 20, 20);
                tfKDP[ii1][ii2].setDocument(new JTextFieldLimit((ii1 == 2) ? 3 : 2, true));
                p2.add(tfKDP[ii1][ii2]);
            }
        }

        lTemp = new JLabel("Temp.:");
        lTemp.setBounds(10, 95, 60, 20);
        p2.add(lTemp);

        tfTemp = new JTextField();
        tfTemp.setBounds(70, 95, 20, 20);
        tfTemp.setDocument(new JTextFieldLimit(2, true));
        p2.add(tfTemp);

        lNazwa = new JLabel("Name :");
        lNazwa.setBounds(180, 15, 60, 20);
        p2.add(lNazwa);

        tfNazwa = new JTextField();
        tfNazwa.setBounds(225, 15, 90, 20);
        tfNazwa.setDocument(new JTextFieldLimit(60));
        p2.add(tfNazwa);

        bAdd = new JButton(LoadFromRes.loadImageAsIconImage("icons/Create.png"));
        bAdd.setBounds(100, 97, 226, 20);
        bAdd.addActionListener(this);
        p2.add(bAdd);

        add(p2);

        p3 = new JPanel();
        p3.setLayout(null);
        p3.setBorder(BorderFactory.createTitledBorder(null, "Planet list", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
        p3.setBounds(0, 122, 483, 210);

        spP = new JPanel();
        spP.setLayout(new BoxLayout(spP, BoxLayout.Y_AXIS));
        sp = new JScrollPane(spP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(10, 14, 463, 186);
        // sp.setLayout(null);

        p3.add(sp);

        add(p3);

        p4 = new JPanel();
        p4.setLayout(null);
        p4.setBorder(BorderFactory.createTitledBorder(null, "Universe", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
        p4.setBounds(0, 332, 483, 47);

        lUniName = new JLabel("Name:");
        lUniName.setBounds(10, 15, 50, 20);
        p4.add(lUniName);

        tfUniName = new JTextField();
        tfUniName.setBounds(50, 15, 80, 20);
        tfUniName.setDocument(new JTextFieldLimit(30));
        p4.add(tfUniName);

        lUniSpeedEco = new JLabel("Economy speed:");
        lUniSpeedEco.setBounds(140, 15, 100, 20);
        p4.add(lUniSpeedEco);

        tfUniSpeedEco = new JTextField();
        tfUniSpeedEco.setBounds(240, 15, 22, 20);
        tfUniSpeedEco.setDocument(new JTextFieldLimit(3, false, true));
        tfUniSpeedEco.setText("1.0");
        p4.add(tfUniSpeedEco);

        add(p4);

        bAccept = new JButton(LoadFromRes.loadImageAsIconImage("icons/Apply.png"));
        bAccept.setBounds(210, 379, 32, 32);
        bAccept.addActionListener(this);
        add(bAccept);

        bCancel = new JButton(LoadFromRes.loadImageAsIconImage("icons/Cancel.png"));
        bCancel.setBounds(242, 379, 32, 32);
        bCancel.addActionListener(this);
        add(bCancel);

        planety = new ArrayList<>();
        planety.clear();
        clearAll();
    }

    public void loadPlanet(Planet pq) {
        tfKDP[0][0].setText(String.valueOf(pq.getMetalMine().getLevel()));
        tfKDP[0][1].setText(String.valueOf(pq.getCrystalMine().getLevel()));
        tfKDP[0][2].setText(String.valueOf(pq.getDeuteriumExtractor().getLevel()));

        tfKDP[1][0].setText(String.valueOf(pq.getMetalMine().getSpeeder()));
        tfKDP[1][1].setText(String.valueOf(pq.getCrystalMine().getSpeeder()));
        tfKDP[1][2].setText(String.valueOf(pq.getDeuteriumExtractor().getSpeeder()));

        tfKDP[2][0].setText(String.valueOf(pq.getMetalMine().getPercent()));
        tfKDP[2][1].setText(String.valueOf(pq.getCrystalMine().getPercent()));
        tfKDP[2][2].setText(String.valueOf(pq.getDeuteriumExtractor().getPercent()));

        tfWsp1.setText(String.valueOf(pq.getCoordinates().getGalaxy()));
        tfWsp2.setText(String.valueOf(pq.getCoordinates().getSystem()));
        tfWsp3.setText(String.valueOf(pq.getCoordinates().getPosition()));

        tfTemp.setText(String.valueOf(pq.getTemperature()));
        tfNazwa.setText(pq.getName());

        deletePlanetFromListByCoordinates(pq.getCoordinates());
    }

    public void deletePlanetFromListByCoordinates(Coordinates c) {
        for (int i = (planety.size() - 1); i >= 0; i--) {
            if (c.equals(planety.get(i).getCoordinates())) {
                planety.remove(i);
            }
        }
        reListPlanets();
    }

    private void closeThis() {
        this.getParent().getParent().getParent().getParent().setVisible(false);
        ((JDialog) this.getParent().getParent().getParent().getParent()).dispose();
    }

    private void clearAll() {
        for (int ii1 = 0; ii1 < 3; ii1++) {
            tfKDP[0][ii1].setText("");
            tfKDP[1][ii1].setText("0");
            tfKDP[2][ii1].setText("100");
        }
        tfNazwa.setText("");
        tfWsp1.setText("");
        tfWsp2.setText("");
        tfWsp3.setText("");
        tfTemp.setText("");
    }

    private void endEverything() {
        Main.app.coreSetPlanets(
                planety,
                new Tech(Integer.valueOf(tfTech2.getText()), Integer.valueOf(tfTech1.getText()), Integer.valueOf(tfTech3.getText()), Integer
                        .valueOf(tfTech4.getText())), tfGracz.getText(), new Universe(tfUniName.getText(), Double.valueOf(tfUniSpeedEco.getText())));
        endSave();
    }

    private void endSave() {
        closeThis();
        if (isEditing) {
            Main.app.makeSilentSaveDialog();
        } else {
            Main.app.openSaveDialog(true);
        }
    }

    private boolean checkValidationPlanet() {
        boolean v = true;
        for (int ii1 = 0; ii1 < 3; ii1++) {
            for (int ii2 = 0; ii2 < 3; ii2++) {
                if (tfKDP[ii1][ii2].getText().length() == 0) {
                    v = false;
                    break;
                }
            }
        }
        if (v) {
            for (JTextField tf : tfKDP[2]) {
                if (Integer.valueOf(tf.getText()) > 100) {
                    v = false;
                    break;
                }
            }
        }
        if (v) {
            if (tfNazwa.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfWsp1.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfWsp2.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfWsp3.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfTemp.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfUniName.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfUniSpeedEco.getText().length() == 0) {
                v = false;
            }
        }
        return v;
    }

    private boolean checkValidationRest() {
        boolean v = true;
        if (tfGracz.getText().length() == 0) {
            v = false;
        }
        if (v) {
            if (tfTech1.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfTech2.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfTech3.getText().length() == 0) {
                v = false;
            }
        }
        if (v) {
            if (tfTech4.getText().length() == 0) {
                v = false;
            }
        }
        return v;
    }

    private void planetAdd() {
        if (checkValidationPlanet()) {
            planety.add(makePlanet());
            spP.add(new JPanelPlanetOnList(planety.get(planety.size() - 1), colorizePlanetOnList()));
            clearAll();
            spP.repaint();
            spP.revalidate();
        } else {
            makeDialogError();
        }
    }

    private boolean colorizePlanetOnList() {
        if (colorizePlanetOnListB) {
            colorizePlanetOnListB = false;
            return true;
        } else {
            colorizePlanetOnListB = true;
            return false;
        }
    }

    private Planet makePlanet() {
        return new Planet(tfNazwa.getText(), new Coordinates(Integer.valueOf(tfWsp1.getText()), Integer.valueOf(tfWsp2.getText()), Integer.valueOf(tfWsp3
                .getText())), Integer.valueOf(tfTemp.getText()), Integer.valueOf(tfKDP[0][0].getText()), Integer.valueOf(tfKDP[0][1].getText()),
                Integer.valueOf(tfKDP[0][2].getText()), Integer.valueOf(tfKDP[2][0].getText()), Integer.valueOf(tfKDP[2][1].getText()),
                Integer.valueOf(tfKDP[2][2].getText()), Integer.valueOf(tfKDP[1][0].getText()), Integer.valueOf(tfKDP[1][1].getText()),
                Integer.valueOf(tfKDP[1][2].getText()));
    }

    private void makeDialogError() {
        JOptionPane.showMessageDialog(this, "<html><center>Fields are filled incorrectly!</center></html>", "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void reListPlanets() {
        spP.removeAll();
        for (Planet po : planety) {
            spP.add(new JPanelPlanetOnList(po, colorizePlanetOnList()));
        }
        spP.repaint();
        spP.revalidate();
        sp.repaint();
        sp.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == bAdd) {
            planetAdd();
        } else if (o == bAccept) {
            if (checkValidationRest()) {
                endEverything();
            } else {
                makeDialogError();
            }
        } else if (o == bCancel) {
            closeThis();
        }

    }

}
