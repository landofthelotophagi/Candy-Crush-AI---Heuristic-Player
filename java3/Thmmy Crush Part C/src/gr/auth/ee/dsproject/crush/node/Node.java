package gr.auth.ee.dsproject.crush.node;

import java.util.ArrayList;

import gr.auth.ee.dsproject.crush.board.Board;

public class Node
{
	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
    public double nodeEvaluation;
    
    // Constructor της κλάσης που δε δέχεται ορίσματα και αρχικοποιεί ένα αντικείμενο
    // της κλάσης με μηδενικές αρχικές συνθήκες
    public Node(Board board){			//ROOT	
    	this.nodeDepth = 0;
    	this.nodeMove = new int[2000];
    	this.nodeBoard = new Board();
    	this.nodeEvaluation = this.evaluate();
    }
    
    public Node(Node parent, Board board, int[] move){			//CHILDREN
    	this.nodeDepth = parent.nodeDepth + 1;
    	this.nodeBoard = board;
    	this.nodeMove = move;
    	this.nodeEvaluation = this.evaluate();
    }
    
    public Node(Node parent, ArrayList<Node> children, int nodeDepth, int[] nodeMove, Board nodeBoard, double nodeEvaluation){
    	this.parent = parent;
    	this.children = children;
    	this.nodeDepth = nodeDepth;
    	this.nodeMove = nodeMove;
    	this.nodeBoard = nodeBoard;
    	this.nodeEvaluation = this.evaluate();
    }
    
    public void setParent(Node parent){
    	this.parent = parent;
    }
    
    public Node getParent(){
    	return parent;
    }
    
    public void setChildren(ArrayList<Node> children){
    	this.children = children;
    }
    
    public ArrayList<Node> getChildren(){
    	return children;
    }
    
    public void setNodeDepth(int nodeDepth){
    	this.nodeDepth = nodeDepth;
    }
    
    public int getNodeDepth(){
    	return nodeDepth;
    }
    
    public void setNodeMove(int[] nodeMove){
    	this.nodeMove = nodeMove;
    }
    
    public int[] getNodeMove(){
    	return nodeMove;
    }
    
    public void setNodeBoard(Board nodeBoard){
    	this.nodeBoard = nodeBoard;
    }
    
    public Board getNodeBoard(){
    	return nodeBoard;
    }
    
    public void setNodeEvaluation(double nodeEvaluation){
    	this.nodeEvaluation = nodeEvaluation;
    }
    
    public double getNodeEvaluation(){
    	return nodeEvaluation;
    }
    
    public double evaluate(){
    	
    }
}
