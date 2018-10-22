
/*
 * Tetris Game
 * @author mareena
 * MXM170015
 * Class for Tetromino
 * The different basic tetrominoes and additional tetrominoes if needed
 *
 */
import java.util.*;

public class Tetromino {
    //Constructors

    TetrominoName name;
    int directionCode;
    Point2D position;
    List<List<Point2D>> points;

    public enum TetrominoName{
        NULL, S, Z, J, L, O, T,  I, 
        X1, X2, X3, X4, X5, X6, X7, X8
    }
    static final List<List<List<Point2D>>> tetrominoes = Collections.unmodifiableList(Arrays.asList(
            Collections.emptyList(),
            // S Tetromino
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(0, 2),
                            new Point2D(1, 2)
                    ),
                    Arrays.asList(
                            new Point2D(0, 0),
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    )
            ),

            // Z Tetromino
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(2, 2)
                    ),
                    Arrays.asList(
                            new Point2D(2, 0),
                            new Point2D(2, 1),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    )
            ),
            // J Tetromino
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 0),
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(2, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(2, 2)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(0, 2)
                    )
            ),

            // L Tetromino
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(2, 0)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(2, 2)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(0, 2),
                            new Point2D(1, 1),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(0, 0),
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    )
            ),

            // Shape O
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(2, 1),
                            new Point2D(2, 2)
                    )
            ),

            // Shape T
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(1, 0)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(1, 2)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(0, 1)
                    )
            ),
            // Shape I
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1),
                            new Point2D(3, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2),
                            new Point2D(1, 3)
                    )
            ),

            // X1
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(0, 1),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(2, 1),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(2, 1),
                            new Point2D(1, 2),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 2),
                            new Point2D(0, 1),
                            new Point2D(1, 1)
                    )
            ),

            // X2
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 0),
                            new Point2D(1, 1),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(2, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 2)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(0, 2)
                    )
            ),

            // x3
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1),
                            new Point2D(1, 2)
                    )
            ),
            // x4
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(0, 1)
                    ),
                    Arrays.asList(
                            new Point2D(0, 0),
                            new Point2D(1, 1)
                    )
            ),
            // X5
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(2, 1),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(1, 2),
                            new Point2D(1, 1)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(1, 1)
                    )
            ),

            // X6
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(2, 1),
                            new Point2D(1, 0)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 2),
                            new Point2D(2, 1)
                    ),
                    Arrays.asList(
                            new Point2D(0, 1),
                            new Point2D(2, 1),
                            new Point2D(1, 2)
                    ),
                    Arrays.asList(
                            new Point2D(1, 0),
                            new Point2D(1, 2),
                            new Point2D(0, 1)
                    )
            ),

            // X7
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 1)
                    )
            ),

            // X8
            Arrays.asList(
                    Arrays.asList(
                            new Point2D(1, 1),
                            new Point2D(2, 0),
                            new Point2D(0, 2)
                    ),
                    Arrays.asList(
                            new Point2D(1, 1),
                            new Point2D(0, 0),
                            new Point2D(2, 2)
                    )
            )
    ));

    static final Point2D[] nextShapeBase = new Point2D[]{
            new Point2D(-1, -1),
            // Tetromino S
            new Point2D(1, -1),
            // Tetromino Z
            new Point2D(1, -1),
            // Tetromino J
            new Point2D(1, 0),
            // Tetromino L
            new Point2D(1, 0),
            // Tetromino O
            new Point2D(1, -1),
            // Tetromino T
            new Point2D(1, 0),
            // Tetromino I
            new Point2D(1, -1),

            // X1
            new Point2D(2, 0),
            // X2
            new Point2D(1, 0),
            // X3
            new Point2D(1, -1),
            // X4
            new Point2D(2, 0),
            // X5
            new Point2D(1, 0),
            // X6
            new Point2D(2, 0),
            // X7
            new Point2D(2,0),
            // X8
            new Point2D(1, 0)
    };

    public Tetromino(TetrominoName name) {
        this.name = name;
        this.position = nextShapeBase[name.ordinal()];
        this.points = Tetromino.tetrominoes.get(name.ordinal());
    }
    public Tetromino(Tetromino tetromino, Point2D coordinate) {
        this.name = tetromino.name;
        this.directionCode = tetromino.directionCode;
        this.position = coordinate;
        this.points = Tetromino.tetrominoes.get(name.ordinal());
    }

    public Tetromino(Tetromino tetromino, int directionCode) {
        this.name = tetromino.name;
        this.directionCode = directionCode;
        this.position = tetromino.position;
        this.points = Tetromino.tetrominoes.get(name.ordinal());
    }


    public TetrominoName getName() {
        return name;
    }

    static Tetromino TetrominoOf(Tetromino tetromino, Point2D base) {
        return new Tetromino(tetromino, base);
    }
    public List<Point2D> getTetromino() {
        List<Point2D> ret = new ArrayList<>();
        this.points.get(directionCode).forEach(c -> ret.add(new Point2D(this.position.x + c.x, this.position.y + c.y)));
        return ret;
    }

    public static Tetromino getRandomTetromino() {
        Random random = new Random();
        int x;
        x = random.nextInt(15) + 1;
        while (Grid.set.contains(x)){
            x = random.nextInt(15) + 1;
        }
        return new Tetromino(TetrominoName.values()[x]);
    }
    public Tetromino pixelDrop() {
        return new Tetromino(this, new Point2D(this.position.x,this.position.y + 1));
    }
    public Tetromino pixelToLeft() {
        return new Tetromino(this, new Point2D(this.position.x - 1,this.position.y));
    }

    public Tetromino pixelToRight() {
        return new Tetromino(this, new Point2D(this.position.x + 1,this.position.y));
    }

    public Tetromino pixelrcw() {
        int directionCode;
        directionCode = (this.directionCode + 1) % this.points.size();
        return new Tetromino(this, directionCode);
    }

    public Tetromino pixelrccw() {

        int directionCode = (this.directionCode - 1);
        if (directionCode < 0)
            directionCode = this.points.size() - 1;
        return new Tetromino(this, directionCode);
    }

}
