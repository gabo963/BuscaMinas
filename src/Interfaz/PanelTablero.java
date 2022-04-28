package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Mundo.Espacio;

public class PanelTablero extends JPanel implements ActionListener
{
	private JButton botones[][];
	
	private InterfazBuscaminas principal;
	
	public PanelTablero( InterfazBuscaminas pPrincipal )
	{
		principal = pPrincipal;
	}
	
	public void actualizar( Espacio[][] pEspacios, boolean pPerdio )
	{
		removeAll();
		
		setLayout( new GridLayout( pEspacios.length, pEspacios[0].length ) );
		botones = new JButton[pEspacios.length][pEspacios[0].length];
		
		for (int i = 0; i < pEspacios.length; i++) 
		{
			for (int j = 0; j < pEspacios[0].length; j++) 
			{
				botones[i][j] = new JButton( );
				botones[i][j].setActionCommand( "" + i + j );
				botones[i][j].addActionListener(this);
				
				//botones[i][j].setText( "" + pEspacios[i][j].darBombasCerca() );
				
				if( pPerdio )
				{
					botones[i][j].setText( "" + pEspacios[i][j].darBombasCerca() );
					botones[i][j].setBackground( Color.GREEN );
					Font tipoLetra = botones[i][j].getFont( );
					Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, 30 );
					botones[i][j].setHorizontalAlignment( JTextField.CENTER );  
					botones[i][j].setFont( nuevoTipoLetra );
					botones[i][j].setForeground( Color.WHITE );
					
					
					if( pEspacios[i][j].darBombasCerca() == 0 )
					{
						botones[i][j].setText( "" );
					}
					if( pEspacios[i][j].darMina() )
					{
						botones[i][j].setText( "M" );
					}
					
					
				}
				
				botones[i][j].setEnabled( !pPerdio );
				
				
				if( pEspacios[i][j].darDestapado() && pEspacios[i][j].darBombasCerca() == 0 )
				{
					botones[i][j].setBackground( Color.LIGHT_GRAY );
					botones[i][j].setText( "" );
					botones[i][j].setEnabled(false);
				}
				if( pEspacios[i][j].darDestapado() && pEspacios[i][j].darBombasCerca() > 0 )
				{
					botones[i][j].setBackground( Color.LIGHT_GRAY );
					botones[i][j].setText( "" + pEspacios[i][j].darBombasCerca() );
					Font tipoLetra = botones[i][j].getFont( );
					Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, 30 );
					botones[i][j].setHorizontalAlignment( JTextField.CENTER );  
					botones[i][j].setFont( nuevoTipoLetra );
					botones[i][j].setForeground( Color.RED );
				}
				
				if( pEspacios[i][j].darMarcado() )
				{
					botones[i][j].setBackground( Color.BLUE );
				}
				
				if( pEspacios[i][j].estaToteado() )
				{
					botones[i][j].setBackground( Color.RED );
				}
				
				
				add( botones[i][j] );
			}
		}
		
		updateUI();
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent e) 
	{
		int fila = Integer.parseInt( "" + e.getActionCommand().charAt(0) );
		int columna = Integer.parseInt( "" + e.getActionCommand().charAt(1) );
		
		principal.click( fila, columna );
	}
}
