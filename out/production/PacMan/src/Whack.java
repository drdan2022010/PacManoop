import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class Whack extends JFrame{
    static int t = 1;
    int success = 0;
    int count = -1;
    int rnum = 0;
    MoleBtnActionListener mbal = new MoleBtnActionListener();
    JLabel label;
    JButton[] btns = new JButton[9];
    public Whack() {
        super("Whack-a-mole");
        JPanel mainp = new JPanel(new BorderLayout());
        JPanel jp1 = new JPanel(new BorderLayout());
        JButton startBtn = new JButton("Start");
        label = new JLabel("");
        jp1.add(startBtn, BorderLayout.WEST);
        startBtn.addActionListener(new StartButtonActionListener());
        jp1.add(label, BorderLayout.EAST);

        JPanel jp2 = new JPanel(new GridLayout(3,3));
        for(int i = 0; i < 9; i++) {
            btns[i] = new JButton();
            btns[i].setBackground(new Color(102,51,0));
            btns[i].setActionCommand(Integer.toString(i));
            jp2.add(btns[i]);
        }
        
        add(mainp);
        mainp.add(jp1, BorderLayout.NORTH);
        mainp.add(jp2, BorderLayout.CENTER);
        
        setSize(500, 560);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private class StartButtonActionListener implements ActionListener {
        javax.swing.Timer stimer;
        java.util.Timer utimer;
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            src.setEnabled(false);
            addALtoMoleBtns();
            stimer = new javax.swing.Timer(1000, new TimerActionListener());
            utimer = new java.util.Timer();
            TimerTask tt = new TimerTask(){
                @Override
                public void run() {
                    showIcon();
                    String m = "Hit " + success + " and missed " + (count-success) + " times!";
                    if(count == 17) {
                        btns[rnum].setIcon(null);
                        utimer.cancel();
                        utimer.purge();
                        JOptionPane.showMessageDialog(null, m);
                        count = -1;
                        success = 0;
                        removeALtoMoleBtns();
                        src.setEnabled(true);
                        dispose();
                        new StartWindow();
                    }
                }
            };
            
            stimer.start();
            utimer.schedule(tt, 1000, 1000);
        }
        public void removeALtoMoleBtns() {
            for(JButton b : btns) {
                b.removeActionListener(mbal);
            }
        }
        public void addALtoMoleBtns() {
            for(JButton b : btns) {
                b.addActionListener(mbal);
            }
        }
        public void showIcon() {
            btns[rnum].setIcon(null);
            rnum = randNum();
            try
            { 
                ImageIcon icon = new ImageIcon(
                    ImageIO.read(getClass().getResourceAsStream("/resources/minigame/mole.png")).getScaledInstance(
                    btns[0].getWidth()-20, btns[0].getHeight()-20,Image.SCALE_SMOOTH));
                
                btns[rnum].setIcon(icon);
                count++;
            }
            catch (Exception ex)
            { 
                JOptionPane.showMessageDialog(null,"Couldn't load the image");
            }
        }
        public int randNum() {
            Random rnd = new Random(System.currentTimeMillis());
            return rnd.nextInt(9);
        }
    }
    private class MoleBtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            btns[rnum].setIcon(null);
        try {
            if (e.getSource() == btns[rnum]) {
                playSound("Ting.wav");
                success++;
            } else {
                playSound("Error.wav");
            }
        }catch(Exception s){
            s.printStackTrace();
        }
    }

        public void playSound(String soundName)
        {
            try
            {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Main.class.getResourceAsStream("resources/sounds/" + soundName));
                clip.open(inputStream);
                clip.start();
            }
            catch(Exception ex)
            {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }


    private class TimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText(Integer.toString(t++));
        }
    }
}