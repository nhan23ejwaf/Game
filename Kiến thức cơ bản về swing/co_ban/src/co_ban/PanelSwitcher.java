/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co_ban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitcher extends JFrame {
    private JPanel panelContainer;
    private JPanel panel1;
    private JPanel panel2;
    private CardLayout cardLayout;

    public PanelSwitcher() {
        // Thiết lập khung
        setTitle("Chuyển giữa các panel");
        setSize(300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo CardLayout để chuyển đổi panel
        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);

        // Panel 1 với layout tùy chỉnh
        panel1 = new JPanel();
        panel1.setLayout(null); // Dùng null layout để tùy chỉnh vị trí

        JPanel subPanel1 = new JPanel();
        subPanel1.setLayout(null); // Đặt null layout cho subPanel1 để tuỳ chỉnh vị trí của các thành phần
        subPanel1.setBackground(Color.LIGHT_GRAY);
        subPanel1.setBounds(0, 0, 150, 700); // Tùy chỉnh vị trí và kích thước

        JLabel helloLabel = new JLabel("Hello");
        helloLabel.setBounds(50, 300, 50, 20); // Tùy chỉnh vị trí của helloLabel trong subPanel1
        subPanel1.add(helloLabel);

        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(Color.YELLOW);
        subPanel2.setBounds(150, 0, 150, 700); // Tùy chỉnh vị trí và kích thước
        subPanel2.add(new JLabel("Good"));

        // Thêm hai panel con vào panel1
        panel1.add(subPanel1);
        panel1.add(subPanel2);

        // Panel 2
        panel2 = new JPanel();
        panel2.setBackground(Color.PINK);
        panel2.add(new JLabel("Đây là Panel 2"));

        // Thêm các panel vào container
        panelContainer.add(panel1, "Panel 1");
        panelContainer.add(panel2, "Panel 2");

        // Tạo nút để chuyển đổi panel
        JButton switchButton = new JButton("Chuyển Panel");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển panel khi nhấn nút
                cardLayout.next(panelContainer);
            }
        });

        // Thêm panel container và nút vào frame
        add(panelContainer, BorderLayout.CENTER);
        add(switchButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PanelSwitcher frame = new PanelSwitcher();
            frame.setVisible(true);
        });
    }
}
