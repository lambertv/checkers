import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CheckersView extends JPanel implements MouseListener{
    private static Board game_board = new Board();
    private static ImagePanel board_panel = new ImagePanel(game_board.get_pieces());
    private Point pressed_space = null;

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JComponent newContentPane = new CheckersView();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        board_panel.setPreferredSize(new Dimension(297,297));
        frame.add(board_panel);

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public CheckersView() {
        board_panel.setPreferredSize(new Dimension(297,297));
        board_panel.addMouseListener(this);
        addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("MOUSE PRESSED");
        if (e.getX() > 16 && e.getX() < 16+33*8 &&
            e.getY() > 16 && e.getY() < 16+33*8) {
            int x = (e.getX()-16)/33;
            int y = (e.getY()-16)/33;
            System.out.println(x);
            System.out.println(y);
            pressed_space = new Point(x,y);
        }
        if (!game_board.is_piece_at(pressed_space)) {
            pressed_space = new Point(-1,-1);
        }
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("MOUSE RELEASED");
        if (e.getX() > 16 && e.getX() < 16+33*8 &&
            e.getY() > 16 && e.getY() < 16+33*8) {
            int x = (e.getX()-16)/33;
            int y = (e.getY()-16)/33;
            System.out.println(x);
            System.out.println(y);
            if (pressed_space.x != -1) {
                Move move = new Move(pressed_space, new Point(x,y));
                game_board.make_move(move);
                pressed_space = null;
            }
            board_panel.update_pieces(game_board.get_pieces());
            board_panel.repaint();
        }

    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("MOUSE ENTERED");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("MOUSE EXITED");
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("MOUSE CLICKED");
    }

}
