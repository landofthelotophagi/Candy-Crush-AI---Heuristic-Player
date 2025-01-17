package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.Tile;

import java.util.ArrayList;

public class HeuristicPlayer implements AbstractPlayer
{
  // TODO Fill the class code.

  int score;
  int id;
  String name;

  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    score = 0;
  }

  @Override
  public String getName ()
  {
    return "evaluation";
  }

  @Override
  public int getId ()
  {
    return id;
  }

  @Override
  public void setScore (int score)
  {
    this.score = score;
  }

  @Override
  public int getScore ()
  {
    return score;
  }

  @Override
  public void setId (int id)
  {

    this.id = id;

  }

  @Override
  public void setName (String name)
  {

    this.name = name;

  }

  @Override
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {

    int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));

    return calculateNextMove(move);

  }

  int[] calculateNextMove (int[] move)
  {

    int[] returnedMove = new int[4];

    if (move[2] == CrushUtilities.UP) {
      // System.out.println("UP");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] + 1;
    }
    if (move[2] == CrushUtilities.DOWN) {
      // System.out.println("DOWN");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] - 1;
    }
    if (move[2] == CrushUtilities.LEFT) {
      // System.out.println("LEFT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] - 1;
      returnedMove[3] = move[1];
    }
    if (move[2] == CrushUtilities.RIGHT) {
      // System.out.println("RIGHT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] + 1;
      returnedMove[3] = move[1];
    }
    return returnedMove;
  }
  
  Tile[] deletedCandies(int[] move, Board board){
	  int numberOfDeletedCandies=0;
	  int i=0;
	  int[] returnedMove = calculateNextMove(move);
	  boolean horizontalSwap = false;
	  boolean verticalSwap = false;
	  int minVS = returnedMove[3];
	  int minHS = returnedMove[2];
	  board = CrushUtilities.boardAfterFirstMove(board, move);
	  
	  ArrayList<Tile> verticalTiles1Destroyed = new ArrayList<Tile>();
	  ArrayList<Tile> verticalTiles2Destroyed = new ArrayList<Tile>();
	  ArrayList<Tile> horizontalTiles1Destroyed = new ArrayList<Tile>();
	  ArrayList<Tile> horizontalTiles2Destroyed = new ArrayList<Tile>();
	 
	  ArrayList<Tile> deletedCandies = new ArrayList<Tile>();
	  
	  //VERTICAL SWAP
	  if(move[2]==3 || move[2]==1){ 
		  verticalSwap = true;
		  int startOfHorizontalAxis = returnedMove[0] - 2;
		  
		  for(i=0; i<5; i++){
			  if(board.contains(startOfHorizontalAxis + i, returnedMove[3])){
				  horizontalTiles1Destroyed.add(board.giveTileAt(startOfHorizontalAxis + i, returnedMove[3]));
				  horizontalTiles2Destroyed.add(board.giveTileAt(startOfHorizontalAxis + i, returnedMove[1]));
			  }
		  }
		  
		  if(returnedMove[3]>returnedMove[1]){
			  minVS = returnedMove[1];
		  }
		  minVS = minVS - 2;
		  for(i=0; i<3; i++){
			  if(board.contains(returnedMove[0], minVS + i)){
				  verticalTiles1Destroyed.add(board.giveTileAt(returnedMove[0], minVS + i));
			  }
			  if(board.contains(returnedMove[0], minVS + i + 3)){
				  verticalTiles2Destroyed.add(board.giveTileAt(returnedMove[0], minVS + i + 3));
			  }
		  }

	  }
	  //HORIZONTAL SWAP
	  else{
		  horizontalSwap = true;
		  int startOfVerticalAxis = returnedMove[1] - 2;
		  
		  for(i=0; i<5; i++){
			  if(board.contains(returnedMove[2], startOfVerticalAxis + i)){
				  verticalTiles1Destroyed.add(board.giveTileAt(returnedMove[2], startOfVerticalAxis + i));
				  verticalTiles2Destroyed.add(board.giveTileAt(returnedMove[0], startOfVerticalAxis + i));
			  }
		  }
		  
		  if(returnedMove[2]>returnedMove[0]){
			  minHS = returnedMove[0];
		  }
		  minHS = minHS - 2;
		  
		  for(i=0; i<3; i++){
			  if(board.contains(minHS + i, returnedMove[1])){
				  horizontalTiles1Destroyed.add(board.giveTileAt(minHS + i, returnedMove[1]));
			  }
			  if(board.contains(minHS + i +3, returnedMove[1])){
				  horizontalTiles2Destroyed.add(board.giveTileAt(minHS + i +3, returnedMove[1]));
			  }
		  }
	  }
	  
	  //CHECK HORIZONTAL TILES TO BE DESTROYED
	  int counter1 = 1;
	  int endIndex1 = -1;
	  
	  int counter2 = 1;
	  int endIndex2 = -1;
	  
	  for(i=0; i<horizontalTiles1Destroyed.size(); i++){
		  //HORIZONTAL TILES 1
		  if(horizontalTiles1Destroyed.get(i).getColor()==horizontalTiles1Destroyed.get(i-1).getColor()){
			  counter1++;
			  endIndex1 = i;
		  }
		  else{
			  if(counter1<3){
				  counter1 = 1;
			  }
		  }
		  //HORIZONTAL TILES 2
		  if(horizontalTiles2Destroyed.get(i).getColor() == horizontalTiles2Destroyed.get(i-1).getColor()){
			  counter2++;
			  endIndex2 = i;
		  }
		  else{
			  if(counter2<3){
				  counter2 = 1;
			  }
		  }
	  }
	  
	  
	  //CHECK VERTICAL TILES TO BE DESTROYED
	  int counter3 = 1;
	  int endIndex3 = -1;
	  
	  int counter4 = 1;
	  int endIndex4 = -1;
	  
	  for(i=1; i<horizontalTiles1Destroyed.size(); i++){
		  //VERTICAL TILES 1
		  if(verticalTiles1Destroyed.get(i) == verticalTiles1Destroyed.get(i-1)){
			  counter3++;
			  endIndex3 = i;
		  }
		  else{
			  if(counter3<3){
				  counter3 = 1;
			  }
		  }
		  //VERTICAL TILES 2
		  if(verticalTiles2Destroyed.get(i) == verticalTiles2Destroyed.get(i-1)){
			  counter4++;
			  endIndex4 = i;
		  }
		  else{
			  if(counter4<3){
				  counter4 = 1;
			  }
		  }
	  }
	  
	  if(counter1>=3){
		  numberOfDeletedCandies += counter1;
		  for(i=0; i<counter1; i++){
			  deletedCandies.add(horizontalTiles1Destroyed.get(endIndex1 - counter1 + i));
		  }
	  }
	  
	  if(counter2>=3){
		  numberOfDeletedCandies += counter2;
		  for(i=0; i<counter2; i++){
			  deletedCandies.add(horizontalTiles2Destroyed.get(endIndex2- counter2 + i));
		  }
	  }
	  
	  if(counter3>=3){
		  numberOfDeletedCandies += counter3;
		  for(i=0; i<counter3; i++){
			  deletedCandies.add(verticalTiles1Destroyed.get(endIndex3 - counter3 +i));
		  }
	  }
	  
	  if(counter4>=3){
		  numberOfDeletedCandies += counter4;
		  for(i=0; i<counter4; i++){
			  deletedCandies.add(verticalTiles2Destroyed.get(endIndex4 - counter4 +i));
		  }
	  }
	  
	  Tile[] delCandies = new Tile[deletedCandies.size()];
	  for(i=0; i<deletedCandies.size(); i++){
		  delCandies[i] = deletedCandies.get(i);
	  }
	  return delCandies;
  }
  
  int heightOfMove(Tile[] tile){
	  int height = tile[1].getY();
	  for(int i=0; i<tile.length; i++){
		  if(tile[i].getY()<height){
			  height = tile[i].getY();
		  }
	  }
	  return height;
  }
  
  int vertOrHorizOrBoth(int[] move, Tile[] tile){
	  int[] returnedMove = calculateNextMove(move);
	  boolean horizontalDeletion = false;
	  boolean verticalDeletion = false;
	  int value = 0;
	  for(int i=0; i<tile.length; i++){
		  if(tile[i].getY()!=returnedMove[1] && tile[i].getY()!= returnedMove[3]){
			  verticalDeletion = true;
		  }
		  if(tile[i].getX()!=returnedMove[0] && tile[i].getX()!= returnedMove[2]){
			  horizontalDeletion = true;
		  }
	  }
	  
	  if(horizontalDeletion && verticalDeletion){
		  value = 3;
	  }
	  
	  if(horizontalDeletion && !verticalDeletion){
		  value = 2;
	  }
	  
	  if(!horizontalDeletion && verticalDeletion){
		  value = 1;
	  }
	  
	  return value;
  }

  int findBestMoveIndex (ArrayList<int[]> availableMoves, Board board)
  {	
	  double[] evals = new double[availableMoves.size()];
    // TODO Fill the missing code.

	  
  }

  double moveEvaluation (int[] move, Board board)
  {
    // TODO Fill the missing code.

  }

}
