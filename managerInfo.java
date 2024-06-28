import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class managerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton Back;
    managerInfo(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel l1 = new JLabel("Name");
        l1.setBounds(40,10,100,20);
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(170,10,100,20);
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290,10,100,20);
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400,10,100,20);
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540,10,100,20);
        add(l5);

        JLabel l6 = new JLabel("Phone no.");
        l6.setBounds(670,10,100,20);
        add(l6);

        JLabel l7 = new JLabel("Email");
        l7.setBounds(790,10,100,20);
        add(l7);

        JLabel l8 = new JLabel("Adhaar no.");
        l8.setBounds(910,10,100,20);
        add(l8);

        table = new JTable();
        table.setBounds(0,40,1000,400);
        add(table);

        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from employee where job = 'Manager'");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e){
            e.printStackTrace();
        }

        Back = new JButton("Back");
        Back.setBounds(420,300,50,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        setBounds(300,200,1000,600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    public static void main(String[] args) {
        new managerInfo();
    }
}
