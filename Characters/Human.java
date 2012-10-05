import java.util.* ;
public class Human extends Omnivore
{
	public Human(int newX, int newY)
	{
		x = newX;
		y = newY;
		energy = 10;
	}
	public void spawn(ObjectSet obj,  int direction )
	{
		Vector <Integer> coords = processCheck(direction);
		Human newHuman = new Human(x+coords.get(0),y+coords.get(1));
		obj.addObj(newHuman);
	}
	/*
	 * This function implements a psudeo human to check the surrounding spaces for food
	 * if there is food it randomly selects among those
	 * otherwise it will move as normal
	 * */
	public int Humancheck ( ObjectSet obj, RandInt r ) 
	{
		
		
		Vector <Integer> possible = new Vector <Integer>();
		for ( int dir = 0; dir < 4; dir++)
		{
			if (!matches(obj,x+adjx[dir],y+adjy[dir]))
			{
				
				int Futx = x+adjx[dir];
				int Futy = y+adjy[dir];
				for ( int Fdir = 0; Fdir < 4; Fdir++)
				{
					if (((obj.getObj(Futx+adjx[Fdir],Futy+adjy[Fdir]) instanceof Animal) || (obj.getObj(Futx+adjx[Fdir],Futy+adjy[Fdir])) instanceof Plant) && !(getClass().isInstance(obj.getObj(Futx+adjx[Fdir],Futy+adjy[Fdir] )))) 
					{
						
						possible.add(dir+1);
						break;
					}
				}
				
			}
		}

		if (possible.isEmpty())
		{
			return 0;
		}

		return possible.get(r.randInt(possible.size()));
		

		
	}
	public void move ( ObjectSet obj, RandInt r )
	{
		Vector<Integer>movecoords = processCheck(edible(obj,r));
			
			int offsetX= movecoords.get(0);
			int offsetY= movecoords.get(1);
			if (offsetX != 0 || offsetY != 0 )
			// there is something edible
			{
			
				consume(obj,offsetX,offsetY);
			}
			
			
			else
			{
				movecoords.clear();
				movecoords=processCheck(Humancheck(obj,r));
				offsetX = movecoords.get(0);
				offsetY = movecoords.get(1);
				if (offsetX!=0 || offsetY!=0)
				{
					x+=offsetX;
					y+=offsetY;
					
				}
				else
				{
					movecoords.clear();
					movecoords=processCheck(checkspace(obj,r));
					offsetX= movecoords.get(0);
					offsetY= movecoords.get(1);
					
					
					if (offsetX!=0 || offsetY!=0)
					{
						x+=offsetX;
						y+=offsetY;
						
					}
		    	}
				
			}
					
		energy-=3;	
		if (energy > 30 ) 
		{
			int direction = checkspace(obj,r);
			if (direction!= 0)
			{
				spawn(obj,direction);
				
			}
			energy-=20;
		}
		
		
		
		die(obj);
	}
}
