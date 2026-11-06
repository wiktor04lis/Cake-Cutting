package SwingApproach;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class EvenPazView extends JPanel {
    private double cakeWidth = 1000;
    private double cakeHeight = 150;
    private int leftMargin = 250;
    private int rowGap = 50;
    private int messageGap = 90;
    private Map<Integer, Cake> cakes = new HashMap<>();
    protected int currCakeID = -1;
    private ArrayList<String> messages = new ArrayList<>();
    private MarkListener listener;

    public EvenPazView() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (cakes.get(currCakeID).cake.contains(e.getPoint())) {
                    double mark = (e.getPoint().getX() - leftMargin) / cakeWidth;
                    System.out.println("mark: " + mark);
                    if (listener != null) {
                        listener.markChosen(mark);
                    }
                }
            }
        });
    }

    private class Cake {
        private final int id;
        private final int parentID;
        private final double leftBound;
        private final double rightBound;
        private final Rectangle2D.Double cake;
        private final int row;

        public Cake(int id, int parentID, double leftBound, double rightBound, Rectangle2D.Double cake, int row) {
            this.id = id;
            this.parentID = parentID;
            this.leftBound = leftBound;
            this.rightBound = rightBound;
            this.cake = cake;
            this.row = row;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Map.Entry<Integer, Cake> rect : cakes.entrySet()) {
            Cake c = rect.getValue();
            g2.setPaint(Color.ORANGE);
            g2.fill(c.cake);
            g2.draw(c.cake);

            g2.setColor(Color.BLACK);
            g2.draw(c.cake);
            g2.drawString("Interval: " + c.leftBound + ", " + c.rightBound, (int) c.cake.getMinX(), (int) c.cake.getMinY() - 1);

        }

        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        /*
        int y = (int) rectangle.getMinY();
        for (String m : messages) {
            g2.drawString(m, (int) rectangle.getMinX(), y);
            y += fm.getHeight();
        }
        */
    }

    public void askAgentForMark(ArrayList<String> message) {
        messages.clear();
        messages = message;
        repaint();
    }

    public void clearInfo() {
        messages.clear();
        repaint();
    }

    public void createCake(int id, double leftBound, double rightBound) {
        Cake cake = new Cake(id, -1, leftBound, rightBound, 
                createRectangle(leftBound, rightBound, 0), 0);
        cakes.put(id, cake);
        repaint();
    }

    private Rectangle2D.Double createRectangle(double leftBound, double rightBound, int row) {
        double x = leftMargin + leftBound * cakeWidth;
        double width = (rightBound - leftBound) * cakeWidth;
        double y = messageGap + row * (cakeHeight + rowGap);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, width, cakeHeight);
        return rectangle;
    }

    public void splitCake(int parentID, int childID, double leftBound, double rightBound) {
        if (!cakes.containsKey(parentID)) {
            throw new IllegalArgumentException("Parent: " + parentID + ", doesn not exist!");
        }
        int parentRow = cakes.get(parentID).row;
        int childRow = parentRow + 1;
        Cake cake = new Cake(childID, parentID, leftBound, rightBound,
                createRectangle(leftBound, rightBound, childRow), childRow);
        cakes.put(childID, cake);
        repaint();
    }

    public void setMarkListener(MarkListener listener) {
        this.listener = listener;
    }
}
