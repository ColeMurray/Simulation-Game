public abstract class Animal extends Living
{
	public void die(ObjectSet obj)
	{
		if (energy<0)
		{
			obj.delObj(this);
		}
	}
	public abstract int edible(ObjectSet obj,RandInt r);
	
}
	
