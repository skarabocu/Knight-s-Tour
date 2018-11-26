//Sinan Karabocuoglu & Kaan Cınar
//17 February 2018
//Table Components
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class KnightsTour
{
  public static void main(String [] args)
  {
    int horizontal[] = {0,0,4,4,1,0,2,3,4,2,4,4,1,3,2,3};
    int vertical[] = {0,4,0,4,1,1,2,3,4,4,2,3,3,1,0,0};
    int number = (int) (Math.random()*15);
    ChessBoardComponents components = new ChessBoardComponents(horizontal[number],vertical[number]);
    components.placeKnight();
    System.out.println(components.printGame());
  }
}
