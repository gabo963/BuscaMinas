package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelControl extends JPanel implements ActionListener
{
	public final static String START = "Iniciar";
	
	public final static String MODO = "Modo";
	
	private InterfazBuscaminas principal;
	
	private JButton btnEmpezar;
	
	private JButton btnModo;
	
	private boolean pisar;
	
	
	public PanelControl( InterfazBuscaminas pPrincipal )
	{
		setLayout( new GridLayout(1,2) );
		
		principal = pPrincipal;
		
		btnEmpezar = new JButton( START );
		btnEmpezar.setActionCommand(START);
		btnEmpezar.addActionListener(this);
		add(btnEmpezar);
		
		btnModo = new JButton( "Pisar" );
		pisar = true;
		btnModo.setActionCommand(MODO);
		btnModo.addActionListener(this);
		add( btnModo );
	}
	
	public boolean darPisar( )
	{
		return pisar;
	}

	
	public void actionPerformed(ActionEvent f) 
	{
		String comando = f.getActionCommand();
		
		if( comando.equals(START) )
		{
			principal.empezar( );
		}
		if( comando.equals(MODO) )
		{
			if( !pisar )
			{
				btnModo.setText( "Pisar" );
				pisar = true;
			}
			else
			{
				btnModo.setText( "Marcar" );
				pisar = false;
			}
			
		}
		
		
	}
}
