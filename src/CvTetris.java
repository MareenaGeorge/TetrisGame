/*
 * Tetris Game
 * @author mareena
 * MXM170015
 * Class for Canvas of Tetris Game
 * The frame where tetris is played
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class CvTetris extends Canvas {
    private static List<Color> palette = Collections.unmodifiableList(Arrays.asList(
            Color.GRAY,
            Color.YELLOW,
            new Color(112, 48, 160),
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            new Color(240, 192, 0),
            new Color(0, 176, 240),
            new Color(170, 168, 165),
            new Color(162, 195, 99),
            new Color(206, 152, 150),
            new Color(214, 114, 46),
            new Color(200, 212, 161),
            new Color(144, 137, 90),
            new Color(29, 54, 96),
            new Color(72, 131, 160)
    ));
    private Point mainArea;
    private Point quitPosition;
    private int lengthSquare;
    private boolean pause = false;
    private boolean TetrominoChange = false;

    private Grid grid;
    private Point rightArea;
    Dimension d;
    int maxX,maxY;
    float x0, y0, rWidth = 230.0F, rHeight = 230.0F, pixelSize,xP,yP,xA,yA;
    int centerX, centerY;
    int level=1;
    int lines, score =0;
    boolean pausearea =false;
    int leftbound,upperbound;

    int iX(float x) {return Math.round(centerX + x / pixelSize);}
    int iY(float y) {return Math.round(centerY - y / pixelSize);}
    float fx(int x) {return (x - centerX) * pixelSize;}
    float fy(int y) {return (centerY - y) * pixelSize;}
    int iL(float l) {return Math.round(l/pixelSize);}
    CvTetris(Grid b) {
        grid = b;
        new Thread(grid).start();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int x = e.getX(), y = e.getY();
                double xq = quitPosition.getX();
                double yq = quitPosition.getY();
                double xm = mainArea.getX();
                double ym = mainArea.getY();
                int buttonFlag=e.getButton();



                if (!e.isConsumed()) {

                    if (x >= xq && x <= xq + lengthSquare * 14 / 5 &&
                            y >= yq && y <= yq + lengthSquare * 3 / 2) {
                        System.exit(0);
                    }
                    if (x < xm || x > xm + 10 * lengthSquare ||
                            y < ym || y > ym + 20 * lengthSquare) {
                        switch (buttonFlag) {
                            case MouseEvent.BUTTON1: {
                                grid.lCheck();
                                break;
                            }
                            case MouseEvent.BUTTON3: {
                                grid.rCheck();
                                break;
                            }
                        }
                    }
                    e.consume();
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (grid.over)
                    return;
                int x = e.getX(), y = e.getY();
                double xm = mainArea.getX();
                double ym = mainArea.getY();
                boolean pausearea =
                        x >= xm + lengthSquare &&
                                x <= xm + (grid.w - 1) * lengthSquare &&
                                y >= ym &&
                                y <= ym + (grid.h - 1) * lengthSquare;

                if (pause != pausearea) {
                    pause = pausearea;
                    if (pause)
                        grid.paused = true;
                    else {
                        grid.resume();
                        TetrominoChange = false;
                    }
                }

                if (TetrominoChange)
                    return;

                boolean TetrominoChanged = isInsideTetromino(e.getPoint(), grid.currentTetromino.getTetromino());
                if (TetrominoChanged) {
                    grid.TetrominoChange();
                    TetrominoChange = true;
                }
                repaint();
            }
        });
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                if (pause) {
                    return;
                }
                else {
                    int num =e.getWheelRotation();
                    int i;
                    if (num > 0) {
                        i = 0;
                        while (i < num % 4) {
                            grid.rcw();
                            i++;
                        }
                    }
                    else{
                        i=0;
                        while (i < Math.abs(num % 4)) {
                            grid.rccw();
                            i++;
                        }
                    }
                }
            }
        });
    }

    void initgr()
    {
        d = getSize();
        maxX = d.width - 1;
        maxY = d.height - 1;
        pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
        centerX = maxX / 2; centerY = maxY / 2;
        lengthSquare = Math.min(d.width / (grid.w + 10), d.height / (grid.h + 9));

        leftbound = (d.width - (grid.w + 6) * lengthSquare) / (2 * lengthSquare);
        upperbound = (d.height - (grid.h - 1) * lengthSquare) / (2 * lengthSquare);
        // Find main area boundary.
        mainArea = new Point((leftbound - 1) * lengthSquare, upperbound * lengthSquare);
        // Find right area boundary.
        rightArea = new Point((leftbound + grid.w) * lengthSquare, (upperbound + 1) * lengthSquare);


    }
    static Color getColor(Tetromino.TetrominoName color) {
        return CvTetris.palette.get(color.ordinal());
    }
    public boolean isInsideTetromino(Point inside, List<Point2D> points) {
        int i;
        int j;
        boolean result = false;
        for (Point2D c : points) {
            Point[] ps = new Point[4];
            ps[0] = new Point(c.x * lengthSquare + mainArea.x, c.y * lengthSquare + mainArea.y);
            ps[1] = new Point((c.x + 1) * lengthSquare + mainArea.x, c.y * lengthSquare + mainArea.y);
            ps[2] = new Point(c.x * lengthSquare + mainArea.x, (c.y + 1) * lengthSquare + mainArea.y);
            ps[3] = new Point((c.x + 1) * lengthSquare + mainArea.x, (c.y + 1) * lengthSquare + mainArea.y);
            i = 0;
            j = ps.length - 1;
            while (i < ps.length) {
                if ((ps[i].y > inside.y) != (ps[j].y > inside.y) && (inside.x < (ps[j].x - ps[i].x) * (inside.y - ps[i].y) / (ps[j].y - ps[i].y) + ps[i].x)) {
                    result = !result;
                }
                j = i++;
            }
        }
        return result;
    }
    public void paint(Graphics g)
    {
        initgr();
        Font FontDetails = new Font( "Times New Roman", Font.BOLD, iL(10) );
        g.setFont(FontDetails);

        drawGrid(g, mainArea, leftbound, upperbound);


        //Draw Current Tetromino

        Color nowcolor;
        if(grid.over)
            nowcolor=Color.GRAY;
        else
            nowcolor=getColor(grid.getCurrentTetromino().getName());

        for (Point2D cube : grid.getCurrentTetromino().getTetromino()) {
            g.setColor(nowcolor);
            g.fillRect(mainArea.x + cube.x * lengthSquare, mainArea.y + cube.y * lengthSquare, lengthSquare, lengthSquare);
            g.setColor(Color.black);
            g.drawRect(mainArea.x + cube.x * lengthSquare, mainArea.y + cube.y * lengthSquare, lengthSquare, lengthSquare);
        }
        //Draw Next Tetromino
        Color nextcolor;
        if(grid.over)
            nextcolor=Color.GRAY;
        else
            nextcolor=getColor(grid.queue.element().getName());
        for (Point2D cube : grid.queue.element().getTetromino()) {
            g.setColor(nextcolor);
            g.fillRect(rightArea.x + cube.x * lengthSquare, rightArea.y + cube.y * lengthSquare, lengthSquare, lengthSquare);
            g.setColor(Color.black);
            g.drawRect(rightArea.x + cube.x * lengthSquare, rightArea.y + cube.y * lengthSquare, lengthSquare, lengthSquare);
        }
        quitPosition = new Point((leftbound + grid.w) * lengthSquare, (upperbound + grid.h - 2) * lengthSquare);
        g.setFont(new Font("default", Font.BOLD, lengthSquare * 2 / 3));
        g.setColor(Color.black);
        g.drawRect(quitPosition.x, quitPosition.y - lengthSquare / 2, lengthSquare * 14 / 5, lengthSquare * 3 / 2);
        g.drawString("QUIT", quitPosition.x + lengthSquare / 2, quitPosition.y + lengthSquare / 2);

        Point levelPosition = new Point((leftbound + grid.w) * lengthSquare, (upperbound + 8) * lengthSquare);
        g.drawString("Level:", levelPosition.x, levelPosition.y);
        g.drawString(Integer.toString(grid.level), levelPosition.x + lengthSquare * 9 / 2, levelPosition.y);

        // Draw Lines .
        Point linesPosition = new Point((leftbound + grid.w) * lengthSquare, (upperbound + 10) * lengthSquare);
        g.drawString("Lines:", linesPosition.x, linesPosition.y);
        g.drawString(Integer.toString(grid.line), linesPosition.x + lengthSquare * 9 / 2, linesPosition.y);

        // Draw Scores .
        Point scoresPosition = new Point((leftbound + grid.w) * lengthSquare, (upperbound + 12) * lengthSquare);

        g.drawString("Score:", scoresPosition.x, scoresPosition.y);
        g.drawString(Integer.toString(grid.score), scoresPosition.x + lengthSquare * 9 / 2, scoresPosition.y);
        //g.drawString("Level: " + level, iX(25), iY(20));
        //g.drawString("Lines: " + lines, iX(25), iY(0));
        //g.drawString("Score: " + score, iX(25), iY(-20));
        //g.drawString("QUIT", iX(50), iY(-90));

        /*g.setColor(Color.BLACK);
        g.drawRect(iX(-100), iY(100), iL(100), iL(200));
        g.drawRect(iX(20), iY(100), iL(70), iL(50));
        g.drawRect(iX(40), iY(-80), iL(40), iL(20));*/
        if (pause) {
            Point pausePosition = new Point(mainArea.x + ((grid.w - 6) / 2) * lengthSquare, mainArea.y + (grid.h - 3) / 2 * lengthSquare);
            g.setFont(new Font("default", Font.BOLD, lengthSquare));
            g.setColor(new Color(0, 112, 192));
            g.drawRect(pausePosition.x + lengthSquare, pausePosition.y, lengthSquare * 4, lengthSquare * 2);
            g.drawString("PAUSE", pausePosition.x +lengthSquare * 3 / 2, pausePosition.y + lengthSquare * 3 / 2);
        }

        repaint();
    }

    private void drawGrid(Graphics g, Point origin, int leftbound, int upperbound) {
        Color RectColor;
        g.drawRect(leftbound * lengthSquare, upperbound * lengthSquare, (grid.w - 2) * lengthSquare, (grid.h - 1) * lengthSquare);
        g.drawRect((leftbound + grid.w) * lengthSquare, upperbound * lengthSquare, 6 * lengthSquare, 4 * lengthSquare);

        int i = 0;
        for (int[] line : grid.area) {
            for (int j = 0; j < grid.w; j++) {
                if (line[j] == 0 || line[j] == Integer.MAX_VALUE)
                    continue;
                if(grid.over)
                    RectColor= Color.GRAY;
                else
                    RectColor=getColor(Tetromino.TetrominoName.values()[line[j]]);

                drawSquare(g, origin, j, i,RectColor);
            }
            i++;
        }
    }
    private void drawSquare(Graphics g, Point origin, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(origin.x + x * lengthSquare, origin.y + y * lengthSquare, lengthSquare, lengthSquare);
        g.setColor(Color.black);
        g.drawRect(origin.x + x * lengthSquare, origin.y + y * lengthSquare, lengthSquare, lengthSquare);
    }
}
