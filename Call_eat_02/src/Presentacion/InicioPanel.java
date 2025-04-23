package Presentacion;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

public class InicioPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
    private CardLayout cardLayout;
    
    public InicioPanel(JPanel panelContenedor, CardLayout cardLayout) {
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        initComponents();
    }
    
    private void initComponents() {
        // Usamos un FlowLayout para centrar los botones
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));
        panelBotones.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("         Call&Eat", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);
        
        ImageIcon logoIcon = new ImageIcon("resources/logo.png");
        Image imagenLogoEscalada = logoIcon.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
        JLabel logoLabel=new JLabel(new ImageIcon(imagenLogoEscalada));
        JPanel panelSuperior=new JPanel(new BorderLayout());
        panelSuperior.add(titulo, BorderLayout.CENTER);
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(logoLabel,BorderLayout.LINE_END);
        // Al hacer clic en "Acceder", se muestra el panel de login
        ImageIcon iconoAcceder = new ImageIcon("resources/acceder.jpg");
        Image imagenAccederEscalada = iconoAcceder.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JButton btnAcceder= new JButton("Acceder",new ImageIcon(imagenAccederEscalada));
        btnAcceder.setFont(new Font("Arial",Font.BOLD,20));
        btnAcceder.setBackground(Color.WHITE);
        btnAcceder.setPreferredSize(new Dimension(140, 140));
        btnAcceder.setContentAreaFilled(false); // elimina el fondo estirado
        btnAcceder.setBorderPainted(false);    // elimina el borde
        btnAcceder.setFocusPainted(false);     // quita ese borde de foco azul
        btnAcceder.setHorizontalAlignment(SwingConstants.CENTER);
        btnAcceder.setVerticalAlignment(SwingConstants.CENTER);
        btnAcceder.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAcceder.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAcceder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "login");
            }
        });
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(btnAcceder);
        panelBotones.add(Box.createHorizontalGlue());
        add(panelSuperior,BorderLayout.NORTH);
        add(panelBotones,BorderLayout.CENTER);
    }
}


