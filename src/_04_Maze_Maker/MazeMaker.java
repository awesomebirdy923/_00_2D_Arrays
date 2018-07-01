package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start

		int randX = new Random().nextInt(w);
		int randY = new Random().nextInt(h);

		Cell randomCell = new Maze(w, h).getCell(randX, randY);

		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(randomCell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited

		currentCell.setBeenVisited(true);

		// B. check for unvisited neighbors using the cell

		if (getUnvisitedNeighbors(currentCell).size() > 0) {
			int r = new Random().nextInt(getUnvisitedNeighbors(currentCell).size());
			uncheckedCells.push(getUnvisitedNeighbors(currentCell).get(r));
			removeWalls(currentCell, getUnvisitedNeighbors(currentCell).get(r));
			currentCell = getUnvisitedNeighbors(currentCell).get(r);
			currentCell.hasBeenVisited();
		}

		// C. if has unvisited neighbors,

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		if (getUnvisitedNeighbors(currentCell).size() == 0 && !uncheckedCells.isEmpty()) {
			Cell poppedCell = uncheckedCells.pop();
			currentCell = poppedCell;
		}

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX() - 1 && c1.getY() == c2.getY()) 
		{
			c1.setEastWall(false);
			c2.setWestWall(false);
		} 
		if(c1.getX() == c2.getX() + 1 && c1.getY() == c2.getY()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> newList = new ArrayList<Cell>();
		if(c.getX() > 0 && c.getY() > 0) {
		if(!maze.getCell(c.getX()+1, c.getY()).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX()+1, c.getY()));
		}
		if(!maze.getCell(c.getX()-1, c.getY()).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX()-1, c.getY()));
		}
		if(!maze.getCell(c.getX(), c.getY()+1).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX(), c.getY()+1));
		}
		if(!maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX(), c.getY()-1));
		}
		}
		return newList;
	}
}