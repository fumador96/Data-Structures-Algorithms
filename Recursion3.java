package PracticeDSA;
import java.util.*;

public class Recursion3 {
	public static ArrayList<String> getMazePath(int endr, int endc, int currr, int currc) {
		ArrayList<String> list = null;
		if (endr == currr && endc == currc) {
			list = new ArrayList<>();
			list.add("");
			return list;
		}
		ArrayList<String> l = new ArrayList<>();
		if (currr < endr) {
			list = getMazePath(endr, endc, currr + 1, currc);
			for (int i = 0; i < list.size(); i++) {
				l.add("V" + list.get(i));
			}
		}
		if (currc < endc) {
			list = getMazePath(endr, endc, currr, currc + 1);
			for (int i = 0; i < list.size(); i++) {
				l.add("H" + list.get(i));
			}
		}
		return l;
	}
	
	public static void printMazePath(int endr,int endc, int currr,int currc,String res) {
		if(currr==endr && currc==endc) {
			System.out.println(res);
			return;
		}
		if(currr<endr) {
			res = res + "V";
			printMazePath(endr, endc, currr+1, currc, res);
			res = res.substring(0, res.length()-1);
		}
		if(currc<endc) {
			res = res + "H";
			printMazePath(endr, endc, currr, currc+1, res);		
			res = res.substring(0, res.length()-1);
		}
		if(currr<endr && currc<endc) {
			res = res + "D";
			printMazePath(endr, endc, currr+1, currc+1, res);
			res = res.substring(0, res.length()-1);
		}
	}
	
	public static int countNQueens(boolean[][] board,int row) {
		if(row==board.length) {
			return 1;
		}
		int count = 0;
		for(int col=0;col<board[row].length;col++) {
			if(isItSafe(board,row,col)) {
				board[row][col] = true;
				count  = count + countNQueens(board, row+1);
				board[row][col] = false;
			}
		}
		return count;
	}
	
	public static boolean isItSafe(boolean[][] board, int row, int col) {
		if(Horizontal(board, row, col)==true && Vertical(board, row, col)==true && diagonal(board, row, col)==true) {
			return true;
		}
		return false;
	}
	
	public static boolean Horizontal(boolean[][] board, int row, int col) {
		for(int i=0;i<board[row].length;i++) {
			if(board[row][i]==true)
				return false;
		}
		return true;
	}
	
	public static boolean Vertical(boolean[][] board, int row, int col) {
		for(int i=0;i<board[col].length;i++) {
			if(board[i][col]==true)
				return false;
		}
		return true;
	}
	public static boolean diagonal(boolean[][] board, int row, int col) {
		for(int i=1;i<board.length;i++) {
			if(row-i>=0 && col+i<board[row].length && board[row-i][col+i]==true) {
				return false;
			}
			if(row-i>=0 && col-i>=0 && board[row-i][col-i]==true) {
				return false;
			}
			if(row+i<board.length && col-i>=0 && board[row+i][col-i]==true) {
				return false;
			}
			if(row+i<board.length && col+i<board[row].length && board[row+i][col+i]==true) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean solveSuduko(int[][] board,boolean[][] fixedcells,int row, int col, int n) {
		if(row==n) {
			return true;
		}
		if(col==n) {
			return solveSuduko(board, fixedcells, row+1, 0, n);
		}
		if(fixedcells[row][col]) {
			return solveSuduko(board, fixedcells, row, col+1, n);
		}
		for(int setnum=0;setnum<n;setnum++) {
			boolean canplace = checkNumber(board,fixedcells,row,col,n,setnum);
			if(canplace) {
				board[row][col] = setnum;
				boolean checkrest = solveSuduko(board, fixedcells, row, col+1,n);
				if(checkrest) {
					return true;
				}
				else {
					board[row][col] = 0;
				}
			}
		}
		return false;
	}
	
	public static void fixedCells(int[][] board, boolean[][] fixedcells) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j]!=0) {
					fixedcells[i][j]=true;
				}
			}
		}
	}
	
	public static void display(int[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static boolean checkNumber(int[][] board,boolean[][] fixedcells, int row, int col, int n, int setnum) {
		for(int i=0;i<n;i++) {
			if(board[row][i]==setnum)
				return false;
			if(board[i][col]==setnum)
				return false;
		}
		int sqrtN = (int)Math.sqrt(n);
		int srow = (row/sqrtN)*sqrtN;
		int scol = (col/sqrtN)*sqrtN;
		for(int i=srow;i<srow + sqrtN;i++) {
			for(int j=scol;j<scol+sqrtN;j++) {
				if(board[i][j]==setnum)
					return false;
			}
		}
		return true;
	}
	
	public static String isPrime(int n){
        int sqr = (int)Math.sqrt(n);
        int i=2;
        while(i<=sqr){
            if(n%i==0)
                return "Not Prime";
            i++;
        }
        return "Prime";
    }
	
	public static void main(String args[]) {
//		//System.out.println(getMazePath(2, 2, 0, 0));
//		//printMazePath(3, 3, 0, 0, "");
//		//boolean[][] board = new boolean[7][7];
//		//System.out.println(countNQueens(board, 0));
//		int[][] board = { {5,3,0,0,7,0,0,0,0},
//						  {6,0,0,1,9,5,0,0,0},
//						  {0,9,8,0,0,0,0,6,0},
//						  {8,0,0,0,6,0,0,0,3},
//						  {4,0,0,8,0,3,0,0,1},
//						  {7,0,0,0,2,0,0,0,6},
//						  {0,6,0,0,0,0,2,8,0},
//						  {0,0,0,4,1,9,0,0,5},
//						  {0,0,0,0,8,0,0,7,9}
//						};
//		int n = 9;
//		boolean[][] fixedcells = new boolean[n][n];
//		fixedCells(board,fixedcells);
//		boolean ans = solveSuduko(board, fixedcells, 0, 0, n);
//		if(ans==true) {
//			display(board);
//		}
//		else {
//			System.out.println("Can't be solved");
//		}
//		Scanner sc = new Scanner(System.in);
//        int n1 = sc.nextInt();
//        int n2 = sc.nextInt();
//        printMazePath(n1,n2,0,0,"");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(isPrime(n));
	}
}
