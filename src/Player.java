import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * This is Player class
 * @author Ronald
 *
 */
public class Player{
	Map<PositionPair,Status>statusMap;
	Status currentStatus;
	private ArrayList<String> visitedPath;
	private int bestScore;
	private String bestData;
	private String bestPathString;
	private ArrayList<String> bestPath;
	/**
	 * This is the constructor of Player
	 */
	public Player(){
		statusMap = new HashMap<>();
		currentStatus = new Status();
		visitedPath = new ArrayList<>();
		bestScore = 0;
	}
	/**
	 * This method will return the visitedPath as ArrayList
	 * @return visitedPath
	 */
	public ArrayList<String> getVisited(){
		return visitedPath;
	}
	/**
	 * This will check if the position p's Pokemon is caught or not
	 * @param p
	 * @return has caught
	 */
	public boolean hasCaught(PositionPair p){
		return currentStatus.getCaughts().contains(p);
	}
	/**
	 * This will check if the position is visited or not
	 * @param p
	 * @return visited or not
	 */
	public boolean visited(PositionPair p){
		String temp = "<"+p.getRow()+","+p.getCol()+">";
		return visitedPath.contains(temp);
	}
	/**
	 * This method will add p into the visitedPath
	 * @param p
	 */
	public void addVisitedPath(PositionPair p){
		String temp = "<"+p.getRow()+","+p.getCol()+">";
		visitedPath.add(temp);
	}
	/**
	 * This method will remove the last item from the visitedPath
	 * This is used to restore the VisiedPath.
	 */
	public void restoreVisitedPath(){
		if(!visitedPath.isEmpty()){
			visitedPath.remove(visitedPath.size()-1);
		}
	}
	/**
	 * This method is to calculate the score of the player 
	 * and replace the best data with the new best data if the score is higher than the previous best score
	 */
	public void calculateBestScore(){
		//System.out.println("BestScoreCalculated");
		int NB = currentStatus.getBalls();
		int NP = currentStatus.getCaughts().size();
		int NS = 0;
		int MCP = 0;
		int Steps = visitedPath.size();
		//System.out.println(visitedPath);
		ArrayList<String> existedType = new ArrayList<>();
		for(Pokemon p:currentStatus.getCaughts()){
			if(p.getCPower()>MCP)MCP = p.getCPower();
			if(!existedType.contains(p.getType())){
				NS++;
				existedType.add(p.getType());
			}
		}
		int currentScore = NB +5*NP+10*NS+MCP -Steps;
		if(currentScore > bestScore){
			bestScore = currentScore;
			bestPath = new ArrayList<>(visitedPath);
			bestData = NB+":"+NP+":"+NS+":"+MCP;
		}
	}
	/**
	 * This method returns the bestpath of the player
	 * @return bestpath
	 */
	public ArrayList<String> getBestPath(){
		return bestPath;
	}
	/**
	 * This method returns the best score of the player
	 * @return bestScore
	 */
	public int getBestScore(){
		return bestScore;
	}
	/**
	 * This method returns the best data i.e. NB:NP:NS:MCP
	 * @return NB:NP:NS:MCP
	 */
	public String getBestData(){
		return bestData;
	}
	/**
	 * This method complete the best path and return it as a string ready to write
	 * @param end
	 * @return best path string
	 */
	public String makeBestPath(PositionPair end){
		String temp = "";
		for(String path:bestPath){
				temp += path+"->";
			}
		
		temp+= "<"+end.getRow()+","+ end.getCol()+">";
		bestPathString = temp;
		return temp;
	}
	/**
	 * This method return the best path string
	 * @return best path string
	 */
	public String getBestPathString(){
		return bestPathString;
	}
	
	
	
}