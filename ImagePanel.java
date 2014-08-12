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

public class ImagePanel extends JPanel {

    private BufferedImage board_image;
    private BufferedImage red_checker_image;
    private BufferedImage black_checker_image;
    private HashMap<Point, CheckerPiece> pieces;

    public ImagePanel(HashMap<Point, CheckerPiece> pieces) {
        try {
            board_image = ImageIO.read(new File("checker_board.png"));
            red_checker_image = ImageIO.read(new File("red_checker.png"));
            black_checker_image = ImageIO.read(new File("black_checker.png"));

        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.pieces = pieces;
    }

    public void update_pieces(HashMap<Point, CheckerPiece> new_pieces) {
        this.pieces = new_pieces;
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
    }

}
