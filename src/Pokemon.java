/**
 * This class is Pokemon and it contains positions, name, type, power and required balls.
 * @author Ronald
 *
 */
public class Pokemon extends PositionPair{
	private String name;
	private String type;
	private int cpower;
	private int reballs;
	
	
	/**
	 * This is the constructor of Pokemon
	 * @param row
	 * @param col
	 * @param name
	 * @param type
	 * @param cpower
	 * @param reballs
	 */
	public Pokemon(int row,int col,String name,String type,int cpower,int reballs){
		super(row,col);
		this.name = name;
		this.type = type;
		this.cpower = cpower;
		this.reballs = reballs;
	}
	/**
	 * This method return the Pokemon's name
	 * @return Pokemon's name
	 */
	public String getName(){
		return name;
	}
	/**
	 * This method returns the Pokemon's type
	 * @return Pokemon's type
	 */
	public String getType(){
		return type;
	}
	/**
	 * This method returns the Pokemon's power
	 * @return Pokemon's power
	 */
	public int getCPower(){
		return cpower;
	}
	/**
	 * This method returns the required balls for capturing the Pokemon
	 * @return required balls
	 */
	public int getReBalls(){
		return reballs;
	}
}
