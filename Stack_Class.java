import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

//import java.commons.lang3.ArrayUtils;
public class Stack_Class {///Class that keeps track of history of the game
	 
	 
	
	private JButton[][] buttons;
	private int solved;
	private int solved_hidden;
	public Stack_Class(JButton[] buttons_1){
		solved=0;
		JButton[][] Temp =new JButton[9][9];
		int count=0;
		for (int i=0;i<9;++i){
			for (int j=0;j<9;++j){
				if (count==buttons_1.length){
					
					break;}
					
				Temp[i][j]=buttons_1[count];
				
				
				++count;
				
			}
		}
		buttons = Temp.clone();
	
		
	}
	
	public void Return_Array(){
		return ;
	}
	public int Bool(){
		return solved;
		
	}
	public JButton [] [] NakedPairs(){
		Pair Temp=getBlankIndex(0,0);
		int x=Temp.getFirst();
		int	y=Temp.getSecond();
		
		int Label;
		
		NakedPairHelper(x,y);
		return buttons;
		
	}
	
	private int NakedPairHelper(int x,int y){
		//Checks to see if a blank in the box
		
		if(Check_Square_Naked(x,y)>0){
				
		}
		else if (Check_Col_Naked(x,y)>0){
			
			
		}
		else if (Check_Row_Naked(x,y)>0){
			
			
			
		}
		////Decides if two blanks (one is the blank in question) have the same exact candidate List
		
					
	}
	private ArrayList<Integer> Check_Square_Naked(int x, int y){
		ArrayList<ArrayList<Integer>> List=new ArrayList<ArrayList<Integer>>(9);
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		int j;
		int i;
		int Start_x=((x/3)*3);
		int End_x=((x/3)*3)+2;
		int Start_y=((y/3)*3);
		int End_y=((y/3)*3)+2;
		
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_X= new Blank_Button(Row,Column,Square);
		Temp=Blank_Cell_X._NakedPairs();
		ArrayList<Integer>Temp_1= new ArrayList<Integer>(9);
		Temp_1.add(0);
		
		System.out.println("Pair for blank"+x+"_"+y);
		for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if( i==x && j==y){
					
					System.out.println("going through"+i+"_"+j);
				}
				else if (buttons[i][j].getText()==""){///Gets the candidates List for other Group2 -Square Wise
					System.out.println("Found a match Square Wise"+i+"_"+j);
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					if(Decision_Function_Naked(Blank_Cell._NakedPairs(),Temp)){
						return Temp;
					
					}

				}
			}
		}
		//Forms the candidate list for the current Blank
			
		return Temp_1;
		
		
	}
	private ArrayList<Integer> Check_Col_Naked(int x, int y){
		
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		int j;
		int i;
		ArrayList<Integer>Temp;
		
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_X= new Blank_Button(Row,Column,Square);
		Temp=Blank_Cell_X._NakedPairs();
		ArrayList<Integer>Temp_1= new ArrayList<Integer>(9);
		Temp_1.add(0);
		for(i=0; i<9;++i){
			if (i==x){
				System.out.println("going through");
				
			}
			else if (buttons[i][y].getText()==""){
				Row=GetRow(i);
				Column=GetColumn(y);
				Square=GetSquare(i,y);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				if(Decision_Function_Naked(Blank_Cell._NakedPairs(),Temp)){
					return Temp;
					
				}
			}
			
		}
		
			
		return Temp_1 ;
	}
	private ArrayList<Integer> Check_Row_Naked(int x, int y){
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		int j;
		int i;
		ArrayList<Integer>Temp;
		
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_X= new Blank_Button(Row,Column,Square);
		Temp=Blank_Cell_X._NakedPairs();	
		ArrayList<Integer>Temp_1= new ArrayList<Integer>(9);
		Temp_1.add(0);
		for(j=0; j<9;++j){
			if (j==y){
				System.out.println("going through");
				
			}
			else if (buttons[x][j].getText()==""){
				Row=GetRow(x);
				Column=GetColumn(j);
				Square=GetSquare(x,j);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				if(Decision_Function_Naked(Blank_Cell._NakedPairs(),Temp)){
					return Temp;
					
				}
				
			}
			
		}
		//Forms the candidate list for the current Blank
		
		return Temp_1; 
		
	}
	private boolean Decision_Function_Naked(ArrayList<Integer> List,ArrayList<Integer> Blank_List){
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		
		//ArrayList<Integer>Temp_1;
			
		Collections.sort(Blank_List);
		Collections.sort(List);
		System.out.println("List of Blank ordered"+Blank_List);
		if (List.size()==Blank_List.size()&& List.equals(Blank_List)){
				System.out.println("Same Candidate List");
				return true;
				
		}	
		
		
		return false;
	}
	public JButton [] [] LockedCand(){
		Pair Temp=getBlankIndex(0,0);
		int x=Temp.getFirst();
		int	y=Temp.getSecond();
		
		int Label;
		
		while((Label=LockedCandHelper(x,y))<0){
			if (x==0 && y==0){
				Temp=getBlankIndex(x,++y);
				
			}
			else{Temp=getBlankIndex(x,y);}
				
			x=Temp.getFirst();
			y=Temp.getSecond();		
			System.out.println("Pair for blank"+Temp.getFirst());
			System.out.println("Pair for blank"+Temp.getSecond());
			
			if (Temp.getFirst()>=9){
				return buttons;
				}
			
			System.out.println("bottom of the while");	
				
		}	
			
		
		System.out.println("Out of Main Function");
		return buttons;
		
	}
	private int LockedCandHelper(int x, int y){
		ArrayList<ArrayList<Integer>> List=new ArrayList<ArrayList<Integer>>(9);
		ArrayList<ArrayList<Integer>> Group1=new ArrayList<ArrayList<Integer>>(9);
		ArrayList<ArrayList<Integer>> Group2 =new ArrayList<ArrayList<Integer>>(9);
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		
		int j;
		int i;
		int Start_x=((x/3)*3);
		int End_x=((x/3)*3)+2;
		int Start_y=((y/3)*3);
		int End_y=((y/3)*3)+2;
		System.out.println("Pair for blank"+x+"_"+y);
		for (j=Start_y;j<=End_y;++j){///Getting the Intersection Cells Candidate List//Row -Wise
			if (buttons[x][j].getText()==""&& j!=y){//ignores blank cell
				System.out.println("Found a match for intersecion"+x+"_"+j);
				Row=GetRow(x);
				Column=GetColumn(j);
				Square=GetSquare(x,j);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._LockedCand());
					
			}
		}
		for (j=0;j<=8;++j){
			if (buttons[x][j].getText()==""&& (j<Start_y || j> End_y)){///Gets the candidates list for one of the Groups- Row Wise
				System.out.println("Found a match Row Wise"+x+"_"+j);
				Row=GetRow(x);
				Column=GetColumn(j);
				Square=GetSquare(x,j);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				Group1.add(Blank_Cell._LockedCand());
			}
		}									
		for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if (buttons[i][j].getText()==""&& i!=x){///Gets the candidates List for other Group2 -Square Wise
					System.out.println("Found a match Square Wise"+i+"_"+j);
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					Group2.add(Blank_Cell._LockedCand());
				}
			}
		}
		
		///Finally adds the actual cnadidate list for the blank cell currently in question. 
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_X= new Blank_Button(Row,Column,Square);
		List.add(Blank_Cell_X._LockedCand());
		for (i=0; i<List.size();++i){
			List.get(i);
			System.out.println("LIst of Intersection"+List.get(i));
		}
		for (i=0; i<Group1.size();++i){
			Group1.get(i);
			System.out.println("LIst of Group 1 Row Wise"+Group1.get(i));
		}
		for (i=0; i<Group2.size();++i){
			Group2.get(i);
			System.out.println("LIst of Group 2 Box wise"+Group2.get(i));
		}
		
		Pair Y=Decision_Function_Locked(List,Group1,Group2);
		System.out.println("Decision_"+"Candidate to be removed if any"+Y.getFirst()+"_"+Y.getSecond());
		if(Y.getFirst()>0 && Y.getSecond()==1 ){//take away candidate - the first value- from group 1
			for (j=0;j<=8;++j){
				if (buttons[x][j].getText()==""&& (j<Start_y || j> End_y)){///Gets the candidates list for one of the Groups- Row Wise
					System.out.println("Taking away candidate in Group1 Row"+x+"_"+j);
					Row=GetRow(x);
					Column=GetColumn(j);
					Square=GetSquare(x,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					int P=Blank_Cell._LockedCand_1(Y.getFirst());
					if(P>0){
						buttons[x][j].setText(Integer.toString(P));
						return 1;
					}
				}
			}						
			
		}
		if(Y.getFirst()>0 && Y.getSecond()==2){//take away candidate from the group 2 
			for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if (buttons[i][j].getText()==""&& i!=x){///Gets the candidates List for other Group2 -Square Wise
					System.out.println("Taking away candidate in Group2 Sqaure"+i+"_"+j);
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					int P=Blank_Cell._LockedCand_1(Y.getFirst());
					if(P>0){
						buttons[i][j].setText(Integer.toString(P));
						return 1;
						
					}
				}
			}
		}
			
		}
		List.clear();
		Group1.clear();
		Group2.clear();
		for (i=Start_x;i<=End_x;++i){///Getting the Intersection Cells Candidate List//Column -Wise
			if (buttons[i][y].getText()==""&& i!=x){//ignores blank cell
				
				Row=GetRow(i);
				Column=GetColumn(y);
				Square=GetSquare(i,y);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._LockedCand());
					
			}
		}
		
		for (i=0;i<=8;++i){
			if (buttons[i][y].getText()==""&& (i<Start_x || i> End_x)){///Gets the candidates list for one of the Groups- Column Wise
				
				Row=GetRow(i);
				Column=GetColumn(y);
				Square=GetSquare(i,y);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				Group1.add(Blank_Cell._LockedCand());
			}
		}									
		
		for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if (buttons[i][j].getText()==""&& j!=y){///Gets the candidates List for other Group2 -Square Wise
					System.out.println("Found a match Square Wise"+i+"_"+j);
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					Group2.add(Blank_Cell._LockedCand());
				}
			}
		}
		///Actually adds the candidate list for the blank cell currently in question
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_Y= new Blank_Button(Row,Column,Square);
		List.add(Blank_Cell_Y._LockedCand());
		for (i=0; i<List.size();++i){
			List.get(i);
			System.out.println("LIst of Intersection-column"+List.get(i));
		}
		for (i=0; i<Group1.size();++i){
			Group1.get(i);
			System.out.println("LIst of Group 1-a column Wise"+Group1.get(i));
		}
		for (i=0; i<Group2.size();++i){
			Group2.get(i);
			System.out.println("LIst of Group 2-a Box wise"+Group2.get(i));
		}
		Y=Decision_Function_Locked(List,Group1,Group2);
		System.out.println("Decision_"+"Candidate to be removed if any"+Y.getFirst()+"_"+Y.getSecond());
		if(Y.getFirst()>0 && Y.getSecond()==1){//Removes Candidates from group 1
			for (i=0;i<=8;++i){
				if (buttons[i][y].getText()==""&& (i<Start_x || i> End_x)){///Gets the candidates list for one of the Groups- Column Wise, sees if it can resolve
					System.out.println("Taking away candidate in Group1 Column"+i+"_"+y);
					Row=GetRow(i);
					Column=GetColumn(y);
					Square=GetSquare(i,y);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					int P=Blank_Cell._LockedCand_1(Y.getFirst());
					if(P>0){
						buttons[i][y].setText(Integer.toString(P));
						return 1;
					}
			}
		}
				
		}
		if(Y.getFirst()>0 && Y.getSecond()==2){//Removes from Group 2, and see if it can resolve
			for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if (buttons[i][j].getText()==""&& j!=y){///Gets the candidates List for other Group2 -Square Wise
					System.out.println("Taking away candidate in Group2 Square"+i+"_"+j);
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					int P=Blank_Cell._LockedCand_1(Y.getFirst());
					if(P>0){
						buttons[i][j].setText(Integer.toString(P)) ;
						return 1;
					}
				}
			}
		}
		
		}
		
		
		return -1;
	}
	ArrayList<Integer> Turn_ArrayList_List(ArrayList<ArrayList<Integer>> List){
		int i=0;
		int j=0;
		ArrayList<Integer> Temp;
		ArrayList<Integer> Final=new ArrayList<Integer> (100);
		for (i=0;i<List.size();++i){
			Temp=(List.get(i));
			for (j=0;j<Temp.size();++j){
				
				Final.add(Temp.get(j));
			}
		}
		return Final;
		
	}
	private Pair Decision_Function_Locked(ArrayList<ArrayList<Integer>> List_Intersecion,ArrayList<ArrayList<Integer>> Group1,ArrayList<ArrayList<Integer>> Group2){
		Pair pair_Null=new Pair(0,0) ;
		if (Group1.size()==0 || Group2.size()==0 || List_Intersecion.size()==0){
			return pair_Null;

		}		
		ArrayList<Integer> Final_List_Intersection=new ArrayList<Integer> (100);
		ArrayList<Integer> Final_Group1=new ArrayList<Integer> (100);
		ArrayList<Integer> Final_Group2=new ArrayList<Integer> (100);
		
		Final_List_Intersection=Turn_ArrayList_List(List_Intersecion);	
		Final_Group1=Turn_ArrayList_List(Group1);
		Final_Group2=Turn_ArrayList_List(Group2);
	
		for (int i=0;i<Final_List_Intersection.size();++i){
			if( Duplicate(Final_List_Intersection.get(i),Final_List_Intersection) && _Exists(Final_List_Intersection.get(i),Final_Group1) && !_Exists(Final_List_Intersection.get(i),Final_Group2)){
				Pair _pair=new Pair(Final_List_Intersection.get(i),1) ;
				return _pair;
				
				
			}
			if (Duplicate(Final_List_Intersection.get(i),Final_List_Intersection) && _Exists(Final_List_Intersection.get(i),Final_Group2) && !_Exists(Final_List_Intersection.get(i),Final_Group1)){
				Pair pair=new Pair(Final_List_Intersection.get(i),2) ;
				return pair;
				
			}
				
			
		}
		return pair_Null;
	}
	private boolean Duplicate(int x,ArrayList<Integer> List){
		int count=0;
		for (int i=0;i<List.size();++i){
			if (x==List.get(i)){
				
				++count;
			}
			if (count==2){
				
				return true;
			}
			
			
		}
		return false;
		
		
		
	}
	private boolean _Exists(int x, ArrayList<Integer> Group){
		for (int i=0;i<Group.size();++i){
			if (x==Group.get(i)){
				
				return true;
			}
			
			
		}
		return false;
		
	}
	public JButton [] [] HiddenAlgo(){
		Pair Temp=getBlankIndex(0,0);
		int x=Temp.getFirst();
		int	y=Temp.getSecond();
		
		int Label;
		
		while((Label=HiddenAlgoHelper(Temp.getFirst(),Temp.getSecond()))<0){
			if (x==0 && y==0){
				Temp=getBlankIndex(x,++y);
				
			}
			else{Temp=getBlankIndex(x,y);}
				
			x=Temp.getFirst();
			y=Temp.getSecond();		
			System.out.println("Pair for blank"+Temp.getFirst());
			System.out.println("Pair for blank"+Temp.getSecond());
			
			if (Temp.getFirst()>=9){
				return buttons;
				}
			
			System.out.println("bottom of the while");	
				
		}
		buttons[x][y].setText(Integer.toString(Label));
		solved_hidden=1;
		return buttons;
		
				
	}
	
	private int HiddenAlgoHelper(int x, int y){
		ArrayList<ArrayList<Integer>> List=new ArrayList<ArrayList<Integer>>(9);
		JButton [] Row;
		JButton [] Column;
		JButton [] Square;
		//Run through the row//
		int j;
		
		System.out.println("Pair for blank"+x+"_"+y);
		for (j=y+1;j<9;++j){
			if (buttons[x][j].getText()==""){
				Row=GetRow(x);
				Column=GetColumn(j);
				Square=GetSquare(x,j);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._HiddenAlgo());//Gets the candidate list, adds it to the overall List
				
				}
				
			}
		System.out.println("Now going backwatds-Row");	
		for (j=y-1;j>=0;--j){
			if (buttons[x][j].getText()==""){
				Row=GetRow(x);
				Column=GetColumn(j);
				Square=GetSquare(x,j);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._HiddenAlgo());
			}
		}
		///Run through the col
		int i;
		System.out.println("Pair for blank-Col"+x+"_"+y);
		for (i=x+1;i<9;++i){
			if (buttons[i][y].getText()==""){
				Row=GetRow(i);
				Column=GetColumn(y);
				Square=GetSquare(i,y);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._HiddenAlgo());
			}
			
		}
		System.out.println("Now going backwatds-Col");
		for (i=x-1;i>=0;--i){
			if (buttons[i][y].getText()==""){
				Row=GetRow(i);
				Column=GetColumn(y);
				Square=GetSquare(i,y);
				Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
				List.add(Blank_Cell._HiddenAlgo());
				
			}
			
		}
		///Run through square
		System.out.println("Pair for blank-Sqaure"+x+"_"+y);
		int Start_x=((x/3)*3);
		int End_x=((x/3)*3)+2;
			
		int Start_y=((y/3)*3);
		int End_y=((y/3)*3)+2;
		for (i=Start_x;i<=End_x;++i){
			for (j=Start_y;j<=End_y;++j){
				if (buttons[i][j].getText()=="" && i!=x && j!=y){
					Row=GetRow(i);
					Column=GetColumn(j);
					Square=GetSquare(i,j);
					Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
					List.add(Blank_Cell._HiddenAlgo());
					
				}
				
			}
			
		}
		///Finally adds the actual cnadidate list for the blank cell currently in question. 
		Row=GetRow(x);
		Column=GetColumn(y);
		Square=GetSquare(x,y);
		Blank_Button Blank_Cell_1= new Blank_Button(Row,Column,Square);
		List.add(Blank_Cell_1._HiddenAlgo());
		
		return _Candidate_List(List);

		
		
	}
	public int _Candidate_List(ArrayList<ArrayList<Integer>> L){//Decides if the blank cell can be resolved if there is a value in its candidate
		//that doesn't appear in any other group candidate lists.///Hidden Value Algorithm
		ArrayList<Integer> Sub;
		ArrayList<Integer> Temp;
		int counter=0;
		Temp=L.get(L.size()-1); ///gets the candidate list for the actual blank cell currently in question
		for (int i=0;i<Temp.size();++i){
			for (int j=0;j<L.size()-1;++j){
				if (L.get(j).contains(Temp.get(i))){
					System.out.println("found it somewhere else"+Temp.get(i));
					counter=0;
					break;
				
				}
				
				if (!L.get(j).contains(Temp.get(i))){
					++counter;
				}
				if (counter ==L.size()-1){
					System.out.println("only exists in blank candidate list solved!"+Temp.get(i));
					return Temp.get(i);
					
				}
			}
		}
		
		for (int i=0; i<L.size()-1;++i){
			Sub=L.get(i);
			System.out.println("Sub LIst"+Sub);
		}
		return -1;
	}
	public JButton [] [] SingleAlgo(){
		int x=0;
		int y=0;
		Pair Temp=getBlankIndex(x,y);
		System.out.println("Pair for blank"+Temp.getFirst());
		System.out.println("Pair for blank"+Temp.getSecond());
		x=Temp.getFirst();
		y=Temp.getSecond();
		JButton [] Row=GetRow(x);
		JButton [] Column=GetColumn(y);
		JButton [] Square=GetSquare(x,y);
		Blank_Button Blank_Cell= new Blank_Button(Row,Column,Square);
		
		while(Blank_Cell._SingleAlgo()<0){
			if (x==0 && y==0){
				Temp=getBlankIndex(x,++y);
				
			}
			else{Temp=getBlankIndex(x,y);}
			
			
			System.out.println("Pair for blank"+Temp.getFirst());
			System.out.println("Pair for blank"+Temp.getSecond());
			
			if (Temp.getFirst()>=9){
				return buttons;
				}
			x=Temp.getFirst();
			y=Temp.getSecond();
			Row=GetRow(x);
			Column=GetColumn(y);
			Square=GetSquare(x,y);
			Blank_Cell= new Blank_Button(Row,Column,Square);
			System.out.println("bottom of the while");
			}
		
		buttons[x][y].setText(Integer.toString(Blank_Cell._SingleAlgo()));
		solved=1;
		return buttons;
	}
	public JButton [] GetRow(int x){
		
		return buttons[x];
		
	}
	public JButton [] GetColumn(int y){
		JButton [] MyColumn=new JButton [9] ;
		for (int i=0;i<9;++i){
			
			MyColumn[i]=buttons[i][y];
		}
		return MyColumn;
		
	}
	public JButton [] GetSquare (int x,int y){
		int Start_x, End_x,Start_y,End_y;
		int count=0;
		JButton [] MySquare=new JButton [9];
	
		Start_x=((x/3)*3);
		End_x=((x/3)*3)+2;
			
		Start_y=((y/3)*3);
		End_y=((y/3)*3)+2;
		for (int i=Start_x;i<=End_x;++i){
			for (int j=Start_y;j<=End_y;++j){ 
				MySquare[count]=buttons[i][j];
				++count;
			}
		}
		return MySquare;
		 
	}
	public Pair getBlankIndex(int x, int y){//Gets the blank index from the argument.(Array of buttons)
		if (y==8){
			++x;
			y=0;
		}
		else if (x==0 && y==0){
			System.out.println("first time in");
			
		}
		else{
			++y;
		}
		
		int i=0;
		int j=0;
		for (i=x;i<9;++i){
			for (j=y;j<9;++j){
				
				if (buttons[i][j].getText()==""){
					
					Pair pair =new Pair(i,j);
					return pair;
				}
				
		}
		y=0;
	}

	Pair pair_1 =new Pair(i,j);
	return pair_1;

}
}
class Pair{ 
    private  int First; //first member of pair
    private int Second; //second member of pair

