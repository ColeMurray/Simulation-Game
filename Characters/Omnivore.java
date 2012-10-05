import java.util.*;
public abstract class Omnivore extends Animal 
{
	public int edible(ObjectSet obj, RandInt r)
	{
		
		Vector <Integer> possible = new Vector <Integer>();
		for ( int dir = 0; dir < 4; dir++)
		{
			if (((obj.getObj(x+adjx[dir],y+adjy[dir]) instanceof Animal) || (obj.getObj(x+adjx[dir],y+adjy[dir])) instanceof Plant) && !(getClass().isInstance(obj.getObj(x+adjx[dir],y+adjy[dir] )))) 
			{
				possible.add(dir+1);
			}
		}
		if (possible.isEmpty())
		{
			return 0;
		}
		return possible.get(r.randInt(possible.size()));
	}
		public void consume ( ObjectSet obj, int offsetX, int offsetY)
	{
		GameObject food = obj.getObj(x+offsetX,y+offsetY);
		int foodEnergy = food.getEnergy();
		int omvEnergy = (3*foodEnergy)/4; 
		energy+=omvEnergy;
		obj.delObj( food );
		x+=offsetX;
		y+=offsetY;
	}
}
