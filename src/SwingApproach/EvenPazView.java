package SwingApproach;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class EvenPazView extends JPanel{
    private int width = 500;
    private int height = 300;
    private double cakeWidth = 1000;
    private double cakeHeight = 300;
    private Rectangle2D.Double rectangle = new Rectangle2D.Double(250, 50, cakeWidth, cakeHeight);
    private ArrayList<String> information = new ArrayList<>();
    private MarkListener listener;

    public EvenPazView(){
        addMouseListener(new MouseAdapter() {
            @Override 
            public void mousePressed(MouseEvent e){
                if (rectangle.contains(e.getPoint())) {
                    double mark = (e.getX() - rectangle.getMinX());
                    if (listener != null) {
                        listener.markChosen(mark);
                    }
                }
            }
        });
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.ORANGE);
        g2.fill(rectangle);
        g2.draw(rectangle);
        System.out.println("Hello");
        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int y = (int) rectangle.getMinY();
        System.out.println(information.size());
        for(String info : information){
            g2.drawString(info, (int) rectangle.getMinX(), y);
            y += fm.getHeight();
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(width, height);
    }

    public void askAgentForMark(ArrayList<String> info){
        information.clear();
        information = info;
        repaint();
    }

    public void clearInfo(){
        information.clear();
        repaint();
    }

    public void splitCake(double leftBound, double rightBound){

    }

    public void setMarkListener(MarkListener listener){
        this.listener=listener;
    }
}