    public Pair(int x, int y) {
		First=x;
		Second=y;
       
    }
	

	
    public int getFirst() {
        return First;
    }

  
	public int getSecond(){
		return Second;
	}
	
	
}
class Blank_Button extends JButton{
	
	private	JButton [] Row;
	private JButton [] Column;
	private JButton [] Square;
	private  ArrayList<Integer> Candidate_List;
	
	public Blank_Button(JButton [] row, JButton [] col, JButton [] square){
		 Row = new JButton[row.length];
		System.arraycopy(row, 0, Row, 0, row.length);
		Column =new JButton [col.length];
		System.arraycopy(col, 0, Column, 0, col.length);
		Square =new JButton [square.length];
		System.arraycopy(square, 0, Square, 0, square.length);
		Candidate_List= new ArrayList<Integer>(9);
		Candidate_List.add(1);
		Candidate_List.add(2);
		Candidate_List.add(3);
		Candidate_List.add(4);
		Candidate_List.add(5);
		Candidate_List.add(6);
		Candidate_List.add(7);
		Candidate_List.add(8);
		Candidate_List.add(9);
		
	}
	public int _SingleAlgo(){
		
		for (int i=0;i<Row.length;++i){
			if (Row[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Row[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			if(Column[i].getText()!=""){
				Candidate_List.remove(new Integer(Integer.parseInt(Column[i].getText())));
				
			}
			else{	
				Candidate_List.remove(new Integer(0));
				
			}
			if (Square[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Square[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			
		}
		
		if (Candidate_List.size()==1){
			return Candidate_List.get(0);
		}
		else{
			return -1;
		}
		
	}
	public ArrayList<Integer> _HiddenAlgo(){
		for (int i=0;i<Row.length;++i){
			if (Row[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Row[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			if(Column[i].getText()!=""){
				Candidate_List.remove(new Integer(Integer.parseInt(Column[i].getText())));
				
			}
			else{	
				Candidate_List.remove(new Integer(0));
				
			}
			if (Square[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Square[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			
		}
		for (int i=0;i<Candidate_List.size();++i){
			System.out.println("Candidate List Left"+Candidate_List.get(i));
			
		}
		return Candidate_List;
	}
	public  ArrayList<Integer> _LockedCand(){
		for (int i=0;i<Row.length;++i){
			if (Row[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Row[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			if(Column[i].getText()!=""){
				Candidate_List.remove(new Integer(Integer.parseInt(Column[i].getText())));
				
			}
			else{	
				Candidate_List.remove(new Integer(0));
				
			}
			if (Square[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Square[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			
		}
		return Candidate_List;
		
	}
	public int _LockedCand_1(int x){
		
		Candidate_List.remove(new Integer(x));
		if (Candidate_List.size()==1){
			return Candidate_List.get(0);
		}
		return -1;
	}
	
	public ArrayList<Integer> _NakedPairs(){
		for (int i=0;i<Row.length;++i){
			if (Row[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Row[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			if(Column[i].getText()!=""){
				Candidate_List.remove(new Integer(Integer.parseInt(Column[i].getText())));
				
			}
			else{	
				Candidate_List.remove(new Integer(0));
				
			}
			if (Square[i].getText()!=""){
				
				Candidate_List.remove(new Integer(Integer.parseInt(Square[i].getText())));
				
			}
			else{
				Candidate_List.remove(new Integer(0));
			}
			
		}
		return Candidate_List;
		}
		
		
	}
	