
package service;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author mayk
 */
public class Bloque extends JFrame{
        
        JPanel jPanel1;
        JLabel jLabel1;
        Block block;
        
        public Bloque(Block block){
            jPanel1 = new JPanel();
            jLabel1 = new JLabel();
            this.block = block;
            initComponent();
        }
        private void initComponent(){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            setResizable(false);
            
            jPanel1.setBackground(block.getColor());

            jLabel1.setFont(new Font("Bitstream Vera Sans", 1, 24)); // NOI18N
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        
            jLabel1.setText(String.valueOf(block.getLife()));
            jPanel1.add(jLabel1);
            
            getContentPane().add(jPanel1);
        
            setSize(block.getWidth(), block.getHeight());
            setLocation(block.getX(), block.getY());
            setVisible(true);
        }
    }