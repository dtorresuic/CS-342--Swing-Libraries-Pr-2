import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

public class Solve_Class {////This class handles the auto solve mechanism
	private ArrayList<JButton []> Set;
	private ArrayList<Pair> Queue;
	
	public Solve_Class(JButton [] buttons){
		JButton []Current_Arrangement=new JButton[buttons.length];
		Set=new ArrayList<JButton []>();
		Current_Arrangement=buttons.clone();
		Pair pair = new Pair(Current_Arrangement);///"Pair" is a class defined after this Solve_Class
		 Queue= new ArrayList<Pair>();
		Queue.add(pair);
		 Set.add(Current_Arrangement);
	}

	public  ArrayList<Integer> Solve(){//Solve function, calls 'possible_Arrangements' in order to decide which
										///which arrangements can be added to the queue//
		while(Queue.size()!=0){
			if (Check_Solve(Queue.get(0).getFirst())){
				
				return Queue.get(0).getSecond();
				
			}
			System.out.println("index of blank"+getBlankindex(Queue.get(0).getFirst()));
			
			
			possible_Arrangements(Queue.get(0),getBlankindex(Queue.get(0).getFirst()));
			System.out.println("Size of Queue before deQueue"+Queue.size());
			//if (Queue.size()>100){break;}
			
			deQueue();
			System.out.println("Size of Queue after deQueue"+Queue.size());
			if (Queue.size()>1000){///Size of Queue gets too big, lets exit. 
			return null;}
		}
		
		return null;
		
	}
	public void possible_Arrangements(Pair buttons,int blank_index){
		///Function that decides which arrangenments are already in the set, adds to queue accordingly 
		JButton[] Temp =new JButton[buttons.getFirst().length];
		Temp=buttons.getFirst().clone();
		if (CheckUp(blank_index,buttons.getFirst(),-4)){///Checks to see if vertical swap is possible
			System.out.println("Switch Up as possible");
			Temp=SwapVerticalUp(blank_index,Temp);///Swaps vertically and checks if this arrangement is already in the set. 
			if (Check_Set(Temp)==false){
				System.out.println("Not in the set");
					
				Pair pair = new Pair(Temp, buttons.getSecond());
				pair.append_Second(-4);
				Set.add(pair.getFirst());
				Queue.add(pair);
				
			}
			
		}
		JButton[] Temp_1 =new JButton[buttons.getFirst().length];//Same as above now checks/swaps vertically down
		Temp_1=buttons.getFirst().clone();
		 if (CheckDown(blank_index,buttons.getFirst(),4)){
			System.out.println("Switch Down as possible");
			Temp_1=SwapVerticalDown(blank_index,Temp_1);
			if (Check_Set(Temp_1)==false){
				System.out.println("Not in the set");
				
				Pair pair = new Pair(Temp_1, buttons.getSecond());
				pair.append_Second(4);
				Set.add(pair.getFirst());
				Queue.add(pair);
				
				
			}
			
		}
		
		 JButton[] Temp_2 =new JButton[buttons.getFirst().length];//checks/swaps horizontally
		Temp_2=buttons.getFirst().clone();
		 if (CheckRight(blank_index,buttons.getFirst(),1)){
			System.out.println("Switch Right as possible");
			Temp_2=SwapRight(blank_index,Temp_2);
			if (Check_Set(Temp_2)==false){
			System.out.println("Not in the set");
			
			Pair pair = new Pair(Temp_2, buttons.getSecond());
			pair.append_Second(1);
			Set.add(pair.getFirst());
			Queue.add(pair);
			for (int k=0;k<Queue.size();++k){
				for(int i=0;i<Queue.get(k).getFirst().length;++i){
				JButton [] matrix=Queue.get(k).getFirst();
				System.out.println("QUeue right now after swapping right"+ matrix[i].getText());
				
			}
				System.out.println("New Pair");
			}
			}
		}
		 JButton[] Temp_3 =new JButton[buttons.getFirst().length];
			Temp_3=buttons.getFirst().clone();
		 if (CheckLeft(blank_index,buttons.getFirst(),-1)){///checks/swaps horizontally
			System.out.println("Switch Left as possible");
			Temp_3=SwapLeft(blank_index,Temp_3);
			if (Check_Set(Temp_3)==false){
			System.out.println("Not in the set");
			
			Pair pair = new Pair(Temp_3, buttons.getSecond());
			pair.append_Second(-1);
			Set.add(pair.getFirst());
			Queue.add(pair);
			}
			for (int k=0;k<Queue.size();++k){
				for(int i=0;i<Queue.get(k).getFirst().length;++i){
				JButton [] matrix=Queue.get(k).getFirst();
				System.out.println("QUeue right now after swapping left"+ matrix[i].getText());
				
			}
				System.out.println("New Pair");
			}
		}
		
	}
	public boolean Check_Solve(JButton [] buttons){//Checks to see if current arrangement(argument) is solved
		for (int i=0;i<buttons.length-1;++i){
			if (buttons[i].getText()=="" && i!=14){
				return false;
			}
			else if (Integer.parseInt(buttons[i].getText())!=i+1){
				return false;
			
			}
		
		}
		return true;	
	}
	public boolean Check_Set(JButton [] buttons){//Checks to see if argument is already in the set
		
		for (JButton [] i : Set){
			int match=0;
			for (int j=0;j<i.length;++j){
				//System.out.println("comparing"+i[j].getText()+buttons[j].getText());
				if (i[j].getText()!=buttons[j].getText()){
					
					break;}
				else {++match;
				System.out.println("match"+match);
				}
			}
			if (match==16){return true;}
		}
		
		return false;
	}
	//////////Below 'Check" Functions are to check if swaps are possible
	public boolean CheckUp(int index,JButton [] buttons,int offset){
		if (index+offset>=0){
			return true;
		}
		else{return false;}
		
	}
	public boolean CheckRight(int index,JButton [] buttons,int offset){
		if (index+offset<=buttons.length-1){
			return true;
		}
		else{return false;}
	}
	public boolean CheckLeft(int index,JButton [] buttons,int offset){
		if (index+offset>=0){
			return true;
		}
		else{return false;}
	}
	public boolean CheckDown(int index,JButton [] buttons,int offset){
		if (index+offset<=buttons.length-1){
			return true;
		}
		else{return false;}
		
	}
	public void deQueue(){
	
	Queue.remove(0);
	
		
	}	
	public int getBlankindex(JButton [] Arrangement){//Gets the blank index from the argument.(Array of buttons)
		int i=0;
		for (i=0;i<Arrangement.length;++i){
			if (Arrangement[i].getText()==""){
			break;}
		}
		return i;
	}
	///////Below functions are to swap. Argument 'count' is the blank index. 
	public JButton [] SwapVerticalDown(int count,JButton [] buttons)   {
		JButton Temp=buttons[count];
		buttons[count]=buttons[count+4];
		buttons[count+4]=Temp;
		return buttons;
		
		}
	public JButton [] SwapVerticalUp(int count,JButton [] buttons){
		for (int j=0;j<buttons.length;++j){
			
		System.out.println("Swapping UP coming in"+buttons[j].getText());
		
		}
		JButton Temp=buttons[count];
		buttons[count]=buttons[count-4];
		buttons[count-4]=Temp;
		
		return buttons;
	}

	public JButton [] SwapRight(int count,JButton [] buttons){
		JButton Temp=buttons[count];
		buttons[count]=buttons[count+1];
		buttons[count+1]=Temp;
		return buttons;
		
		
	}
	public JButton [] SwapLeft(int count,JButton [] buttons){
		JButton Temp=buttons[count];
		buttons[count]=buttons[count-1];
		buttons[count-1]=Temp;
		return buttons;
	}

}
//Pair class defined////
class Pair{ 
    private JButton[] First; //first member of pair
    private ArrayList<Integer> Second; //second member of pair

    public Pair(JButton[] buttons, ArrayList<Integer> second) {
		First=new JButton[buttons.length];
		Second=new ArrayList<Integer>();
        First = buttons.clone();
		for(int p : second) {
			Second.add(p);
		}
        
    }
	public Pair(JButton [] buttons){
		First=new JButton[buttons.length];
		Second=new ArrayList<Integer>();
        First = buttons.clone();
        
	}

	
    public JButton[] getFirst() {
        return First;
    }

    public ArrayList<Integer> getSecond() {
        return Second;
    }
	public void append_Second(int x){
		Second.add(x);
	}
	
	
}