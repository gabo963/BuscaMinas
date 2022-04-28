package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Interfaz.*;

import Mundo.Buscaminas;
import javafx.scene.layout.Border;

public class InterfazBuscaminas extends JFrame
{

	private Buscaminas mundo;

	private PanelControl panelControl;

	private PanelTablero panelTablero;
	
	private PanelInformacion panelInformacion;

	public InterfazBuscaminas() throws Exception 
	{

		setTitle("Buscaminas") ;
		setSize(1000,1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		panelControl = new PanelControl(this);
		add( panelControl, BorderLayout.NORTH );

		panelTablero = new PanelTablero(this);
		add( panelTablero, BorderLayout.CENTER );
		
		panelInformacion = new PanelInformacion();
		add( panelInformacion, BorderLayout.SOUTH );
		
	}

	public void empezar() 
	{


		mundo = new Buscaminas(10, 10);

		panelTablero.actualizar( mundo.darEspacios(), false );
		panelInformacion.actualizar(  mundo.darCantidadMinas(), mundo.darCantidadMarcadas() );


	}

	public void click(int pFila, int pColumna ) 
	{
		if( panelControl.darPisar() )
		{
			try 
			{
				mundo.destapar(pFila, pColumna);
				panelTablero.actualizar( mundo.darEspacios(), false );
			} 
			catch (Exception e) 
			{
				panelTablero.actualizar( mundo.darEspacios(), true );
				JOptionPane.showMessageDialog(this, e.getMessage());
				// TODO Auto-generated catch block

			}
		}
		else
		{
			mundo.marcar(pFila, pColumna);
			panelTablero.actualizar( mundo.darEspacios(), false );
		}
		
		panelInformacion.actualizar(  mundo.darCantidadMinas(), mundo.darCantidadMarcadas() );
		
		if( mundo.gano() )
		{
			JOptionPane.showMessageDialog(this, "Felicidades ha ganado!");
			panelTablero.actualizar(mundo.darEspacios(), true);
		}
	}

	public static void main (String[] pArgs) {
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazBuscaminas interfaz = new InterfazBuscaminas();
			interfaz.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}	
}
