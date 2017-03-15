import java.util.ArrayList;
/**
 * This class contains the Status of the player
 * @author Ronald
 *
 */
public class Status {
	/**
	 * Status contains the caught Pokemons of the player represented by a Pokemon ArrayList
	 * and number of balls that the player have.
	 */
	private ArrayList<Pokemon>caughts;
	private int numOfBalls;
	/**
	 * No-arg constructor
	 */
	public Status(){
		caughts = new ArrayList<>();
		numOfBalls = 0;
	}
	/**
	 * This will get a status and copy the data inside the status to this Status
	 * The param should be a backup status.
	 * @param backup
	 */
	public void restoreStatus(Status backup){
		caughts = new ArrayList<>(backup.caughts);
		numOfBalls = backup.numOfBalls;
		
	}
	/**
	 * This will return a new Status containing all data in this Status for backup purpose
	 * @return backuped Status
	 */
	public Status backUpStatus(){
		Status temp = new Status();
		temp.caughts = new ArrayList<>(this.caughts);
		temp.numOfBalls = numOfBalls;
		return temp;
	}
	/**
	 * This method will return the Pokemon Arraylist
	 * @return caughts Pokemon
	 */
	public ArrayList<Pokemon> getCaughts(){
		return caughts;
	}
	/**
	 * This method will add number of balls in the Status
	 * @param balls
	 */
	public void addBalls(int balls){
		numOfBalls += balls;
	}
	/**
	 * This method is similar to the previous method.
	 * This method will decrease the number of balls in the Status
	 * @param balls
	 */
	public void delBalls(int balls){
		numOfBalls -= balls;
	}
	/**
	 * This method will return the number of balls in this Status
	 * @return number of balls
	 */
	public int getBalls(){
		return numOfBalls;
	}
	/**
	 * This method will add Pokemon to the caught Pokemon ArrayList
	 * @param new Pokemon
	 */
	public void addCaughts(Pokemon newPo){
		caughts.add(newPo);
	}
	/**
	 * This method will remove Pokemon from the caught Pokemon ArrayList
	 * @param delPo
	 */
	public void delCaughts(Pokemon delPo){
		caughts.remove(delPo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caughts == null) ? 0 : caughts.hashCode());
		result = prime * result + numOfBalls;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (caughts == null) {
			if (other.caughts != null)
				return false;
		} else if (!caughts.equals(other.caughts))
			return false;
		if (numOfBalls != other.numOfBalls)
			return false;
		return true;
	}
	
	
	
}
