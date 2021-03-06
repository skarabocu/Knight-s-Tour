//Sinan Karabocuoglu & Kaan Cınar
//17 February 2018
//Table Components
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TableComponents
{
String game = "";//the final display of the board
int currentRow = 0;//current row
int currentColumn = 0;//current colum
int moveNumber = 1;//number of moves
int horizontalMoves[] = {2,1,-1,-2,-2,-1,1,2};//possible horizontal moves
int verticalMoves[] = {-1,-2,-2,-1,1,2,2,1};//possible vertical moves
int forbiddenRow = 0;//after a backtrack, program signs the erased row as forbidden, so backtrack won't repeat itself.
int forbiddenColum = 0;//after a backtrack, program signs the erased column as forbidden, so backtrack won't repeat itself.
ArrayList <Location> moves = new ArrayList<Location>();//list of all the moves
int boxes[][] = //the board array
  {
    {2,3,4,4,4,4,3,2},
    {3,4,6,6,6,6,4,3},
    {4,6,8,8,8,8,6,4},
    {4,6,8,8,8,8,6,4},
    {4,6,8,8,8,8,6,4},
    {4,6,8,8,8,8,6,4},
    {3,4,6,6,6,6,4,3},
    {2,3,4,4,4,4,3,2},
  };
int finalGame[][] = //the board array
  {
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},
  };

 public TableComponents(int a, int b)
 {
   currentRow = a;
   currentColumn = b;
   Location location = new Location(currentRow, currentColumn);//creates the location
   moves.add(location);//adds to the list
   finalGame [currentRow][currentColumn] = moveNumber;//signs the final board
   moveNumber++;//increment the move number
 }
 public boolean isPossible(int horizontal, int vertical)//checks if the move is possible
 {
   boolean found = false;
   if((currentRow + horizontal >=0) && (currentColumn + vertical >=0) && (currentRow + horizontal <=7) && (currentColumn + vertical <=7))//checks if the location is on the chessboard
   {
     if(finalGame[currentRow + horizontal][currentColumn + vertical] == 0)//checks if the location is occupied or not
     { 
   found = true;
     }
   }
   return found;
 }
 public void nextMove()//decides the next move
 {
   int a = 0;//order of the arrays
   int move[] = new int [8];//the order of the move
   int numbers [] = new int [8];//the accessibility of the move
   for(int i = 0; i< 8; i++)//loop runs all the possible moves and decides wheter it is valid or not, then add the valid moves to an array
   {
     if(isPossible(horizontalMoves[i],verticalMoves[i]))//checks if the move is possible
     {
       boxes[currentRow + horizontalMoves[i]][currentColumn + verticalMoves[i]] -=1;
       if(!((currentRow + horizontalMoves[i] == forbiddenRow) && (currentColumn + verticalMoves[i] == forbiddenColum)))//checks if the position is forbidden or not
       {
       numbers[a]= boxes[currentRow + horizontalMoves[i]][currentColumn + verticalMoves[i]];//adds the accesibility to an array
       move[a] = i;//adds the index number to another array
       a++;//increments the index of the array
       }
     }
   }
   if(a == 0)//if there is no more available box, the program will back track
   {
    forbiddenRow = currentRow;//sets the row as forbidden
    forbiddenColum = currentColumn;//sets the column as forbidden
    currentRow = moves.get(moves.size()-1).getRow();//goes to the previous row
    currentColumn = moves.get(moves.size()-1).getColum();//goes to the previous column
    finalGame[(moves.get(moves.size()-1)).getRow()][(moves.get(moves.size()-1)).getColum()] = 0;
    moveNumber--;
    moves.remove(moves.size()-1);//remove the last move
    System.out.println("backtrack");
    nextMove();//recurse
   }
   else{
   int lowest = numbers [0];//the lowest accessibility
   int lowestOrder = 0;//order of the lowest accessibility
   for(int c = 0;c< a; c++)//loop for finding the lowest accessibility 
   {
     if(numbers[c]< lowest)
     {
       lowest = numbers[c];
       lowestOrder = c;
     }
   }
   currentRow += horizontalMoves[move[lowestOrder]];
   currentColumn += verticalMoves[move[lowestOrder]];
   }
 }
 public void placeKnight()
 {
   nextMove();//finds the next move
   System.out.println(currentRow + " " +currentColumn+": "+moveNumber);
   System.out.println(printGame());
   game = "";
   finalGame[currentRow][currentColumn] = moveNumber;//sign the position
   moveNumber++;//increment the move number
   Location loc = new Location(currentRow, currentColumn);//creates new location
   moves.add(loc);//add the location to the list
   forbiddenRow = 0;//reset the forbiddenRow
   forbiddenColum = 0;//reset the forbiddenColumn
   if(moveNumber != 65)//recursion
   {
   placeKnight();//recurse
   }
   else{}
 }
 public String printGame()
 {
  for(int a = 0; a <8;a++)
    {
      for(int b = 0; b<8; b++)
      {
        game = game + finalGame[a][b]+ " ";
      }
      game = game + "\n";
    }
    return game + "\n"; 
 }
}

