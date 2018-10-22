/*
 * Tetris Game
 * @author mareena
 * MXM170015
 * Class for additional Tetromino
 * To draw additional tetrominoes for selection in frame
 *
 */
import java.awt.*;

public class AdditionalTetromino extends Canvas {

    Tetromino tetromino;
    int squareLen;
    public AdditionalTetromino(Tetromino t, int squareLength, int w, int h) {
        tetromino = t;
        squareLen = squareLength;
        setSize(w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color color = CvTetris.getColor(tetromino.getName());
        for (Point2D square : tetromino.getTetromino()) {
            g.setColor(color);
            g.fillRect((square.x) * squareLen, (square.y) * squareLen, squareLen, squareLen);
            g.setColor(Color.black);
            g.drawRect((square.x) * squareLen, ( square.y) * squareLen, squareLen, squareLen);
        }
    }
}
