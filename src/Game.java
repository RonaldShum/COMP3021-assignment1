import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;
/**
 * This class is the game and it also contains the main function
 *
 */
public class Game {
	Player player = new Player();
	
	/**
	 * This function initialize the game, including reading the map and finding the best path
	 * @param inputFile
	 * @throws Exception
	 */
	public void initialize(File inputFile) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		// Read the first of the input file
		String line = br.readLine();
		int M = Integer.parseInt(line.split(" ")[0]);
		int N = Integer.parseInt(line.split(" ")[1]);
		
		// To do: define a map
		GameMap map = new GameMap(M, N);
		
		// Read the following M lines of the Map
		for (int i = 0; i < M; i++) {
			line = br.readLine();
			
			
			// to do
			// Read the map line by line
			map.addLine(i, line);
		}
		//map.printMap();
		
		// to do
		// Find the number of stations and pokemons in the map 
		// Continue read the information of all the stations and pokemons by using br.readLine();
		int[] num = {0,0};
		map.findNum(num);
		/*System.out.println("This is start:"+map.Start.getRow()+" "+map.Start.getCol());
		System.out.println("This is end:"+map.End.getRow()+" "+map.End.getCol());
		System.out.println("Number of P/S: "+num[0]+"/"+num[1]);*/
		
		for(int i = 0; i < num[0]; i++){
			line = br.readLine();
			map.addPokemon(line);
			//System.out.println(map.specialCell.get(i).getRow()+","+map.specialCell.get(i).getCol());
		}
		
		for(int i = 0; i<num[1];i++){
			line = br.readLine();
			map.addStation(line);
		}
		Pokemon test1;
		Stations test2;
		for(int i = 0; i< num[0]+num[1];i++){
			if(map.specialCell.get(i) instanceof Pokemon){
				test1 = (Pokemon)map.specialCell.get(i);
			//	System.out.println(test1.getName()+" "+test1.getType()+" "+test1.getRow()+" "+test1.getCol());
			}else{
				test2 = (Stations) map.specialCell.get(i);
			//	System.out.println(test2.getPBalls()+" "+test2.getRow()+" "+test2.getCol());
			}
		}
		
		br.close();
		//System.out.println(map.getMapChar(new PositionPair(1, 14)));
		findPath(map.Start, map.End, map,player);
		/*System.out.println(player.getBestScore());
		System.out.println(player.getBestData());
		System.out.println(player.getBestPath());*/
		//System.out.println("76");
		player.makeBestPath(map.End);
	//	System.out.println(player.getBestPathString());
		
	}
	/**
	 * This main read input txt and ouput txt
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		File inputFile = new File("./sampleIn.txt");
		File outputFile = new File("./sampleOut.txt");
		
		if (args.length > 0) {
			inputFile = new File(args[0]);
		} 

		if (args.length > 1) {
			outputFile = new File(args[1]);
		}
		
		Game game = new Game();
		game.initialize(inputFile);
		// TO DO 
		// Read the configures of the map and pokemons from the file inputFile
		// and output the results to the file outputFile
		FileWriter fw = new FileWriter(outputFile.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		//System.out.println(game.player.getBestScore());
		bw.write(new String(game.player.getBestScore()+""));
		bw.newLine();
		bw.write(game.player.getBestData());
		bw.newLine();
		bw.write(game.player.getBestPathString());
		//bw.newLine();
		//bw.write("test");
		bw.close();
	}
	/**
	 * This method find the best path and store it inside player
	 * @param start
	 * @param end
	 * @param map
	 * @param player
	 */
	void findPath(PositionPair start,PositionPair end,GameMap map,Player player){
		//if it reach the end
		if(start.getCol()<0||start.getRow()<0||start.getRow()>=map.rowT||start.getCol()>=map.colT){
			return;
		}
		
		char current = map.getMapChar(start);
		if(current=='#')return;
		Status playerBack;
		Status mapBack;
		
		if(player.statusMap.get(start)==null){
			mapBack = null;
		}else{
			mapBack = player.statusMap.get(start).backUpStatus();
		}
		playerBack = player.currentStatus.backUpStatus();
	/*	
		System.out.print(player.getVisited());
		System.out.print("Current Caught:"+player.currentStatus.getCaughts());
		if(player.statusMap.get(start) !=null){
			System.out.print("Previous status:"+player.statusMap.get(start).getCaughts());
		}
		System.out.print(start.getRow()+","+start.getCol());
		System.out.println((player.visited(start)+" "+player.currentStatus.equals(player.statusMap.get(start))));
		if(player.statusMap.get(new PositionPair(4, 4))!=null){
		System.out.println("4,4:"+player.statusMap.get(new PositionPair(4, 4)).getCaughts());
		}
		if(player.statusMap.get(new PositionPair(5, 4))!=null){
			System.out.println("5,4:"+player.statusMap.get(new PositionPair(5, 4)).getCaughts());
			}*/
		if(start.equals(end)){
			player.calculateBestScore();
			return;
		}else if(player.visited(start)&&player.currentStatus.equals(player.statusMap.get(start))){
			return;//return if it's wall or visited with same status
		}else if(current=='S'&&!player.visited(start)){
			int index = map.specialCell.indexOf(start);
			player.currentStatus.addBalls(((Stations)map.specialCell.get(index)).getPBalls());
		}else if(current=='P'&&!player.hasCaught(start)){
			int index = map.specialCell.indexOf(start);
			Pokemon temp = (Pokemon) map.specialCell.get(index);
			if(player.currentStatus.getBalls()>=temp.getReBalls()){
				player.currentStatus.addCaughts(temp);//caught
				player.currentStatus.delBalls(temp.getReBalls());//del balls
			}
		}
		//update states
		player.addVisitedPath(start);
		//System.out.println("I am updating "+start.getRow()+","+start.getCol()+" with "+player.currentStatus.getCaughts());
		player.statusMap.put(start, player.currentStatus.backUpStatus());
	
		
		//go up right down left
		PositionPair up = new PositionPair(start.getRow()-1,start.getCol());
		PositionPair right = new PositionPair(start.getRow(),start.getCol()+1);
		PositionPair down = new PositionPair(start.getRow()+1,start.getCol());
		PositionPair left = new PositionPair(start.getRow(), start.getCol()-1);
		
		findPath(up, end, map, player);
		if(!player.currentStatus.equals(player.statusMap.get(start))){
		System.out.println("Problems");
		}
		findPath(right, end, map, player);
		findPath(down, end, map, player);
		findPath(left, end, map, player);
		
		player.currentStatus.restoreStatus(playerBack);
		player.statusMap.put(start, mapBack);
		player.restoreVisitedPath();
		
		
		
		
	}
}


