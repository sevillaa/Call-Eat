package Presentacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;

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
        setLayout(new FlowLayout());
        
        JButton btnAcceder = new JButton("Acceder");
        JButton btnRegistrarse = new JButton("Registrarse");
        
        // Al hacer clic en "Acceder", se muestra el panel de login
        btnAcceder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "login");
            }
        });
        
        // Al hacer clic en "Registrarse", se muestra el panel de registro
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "registro");
            }
        });
        
        add(btnAcceder);
        add(btnRegistrarse);
    }
}


