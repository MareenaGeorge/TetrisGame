/*
 * Tetris Game
 * @author mareena
 * MXM170015
 * Main Class
 * Class for TetrisGame start screen
 * The frame where game is customized and controlled
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
public class TetrisGame extends Frame {
    static TetrisGame g;
    static Grid b;
    static Checkbox[] checkboxes = new Checkbox[10];
    static Scrollbar speedScrollbar,nRowsScrollbar,scoreFactorScrollbar,widthScrollbar,heightScrollbar;
    static Label speedLabel,nRowsLabel,scoreFactorLabel,widthLabel,heightLabel,vLabel;
    static Label vspeedLabel,vnRowsLabel,vscoreFactorLabel,vwidthLabel,vheightLabel;
    public static void gameOver()
    {
        Frame f = new Frame("Game Over");
        f.setLayout(null);
        f.setBounds(550, 300, 350, 180);

        Dimension df = f.getSize();
        Panel p=new Panel();
        int up = df.height / 10;
        int left = df.width / 10;
        int width =4 * left;
        int height =2 * up;
        Label score = new Label("Score: " + b.score, Label.CENTER);
        score.setBounds(left*2, 2 * up, width, height);
        Label level = new Label("Level: " + b.level, Label.CENTER);
        level.setBounds(left*2, 4 * up, width, height);
        Label line = new Label("Line: " + b.line, Label.CENTER);
        line.setBounds(left*2, 6 * up, width, height);
        //g.drawString("Score: " + b.score,left, 2 * intercept);
        Button quitbutton = new Button("Quit");
        quitbutton.setBounds(2 * left, (int) (10.5 * up), left, up);
        quitbutton.addActionListener(e -> System.exit(0));
        //new TetrisGame();
        f.add(score);
        f.add(level);
        f.add(line);
        f.add(quitbutton);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        customize();
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            if (b != null && b.over) {
                break;
            }
        }
        gameOver();

    }

    TetrisGame(int speed, int rol, int score, int w, int h, Set set) {

        super("Tetris Game");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(600, 600);
        b = new Grid(speed, rol, score, w, h, set);
        add("Center", new CvTetris(b));
        setVisible(true);

    }

    public static void customize() {
        Frame f = new Frame("Start Tetris Game.");
        f.setLayout(new BorderLayout());

        Panel center = new Panel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        Panel parameters = new Panel();
        parameters.setLayout(new BoxLayout(parameters, BoxLayout.Y_AXIS));

        int[] defaultValues = new int[]{1, 20, 1, 10, 20};
        // s[0] => speed factor (range: 0.1-1.0).
        speedScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 11);
        // s[1] => number of rows required for each Level of difficulty (range: 20-50).
        nRowsScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 20, 51);
        // s[2] => scoring factor (range: 1-10).
        scoreFactorScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 11);
        // s[3] => width of the play board (range 10 - 20)
        widthScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 10, 21);
        // s[4] => height of the play board (range 20 - 30)
        heightScrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 20, 1, 20, 31);

        speedLabel = new Label("Speed Factor      ", Label.LEFT);
        nRowsLabel = new Label("Rows in each Level", Label.LEFT);
        scoreFactorLabel = new Label("Scoring Factor    ", Label.LEFT);
        widthLabel = new Label("Width of Board    ", Label.LEFT);
        heightLabel = new Label("Height of Board   ", Label.LEFT);
        vspeedLabel=new Label("", Label.LEFT);
        vnRowsLabel= new Label("", Label.LEFT);
        vscoreFactorLabel= new Label("", Label.LEFT);
        vwidthLabel = new Label("", Label.LEFT);
        vheightLabel= new Label("", Label.LEFT);

        int labelWidth = 180;
        int scrollbarWidth = 400;
        int valueWidth = 100;
        int intercept = 20;
        int height = 30;
        Font tt = new Font("Times New Roman", Font.PLAIN, 12);

        speedLabel.setFont(tt);
        speedScrollbar.setSize(2000, 10);
        vspeedLabel.setFont(tt);
        vspeedLabel.setText((speedScrollbar.getValue() < 10 ? " " : "") + speedScrollbar.getValue());
        speedScrollbar.addAdjustmentListener(e -> vspeedLabel.setText((speedScrollbar.getValue() < 10 ? " " : "") + speedScrollbar.getValue()));
        Panel t = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        t.add(speedLabel, BorderLayout.WEST);
        t.add(speedScrollbar, BorderLayout.CENTER);
        t.add(vspeedLabel, BorderLayout.EAST);
        parameters.add(t);


        nRowsLabel.setFont(tt);
        nRowsScrollbar.setSize(2000, 10);
        vnRowsLabel.setFont(tt);
        vnRowsLabel.setText((nRowsScrollbar.getValue() < 50 ? " " : "") + nRowsScrollbar.getValue());
        nRowsScrollbar.addAdjustmentListener(e -> vnRowsLabel.setText((nRowsScrollbar.getValue() < 50 ? " " : "") + nRowsScrollbar.getValue()));
        Panel t1 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        t1.add(nRowsLabel, BorderLayout.WEST);
        t1.add(nRowsScrollbar, BorderLayout.CENTER);
        t1.add(vnRowsLabel, BorderLayout.EAST);
        parameters.add(t1);


        scoreFactorLabel.setFont(tt);
        scoreFactorScrollbar.setSize(2000, 10);
        vscoreFactorLabel.setFont(tt);
        vscoreFactorLabel.setText((scoreFactorScrollbar.getValue() < 10 ? " " : "") + scoreFactorScrollbar.getValue());
        scoreFactorScrollbar.addAdjustmentListener(e -> vscoreFactorLabel.setText((scoreFactorScrollbar.getValue() < 10 ? " " : "") + scoreFactorScrollbar.getValue()));
        Panel t2 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        t2.add(scoreFactorLabel, BorderLayout.WEST);
        t2.add(scoreFactorScrollbar, BorderLayout.CENTER);
        t2.add(vscoreFactorLabel, BorderLayout.EAST);
        parameters.add(t2);


        widthLabel.setFont(tt);
        widthScrollbar.setSize(2000, 10);
        vwidthLabel.setFont(tt);
        vwidthLabel.setText((widthScrollbar.getValue() < 20 ? " " : "") + widthScrollbar.getValue());
        widthScrollbar.addAdjustmentListener(e -> vwidthLabel.setText((widthScrollbar.getValue() < 20 ? " " : "") + widthScrollbar.getValue()));
        Panel t3 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        t3.add(widthLabel, BorderLayout.WEST);
        t3.add(widthScrollbar, BorderLayout.CENTER);
        t3.add(vwidthLabel, BorderLayout.EAST);
        parameters.add(t3);


        heightLabel.setFont(tt);
        heightScrollbar.setSize(2000, 10);
        vheightLabel.setFont(tt);
        vheightLabel.setText((heightScrollbar.getValue() < 30 ? " " : "") + heightScrollbar.getValue());
        heightScrollbar.addAdjustmentListener(e -> vheightLabel.setText((heightScrollbar.getValue() < 30 ? " " : "") + heightScrollbar.getValue()));
        Panel t4 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        t4.add(heightLabel, BorderLayout.WEST);
        t4.add(heightScrollbar, BorderLayout.CENTER);
        t4.add(vheightLabel, BorderLayout.EAST);
        parameters.add(t4);
        
        f.setBounds(500, 350, intercept * 4 + labelWidth + scrollbarWidth + valueWidth, height * 16);


        Panel bottom = new Panel(new FlowLayout());
        Button b = new Button("OK");
        Button d = new Button("Reset");
        Button q = new Button("Quit");

        d.addActionListener(e -> {
            speedScrollbar.setValue(defaultValues[0]);
            nRowsScrollbar.setValue(defaultValues[1]);
            scoreFactorScrollbar.setValue(defaultValues[2]);
            widthScrollbar.setValue(defaultValues[3]);
            heightScrollbar.setValue(defaultValues[4]);
            vspeedLabel.setText("" + speedScrollbar.getValue());
            vnRowsLabel.setText("" + nRowsScrollbar.getValue());
            vscoreFactorLabel.setText("" + scoreFactorScrollbar.getValue());
            vwidthLabel.setText("" + widthScrollbar.getValue());
            vheightLabel.setText("" + heightScrollbar.getValue());
        });

        b.addActionListener(e -> {
            int speed = speedScrollbar.getValue();
            int rol = nRowsScrollbar.getValue();
            int score = scoreFactorScrollbar.getValue();
            int w = widthScrollbar.getValue();
            int h = heightScrollbar.getValue();

            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < 8; i++) {
                if (!checkboxes[i].getState())
                    set.add(i + 8);
            }

            g = new TetrisGame(speed, rol, score, w, h, set);
            f.dispose();
        });

        q.addActionListener(e -> System.exit(0));

        bottom.add(b);
        bottom.add(d);
        bottom.add(q);


        Panel x = new Panel(new GridLayout(2, 8));
        for (int i = 8; i <= 15; i++) {
            AdditionalTetromino moreTetromino = new AdditionalTetromino(new Tetromino(Tetromino.TetrominoName.values()[i]), 15, 80, 46);
            Panel tp = new Panel();
            tp.add(moreTetromino);
            x.add(tp);
        }


        for (int i = 0; i < 8; i++) {
            checkboxes[i] = new Checkbox();
            Panel tp = new Panel();
            tp.add(checkboxes[i]);
            x.add(tp);
        }

        Label pc = new Label("Choose game parameters", Label.CENTER);
        pc.setFont(tt);
        center.add(pc);

        center.add(new Label());

        center.add(parameters);

        center.add(new Label());
        center.add(new Label());
        center.add(new Label());

        Label c = new Label("Choose additional Tetrominoes", Label.CENTER);
        c.setFont(tt);
        center.add(c);

        center.add(new Label());

        center.add(x);

        center.add(new Label());

        f.add(center, BorderLayout.CENTER);
        f.add(bottom, BorderLayout.SOUTH);
        f.add(new Label(), BorderLayout.NORTH);
        f.add(new Panel(), BorderLayout.EAST);
        f.add(new Panel(), BorderLayout.WEST);
        f.setVisible(true);
    }

}
