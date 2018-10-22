/*
 * Tetris Game
 * @author mareena
 * MXM170015
 * Class for Grid or Board of Main Area Screen
 * The frame where tetrominoes are moved in Main Screen
 *
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Grid implements Runnable {

    Queue<Tetromino> queue;
    Tetromino currentTetromino;
    boolean over = false;

    private volatile boolean running = true;
    public volatile boolean paused = false;
    private final Object pauseLock = new Object();

    int w;
    int h;
    LinkedList<int[]> area;

    int level = 1;
    int line = 0;
    int lineOfCurrentLevel = 0;
    int scoreFactor = 0;
    final int rol;
    int score = 0;
    int speed;
    static Set set;

    public Grid(int speedB, int rolB, int scoreFactorB, int width, int height, Set setB) {

        scoreFactor = scoreFactorB;
        w = width + 2;
        h = height + 1;
        rol = rolB;
        speed = speedB;
        area = new LinkedList<>();
        queue = new LinkedList<>();
        Grid.set = setB;
        getRandomTetromino();
        initialize();
    }
    public Grid( int rolB, int scoreFactorB, int width, int height, Set setB) {
        w = width + 2;
        h = height + 1;
        rol = rolB;
        scoreFactor = scoreFactorB;
        area = new LinkedList<>();
        queue = new LinkedList<>();
        Grid.set = setB;
        getRandomTetromino();
        initialize();
    }
    public Grid(int rolB,int width, int height, Set setB) {
        w = width + 2;
        h = height + 1;
        rol = rolB;
        area = new LinkedList<>();
        queue = new LinkedList<>();
        Grid.set = setB;
        getRandomTetromino();
        initialize();
    }

    private void initialize() {

        int[] line;
        int i = 0;
        while ( i < h - 1) {
            line = new int[w];

            line[0] = line[w - 1] = Integer.MAX_VALUE;
            int j = 1;
            while ( j <= w - 2) {
                line[j] = 0;
                j++;
            }
            area.add(line);
            i++;
        }

        line = new int[w];
        i = 0;
        while (i < w) {
            line[i] = Integer.MAX_VALUE;
            i++;
        }
        area.add(line);
    }




    private Tetromino remove() {
        Tetromino cur = queue.remove();
        if (queue.isEmpty())
            getRandomTetromino();

        int shift;
        shift = (w/2 - 1) ;
        int i = 1;
        while(i <= shift){
            cur = cur.pixelToRight();
            i++;}
        return cur;
    }
    public void getRandomTetromino() {
        Tetromino randomTetromino;
        while (queue.size() < 100) {
            randomTetromino =Tetromino.getRandomTetromino();
            queue.add(Tetromino.getRandomTetromino());
        }
    }

    public Tetromino getCurrentTetromino() {
        if (currentTetromino == null)
            currentTetromino = remove();
        return currentTetromino;
    }

    public void run() {
        int removed;

        while (running) {
            synchronized (pauseLock) {
                if (!running) {
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) {
                        break;
                    }
                }
            }

            if (over) {
                return;
            }

            Tetromino next = getCurrentTetromino().pixelDrop();

            if (check(next)==1)
                currentTetromino = next;
            else {
                for (Point2D points : currentTetromino.getTetromino()) {
                    area.get(points.y)[points.x] = currentTetromino.name.ordinal();
                }
                removed = 0;
                Iterator<int[]> iter = area.iterator();

                int[] cur;

                int l = 1;
                while (iter.hasNext() && l < h) {
                    cur = iter.next();
                    if (AnalyzeRows(cur)==1) {
                        removed++;
                        iter.remove();
                    }
                    l++;
                }

                for (int i = 1; i <= removed; i++) {
                    cur = new int[w];

                    cur[0] = cur[w - 1] = Integer.MAX_VALUE;
                    for (int j = 1; j <= w - 2; j++)
                        cur[j] = 0;

                    area.addFirst(cur);
                }
                line = line+removed;
                lineOfCurrentLevel = lineOfCurrentLevel+ removed;
                if (lineOfCurrentLevel > rol) {
                    level++;
                    lineOfCurrentLevel = 0;
                }
                score = score+ (level * scoreFactor * removed);
                currentTetromino = remove();

                if (check(currentTetromino)==0)
                    over = true;
            }
            try {
                Thread.sleep((long) (500 / (1 + ((float) level * (float) speed / 10.0))));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    private int AnalyzeRows(int[] line) {
        for (int i : line)
            if (i == 0)
                return 0;
        return 1;
    }

    private int check(Tetromino shape) {
        for (Point2D points : shape.getTetromino()) {
            if (points.y < 0)
                return 0;
            if (area.get(points.y)[points.x] > 0) {
                return 0;
            }
        }
        return 1;
    }


    public void lCheck() {

        Tetromino next = getCurrentTetromino().pixelToLeft();

        if (check(next)==1)
            currentTetromino = next;
    }

    public void rCheck() {

        Tetromino next = getCurrentTetromino().pixelToRight();

        if (check(next)==1)
            currentTetromino = next;

    }


    public void rcw() {

        Tetromino next = currentTetromino.pixelrccw();

        if (check(next)==1)
            currentTetromino = next;
    }

    public void rccw() {

        Tetromino next = currentTetromino.pixelrccw();

        if (check(next)==1)
            currentTetromino = next;
    }

    void TetrominoChange() {
        Tetromino nextDiff;

        for (nextDiff = remove();nextDiff.name == currentTetromino.name;)
            nextDiff = remove();

        currentTetromino = Tetromino.TetrominoOf(nextDiff, currentTetromino.position);
        score = score - level * scoreFactor;
        if (score <= 0)
            score = 0;
    }
}
