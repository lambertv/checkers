import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.Map;
import java.util.LinkedList;

public class ImagePanel extends JPanel {

    private BufferedImage board_image;
    private BufferedImage red_checker_image;
    private BufferedImage black_checker_image;
    private BufferedImage active_red_image;
    private BufferedImage active_black_image;
    private HashMap<Point, CheckerPiece> pieces;
    private HashMap<Point, LinkedList<Move>> available_moves;

    public ImagePanel(HashMap<Point, CheckerPiece> pieces, HashMap<Point, LinkedList<Move>> available_moves) {
        try {
            board_image = ImageIO.read(new File("checker_board.png"));
            red_checker_image = ImageIO.read(new File("red_checker.png"));
            black_checker_image = ImageIO.read(new File("black_checker.png"));
            active_black_image = ImageIO.read(new File("active_black.png"));
            active_red_image = ImageIO.read(new File("active_red.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.pieces = pieces;
        this.available_moves = available_moves;
    }

    public void update(HashMap<Point, CheckerPiece> new_pieces, HashMap<Point, LinkedList<Move>> available_moves) {
        this.pieces = new_pieces;
        this.available_moves = available_moves;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board_image, 0, 0, null);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Point space = new Point(x,y);
                if (pieces.containsKey(space) && pieces.get(space).get_color() == PieceType.BLACK) {
                    g.drawImage(black_checker_image, 16+x*33, 16+y*33, null);
                } else if (pieces.containsKey(space) && pieces.get(space).get_color() == PieceType.RED) {
                    g.drawImage(red_checker_image, 16+x*33, 16+y*33, null);
                }
            }
        }
        for (Map.Entry<Point, LinkedList<Move>> entry : available_moves.entrySet()) {
            Point start_space = entry.getKey();
            int x = start_space.x;
            int y = start_space.y;
            if (pieces.get(start_space).get_color() == PieceType.BLACK) {
                g.drawImage(active_black_image, 16+x*33, 16+y*33, null);
            } else if (pieces.get(start_space).get_color() == PieceType.RED) {
                g.drawImage(active_red_image, 16+x*33, 16+y*33, null);
            }
        }
    }
}
