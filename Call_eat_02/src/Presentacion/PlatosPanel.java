package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PlatosPanel extends JPanel{
	
	private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Controlador controlador;
	
	public PlatosPanel(JPanel panelContenedor, CardLayout cardLayout,Controlador controlador){
		this.panelContenedor=panelContenedor;
		this.cardLayout=cardLayout;
		this.controlador=controlador;
		initComponents();
	}
	private void initComponents() {
		this.setSize(1000,500);
		this.setLayout(new BorderLayout());//Panel izquierdo para platos y el derecho para los botones
		JPanel panelPlatos=new JPanel(new GridLayout(0,3));
		JPanel panelBotones=new JPanel(new FlowLayout());
		JPanel botonAtras=new JPanel();
		this.add(panelPlatos,BorderLayout.CENTER);
		this.add(panelBotones,BorderLayout.LINE_END);
		this.add(botonAtras,BorderLayout.PAGE_START);
		
		
	}
}
