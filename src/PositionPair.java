/**
 * This is a basic Pair class to represent cell locations in the map
 * @author Ronald
 *
 */
public class PositionPair {
	private int row;
	private int col;
	/**
	 * This is the constructor of the PositionPair
	 * @param row
	 * @param col
	 */
	public PositionPair(int row,int col){
		this.row = row;
		this.col = col;
	}
	/*
	void rowControl(int i){
		row +=i;
	}
	
	void colControl(int i){
		col +=i;
	}
	*/
	/**
	 * This method will return the row of the PositionPair
	 * @return row
	 */
	public int getRow(){
		return row;
	}
	/**
	 * This method will return the column of the PositionPair
	 * @return column
	 */
	public int getCol(){
		return col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (getClass() != obj.getClass())
		//	return false;
		PositionPair other = (PositionPair) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
