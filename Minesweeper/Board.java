/*board class that manages and stores all the games cells*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Board {
    ArrayList<ArrayList<Cell>> theBoard = new ArrayList<ArrayList<Cell>>();

    //constructor
	public Board(){
        Scanner scan = new Scanner(System.in);
        System.out.print("What board size you would like?\n>>> ");
        //TODO sanitize and error check input
        int n = scan.nextInt();
        GenerateBoard(n);
    }

    //generate a new random board
    private void GenerateBoard(int n){
        //iterate over rows
        for(int i = 0; i < n; i++){
            //add a new row
            this.theBoard.add(new ArrayList<Cell>());
            //iterate over coloumns and add a cell
            for(int j = 0; j < n; j++){
                this.theBoard.get(i).add(this.GenerateCell());
            }
        }
        //set cells neighbors ... if neighbors out of bounds set to null
        for(int i = 0; i < this.theBoard.size(); i++){
            for(int j = 0; j < this.theBoard.size(); j++){
                //top
                if(i-1 > -1){
                    this.GetCell(i,j).SetTop(this.GetCell(i-1,j));
                }
                else{
                    this.GetCell(i,j).SetTop(null);
                }
                //bottom
                if(i+1 < n){
                    this.GetCell(i,j).SetBottom(this.GetCell(i+1,j));
                }
                else{
                    this.GetCell(i,j).SetBottom(null);
                }
                //right
                if(j+1 < n){
                    this.GetCell(i,j).SetRight(this.GetCell(i,j+1));
                }
                else{
                    this.GetCell(i,j).SetRight(null);
                }
                //left
                if(j-1 > -1){
                    this.GetCell(i,j).SetLeft(this.GetCell(i,j-1));
                }
                else{
                    this.GetCell(i,j).SetLeft(null);
                }
                //topRight
                if((i-1 > -1) && (j+1 < n)){
                    this.GetCell(i,j).SetTopRight(this.GetCell(i-1,j+1));
                }
                else{
                    this.GetCell(i,j).SetTopRight(null);
                }
                //topLeft
                if((i-1 > -1) && (j-1 > -1)){
                    this.GetCell(i,j).SetTopLeft(this.GetCell(i-1,j-1));
                }
                else{
                    this.GetCell(i,j).SetTopLeft(null);
                }
                //bottomRight
                if((i+1 < n) && (j+1 < n)){
                    this.GetCell(i,j).SetBottomRight(this.GetCell(i+1,j+1));
                }
                else{
                    this.GetCell(i,j).SetBottomRight(null);
                }
                //bottomLeft
                if((i+1 < n) && (j-1 > -1)){
                    this.GetCell(i,j).SetBottomLeft(this.GetCell(i+1,j-1));
                }
                else{
                    this.GetCell(i,j).SetBottomLeft(null);
                }
            }
        }
        return;
    }

    //generate a random cell
    private Cell GenerateCell(){
        Random rand = new Random();
        boolean val = false;
        if((rand.nextInt((10 - 1) + 1) + 1) == 1){
            val = true;
        }
        return(new Cell(val));
    }

    //get a selected cell
    private Cell GetCell(int x, int y){
        return(this.theBoard.get(x).get(y));
    }

    //get a user guess coridinantes
    //returns true if spot is a mine and false if not
    public boolean GetGuess(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter x cord (coloumn num) of your choice\n>>> ");
        int c = scan.nextInt();
        //make sure x in acceptable range
        while((c >= this.theBoard.size()) || (c < 0)){
            System.out.print("[!] Invalid entry please enter a valid x value\n>>> ");
            c = scan.nextInt();
        }
        System.out.print("Enter y cord (row num) of your choice\n>>> ");
        int r = scan.nextInt();
        //make sure x in acceptable range
        while((r >= this.theBoard.size()) || (r < 0)){
            System.out.print("[!] Invalid entry please enter a valid y value\n>>> ");
            r = scan.nextInt();
        }
        return(this.GetCell(r,c).MakeShowing());
    }
    
    //check for victory
    public boolean CheckVictory(){
        for(int i = 0; i < this.theBoard.size(); i++){
            for(int j = 0; j < this.theBoard.size(); j++){
                //check if cell not showing and is not mine ... if both true then game hasnt been won yet
                if((!this.GetCell(i,j).GetShowing()) && (!this.GetCell(i,j).GetIsMine())){
                    return(false);
                }
            }
        }
        return(true);
    }

    //print the board
    public void Display(){
        //print coloumn numbers
        System.out.print("  ");
        for(int i = 0; i < this.theBoard.size(); i++){
            System.out.print(i + " ");
        }
        System.out.println();
        //print row nums and mines
        for(int i = 0; i < this.theBoard.size(); i++){
            for(int j = -1; j < this.theBoard.size(); j++){
                if(j == -1){
                    System.out.print(i + " ");
                }
                else{
                    System.out.print(this.GetCell(i,j).toString() + " ");
                }
            }
            System.out.println();
        }
        return;
    }
}