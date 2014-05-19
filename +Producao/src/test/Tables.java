/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Brawan
 */
public class Tables{
    
    public Tables(final String[] columnNames , final Object[][] data){
        
        final JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem export = new JMenuItem("Export");
        JMenuItem close = new JMenuItem("Close");
        
        menuBar.add(fileMenu);
        fileMenu.add(export);
        fileMenu.add(close);

                
        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object name;
                name = JOptionPane.showInputDialog(null,"Escreva o Nome Desejado","Name Required",JOptionPane.OK_CANCEL_OPTION);
                 
                if(name != null){
                    try {
                        Export writer = new Export();
                        writer.exportStr(columnNames,(String)name+".csv",false);
                        writer.exportOM(data,(String)name+".csv",true);

                    } catch (IOException ex) {
                        Logger.getLogger(Tables.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           }
        });
        
       close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuBar.setEnabled(false);
            }
        });

       
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
       
        JFrame frame = new JFrame("Result");
        frame.setJMenuBar(menuBar);
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        scrollPane.setOpaque(true);
        frame.setContentPane(scrollPane);
        frame.pack();
        frame.setVisible(true);
    
    }

    
}
