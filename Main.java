import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
	
		Scanner kb = new Scanner(System.in);
		System.out.print("Please Enter flight data file Name(without the .txt @ the end) : ");
		String FileDataN = kb.nextLine();
		
		System.out.print("Please Enter flight plans file Name(without the .txt @ the end) : ");
		String FilePlansN = kb.nextLine();
		
	File FD = new File(FileDataN+".txt");
	File FP = new File(FilePlansN+".txt");

	Scanner FDscan =  new Scanner(FD);	
	Scanner FPscan = new Scanner(FP);
	
	
	ArrayList<String> data  = new ArrayList<String>();	
	
	Random random = new Random();
	
		
	int FDcounter = 0;
	
	while(FDscan.hasNextLine())
	{
	FDscan.nextLine();
	FDcounter++;
	
	}
	
	//System.out.println(FDcounter);
	FDscan.close();
	
	FDscan = null;
	
	FDscan = new Scanner(FD);
	
	

	/*while(FDscan.hasNextLine())
	{
		System.out.println(FDscan.nextLine());
	}*/
	
FDscan.close();
	
	FDscan = null;
	
	FDscan = new Scanner(FD);
	
	
	while(FDscan.hasNextLine())
	{
		StringTokenizer str = new StringTokenizer(FDscan.nextLine(),"|");
		while(str.hasMoreTokens())
		{
			data.add(str.nextToken());
		
		}
	}
	//Reads the Filght Data file and closes it, then prints the data into console.
	
	System.out.println();
	
	int FPcounter = 0;
	
	while(FPscan.hasNextLine())
	{
		FPscan.nextLine();
		FPcounter++;
	}
	//System.out.println(FPcounter);
	FPscan.close();
	
	FPscan = null;
	
	FPscan = new Scanner(FP);
	
	/*while(FPscan.hasNextLine())
	{
		System.out.println(FPscan.nextLine());
	}
	*/
	
FPscan.close();
	
	FPscan = null;
	
	FPscan = new Scanner(FP);
	
	ArrayList<String> fpdata  = new ArrayList<String>();
	
	while(FPscan.hasNextLine())
	{
		StringTokenizer str = new StringTokenizer(FPscan.nextLine(),"|");
		while(str.hasMoreTokens())
		{
			fpdata.add(str.nextToken());
		
		}
	}
	

	//Reads the Flight path file and closes it, then prints the data into the console.
	
ArrayList<String> fdata = new ArrayList<>(data);
Iterator<String> iteratror = fdata.iterator();
while (iteratror.hasNext()) {
	String element =  iteratror.next();
	try {
	    Integer.parseInt(element);
	    iteratror.remove();
	} catch (NumberFormatException e){}
	
}

	
	
Set<String> s = new LinkedHashSet<String>(fdata);



	


	
	ArrayList<String> fdatas = new ArrayList<>(s);
	//System.out.println(fdata);
	System.out.println();
	//System.out.println(fdatas);
	System.out.println();
	
	
	
	ArrayList<Integer> Fdata_TC = new ArrayList<>();
	for(String x  : data)
	{
		try{
			
		Fdata_TC.add(Integer.parseInt(x));
		
		} catch(NumberFormatException ex){}
		
	}

	
	
	
	ArrayList<Integer> Fdata_Time = new ArrayList<>();
	ArrayList<Integer> Fdata_Cost = new ArrayList<>();
	
	for(int x = 0; x<Fdata_TC.size();x+=2)
	{
		Fdata_Time.add(Fdata_TC.get(x));
	}
	
	//Times for the flights
	//System.out.println(Fdata_Time);
	System.out.println();
	
	for(int x = 1; x<Fdata_TC.size();x+=2)
	{
		Fdata_Cost.add(Fdata_TC.get(x));
	}
	
	//cost for the flights
	//System.out.println(Fdata_Cost);
	System.out.println();
	
	
	
	int node = 0;
	for(int x = 0; x < fdatas.size();x++)
	{	
		node++;
	}
	
	Graph graph = new Graph(node);
	
	for(int x = 0; x < fdatas.size();x++)
	{
		graph.addNode(new Node(fdatas.get(x)));
		
	}
	
	
	
	
	for(int x = 0; x < fdata.size();x++)
	{
		if(fdatas.size()!=fdata.size())
		{
			fdatas.add(null);
		}
	}
	
	
	//System.out.println(fdatas);
	
	ArrayList<Integer> nodepoint = new ArrayList<>();
	
	
	for (int i = 0; i < fdata.size(); i++) {
	    String element = fdata.get(i);
	    int index = fdatas.indexOf(element);
	    
	    nodepoint.add(index);
	   
	}


	//System.out.println(nodepoint);
	int counters = 0;
	for (int i = 0; i < FDcounter*2; i+=2) {
	    int min = nodepoint.get(i);
	    int max = nodepoint.get(i+1);
	   // System.out.println(" Min: " + min + " Max: " + max);

	    

	    graph.addEdge(min, max, Fdata_Time.get(counters), Fdata_Cost.get(counters));
	    graph.addEdge(max, min, Fdata_Time.get(counters), Fdata_Cost.get(counters));
	    counters++;
	}

System.out.println("Adjecency List for the Flight Data");
System.out.println("-----------------------------------");
System.out.println();	
	graph.print();
	
	
	System.out.println();
	

	
	ArrayList<String> Need = new ArrayList<>();
	
	for(int x =2; x< fpdata.size();x+=2)
	{
		String need = fpdata.get(x);
		
		Need.add(need);
		
		fpdata.remove(x);
	}
	
	//System.out.println(fpdata);
	//System.out.println(Need);
	
	System.out.println();
	
	System.out.print("Output: ");
	
	
	
	ArrayList<String> Needs = new ArrayList<>(Need);
	for(int x = 0; x<Need.size();x++)
	{
		if(Need.get(x).equals("T"))
		{
			Needs.set(x, " (Time)");
		}
		else if(Need.get(x).equals("C"))
		{
			Needs.set(x, " (Cost)");
		}
	}

	System.out.print("What do you want to name the Output File as(dont include .txt @ end): ");
	String ans = kb.nextLine();
	System.out.println("Enjoy your file, It is in the project path 8)");
	try (FileWriter fileWriter = new FileWriter(ans+".txt", false);
		     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		     PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

		    int c = 1;
		    for (int x = 0; x < fpdata.size(); x += 2) {
		        String s1 = "Flight " + c + ": " + fpdata.get(x) + ", " + fpdata.get(x + 1) + " " + Needs.get(c - 1);
		        //System.out.print(s1);

		        // Write the contents to the output file
		        printWriter.println(s1);
		        printWriter.flush();
		       
		        graph.dfs(fpdata.get(x), fpdata.get(x + 1), 3, printWriter);
		        printWriter.println();
		        c++;
		    }
		} catch (IOException e) {
		    System.err.println("Error writing to the output file: " + e.getMessage());
		}

	
	//Start Point
	

//Destination
	
	
	
	
}
}