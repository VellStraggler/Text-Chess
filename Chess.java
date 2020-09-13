import java.util.Scanner;
public class Chess{
  public static void main(String[] args){
    System.out.println("-=2-Player Chess=-\ni.e. \"a4b4\" to move from A4 to B4");
    game();}
  public static void game(){
    String[][] grid = new String[8][8];
    grid = initialize(grid);
    boolean playing = true;
    boolean turn = true;
    Scanner usr = new Scanner(System.in);
    while(playing){
      print(grid,turn);
      playing = move(usr,grid,turn);
      turn = !turn;}
    System.out.print("Game over. ");
    if(turn){ System.out.println("Black wins.");}
    else{ System.out.println("White wins.");}
    System.out.println("Play again? (y)es or (n)o: ");
    if(usr.next().equals("y")){game();}}
  public static boolean move(Scanner usr,String[][] grid,boolean turn){
    String cmd = usr.next();
    if(cmd.equals("q")){ return false;}
    else{
      int[] xyxy = new int[4];// x1,r1,x2,r2
      for(int i = 0;i<2;i++){
        xyxy[2*i + 1] = (int)cmd.charAt(i * 2)-97;
        xyxy[2*i] = (int)cmd.charAt(1 + (i*2))-49;}
      if(check(xyxy,grid,turn)){
        if(grid[xyxy[2]][xyxy[3]].equals("♚ ")||grid[xyxy[2]][xyxy[3]].equals("♔ ")){
          return false;}//king dead, game over
        grid[xyxy[2]][xyxy[3]] = grid[xyxy[0]][xyxy[1]];//replaces target
        grid[xyxy[0]][xyxy[1]] = "  ";//erases what's left behind
        }else{ System.out.println("Illegal Move, Try Again");
          move(usr,grid,turn);}
    }return true;}
  public static boolean check(int[] xyxy, String[][] grid, boolean turn){//DONT FORGET
    if(turn){
      if(checkmult(grid,xyxy,"♟♜♞♝♛♚")){                //BLACK IS WHITE, WHITE IS BLACK
  	if(grid[xyxy[0]][xyxy[1]].equals("♟ ")){
  	  if(validpawn(grid, xyxy,turn)){
  	    if(xyxy[3]==0){grid[xyxy[0]][xyxy[1]] = "♛ ";
  	      return true;}}
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♜ ")||grid[xyxy[0]][xyxy[1]].equals("♛ ")){
  	  return(validrook(grid,xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♝ ")||grid[xyxy[0]][xyxy[1]].equals("♛ ")){
  	  return(validbishop(grid,xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♞ ")){
  	  return(validhorse(xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♚ ")){
  	  return(validking(grid,xyxy));}}
    }else{
     if(checkmult(grid,xyxy,"♙♖♘♕♔♗")){
        if(grid[xyxy[0]][xyxy[1]].equals("♙ ")){
          if(validpawn(grid, xyxy,turn)){
  	    if(xyxy[3]==0){grid[xyxy[0]][xyxy[1]] = "♕ "; return true;}}}
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♖ ")||grid[xyxy[0]][xyxy[1]].equals("♕ ")){
  	  return(validrook(grid,xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♗ ")||grid[xyxy[0]][xyxy[1]].equals("♕ ")){
  	  return(validbishop(grid,xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♘ ")){
  	  return(validhorse(xyxy));
  	}else if(grid[xyxy[0]][xyxy[1]].equals("♔ ")){
  	  return(validking(grid,xyxy));
  	}}
   return false;}
  public static boolean validrook(String[][] grid, int[] xyxy){
    if(xyxy[0]==xyxy[2]){//up or down
      if(xyxy[3] > xyxy[1]){//down
        if(!rookloop(grid,xyxy,xyxy[1]+1,xyxy[3])){ return false;}}
      else{//up
        if(!rookloop(grid,xyxy,xyxy[3]+1,xyxy[1])){ return false;}}}
    else if(xyxy[1]==xyxy[3]){//left or right
      if(xyxy[2] > xyxy[0]){//right
        for(int i = xyxy[0]+1;i<xyxy[2];i++){
          if(!grid[i][xyxy[1]].equals("  ")){
            return false;}}}
      else{//left
        for(int i = xyxy[2]+1;i<xyxy[0];i++){
          if(!grid[i][xyxy[1]].equals("  ")){
            return false;}}}}
    return true;}
  public static boolean rookloop(String[][] grid, int[] xyxy,int n1, int n2){
    for(int i = n1;i<n2;i++){
      if(!grid[xyxy[0]][i].equals("  ")){return false;}}return true;}
  public static boolean validbishop(String[][] grid, int[] xyxy){
    if(xyxy[3]-xyxy[1] == xyxy[2]-xyxy[0]){//upleft or downright
      if(xyxy[3]<xyxy[1]){//upleft
        for(int i = 0;i<xyxy[1]-xyxy[3]-1;i++){
          if(!grid[xyxy[2]+i+1][xyxy[3]+i+1].equals("  ")){
            return false;}}}
      else{
        for(int i = 0;i<xyxy[3]-xyxy[1]-1;i++){
          if(!grid[xyxy[0]+i+1][xyxy[1]+i+1].equals("  ")){
            return false;}}}}
    else if(xyxy[2]-xyxy[0] == xyxy[1]-xyxy[3]){//upright or downleft
      if(xyxy[2]<xyxy[0]){//down left
        for(int i = 0;i<xyxy[0]-xyxy[2]-1;i++){
          if(!grid[xyxy[2]+i+1][xyxy[3]-i-1].equals("  ")){
            return false;}}}
      else{
        for(int i = 0;i<xyxy[2]-xyxy[0]-1;i++){
          if(!grid[xyxy[0]+i+1][xyxy[1]-i-1].equals("  ")){
            return false;}}}} return true;}
  public static boolean validking(String[][] grid, int[] xyxy){
    return(xyxy[3]-xyxy[1]<2 && xyxy[3]-xyxy[1]>-2 && xyxy[2]-xyxy[0]<2 && xyxy[2] - xyxy[0] >-2);}
  public static boolean validpawn(String[][] grid, int[] xyxy,boolean white){
    int i = -1;
    if(white){i*=-1;}//black and white pawns move in opposite dirs.
    if(grid[xyxy[2]][xyxy[3]].equals("  ")){ //target is empty
      if(xyxy[2]==xyxy[0]){//straightpath
       if(xyxy[1]==xyxy[3]+1*i){//moving 1 fwd
         return true;}
      else if((xyxy[1]==6&&white)||(xyxy[1]==1&&!white)){//2 fwd
  	if(xyxy[1]==xyxy[3]+2*i && grid[xyxy[0]][xyxy[1]-(1*i)].equals("  ")){
  	  return true;}}}}
    else{//attacking
      if((xyxy[0]+1==xyxy[2]||xyxy[0]-1==xyxy[2])&&(xyxy[1]-(1*i)==xyxy[3])){
        return true;}}
    return false;}
  public static boolean validhorse(int[] xyxy){
    for(int i = 0; i<2;i++){
      for(int o = 0;o<2;o++){
       for(int p = 0; p <2;p++){
        if(xyxy[i]+((o*2)-1)==xyxy[i+2] && xyxy[1-i]+((p*4)-2)==xyxy[3-i]){return true;}
    }}}return false;}
  public static boolean checkmult(String[][] grid,int[] xyxy,String list){
    for(int i=0;i<list.length();i++){
      if(grid[xyxy[2]][xyxy[3]].equals(list.substring(i,i+1) + " ")){//team piece in the way
        return false;}} return true;}
  public static void print(String[][] grid, boolean turn){
    for(int y = 0;y<8;y++){ // place the black squares
      for(int x = 0; x<8;x++){
        if((y+x)%2==0 && grid[x][y].equals("  ")){
          grid[x][y] = "██";}
        if((y+x)%2==1 && grid[x][y].equals("██")){
          grid[x][y] = "  ";}}}
    System.out.println("  1 2 3 4 5 6 7 8");
    for(int y = 0;y<8;y++){
      if(turn){System.out.print((char)((int) 'A' + y)+ " ");}
      else{System.out.print((char)((int) 'H' -y) + " ");}
      for(int x = 0;x<8;x++){
        if(turn){
          System.out.print(grid[x][y]);
          if((grid[x][y]).equals("██")){
            grid[x][y] = "  ";}}
        else{System.out.print(grid[x][7-y]);
          if((grid[x][7-y]).equals("██")){
              grid[x][7-y] = "  ";}}}
      System.out.println();}
      if(turn){
        System.out.println("White's Turn");
      } else{ System.out.println("Black's Turn");}}
  public static String[][] initialize(String[][] grid){
    String gridline = "♖♙    ♟♜♘♙    ♟♞♗♙    ♟♝♔♙    ♟♛♕♙    ♟♚♗♙    ♟♝♘♙    ♟♞♖♙    ♟♜";
    for(int y = 0; y<8; y++){ for(int x=0; x<8; x++){
        grid[y][x] = gridline.substring(8*y+x,8*y+x+1) + " ";
    }}return grid;}}
