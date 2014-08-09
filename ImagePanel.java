import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage board_image;
    private BufferedImage red_checker_image;
    private BufferedImage black_checker_image;
    private LinkedList<CheckerPiece> pieces;

    public ImagePanel(LinkedList<CheckerPiece> pieces) {
        try {
            board_image = ImageIO.read(new File("checker_board.png"));
            red_checker_image = ImageIO.read(new File("red_checker.png"));
            black_checker_image = ImageIO.read(new File("black_checker.png"));

        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.pieces = pieces;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board_image, 0, 0, null);

        ListIterator<CheckerPiece> list_iterator = pieces.listIterator();
        while (list_iterator.hasNext()) {
            CheckerPiece current_piece = list_iterator.next();
            int x = current_piece.getX();
            int y = current_piece.getY();
            if (current_piece.get_color() == PieceType.BLACK) {
                g.drawImage(black_checker_image, 16+x*33, 16+y*33, null);
            } else {
                g.drawImage(red_checker_image, 16+x*33, 16+y*33, null);
            }
        }
    }

}
