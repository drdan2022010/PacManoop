import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartWindow extends JFrame {

    public StartWindow(){
        setSize(600,400);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon logo = new ImageIcon();
        try {
            logo = new ImageIcon(ImageIO.read(this.getClass().getResource("/resources/images/pacman_logo1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Register Custom fonts
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/fonts/crackman.ttf")));
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        getContentPane().add(new JLabel(logo),BorderLayout.NORTH);

        JPanel buttonsC = new JPanel();
        buttonsC.setBackground(Color.black);
//        buttonsC.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        buttonsC.setLayout(new BoxLayout(buttonsC,BoxLayout.Y_AXIS));
        FansyButton startButton = new FansyButton("Start Game");
        FansyButton customButton = new FansyButton("Edit game");
        FansyButton mini = new FansyButton("Whack a mole");
        FansyButton exit = new FansyButton("Exit");

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mini.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    PacWindow pw = new PacWindow();

                    dispose();
                    checkmap.gain = 1;
            }
        });

        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor a = new MapEditor();
                checkmap.gain = 0;
                dispose();
            }
        });

        mini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Whack();
                dispose();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonsC.add(startButton);
        buttonsC.add(customButton);
        buttonsC.add(mini);
        buttonsC.add(exit);

        getContentPane().add(buttonsC);

        System.out.print('\n');
        System.out.println("PacMan v1.0   Developed By : Team Five");
        System.out.println("-----------------------------------------");
        setVisible(true);
    }


}
