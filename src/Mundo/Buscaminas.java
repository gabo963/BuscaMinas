package Mundo;

import java.util.Random;

public class Buscaminas 
{

	private int filas;

	private int columnas;

	private Espacio espacios[][];

	private int movimientos;

	public Buscaminas( int pFilas, int pColumnas )
	{
		filas = pFilas;
		columnas = pColumnas;

		espacios = new Espacio[ filas ][ columnas ];

		for (int i = 0; i < espacios.length; i++) 
		{
			for (int j = 0; j < espacios[0].length; j++) 
			{
				espacios[i][j] = new Espacio();
			}
		}

		minarCampo();

		for (int i = 0; i < espacios.length; i++) 
		{
			for (int j = 0; j < espacios[0].length; j++) 
			{
				espacios[i][j].cambiarBombasCerca( contarMinas(i, j) );
			}
		}
	}

	public Espacio[][] darEspacios( )
	{
		return espacios;
	}

	public int darFilas( )
	{
		return filas;
	}

	public int darColumnas( )
	{
		return columnas;
	}

	public int darMovimientos( )
	{
		return movimientos;
	}
	
	public boolean gano(  )
	{
		boolean gano = false;
		int cantidad = 0;
		
		
		for( int i = 0; i < espacios.length; i++ )
		{
			for (int j = 0; j < espacios[0].length; j++) 
			{
				if( espacios[i][j].darMarcado() && espacios[i][j].darMina() )
				{
					cantidad++;
				}
			}
		}
		
		if( cantidad == darCantidadMinas() )
		{
			gano = true;
		}
		
		return gano;
	}

	public void destapar( int pF, int pC ) throws Exception
	{
		if( !espacios[pF][pC].darMarcado() )
		{
			//Esta en 0
			if( espacios[pF][pC].darBombasCerca() == 0 && !espacios[pF][pC].darMina() )
			{
				destaparVaciasAledanas(pF, pC);
			}

			//Tiene Numero
			if( espacios[pF][pC].darBombasCerca() > 0 && !espacios[pF][pC].darMina() )
			{
				espacios[pF][pC].destapar();
			}

			//Tiene una Bomba
			if( espacios[pF][pC].darMina() )
			{
				espacios[pF][pC].totear();
				throw new Exception( "¡Pisaste una Mina!" );
			}
			
			//Vaciar vecinas
			if( espacios[pF][pC].darBombasCerca() == contarMarcadas(pF, pC) )
			{
				destaparAledanas(pF, pC);
			}
			
		}
	}
	
	public void destaparAledanas( int pF, int pC ) throws Exception
	{
		for( int i = -1; i < 2; i++ )
		{
			for( int j = -1; j < 2; j++ )
			{
				if( pF + i >= 0 && pF + i < filas && pC + j >= 0 && pC + j < columnas )
				{
					if( !espacios[pF + i][ pC + j ].darMarcado() )
					{
						if( espacios[pF + i][ pC + j ].darMina() )
						{
							espacios[pF + i][ pC + j ].totear();
							throw new Exception( "¡Pisaste una Mina!" );
						}
						else
						{
							espacios[pF + i][ pC + j ].destapar();
						}
					}
				}
			}
		}
	}

	public void destaparVaciasAledanas( int pF, int pC )
	{
		for( int i = -1; i < 2; i++ )
		{
			for( int j = -1; j < 2; j++ )
			{
				if( pF + i >= 0 && pF + i < filas && pC + j >= 0 && pC + j < columnas )
				{
					if( espacios[pF + i][pC + j].darBombasCerca() == 0 && !espacios[pF + i][pC + j].darDestapado())
					{
						espacios[pF + i][pC + j].destapar();
						destaparVaciasAledanas(pF + i, pC + j);

					}

					if( espacios[pF + i][pC + j].darBombasCerca() > 0 && !espacios[pF + i][pC + j].darDestapado() )
					{
						espacios[pF + i][pC + j].destapar();
					}
				}
			}
		}
	}

	public void marcar( int pF, int pC )
	{
		espacios[pF][pC].marcar( !espacios[pF][pC].darMarcado() );
	}

	public void minarCampo( )
	{
		int minas = (int) ((filas * columnas)* 0.15) ;

		int contador = 0;

		while( contador < minas )
		{
			int fila = generarNumeroAleatorio(0, filas);
			int columna = generarNumeroAleatorio(0, columnas);

			if( !espacios[fila][columna].darMina() )
			{
				espacios[fila][columna].minar();
				contador++;
			}

		}

	}

	public int contarMinas( int pF, int pC )
	{
		int minas = 0;

		for (int i = -1; i < 2; i++) 
		{
			for (int j = -1; j < 2; j++) 
			{
				if( pF + i >= 0 && pF + i < filas && pC + j >= 0 && pC + j < columnas )
				{
					if( !(pF + i == pF && pC + j == pC) )
					{
						if( espacios[ pF + i ][ pC + j ].darMina() )
						{
							minas++;
						}
					}
				}
			}
		}

		return minas;
	}

	public int contarMarcadas( int pF, int pC )
	{
		int minas = 0;

		for (int i = -1; i < 2; i++) 
		{
			for (int j = -1; j < 2; j++) 
			{
				if( pF + i >= 0 && pF + i < filas && pC + j >= 0 && pC + j < columnas )
				{
					if( !(pF + i == pF && pC + j == pC) )
					{
						if( espacios[ pF + i ][ pC + j ].darMarcado() )
						{
							minas++;
						}
					}
				}
			}
		}

		return minas;
	}

	public int darCantidadMinas( )
	{
		int minas = 0;

		for (int i = 0; i < espacios.length; i++) 
		{
			for (int j = 0; j < espacios[0].length; j++) 
			{
				if( espacios[i][j].darMina() )
				{
					minas++;
				}
			}
		}

		return minas;
	}
	
	public int darCantidadMarcadas( )
	{
		int minas = 0;

		for (int i = 0; i < espacios.length; i++) 
		{
			for (int j = 0; j < espacios[0].length; j++) 
			{
				if( espacios[i][j].darMarcado() )
				{
					minas++;
				}
			}
		}

		return minas;
	}

	private int generarNumeroAleatorio( int pLimiteInferior, int pLimiteSuperior )
	{
		return new Random().nextInt(pLimiteSuperior-pLimiteInferior) + pLimiteInferior;
	}
}
