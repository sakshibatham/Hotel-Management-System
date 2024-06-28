import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame implements ActionListener {
    JTable table;
    JButton Back;
    Department(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel l1 = new JLabel("Department");
        l1.setBounds(180,10,100,20);
        add(l1);

        JLabel l2 = new JLabel("Budget");
        l2.setBounds(480,10,100,20);
        add(l2);

        table = new JTable();
        table.setBounds(0,50,700,350);
        add(table);

        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from department");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e){
            e.printStackTrace();
        }

        Back = new JButton("Back");
        Back.setBounds(280,400,120,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        setBounds(400,200,700,480);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    public static void main(String[] args) {
        new Department();
    }
}
