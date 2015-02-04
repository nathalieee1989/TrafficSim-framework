package core;

import java.util.*;

public class Road {
	private List<Lane> lanes;
	private int number_of_lanes;
	
	//AM > Create lane(s) and set their length
	public Road(int number_of_lanes, int lane_length)
	{
		this.number_of_lanes = number_of_lanes;
		lanes = new ArrayList<Lane>();
		for(int i = 0; i < this.number_of_lanes; i++)
		{
			Lane lane = new Lane(lane_length);
			lanes.add(lane);
		}	
	}

	public List<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}
	
	public boolean addCar(Car c)
	{
		/*
		 * AM > Randomly add car to a lane
		 * if the lane is occupied add car to the next lane
		 * if all lanes are full then return false
		 * on successful insertion return true;
		 */
		int randomLane = new Random().nextInt((number_of_lanes - 1) + 1) + 1;
		Lane choosenLane = lanes.get(randomLane-1);
		
		if(choosenLane.addCar(c))
		{
			return true;
		}
		else
		{
			//AM > Attempt to add a car to another lane.
			for(Lane l: lanes)
			{
				if(!l.equals(choosenLane))
				{
					if(l.addCar(c))
						return true;
				}
			}
			// AM > We have exhausted all lanes return false;
			return false;
		}
	}
	
}