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
		width = 38;
		height = 38;
		maze = new Maze(width, height);

		// 4. select a random cell to start

		int randX = new Random().nextInt(w);
		int randY = new Random().nextInt(h);

		Cell randomCell = maze.getCell(randX, randY);

		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(randomCell);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited

		currentCell.setBeenVisited(true);

		// B. check for unvisited neighbors using the cell

		ArrayList<Cell> cellsThatHaveNotBeenNoticed = getUnvisitedNeighbors(currentCell);

		if (cellsThatHaveNotBeenNoticed.size() > 0) {
			int r = new Random().nextInt(cellsThatHaveNotBeenNoticed.size());
			Cell uncheckedCell = cellsThatHaveNotBeenNoticed.get(r);
			uncheckedCells.push(uncheckedCell);
			removeWalls(currentCell, uncheckedCell);
			currentCell = uncheckedCell;
			currentCell.hasBeenVisited();
			selectNextPath(currentCell);
		} else {
			if (!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				System.out.println("NYESSS");
				selectNextPath(currentCell);
			}
		}
		System.out.println(cellsThatHaveNotBeenNoticed.size());

		// C. if has unvisited neighbors,

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		} else {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> newList = new ArrayList<Cell>();
		if (c.getX() > 0 && !maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX() - 1, c.getY()));
		}
		if (c.getX() < maze.getWidth() - 1 && !maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX() + 1, c.getY()));
		}
		if (c.getY() > 0 && !maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX(), c.getY() - 1));
		}
		if (c.getY() < maze.getHeight() - 1 && !maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited()) {
			newList.add(maze.getCell(c.getX(), c.getY() + 1));
		}
		return newList;
	}
}