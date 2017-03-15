import java.util.ArrayList;
import java.util.Map;

public class GameMap {
	int rowT;
	int colT;
	private char[][] mapArray;
	PositionPair Start;
	PositionPair End;
	ArrayList<PositionPair> specialCell;
	
	
	GameMap(int M, int N){
		rowT = M;
		colT = N;
		specialCell = new ArrayList<>();
		mapArray = new char[M][N];
	}
	
	public char getMapChar(PositionPair p){
		return mapArray[p.getRow()][p.getCol()];
	}
	
	public void addLine(int row,String line){
		mapArray[row] = line.toCharArray();
	}
	
	public void addStation(String line){
		String[] data = line.trim().split(",");
		int locRow = Integer.parseInt(data[0].replaceAll("\\D+", ""));
		int locCol = Integer.parseInt(data[1].replaceAll("\\D+", ""));
		int numberOfBalls = Integer.parseInt(data[2].trim().replaceAll("\\D+", ""));
		specialCell.add(new Stations(locRow,locCol, numberOfBalls));
	}
	
	public void addPokemon(String line){
		String[] data = line.trim().split(",");
		int locRow = Integer.parseInt(data[0].replaceAll("\\D+", ""));
		int locCol = Integer.parseInt(data[1].replaceAll("\\D+", ""));
		String name = data[2].trim();
		String type = data[3].trim();
		int power = Integer.parseInt(data[4].trim());
		int ballsNeeded = Integer.parseInt(data[5].trim());
		specialCell.add(new Pokemon(locRow,locCol, name, type, power, ballsNeeded));
	}
	
	void printMap(){
		for(int i = 0; i < rowT; i++ ){
			System.out.print(mapArray[i]);
			System.out.print("\n");
		}
	}
	
	void findNum(int[] num){
		char temp;
		for(int i = 0 ; i < rowT;i++){
			for(int j =0; j<colT;j++){
				temp = mapArray[i][j];
				if(temp == 'P')num[0]++;
				if(temp == 'S')num[1]++;
				if(temp == 'B'){
					Start = new PositionPair(i,j);
				}
				if(temp == 'D'){
					End = new PositionPair(i, j);
				}
			}
		}
	
	}
	
}