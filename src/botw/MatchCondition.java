package botw;

public interface MatchCondition<T>
{
	/*
	 * This is used to compare the item to some condition
	 * you provide. 
	 */
	public boolean matchCondition(T a);
}
