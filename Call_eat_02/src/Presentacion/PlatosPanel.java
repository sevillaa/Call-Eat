package Presentacion;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class PlatosPanel extends JPanel{
	
	private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Controlador controlador;
	
	public PlatosPanel(JPanel panelContenedor, CardLayout cardLayout,Controlador controlador){
		this.panelContenedor=panelContenedor;
		this.cardLayout=cardLayout;
		this.controlador=controlador;
		
	}
}
