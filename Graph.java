package PracticeDSA;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Graph {
	
	private int V;
	private LinkedList<Integer> adjListArray[];
	private int[][] trans;
	private static final int DEFAULT_VALUE = 4;
	
	Graph(){
		this(DEFAULT_VALUE);
	}
    
    @SuppressWarnings("unchecked")
	// constructor 
    Graph(int V){
        this.V = V;
        trans = new int[V][V];
        adjListArray = new LinkedList[V];
        for(int v = 0; v < V ; v++){
            adjListArray[v] = new LinkedList<>();
        }
    }
    
    //Adding edges to an UNDIRECTED graph.
    public void addEdge(int src, int dest) {
    	this.addEdge(this.V, src, dest);
    }
    
    private void addEdge(int V, int src, int dest){
    	this.adjListArray[src].addFirst(dest);
    	this.adjListArray[dest].addFirst(src);
    }
    
    //Breadth First Search(Traversal) of a DIRECTED graph.
    public void BFS(int start) {
    	boolean arr[] = new boolean[this.V];
    	this.BFS(start,arr);
    }
    
    private void BFS(int start, boolean arr[]) {
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(start);
    	arr[start] = true;
    	while(!queue.isEmpty()) {
    		int print = queue.poll();
    		System.out.print(print+" ");
    		Iterator<Integer> itr = this.adjListArray[print].listIterator();
    		while(itr.hasNext()) {
    			int n = itr.next();
    			if(arr[n]==false) {
    				queue.add(n);
    				arr[n] = true;
    			}
    		}
    	}
    }
    
  //Depth First Search(Traversal) of a DIRECTED graph.
    public void DFS(int start){
        boolean visited[] = new boolean[this.V];
        this.DFS(start,visited);
    }
    
    private void DFS(int start, boolean[] vis){
        System.out.print(start+" ");
        vis[start] = true;
        Iterator<Integer> itr = adjListArray[start].listIterator();
        //This while loop leads to few redundant calls.
        while(itr.hasNext()){
            int n = itr.next();
            if(vis[n]==false){
                this.DFS(n,vis);
            }
        }
    }
    
  //FINDING THE STRONGLY CONNECTED COMPONENT.
    public void stronglyConnectedComponent(){
    	boolean visited[] = new boolean[this.V];
        Stack<Integer> s = new Stack<>();
        for(int i=1;i<this.V;i++){
        	DFSStack(i, visited,s);
        }
        Graph trans = this.transposeGraph();
        visited = new boolean[this.V];
        while(!s.isEmpty()){
            int pop = s.pop();
            if(visited[pop]==false){
                trans.DFS(pop,visited);
                System.out.println();   
            }
        }
    }
   
    private void DFSStack(int v,boolean visited[],Stack<Integer> s){
        visited[v] = true;
        Iterator<Integer> i = adjListArray[v].listIterator();
        //Visiting all the adjacent nodes of "n",
        while (i.hasNext()){
            int n = i.next();
            if (!visited[n]){
                this.DFSStack(n, visited,s);
            }               
        }
        //and then pushing it in the satck. 
        s.push(v);
    }
    
    private Graph transposeGraph(){
        Graph trans = new Graph(this.V);
        for(int v=0;v<this.V;v++){
            Iterator<Integer> itr = this.adjListArray[v].listIterator();
            while(itr.hasNext()){
                trans.addEdge(itr.next(),v);
            }
        }
        return trans;
    }
    
    //Finding the mother vertex of a graph.
    public int findMotherVertex() {
    	boolean[] vis = new boolean[this.V];
    	return this.findMotherVertex(vis);
    }
    
    private int findMotherVertex(boolean[] vis) {
    	int m = 0;
    	for(int i=0;i<this.V;i++) {
    		if(vis[i]==false) {
    			this.DFSUtil(i,vis);
    			m = i;
    		}
    	}
    	vis = new boolean[this.V];
    	this.DFSUtil(m, vis);
    	for(int i=0;i<this.V;i++) {
    		if(vis[i]==false)
    			return -1;
    	}
    	return m;
    }
    
    private void DFSUtil(int v,boolean visited[]){
        visited[v] = true;
        Iterator<Integer> i = adjListArray[v].listIterator();
        //Visiting all the adjacent nodes of "n",
        while (i.hasNext()){
            int n = i.next();
            if (!visited[n]){
                this.DFSUtil(n, visited);
            }               
        }
        //and then pushing it in the satck. 
    }
    
  //Matrix format of routes in a graph from every vertex to every other vertex.
    public void transitiveClosure() {

         for (int i = 0; i < this.V; i++) {
        	 transUtil(i, i);
         }

         for (int i = 0; i < this.V; i++) {
         System.out.println(Arrays.toString(trans[i]));
         }
     }

     private void transUtil(int s, int v) {
         trans[s][v] = 1;
         Iterator<Integer> itr = adjListArray[v].listIterator();
         while(itr.hasNext()==true) {
             int n = itr.next();
             if (trans[s][n]==0) {
            	 transUtil(s,n);
             }
         }
     }
     
     //Finding the total number of nodes in a particular level of a GENERIC TREE.
     public void nodesAtaLevel(int level){
         Queue<Integer> queue = new LinkedList<>();
         int n = this.nodesAtaLevel(level,queue);
         System.out.println(n);
     }    
     
     private int nodesAtaLevel(int level, Queue<Integer> queue){
         int l = 0;
         queue.add(0);
         if(level==0) {
        	 return 1;
         }
         queue.add(Integer.MIN_VALUE);
         while(!queue.isEmpty()){
             if(l==level){
                 break;
             }
             int temp = queue.poll();
             if(temp!=Integer.MIN_VALUE){
                 Iterator<Integer> itr = adjListArray[temp].listIterator();
                 while(itr.hasNext()==true){
                     int n = itr.next();
                     queue.add(n);
                 }
             }
             else {
            	 queue.add(Integer.MIN_VALUE);
            	 l++;
             }
         }
         int s = -1;
         int k = 0;
         while(k!=Integer.MIN_VALUE && !queue.isEmpty()){
        	 k=queue.poll();
             s++;
         }
         return s;
     }
     
     public void countPathsBwNodes(int src, int dest) {
    	 if(src==dest) {
    		 System.out.println("Same Nodes");
    	 }
    	 boolean[] vis = new boolean[this.V];
    	 int paths = this.countPathsBwNodes(src, dest, vis);
    	 System.out.println(paths);
     }
     
     private int countPathsBwNodes(int src, int dest, boolean[] vis) {
    	 int path = 0;
    	 if(src==dest) {
    		 path++;
    		 return path;
    	 }
    	 vis[src] = true;
    	 Iterator<Integer> itr = adjListArray[src].listIterator();
    	 while(itr.hasNext()==true) {
    		 int n = itr.next();
    		 if(vis[n]==false)
    			 path = path + this.countPathsBwNodes(n, dest, vis);
    		 else {
    			 break;
    		 }
    	 }
    	 return path;
     }
     
     //Finding path in a rectangular grid with circles in it.
     public String rectangleWithCircles(int m, int n, int k, int r, int[] X, int[] Y){
         boolean[][] block = new boolean[m][n];
         for(int i=0;i<k;i++){
             //Center
             block[X[i]-1][Y[i]-1] = true;
             //Towards Rows.
             if(Y[i]!=1)
             block[X[i]-1][Y[i]-r-1] = true;
             if(Y[i]!=n)
             block[X[i]-1][Y[i]] = true;
             //Towards Columns
             if(X[i]!=1)
             block[X[i]-2][Y[i]-1] = true;
             if(X[i]!=m)
             block[X[i]][Y[i]-1] = true;
         }
         if(block[0][0]==true){
             return "Not Possible";
         }
         else
             return this.pathPossible(m,n,block,1,1);        
     }
     
     private String pathPossible(int m, int n,boolean[][] block, int x, int y){
         if(x==m && y==n){
             return "Possible";    
         }
         if(x!=m && block[x][y-1]==false){
             return this.pathPossible(m, n, block, x+1, y);
         }else if(y!=n && block[x-1][y]==false){
             return this.pathPossible(m, n, block, x, y+1);
         }
         else{
             return "Not Possible";
         }
     }
     
     //Constructing a graph of a n-ary tree, given parent array only.
     public Graph NaryTreetoGraph(int[] parent){
         Graph gtree = new Graph(parent.length);
         for(int i=1;i<gtree.V;i++){
             gtree.addEdge(parent[i],i);
         }
         return gtree;
     }
     
     //Maximum number of edges to be added to a tree so that it stays a Bipartite graph.
     public int maxEdgeReqForBipartite(int v){
         boolean[] vis = new boolean[this.V];
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(0);
         q.add(Integer.MIN_VALUE);
         int counter = 0;
         int countr = 1;
         int countl = 0;
         while(!q.isEmpty()){
             int temp = q.poll();
             if(temp!=Integer.MIN_VALUE){
                 Iterator<Integer> itr = this.adjListArray[temp].listIterator();
                 while(itr.hasNext()){
                     int n = itr.next();
                     if(vis[n]==false){
                         q.add(n);
                         if(counter%2==0){
                             countl++;
                         }else{
                             countr++;
                         }
                         vis[n]=true;
                     }
                 }
             }else{
                 if(q.isEmpty()){
                     break;
                 }
                 q.add(Integer.MIN_VALUE);
                 counter++;
             }
         }
         return countr*countl - v + 1;
     }
     //END
     
     
     //Printing All paths between two nodes using BFS.   
     public void allPathsBTW2Vertices(int u, int v){
             boolean[] vis = new boolean[this.V];
             String s = Integer.toString(u);
             this.allPathsBTW2Vertices(u, v, vis, s);
     }
     Queue<Integer> qu = new LinkedList<Integer>();
     private void allPathsBTW2Vertices(int u, int v, boolean[] vis, String path){
         if(u==v){
             System.out.println(path);
             return;
         }
         qu.add(u);
         vis[u] = true;
         while(!qu.isEmpty()){
             int temp = qu.poll();
             Iterator<Integer> itr = this.adjListArray[temp].listIterator();
             while(itr.hasNext()){
                 int n = itr.next();
                 if(vis[n]==false){
                     vis[n] = true;
                     this.allPathsBTW2Vertices(n, v, vis, path+n);
                 }
                 vis[n] = false;
             }
         }
     }
     
     //Minimum edges between two nodes.
     public int minEdgesBTW2Nodes(int u, int v){
             boolean[] vis = new boolean[this.V];
             return this.minEdgesBTW2Nodes(u, v, vis);
         }
         
     private int minEdgesBTW2Nodes(int u, int v, boolean[] vis){
    	 Queue<Integer> q = new LinkedList<Integer>();
    	 q.add(u);
    	 int[] distance = new int[this.V];
    	 vis[u] = true;
    	 while(!q.isEmpty()){
    		 int temp = q.poll();
    		 Iterator<Integer> itr = this.adjListArray[temp].listIterator();
    		 while(itr.hasNext()){
    			 int n = itr.next();
    			 if(vis[n]==false){
    				 q.add(n);
    				 vis[n] = true;
    				 
    				 //Attaching the number of steps(edges) 
    				 //required to reach node n, if, to reach 
    				 //temp it required distance[temp].
    				 
    				 distance[n] = distance[temp] + 1;
    			 }else continue;
    		 }
    	 }
    	 
    	 //Ultimately, The BFS will lead to the shortest travel 
    	 //to reach the node V prior to any other routes.
    	 
    	 return distance[v];
     }
     
     //Move weighting scale alternatively under given constraints.
     public void printWeightsOnScale(int[] weight, int steps, int wl, int wr, boolean turn, int last, String ans){
         if(steps==0){
             System.out.println(ans);
             return;
         }
         if(turn==true){
             for(int i=0;i<weight.length;i++){
                 if(wl+weight[i]>wr && weight[i]!=last){
                     this.printWeightsOnScale(weight, steps-1, wl+weight[i], wr, false, weight[i], ans+" "+weight[i]);
                     return;
                 }
             }
         }
         else{
             for(int j=0;j<weight.length;j++){
                 if(wr+weight[j]>wl && weight[j]!=last){
                     this.printWeightsOnScale(weight, steps-1, wl, wr+weight[j], true, weight[j], ans+" "+weight[j]);
                     return;
                 }
             }
         }
     }
     
     //Delete Edge to minimize subtree sum difference
     public int minSumDifference(int[] weight){
         int total = 0;
         for(int i=0;i<weight.length;i++){
             total += weight[i];
         }
         this.dfsSubtreeSum(0, -1, total, weight);
         return res;
     }
     private static int res = Integer.MAX_VALUE;
     private void dfsSubtreeSum(int v, int parent, int total, int[] subtree){
         int sum = subtree[v];
         Iterator<Integer> itr = this.adjListArray[v].listIterator();
         while(itr.hasNext()){
             int n = itr.next();
             if(n!=parent){
                 this.dfsSubtreeSum(n, v, total, subtree);
                 sum += subtree[n];
             }
         }
         
         subtree[v] = sum;
         
         if(v!=0 && res>Math.abs(total-2*sum)){
             res = Math.abs(total-2*sum);
         }
     }
     
     //Find the smallest binary digit multiple of given number.
     public int binaryDigitMultiple(int n) {
    	 Queue<Integer> q = new LinkedList<>();
    	 q.add(1);
    	 while(!q.isEmpty()) {
    		 int temp = q.poll();
    		 if(temp%n==0) {
    			 return temp;
    		 }
    		 q.add(temp*10);
    		 q.add(temp*10+1);
    	 }
    	 return 0;
     }
     
     //Roots of a tree which give minimum height.
     public int[] minimumHeightRoot() {
    	 Queue<Integer> q = new LinkedList<>();
    	 //Adding all the leaves into Queue.
    	 for(int i=0;i<this.V;i++) {
    		 if(this.adjListArray[i].size()==1) {
    			 q.add(i);
    		 }
    	 }
    	 int[] arr = new int[0];
    	 boolean[] vis = new boolean[this.V];

    	 //Doing BFS from all the leaves.
    	 while(!q.isEmpty()) {
    		 int temp = q.poll();
    		 if(q.size()==1 && vis[temp]==true && vis[q.peek()]==true) {
    			 arr = new int[2];
    			 arr[0] = temp;
    			 arr[1] = q.poll();    			 
    			 return arr;
    		 }

    		 else if(q.size()==0 && vis[temp]==true) {
    			 arr = new int[1];
    			 arr[0] = temp;
    			 return arr;
    		 }

    		 Iterator<Integer> itr = this.adjListArray[temp].listIterator();
    		 while(itr.hasNext()) {
    			 int n = itr.next();
    			 if(!vis[n]) {
    				 vis[n]=true;
    				 q.add(n);
    			 }
    		 }
    	 }
    	 return arr;
     }
     
     //Height of leaves from given root in a graph.
     public int heightGraph(int root) {
    	 boolean[] vis = new boolean[this.V];
    	 Queue<Integer> q = new LinkedList<>();
    	 q.add(root);
    	 int height = 0;
    	 while(!q.isEmpty()) {
    		 boolean flag = false;
    		 int temp = q.poll();
    		 Iterator<Integer> itr = this.adjListArray[temp].listIterator();
    		 while(itr.hasNext()){
    			 int n =itr.next();
    			 if(vis[n]==false) {
    				 vis[n] = true;
    				 //Flag here tells, whether any new new node has been visited or not in a new level.
    				 flag = true;
    				 q.add(n);
    			 }
    		 }
    		 if(flag==true)
    			 height++;
    	 }
    	 return height;
     }
     
   //Minimum steps required for a knight to go to a given position.
     public int minStepsForKnight(int n, int[] currPos, int[] targetPos){
         int[] allowedX = {-2, -1, 1, 2, -2, -1, 1, 2};
         int[] allowedY = {-1, -2, -2, -1, 1, 2, 2, 1};
         
         boolean[][] vis = new boolean[n+1][n+1];

         cellInfo cell = new cellInfo(currPos[0], currPos[1], 0);
         Queue<cellInfo> q = new LinkedList<cellInfo>();
         q.add(new cellInfo(currPos[0],currPos[1],0));
         vis[cell.x][cell.y] = true;
         while(!q.isEmpty())
         {
             cell = q.poll();
             if(cell.x==targetPos[0] && cell.y==targetPos[1])
             {
                 return cell.dis;
             }
             for(int i=0;i<8;i++)
             {
                 int x = cell.x + allowedX[i];
                 int y = cell.y + allowedY[i];
                 if(this.isInsideBoard(x, y, n) && !vis[x][y])
                 {
                     vis[x][y]=true;
                     q.add(new cellInfo(x,y,cell.dis+1));
                 }
             }
         }
         return 0;
     }
     
     //Contains cell info.
     private class cellInfo{
         int x;
         int y;
         int dis;
         cellInfo(int x, int y, int dis)
         {
             this.x = x;
             this.y = y;
             this.dis = dis;
         }
     }

     private boolean isInsideBoard(int x, int y, int n){
         if(x<=n && x>=1 && y<=n && y>=1){
             return true;
         }
         return false;
     }
     
     //Minimum number of operation required to convert number x into y.
     public int minOperationfromXtoY(int x, int y){
         if(x==0){
             System.out.println("0 can't be converted to any other numbers by these two operations.");
             return -1;
         }
         Queue<Integer> q = new LinkedList<Integer>();
         int steps = 0;
         int min = Integer.MIN_VALUE;
         q.add(x);
         q.add(min);
         while(!q.isEmpty()){
             int temp = q.poll();
             if(temp==y){
                 return steps;
             }
             if(temp!=min){
                 q.add(temp*2);
                 q.add(temp-1);
             }else{
                 q.add(min);
                 steps++;
             }
         }
         return 0;
     }
     
     //Minimum steps to reach end of array under constraints.
     public int reachEndOfArray(int[] arr){
         boolean[] vis = new boolean[arr.length];
         if(arr.length==1){
             return 0;
         }
         Queue<Integer> q = new LinkedList<Integer>();
         int curri = 0;
         vis[curri]=true;
         int steps = 1;
         int min = Integer.MIN_VALUE;
         if(curri!=0){
             q.add(curri-1);
         }
         if(curri!=arr.length-1){
             q.add(curri+1);
         }
         ArrayList<Integer> indices = this.allIndexOf(arr, curri);
         for(int i=0;i<indices.size();i++){
             if(!vis[indices.get(i)])
                 q.add(indices.get(i));
         }
         q.add(min);
         while(!q.isEmpty()){
             int temp = q.poll();
             if(temp==arr.length-1){
                 return steps;
             }
             if(temp!=min){
                 vis[temp]=true;
                 if(temp!=0 && !vis[temp-1]){
                     q.add(temp-1);
                 }
                 if(temp!=arr.length-1){
                     q.add(temp+1);
                 }
                 ArrayList<Integer> indice = this.allIndexOf(arr, temp);
                 for(int i=0;i<indice.size();i++){
                     if(!vis[indice.get(i)])
                         q.add(indice.get(i));
                 }
             }else{
                 q.add(min);
                 steps++;
             }
         }
         return 0;
     }
     
     //Returns all the indices with same values.
     private ArrayList<Integer> allIndexOf(int[] arr, int curri){
         ArrayList<Integer> indices = new ArrayList<Integer>();
         for(int i=0;i<arr.length;i++){
             if(arr[i]==arr[curri] && i!=curri){
                 indices.add(i);
             }
         }
         return indices;
     }
     
     //Print stepping Numbers.
     public void printSteppingNumbers(int x, int y){
         for(int i=0;i<=9;i++){
             this.steppingNumbers(x, y, i);
         }
     }
     
     //Give Stepping numbers using BFS. [Same approach can be 
     //applied using recursive DFS.]
     private void steppingNumbers(int x, int y, int firstdig){
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(firstdig);
         while(!q.isEmpty()){
             int temp = q.poll();
             int lastdig = temp%10;
             if(temp>=x && temp<=y){
                 System.out.println(temp+" ");
             }
             if(temp==0 || temp>y)
                 continue;
             int a = temp*10+lastdig+1;
             int b = temp*10+lastdig-1;
             if(lastdig==0){
                 q.add(a);
             }
             if(lastdig==9){
                 q.add(b);


             }
             if(a<=y && a>x){
                 q.add(a);
                 this.addEdge(temp,a);
             }
             if(b<=y && b>x){
                 q.add(b);
                 this.addEdge(temp, b);
             }
         }
     }
     
     //A matrix probability question        
     public float probInsideMatrix(int[][] mat, int steps, int currx, int curry){
         float prob = 0.000f;
         int[] currPos = {currx,curry};
         if(!this.isInsideMatrix(currPos, mat)){
             return prob; 
         }
         if(steps==0){
             return 1.000f;
         }
         
         prob += this.probInsideMatrix(mat, steps-1, currx-1, curry);
         
         prob += this.probInsideMatrix(mat, steps-1, currx+1, curry);
         
         prob += this.probInsideMatrix(mat, steps-1, currx, curry-1);
         
         prob += this.probInsideMatrix(mat, steps-1, currx, curry+1);
         
         return prob/4;
     }
         
     private boolean isInsideMatrix(int[] currPos, int[][] mat){
         if(currPos[0]>=0 && currPos[0]<mat[0].length && currPos[1]>=0 && currPos[1]<mat.length)
             return true;
         return false;
     }
     
     public boolean samePath(int x, int y) {
    	 return true;
     }
         
//     //Find length of the largest region in Boolean Matrix.
//     public int largestRegionInMatrix(int[][] mat) {
//    	 for(int i=0;i<mat.length;i++) {
//    		 for(int j=0;j<mat[i].length;j++) {
//    			 
//    		 }
//    	 }
//    	 return 1;
//     }
//     
//     private boolean isSafeForVertex(int i, int j, boolean[][] vis) {
//    	 if(i<vis.length && j<vis[i].length && i>=0 && j>=0 && vis[i][j]==false)
//    		 return true;
//    	 return false;
//     }
     
     
     
     private void display(){       
    	for(int v = 0; v < V; v++)
    	{
    		System.out.println("Adjacency list of vertex "+ v);
    		System.out.print("head");
    		for(Integer node: this.adjListArray[v]){
    			System.out.print(" -> "+node);
    		}
    		System.out.println();
    	}
    }
    
    
  
    public static void main(String args[]){
    	Graph graph = new Graph(8);
    	graph.addEdge(1, 3);
    	graph.addEdge(3, 2);
    	graph.addEdge(2, 1);
    	graph.addEdge(4, 1);
    	graph.addEdge(4, 5);
    	graph.addEdge(5, 7);
    	graph.addEdge(7, 6);
    	graph.addEdge(6, 5);
    	//graph.addEdge(5, 2);
    	graph.display();
//    	int[] arr = graph.minimumHeightRoot();
//    	for(int i=0;i<arr.length;i++) {
//    		System.out.println(arr[i]+" ");
//    	}
//    	System.out.println("Height is: "+graph.heightGraph(arr[0]));
    	graph.stronglyConnectedComponent();
    }
}
