/* ΔΟΜΕΣ ΔΕΔΟΜΕΝΩΝ 2016-17
 * DS - CANDY CRUSH
 * 
 * ΕΥΡΙΠΙΔΗΣ ΜΠΑΛΤΖΗΣ
 * ΑΕΜ: 8196
 * ΤΗΛ: 6980727893
 * e-mail; evrimpal@ece.auth.gr
 * 
 * ΒΑΣΙΛΗΣ ΜΠΕΛΛΟΣ
 * ΑΕΜ: 8715
 * ΤΗΛ: 6947998401
 * e-mail: v8.bellos@gmail.com
 */
package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.Tile;

import java.util.ArrayList;

public class HeuristicPlayer implements AbstractPlayer
{
  // TODO Fill the class code.

  int score;		// το άθροισμα των βαθμών του παίκτη
  int id;			// η ταυτότητα του παίκτη
  String name;		// το όνομα του παίκτη
  
  /* Συνάρτηση που αρχικοποιεί ένα αντικείμενο της κλάσης HeuristicPlayer.
   * Δέχεται ως όρισμα ένα αντικέιμενο της κλάσης Integer (pid), το οποίο 
   * και αποτελεί την αποκλειστική ταυτότητα του παίκτη που δημιουργείται.
   * Ταυτόχρονα αρχικοποιείται (μηδενίζεται) και το σκορ του παίκτη.
   */
  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    score = 0;
  }
  
  // Συνάρτηση που επιστρέφει το όνομα του παίκτη στην καλούσα συνάρτηση.
  @Override
  public String getName ()
  {
    return "evaluation";
  }
  
  // Συνάρτηση που επιστρέφει την ταυτότητα του παίκτη στην καλούσα συνάρτηση.
  @Override
  public int getId ()
  {
    return id;
  }
  
  /* Συνάρτηση που δέχεται ως όρισμα μια ακέραια μεταβλητή (int) και περνά την
   * τιμή της στη μεταβήτή που αντιστοιχεί στο σκορ του παίκτη.
   */
  @Override
  public void setScore (int score)
  {
    this.score = score;
  }
  
  // Συνάρτηση που επιστρέφει το σκορ του παίκτη στην καλούσα συνάρτηση.
  @Override
  public int getScore ()
  {
    return score;
  }
  
  /* Συνάρτηση που δέχεται ως όρισμα μια ακέραια μεταβλητή (int) και περνά την
   * και περνά την τιμή της στη μεταβλητή που αντιστοιχή στην ταυτότητα του παίκτη.
   */
  @Override
  public void setId (int id)
  {

    this.id = id;

  }

  /* Συνάρτηση που δέχεται ως όρισμα ένα αντικείμενο της κλάσης String και περνά
   * την τιμή του στη μεταβλητή που αντιστοιχεί στο όνομα του παίκτη.
   */
  @Override
  public void setName (String name)
  {

    this.name = name;

  }

  /* Συνάρτηση που δέχεται ως ορίσματα ένα αντικείμενο της κλάσης ArrayList
   * (δυναμικός πίνακας) και ένα αντικείμενο της κλάσης Board (ουσιαστικά το ταμπλό), 
   * καλεί τη συνάρτηση findBestMoveIndex για να υπολογίσει τη θέση της καλύτερησ
   * δυνατής κίνησης και στη συνέχεια επιστρέφει μέσω της συνάρτησης calculateNextMove
   * την τετράδα των συντεταγμένων των 2 πλακιδίων που πρόκειται να ανταλλάξουν θέση.
   */
  @Override
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
	System.out.println("mpainw sthn findbestmoveindex");  
    int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));
    
    return calculateNextMove(move);

  }
  
  /* Συνάρτηση που δέχεται ως όρισμα της συντεταγμένες του πλακιδίου που πρόκειται να
   * αλλάξει θέση, καθώς και την κατεύθυνση της κίνησης και επιστρέφει την τετράδα των 
   * συντεταγμένων των 2 πλακιδίων που πρόκειται να ανταλλάξουν θέση.
   */
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
  /* Συνάρτηση που δέχεται ως ορίσματα την κίνηση move (x, y, κατεύθυνση) και ένα αντικείμενο
   * της κλάσης Board (το ταμπλό δηλαδή), υπολογίζει για την κίνηση αυτή ποια πλακίδια (Tiles)του
   * ταμπλό θα αφαιρεθούν και επιστρέφει έναν πίνακα αντικειμένων της κλάσης Tile που τα περιέχει.
   */
  Tile[] deletedCandies(int[] move, Board board){
	  int numberOfDeletedCandies=0;
	  int i=0;
	  int[] returnedMove = calculateNextMove(move);
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
		  int startOfHorizontalAxis = returnedMove[0] - 2;
		  System.out.println("horizontal: " + startOfHorizontalAxis);
		  
		  for(i=0; i<5; i++){
			  if((startOfHorizontalAxis + i)>=0 && (startOfHorizontalAxis + i)<CrushUtilities.NUMBER_OF_COLUMNS){
				  horizontalTiles1Destroyed.add(board.giveTileAt(startOfHorizontalAxis + i, returnedMove[3]));
				  horizontalTiles2Destroyed.add(board.giveTileAt(startOfHorizontalAxis + i, returnedMove[1]));
			  }
		  }
		  
		  if(returnedMove[3]>returnedMove[1]){
			  minVS = returnedMove[1];
		  }
		  minVS = minVS - 2;
		  System.out.println("minVS: " + minVS);
		  for(i=0; i<3; i++){
			  if((minVS + i)>=0 && (minVS + i)<CrushUtilities.NUMBER_OF_PLAYABLE_ROWS){
				  verticalTiles1Destroyed.add(board.giveTileAt(returnedMove[0], minVS + i));
			  }
			  if((minVS + i + 3)>=0 && (minVS + i + 3)<CrushUtilities.NUMBER_OF_PLAYABLE_ROWS){
				  verticalTiles2Destroyed.add(board.giveTileAt(returnedMove[0], minVS + i + 3));
			  }
		  }

	  }
	  //HORIZONTAL SWAP
	  else{
		  int startOfVerticalAxis = returnedMove[1] - 2;
		  System.out.println("vertical: " + startOfVerticalAxis);
		  
		  for(i=0; i<5; i++){
			  if((startOfVerticalAxis + i)>=0 && (startOfVerticalAxis + i)<CrushUtilities.NUMBER_OF_PLAYABLE_ROWS){
				  verticalTiles1Destroyed.add(board.giveTileAt(returnedMove[2], startOfVerticalAxis + i));
				  verticalTiles2Destroyed.add(board.giveTileAt(returnedMove[0], startOfVerticalAxis + i));
			  }
		  }
		  
		  if(returnedMove[2]>returnedMove[0]){
			  minHS = returnedMove[0];
		  }
		  minHS = minHS - 2;
		  System.out.println("minHs: " + minHS);
		  
		  for(i=0; i<3; i++){
			  if((minHS + i)>=0 && (minHS + i)<CrushUtilities.NUMBER_OF_COLUMNS){
				  horizontalTiles1Destroyed.add(board.giveTileAt(minHS + i, returnedMove[1]));
			  }
			  if((minHS + i +3)>=0 && (minHS + i +3)<CrushUtilities.NUMBER_OF_COLUMNS){
				  horizontalTiles2Destroyed.add(board.giveTileAt(minHS + i +3, returnedMove[1]));
			  }
		  }
	  }
	  
	  //CHECK HORIZONTAL TILES TO BE DESTROYED
	  int counter1 = 1;
	  int endIndex1 = -1;
	  
	  int counter2 = 1;
	  int endIndex2 = -1;
	  
	  for(i=1; i<horizontalTiles1Destroyed.size(); i++){
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
	  }
		  //HORIZONTAL TILES 2
	  for(i=1; i<horizontalTiles2Destroyed.size(); i++){
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
	  
	  for(i=1; i<verticalTiles1Destroyed.size(); i++){
		  //VERTICAL TILES 1
		  if(verticalTiles1Destroyed.get(i).getColor() == verticalTiles1Destroyed.get(i-1).getColor()){
			  counter3++;
			  endIndex3 = i;
		  }
		  else{
			  if(counter3<3){
				  counter3 = 1;
			  }
		  }
	  }
		  //VERTICAL TILES 2
	  for(i=1; i<verticalTiles2Destroyed.size(); i++){
		  if(verticalTiles2Destroyed.get(i).getColor() == verticalTiles2Destroyed.get(i-1).getColor()){
			  counter4++;
			  endIndex4 = i;
		  }
		  else{
			  if(counter4<3){
				  counter4 = 1;
			  }
		  }
	  }
	  
	  System.out.println("endIndex1: " + endIndex1 );
	  System.out.println("endIndex2: " + endIndex2 );
	  System.out.println("endIndex3: " + endIndex3 );
	  System.out.println("endIndex4: " + endIndex4 );
	  
	  System.out.println("counter1: " + counter1 );
	  System.out.println("counter2: " + counter2 );
	  System.out.println("counter3: " + counter3 );
	  System.out.println("counter4: " + counter4 );
	  
	  if(counter1>=3){
		  numberOfDeletedCandies += counter1;
		  System.out.println("numberOfDeletedCandies1= " + numberOfDeletedCandies);
		  for(i=0; i<counter1; i++){
			  System.out.println(endIndex1 - (counter1-1) + i);
			  deletedCandies.add(horizontalTiles1Destroyed.get(endIndex1 - (counter1-1) + i));
		  }
	  }
	  
	  if(counter2>=3){
		  numberOfDeletedCandies += counter2;
		  System.out.println("numberOfDeletedCandies2= " + numberOfDeletedCandies);
		  for(i=0; i<counter2; i++){
			  System.out.println(endIndex2 - (counter2-1) + i);
			  deletedCandies.add(horizontalTiles2Destroyed.get(endIndex2 - (counter2-1) + i));
		  }
	  }
	  
	  if(counter3>=3){
		  numberOfDeletedCandies += counter3;
		  System.out.println("numberOfDeletedCandies3= " + numberOfDeletedCandies);
		  for(i=0; i<counter3; i++){
			  System.out.println(endIndex3-(counter3-1)+i);
			  deletedCandies.add(verticalTiles1Destroyed.get(endIndex3-(counter3-1)+i));
		  }
	  }
	  
	  if(counter4>=3){
		  numberOfDeletedCandies += counter4;
		  System.out.println("numberOfDeletedCandies4= " + numberOfDeletedCandies);
		  for(i=0; i<counter4; i++){
			  System.out.println(endIndex4-(counter4-1)+i);
			  deletedCandies.add(verticalTiles2Destroyed.get(endIndex4-(counter4-1)+i));
		  }
	  }
	  
	  Tile[] delCandies = new Tile[deletedCandies.size()];
	  for(i=0; i<deletedCandies.size(); i++){							  //Create new array(standard sized) for deletedCandies
		  delCandies[i] = deletedCandies.get(i);
	  }
	  
	  return delCandies;
  }
  
  /* Συνάρτηση που δέχεται ως όρισμα έναν πίνακα αντικειμένων Tile, που περιέχει το σύνολο
   * των πλακιδίων που πρόκειται να διαγραφούν κατά την επιλεγμένη κίνηση, και επιστρέφει
   * την τεταγμένη του πλακιδίου που θα διαγραφεί και ταυτόχρονα βρίσκεται στη χαμηλότερη
   * θέση του ταμπλό με τη μορφή ακεραίου αριθμού (int).
   */
  int heightOfMove(Tile[] tile){
	  if(tile.length>0){
		  int height = tile[0].getY();
		  for(int i=0; i<tile.length; i++){
			  if(tile[i].getY()<height){
				  height = tile[i].getY();
			  }
		  }
		  return height;
	  }
	  else{
		  return -1;
	  }
  }
  
  /* Συνάρτηση που δέχεται ως ορίσματα την επιλεγμένη κίνηση (x,y, κατεύθυνση) και έναν
   * πίνακα αντικειμένων της κλάσης Tile, που περιέχει το σύνολο των πλακιδίων που πρόκειται
   * να διαγραφούν κατά την επιλεγμένη κίνηση, και επιστρέφει μια κατάλληλη ακέραια (int)
   * τιμή που υποδεικνύει κατά πόσο τα πλακίδια που πρόκειται να διαγραφούν βρίσκονται
   * σε οριζόντια γραμμή. κατακόρυφη στήλη ή αν κατά τη διαγραφή θα αφαιρεθούν πλακίδια
   * και των δυο κατευθύνσεων.
   */
  int vertOrHorizOrBoth(int[] move, Tile[] tile){
	  int[] returnedMove = calculateNextMove(move);
	  boolean horizontalDeletion = false;
	  boolean verticalDeletion = false;
	  int value = 0;							// no deletion
	  for(int i=0; i<tile.length; i++){
		  if(tile[i].getY()!=returnedMove[1] && tile[i].getY()!= returnedMove[3]){
			  verticalDeletion = true;
		  }
		  if(tile[i].getX()!=returnedMove[0] && tile[i].getX()!= returnedMove[2]){
			  horizontalDeletion = true;
		  }
	  }
	  
	  if(horizontalDeletion && verticalDeletion){ // both
		  value = 3;
	  }
	  
	  if(horizontalDeletion && !verticalDeletion){ // horizontal only
		  value = 1;
	  }
	  
	  if(!horizontalDeletion && verticalDeletion){ // vertical only
		  value = 2;
	  }
	  
	  return value;
  }
  
  /* Συνάρτηση που δέχεται ως ορίσματα ένα αντικείμενο της κλάσης ArrayList (δυναμικός πίνακας)
   * και ένα αντικείμενο της κλάσης Board (ουσιαστικά το ταμπλό), και για όλο το περιεχόμενο
   * του δυναμικού πίνακα, που είναι το σύνολο των διαθέσιμων κινήσεων του παίκτη, καλεί τη
   * συνάρτηση moveEvaluation, αποθηκεύει σε έναν πίνακα της μορφής double[] τις επιστρεφόμενες
   * τιμές και στη συνέχεια επιλέγει και επιστρέφει το δείκτη του πίνακα στη θέση με το μεγαλύτερο
   * περιεχόμενο, στην οποία περιέχεται και η καλύτερη διαθέσιμη κίνηση. 
   */ 
  int findBestMoveIndex (ArrayList<int[]> availableMoves, Board board)
  {	
	  double[] evals = new double[availableMoves.size()];
	  for(int i=0; i<availableMoves.size(); i++){
		  evals[i] = moveEvaluation(availableMoves.get(i), board);
	  }
	  
	  int bestIndex = 0;
	  double maxEval = -1;
	  for(int i=0; i<availableMoves.size(); i++){
		  if(evals[i]>maxEval){
			  maxEval = evals[i];
			  bestIndex = i;
		  }
	  }
	  
	  return bestIndex;
  }
  
  /* Συνάρτηση που δέχεται ως ορίσματα την κίνηση move (x, y, κατεύθυνση) και ένα αντικείμενο
   * της κλάσης Board (το ταμπλό δηλαδή), καλεί τις συναρτήσεις deletedCandies, heightOfMove
   * και directionOfDeletion και με κατάλληλο υπολογισμό επιστρέφει την αξιολόγηση της 
   * επιλεγμένης κίνησης σε μια μεταβλητή τύπου double, η οποία και αποθηκέυεται στον πίνακα
   * evals[] της καλούσας συνάρτησης findBestMoveIndex.
   */
  double moveEvaluation (int[] move, Board board){
    double evaluation= 0;
	
	Tile[] tile = deletedCandies(move, board);
	System.out.println("deleted Candies = " + tile.length);

	int height = heightOfMove(tile);
	System.out.println("heigth of move = " + height);
	
	int directionOfDeletion = vertOrHorizOrBoth(move, tile);
	System.out.println("direction(s) of deletion = " + directionOfDeletion);
	
	if(directionOfDeletion>=1){
		evaluation = 8 * tile.length -  9 * height + 8 * directionOfDeletion;
	}
	else{
		evaluation = 0;
	}
	
    return evaluation;
  }
}
