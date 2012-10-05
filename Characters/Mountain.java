public class Mountain extends GameObject
{
	public Mountain ( int newX, int newY )
	{
		x = newX ;
		y = newY ;
		//energy = 0; //mountains have no energy
		
	}
	public void move(ObjectSet obj,RandInt r)
	{
		//do nothing mountains don't move
	}
	public int getEnergy()
	{
		return 0;
	}
}
