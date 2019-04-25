package harlekinskat4;

/**
 * Class File allowing a rescaling of the frame with all its contents to the screen resolution.
 * 
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.ImageIcon;

public class RescaledIcon extends ImageIcon {
    
    // used to create an icon using a file path
    public RescaledIcon(URL location) {
        super(location);
    }
    
    public RescaledIcon(String filename) {
        super(filename);
    }
    
    // paints the icon (rescales the icon to fit the size of the component (button))
    // @see ImageIcon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        Image image = getImage();
        if (image == null) {
          return;
        }
        Insets insets = ((Container) c).getInsets();
        x = insets.left;
        y = insets.top;

        int w = c.getWidth() - x - insets.right;
        int h = c.getHeight() - y - insets.bottom;

        ImageObserver io = getImageObserver();
        
        // optimisation
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, w, h, io == null ? c : io);
        g2d.dispose();
        g.drawImage(bi, x, y, w, h, io == null ? c : io);
    }

    @Override
    public int getIconWidth() {
        return 0; // size of icon determined by size of component
    }

    @Override
    public int getIconHeight() {
        return 0; // size of icon determined by size of component
    }
    
}
