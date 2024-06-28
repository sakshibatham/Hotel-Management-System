import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame implements ActionListener {
    Choice ccustomer;
    JTextField tfroom, tfname, tfCheckIn, tfpaid, tfpending;
    JButton check, update, Back;
    UpdateCheck(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90,20,200,30);
        text.setForeground(Color.BLUE);
        add(text);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30,80,100,20);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(200,80,150,25);
        add(ccustomer);

        try{
            conn conn = new conn();
            ResultSet rs = conn.s.executeQuery("select * from customer");
            while (rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room No.");
        lblroom.setBounds(30,120,100,20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200,120,150,25);
        add(tfroom);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,160,100,20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);

        JLabel lblcheckIn = new JLabel("Check-in TIme");
        lblcheckIn.setBounds(30,200,100,20);
        add(lblcheckIn);

        tfCheckIn = new JTextField();
        tfCheckIn.setBounds(200,200,150,25);
        add(tfCheckIn);

        JLabel lblPaid = new JLabel("Amount Paid");
        lblPaid.setBounds(30,240,100,20);
        add(lblPaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200,240,150,25);
        add(tfpaid);

        JLabel lblpending = new JLabel("Pending Amount");
        lblpending.setBounds(30,280,100,20);
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(200,280,150,25);
        add(tfpending);

        check = new JButton("Check");
        check.setBounds(30,340,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150,340,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        Back = new JButton("Back");
        Back.setBounds(270,340,100,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setBounds(300,200,980,500);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+id+"'";
            try{
                conn conn = new conn();
                ResultSet rs = conn.s.executeQuery(query);
                while (rs.next()){
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfCheckIn.setText(rs.getString("checkin_time"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                ResultSet rs2 = conn.s.executeQuery("Select * from room where roomnumber = '"+tfroom.getText()+"'");
                while (rs2.next()){
                    String price = rs2.getString("price");
                    int amountPending = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpending.setText("" + amountPending);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfCheckIn.getText();
            String deposit = tfpaid.getText();

            try{
                conn c = new conn();
                c.s.executeUpdate("update customer set room = '"+room+"', name = '"+name+"', checkin_time = '"+checkin+"', deposit = '"+deposit+"' where number = '"+number+"'");

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);
                new Reception();

            }catch (Exception e){
                e.printStackTrace();
            }

        } else if (ae.getSource() == Back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
