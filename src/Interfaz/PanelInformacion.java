package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelInformacion extends JPanel
{
	private JLabel labelCantidadMinas;
	
	private JTextField txtCantidadMinas;
	
	public PanelInformacion( )
	{
		setLayout( new GridLayout(1,2) );
		setBorder( new TitledBorder( "Información:" ) );
		
		labelCantidadMinas = new JLabel("Cantidad de Minas: ");
		txtCantidadMinas = new JTextField(" :( ");
		txtCantidadMinas.setEditable(false);
		
		add( labelCantidadMinas );
		add( txtCantidadMinas );
	}
	
	public void actualizar( int pCantidadMinas, int pCantidadMarcadas )
	{
		txtCantidadMinas.setText( pCantidadMarcadas + " / " + pCantidadMinas );
	}

}
