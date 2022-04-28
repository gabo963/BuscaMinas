package Mundo;

public class Espacio 
{

	private boolean mina;
	
	private boolean marcado;
	
	private boolean destapado;
	
	private boolean toteado;
	
	private int bombasCerca;
	
	public Espacio( )
	{
		mina = false;
		toteado = false;
		bombasCerca = 0;
		marcado = false;
		destapado = false;
	}
	
	public void destapar( )
	{
		destapado = true;
	}
	
	public void totear( )
	{
		toteado = true;
	}
	
	public boolean estaToteado( )
	{
		return toteado;
	}
	
	public boolean darDestapado( )
	{
		return destapado;
	}
	
	public void minar( )
	{
		mina = true;
	}
	
	public void marcar( boolean pMarcado )
	{
		marcado = pMarcado;
	}
	
	public boolean darMarcado( )
	{
		return marcado;
	}
	
	public boolean darMina()
	{
		return mina;
	}
	
	public int darBombasCerca()
	{
		return bombasCerca;
	}
	
	public void cambiarBombasCerca( int pBombas )
	{
		bombasCerca = pBombas;
	}
}
