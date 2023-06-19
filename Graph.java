import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {

	static LinkedList<LinkedList<Node>> alist;
	
	boolean visited[];
	
	Graph(int nodes){
		
		alist  =new LinkedList<>();
		visited = new boolean[nodes];
		
		//for(int i =0; i<nodes;i++)
		//{
		//	alist.add(i,new LinkedList<>());
		//}
	}
	public void addNode(Node node)
	{
		LinkedList<Node> currentList = new LinkedList<>();
		currentList.add(node);
		alist.add(currentList);
	}
	public void addEdge(int src, int dst,Integer time,Integer cost)
	{
		int times =0;
		int costs = 0;
		
		
		times = time; 
		costs  = cost;
		
		//System.out.println(times);
		LinkedList<Node> currentList = alist.get(src);
		Node dstNode = alist.get(dst).get(0);
		currentList.add(new Node(dstNode.data, time, cost));
	}
	

	public boolean checkEdge(int src, int dst) {
	    LinkedList<Node> currentList = alist.get(src);
	    String dstData = alist.get(dst).get(0).data;

	    for (Node node : currentList) {
	        if (node.data.equals(dstData)) {
	            return true;
	        }
	    }
	    return false;
	}

	public void print()
	{
		for(LinkedList<Node> currentList : alist)
		{
			for(Node node : currentList)
			{
				System.out.print(node.data + " -> ");
			}
			System.out.println();
			System.out.println("↓");
			
		}
	}
	
	
	
	public void dfs(String start, String end, int limit,PrintWriter printWriter) {
	    for (int i = 0; i < visited.length; i++) {
	        visited[i] = false;
	    }

	    Stack<Node> nodeStack = new Stack<>();
	    Stack<ArrayList<String>> pathStack = new Stack<>();

	    Node startNode = null;
	    for (int i = 0; i < alist.size(); i++) {
	        if (alist.get(i).get(0).data.equals(start)) {
	            startNode = alist.get(i).get(0);
	            break;
	        }
	    }
	    if (startNode == null) {
	    	printWriter.println("Starting point not found");
	        return;
	    }

	    nodeStack.push(startNode);
	    ArrayList<String> initialPath = new ArrayList<>();
	    initialPath.add(startNode.data);
	    pathStack.push(initialPath);
	    int totalTime = 0;
	    int totalCost = 0;
	    int counter= 0;
	    int path=1;
	    while (!nodeStack.isEmpty() && counter < limit) {
	        Node currentNode = nodeStack.pop();
	        ArrayList<String> currentPath = pathStack.pop();
	        int nodeIndex = -1;
	        for (int i = 0; i < alist.size(); i++) {
	            if (alist.get(i).get(0).data.equals(currentNode.data)) {
	                nodeIndex = i;
	                break;
	            }
	        }

	        if (nodeIndex == -1) {
	        	printWriter.println("The path is not available");
	            return;
	        }

	        if (!visited[nodeIndex]) {
	            visited[nodeIndex] = true;
	            totalTime += currentNode.time;
	            totalCost += currentNode.cost;
	            LinkedList<Node> neighbors = alist.get(nodeIndex);
	            for (int i = 1; i < neighbors.size(); i++) {
	                Node neighbor = neighbors.get(i);
	                ArrayList<String> newPath = new ArrayList<>(currentPath);
	                newPath.add(neighbor.data);
	               
	                if (neighbor.data.equals(end)) {
	                	if((totalCost !=0 && totalTime!=0))
	                	printWriter.print("Path "+path+": ");
	                	for(int x = 0; x < newPath.size();x++)
		                {
	                		
	                		if((totalCost !=0 && totalTime!=0))
	                		
	                			printWriter.print(newPath.get(x)+" -> ");
	                		
		                }
	                	if(totalCost !=0 && totalTime!=0) {
	                		printWriter.println( " Time: " + totalTime+" Cost: " + totalCost+".00");
	                	counter++;
                		path++;
	                	}
	                } else {
	                    nodeStack.push(neighbor);
	                    pathStack.push(newPath);
	                }
	            }
	        }
	    }
	}

	
	
}
