import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class addDriver extends JFrame implements ActionListener {
    JButton add, cancel;
    JTextField tfname, tfage, tfcarcompany, tfmodel, tflocation;
    JComboBox availablecombo, tfgender;
    addDriver(){
        setBounds(300,200,980,470);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma", Font.BOLD,18));
        heading.setBounds(150, 20, 200,20);
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblname.setBounds(60, 70, 120,30);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200,70,150,30);
        add(tfname);

        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblage.setBounds(60, 110, 120,30);
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200,110,150,30);
        add(tfage);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblgender.setBounds(60, 150, 120,30);
        add(lblgender);

        String genderOptions[] = {"Male", "Female"};
        tfgender = new JComboBox(genderOptions);
        tfgender.setBounds(200,150,150,30);
        tfgender.setBackground(Color.WHITE);
        add(tfgender);

        JLabel lblcarcompany = new JLabel("Car Company");
        lblcarcompany.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblcarcompany.setBounds(60, 190, 120,30);
        add(lblcarcompany);

        tfcarcompany = new JTextField();
        tfcarcompany.setBounds(200,190,150,30);
        add(tfcarcompany);

        JLabel lblmodel = new JLabel("Car Model");
        lblmodel.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblmodel.setBounds(60, 230, 120,30);
        add(lblmodel);

        tfmodel = new JTextField();
        tfmodel.setBounds(200,230,150,30);
        add(tfmodel);

        JLabel lblavailable = new JLabel("Availibilty");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN,16));
        lblavailable.setBounds(60, 270, 120,30);
        add(lblavailable);

        String driverOptions[] = {"Available", "Busy"};
        availablecombo = new JComboBox(driverOptions);
        availablecombo.setBounds(200,270,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        JLabel lbllocation = new JLabel("Location");
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN,16));
        lbllocation.setBounds(60, 310, 120,30);
        add(lbllocation);

        tflocation = new JTextField();
        tflocation.setBounds(200,310,150,30);
        add(tflocation);

        add = new JButton("Add Driver");
        add.setBounds(60,370,130,30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,370,130,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,60,500,300);
        add(image);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String name = tfname.getText();
            String age = (String) tfage.getText();
            String gender = (String) tfgender.getSelectedItem();
            String company = tfcarcompany.getText();
            String brand = tfmodel.getText();
            String available = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();

            try{
                conn conn = new conn();

                String str = "insert into driver values('"+name+"','"+age+"','"+gender+"','"+company+"','"+brand+"','"+available+"','"+location+"')";
                conn.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null,"New Driver Added Successfully");
                setVisible(false);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new addDriver();
    }
}
