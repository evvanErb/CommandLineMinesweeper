/*cell class for all cells on board*/

class Cell {
    private Cell topAdjacent;
    private Cell bottomAdjacent;
    private Cell rightAdjacent;
    private Cell leftAdjacent;
    private Cell topRightAdjacent;
    private Cell topLeftAdjacent;
    private Cell bottomRightAdjacent;
    private Cell bottomLeftAdjacent;
    private boolean isMine;
    private boolean showing;

    //constructor
	public Cell(boolean isMine){
        this.isMine = isMine;
        this.showing = false;
    }

    //return num of mines adjacent
    private int GetNumAdjacentMines(){
        int total = 0;
        if((this.topAdjacent != null) && (topAdjacent.GetIsMine())){
            total++;
        }
        if((this.bottomAdjacent != null) && (bottomAdjacent.GetIsMine())){
            total++;
        }
        if((this.rightAdjacent != null) && (rightAdjacent.GetIsMine())){
            total++;
        }
        if((this.leftAdjacent != null) && (leftAdjacent.GetIsMine())){
            total++;
        }
        if((this.topRightAdjacent != null) && (topRightAdjacent.GetIsMine())){
            total++;
        }
        if((this.topLeftAdjacent != null) && (topLeftAdjacent.GetIsMine())){
            total++;
        }
        if((this.bottomRightAdjacent != null) && (bottomRightAdjacent.GetIsMine())){
            total++;
        }
        if((this.bottomLeftAdjacent != null) && (bottomLeftAdjacent.GetIsMine())){
            total++;
        }
        return(total);
    }

    //Set this Cells neighbors
    public void SetTop(Cell toSet){
        this.topAdjacent = toSet;
        return;
    }
    public void SetBottom(Cell toSet){
        this.bottomAdjacent = toSet;
        return;
    }
    public void SetRight(Cell toSet){
        this.rightAdjacent = toSet;
        return;
    }
    public void SetLeft(Cell toSet){
        this.leftAdjacent = toSet;
        return;
    }
    public void SetTopRight(Cell toSet){
        this.topRightAdjacent = toSet;
        return;
    }
    public void SetTopLeft(Cell toSet){
        this.topLeftAdjacent = toSet;
        return;
    }
    public void SetBottomRight(Cell toSet){
        this.bottomRightAdjacent = toSet;
        return;
    }
    public void SetBottomLeft(Cell toSet){
        this.bottomLeftAdjacent = toSet;
        return;
    }

    //make this cell show and if blank make all its blank show
    //return true if mine
    public boolean MakeShowing(){
        if(this.GetIsMine()){
            this.showing = true;
            return(true);
        }
        //if its already showing
        if(this.GetShowing()){
            return(false);
        }
        if(this.GetNumAdjacentMines() > 0){
            this.showing = true;
            return(false);
        }
        this.showing = true;
        //if blank run showing on all adjacent cells if theyre not null
        if (this.topAdjacent != null){
            this.topAdjacent.MakeShowing();
        }
        if (this.bottomAdjacent != null){
            this.bottomAdjacent.MakeShowing();
        }
        if (this.rightAdjacent != null){
            this.rightAdjacent.MakeShowing();
        }
        if (this.leftAdjacent != null){
            this.leftAdjacent.MakeShowing();
        }
        if (this.topRightAdjacent != null){
            this.topRightAdjacent.MakeShowing();
        }
        if (this.topLeftAdjacent != null){
            this.topLeftAdjacent.MakeShowing();
        }
        if (this.bottomRightAdjacent != null){
            this.bottomRightAdjacent.MakeShowing();
        }
        if (this.bottomLeftAdjacent != null){
            this.bottomLeftAdjacent.MakeShowing();
        }
        return(false);
    }

    //accessors
    //return true if this cell is a mine
    public boolean GetIsMine(){
        return(this.isMine);
    }

    //return true if this cell has been revealed to the player
    public boolean GetShowing(){
        return(this.showing);
    }
    
    //to string
    public String toString(){
        if(!this.GetShowing()){
            return("?");
        }
        else if(this.GetIsMine()){
            return("X");
        }
        return(String.valueOf(this.GetNumAdjacentMines()));
    }
}