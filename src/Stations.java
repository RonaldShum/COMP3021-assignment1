/**
 * This class is represent the stations in Pokemon game
 * @author Ronald
 *
 */
public class Stations extends PositionPair{
	private int pBalls;
	/**
	 * This is the constructor of the Station
	 * @param row
	 * @param col
	 * @param balls
	 */
	public Stations(int row,int col,int balls){
		super(row, col);
		pBalls = balls;
	}
	/**
	 * This mehtod will return the number of balls sotred in the station
	 * @return number of balls
	 */
	public int getPBalls(){
		return pBalls;
	}

}
