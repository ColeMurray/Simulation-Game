public abstract class GameObject 
{
	protected int x;
	protected int y;
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public abstract void move(ObjectSet obj,RandInt r);
	public abstract int getEnergy();
		
		
}
