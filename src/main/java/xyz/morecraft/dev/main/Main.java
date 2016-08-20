package xyz.morecraft.dev.main;

import xyz.morecraft.dev.components.JPanelInformation;
import xyz.morecraft.dev.components.JPanelNewDatabase;
import xyz.morecraft.dev.functions.CoreBasicMain;
import xyz.morecraft.dev.functions.LoadFromRes;
import xyz.morecraft.dev.objects.CharonDbFileFilter;
import xyz.morecraft.dev.objects.Generator;
import xyz.morecraft.dev.types.Coordinates;
import xyz.morecraft.dev.types.Planet;
import xyz.morecraft.dev.types.Tech;
import xyz.morecraft.dev.types.Universe;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

import static xyz.morecraft.dev.functions.GetAppPatch.getAppDataRoaming;
import static xyz.morecraft.dev.functions.TextUtils.processFileNameToSave;


public class Main extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu mFile, mHelp, mEdit;
    public final JMenu mFileOpenRecent;
    private JMenuItem miFileOpen, miFileSave, miFileSaveAs, mHelpAbout, mEditAll, mEditSetLoadInstant;
    private JMenuItem miFileNowy;
    public JMenuItem miFileOpenRecentBrak;
    public static Main app;
    public static String MAIN_APPPATH = getAppDataRoaming() + "\\.charon-og-system";
    public static String DEFAULT_LOC = null;
    private static Generator core;

    public Main() {
        setTitle("Charon OG Miner System Alpha");
        setSize(800, 605);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(exitListener);

        CoreBasicMain.setUILookAndFeel();
        setIconImage(LoadFromRes.loadImageAsImage("icons/icon_app.png"));

        menuBar = new JMenuBar();

        mFile = new JMenu("File");
        mFile.setIcon(LoadFromRes.loadImageAsIconImage("icons/New_document.png"));
        // menu1.addMenuListener(new MyMenuListener());

        miFileNowy = new JMenuItem("New...", LoadFromRes.loadImageAsIconImage("icons/New_document.png"));
        miFileNowy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        miFileNowy.addActionListener(this);

        miFileOpen = new JMenuItem("Open file...", LoadFromRes.loadImageAsIconImage("icons/Folder.png"));
        miFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        miFileOpen.addActionListener(this);

        mFileOpenRecent = new JMenu("Open recent...");
        mFileOpenRecent.setIcon(LoadFromRes.loadImageAsIconImage("icons/Briefcase.png"));
        miFileOpenRecentBrak = new JMenuItem("Brak");
        miFileOpenRecentBrak.setEnabled(false);
        mFileOpenRecent.add(miFileOpenRecentBrak);

        miFileSave = new JMenuItem("Save", LoadFromRes.loadImageAsIconImage("icons/Save.png"));
        miFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        miFileSave.addActionListener(this);

        miFileSaveAs = new JMenuItem("Save as...", LoadFromRes.loadImageAsIconImage("icons/Save.png"));
        miFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        miFileSaveAs.addActionListener(this);

        mFile.add(miFileNowy);
        mFile.addSeparator();
        mFile.add(miFileOpen);
        mFile.add(mFileOpenRecent);
        mFile.addSeparator();
        mFile.add(miFileSave);
        mFile.add(miFileSaveAs);
        menuBar.add(mFile);

        mEdit = new JMenu("Edit");
        mEdit.setIcon(LoadFromRes.loadImageAsIconImage("icons/Modify.png"));
        mEditAll = new JMenuItem("Edit all...", LoadFromRes.loadImageAsIconImage("icons/Notes.png"));
        mEditAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        mEditAll.addActionListener(this);
        mEditAll.setEnabled(false);
        mEditSetLoadInstant = new JMenuItem("Load on startup", LoadFromRes.loadImageAsIconImage("icons/Display.png"));
        mEditSetLoadInstant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        mEditSetLoadInstant.addActionListener(e -> core.makeCurrentPathLoadInstant());
        //mEditSetLoadInstant.setEnabled(false);
        mEdit.add(mEditAll);
        mEdit.add(mEditSetLoadInstant);
        menuBar.add(mEdit);

        mHelp = new JMenu("Help");
        mHelp.setIcon(LoadFromRes.loadImageAsIconImage("icons/Help.png"));
        mHelpAbout = new JMenuItem("About...", LoadFromRes.loadImageAsIconImage("icons/Help_book.png"));
        mHelpAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        mHelpAbout.addActionListener(this);
        mHelp.add(mHelpAbout);
        menuBar.add(mHelp);

        // add(menuBar);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        app = new Main();
        core = new Generator();
        core.abandonedActions();
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == miFileOpen) {
            openOpenDialog();
        } else if (o == mFileOpenRecent) {
            // TODO Open Recent
        } else if (o == miFileSave) {
            core.saveFile(null);
        } else if (o == miFileSaveAs) {
            openSaveDialog(false);
        } else if (o == miFileNowy) {
            makeDatabaseDialog(null, null, null, null, false);
        } else if (o == mHelpAbout) {
            makeInformationAbout();
        } else if (o == mEditAll) {
            makeDatabaseDialog(core.getPlanets(), core.getTech(), core.getPlayer(), core.getUni(), true);
        }

    }

    public void openSaveDialog(boolean g) {
        final JFileChooser fc = new JFileChooser(DEFAULT_LOC);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new CharonDbFileFilter());
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (g) {
                core.saveFileAndProcess(processFileNameToSave(file.getPath()));
            } else {
                int n = 0;
                if (file.exists()) {
                    Object[] options = {"Yes, replace", "Cancel"};
                    n = JOptionPane.showOptionDialog(Main.app, "<html><center>Selected file exests, override?</center></html>",
                            "Attention!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
                if (n == 0) {
                    core.saveFile(processFileNameToSave(file.getPath()));
                }
            }
        } else {
            // log.append("" + newline);
        }
    }

    public void makeSilentSaveDialog() {
        core.saveFile(null);
    }

    public void coreProcessFile(String path) {
        core.processFile(path);
    }

    public void coreSetPlanets(List<Planet> arp, Tech tre, String nm, Universe uni) {
        core.processAll(arp, tre, nm, uni);
    }

    public void coreSetCurrentFile(String ty) {
        //System.out.println(ty);
        //core.setCurrentFile(ty);
    }

    public void changeSthLiczOnPlanet(int co, int gdzieZm, boolean czyZwiekszyc, Coordinates gdzie) {
        core.changeAndCalculateOnPlanet(co, gdzieZm, czyZwiekszyc, gdzie);
    }

    public Planet getPlanetByCoords(Coordinates cort) {
        return core.getPlanetByCoordinates(cort);
    }

    public Tech getTech() {
        return core.getTech();
    }

    public Universe getUni() {
        return core.getUni();
    }

    public void setContentEditable(boolean r) {
        mEditAll.setEnabled(r);
    }

    private void makeDatabaseDialog(List<Planet> arp, Tech tre, String nm, Universe uni, boolean b) {
        JDialog newDb = new JDialog(this, true);
        newDb.setSize(490, 442);
        newDb.setResizable(false);
        newDb.setLocationRelativeTo(this);
        if (arp == null || tre == null || nm == null) {
            newDb.add(new JPanelNewDatabase());
            newDb.setTitle("Add new database");
        } else {
            newDb.add(new JPanelNewDatabase(arp, tre, nm, uni));
            newDb.setTitle("Edit database");
        }
        newDb.setVisible(true);
    }

    private void makeInformationAbout() {
        JDialog infAb = new JDialog(this, true);
        infAb.setTitle("About app");
        infAb.setSize(400, 120);
        infAb.setResizable(false);
        infAb.setLocationRelativeTo(this);
        infAb.add(new JPanelInformation());
        infAb.setVisible(true);
    }

    private static void makeCloseDialogSave() {
        if (core.isToSave()) {
            Object[] options = {"Yes, save", "No, abort changes", "Cancel"};
            int n = JOptionPane.showOptionDialog(Main.app, "<html><center>Save changes in current file?</center></html>", "Attention!",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                core.saveFile(null);
            } else if (n == 1) {
                app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else if (n == 2) {
                app.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } else {
            app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }

    private void openOpenDialog() {
        final JFileChooser fc = new JFileChooser(DEFAULT_LOC);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new CharonDbFileFilter());
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            coreProcessFile((file.getPath()));
        } else {
            // log.append("Open command cancelled by user." + newline);
        }
    }

    private static WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            makeCloseDialogSave();
        }
    };
}
