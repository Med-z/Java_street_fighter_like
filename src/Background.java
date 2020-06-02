/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author mateo
 */
public class Background extends JFrame
    {
        private Container c;
        private JPanel imagePanel;
        private String filePath;
        public Background(String filePath)
        {
            super("background de la zone de combat");
            this.filePath = filePath;
            initialize();
        }
        private void initialize()
        {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            c = getContentPane();
            imagePanel = new JPanel()
            {
                @Override
                public void paint(Graphics g)
                {
                    try
                    {
                        BufferedImage image = ImageIO.read(new File(filePath));
                        g.drawImage(image, 0, 0, null);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            imagePanel.setPreferredSize(new Dimension(640, 480));
            c.add(imagePanel);
        }
    }
