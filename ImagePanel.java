import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage board_image;
    private BufferedImage red_checker_image;
    private BufferedImage black_checker_image;
    private PieceType[][] checker_pieces = new PieceType[8][8];

    public ImagePanel() {
        try {
            board_image = ImageIO.read(new File("checker_board.png"));
            red_checker_image = ImageIO.read(new File("red_checker.png"));
            black_checker_image = ImageIO.read(new File("black_checker.png"));

        } catch (IOException ex) {
            System.out.println(ex);
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((y < 3 && x%2==0 && y != 1 )|| 
                    (y == 1 && x%2==1)) {
                    checker_pieces[x][y] = PieceType.BLACK;
                } else if ((y > 4 && x%2==1 && y != 6)||
                           (y == 6 && x%2==0)) {
                    checker_pieces[x][y] = PieceType.RED;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board_image, 0, 0, null);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checker_pieces[i][j] == PieceType.BLACK) {
                    g.drawImage(black_checker_image, 16+i*33, 16+j*33, null);
                } else if (checker_pieces[i][j] == PieceType.RED) {
                    g.drawImage(red_checker_image, 16+i*33, 16+j*33, null);
                }
            }
        }
    }

}
