import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;
public class Stack_Class {///Class that keeps track of history of the game
	 
	//private  JButton [] Stack;
	private int Moves;
	ArrayList <JButton []> Stack;
	
	public Stack_Class(JButton[] button){
		Stack=new ArrayList<JButton []>();
		JButton[] Temp =new JButton[button.length];
		Temp=button.clone();
	   Push_Back(Temp);
		
		Moves=-1;
		
	}
	public int get_size(){
		return Stack.size();
		
	}

	public void Push_Back(JButton []button ){
		JButton[] Temp =new JButton[button.length]; 
		Temp=button.clone();
		JButton []Torres;
		
		for(int i=0; i<Stack.size();++i){
			System.out.println("i"+i);
			Torres=Stack.get(i);
			for (int j=0;j<Torres.length;++j){
				System.out.println("Before"+Torres[j].getText());
			}
			System.out.println("Next Stack");
		}
		Stack.add(Temp);
		
		++Moves;
	}
	public void Pop(){
		Stack.remove(0);
		--Moves;
	}
	public int get_Moves(){
		return Moves;
		
	}
	public JButton [] goBack(){///Returns arrangement of the previous move. 
		JButton [] David;
		
		Stack.remove(Stack.size()-1);
		JButton [] Final=Stack.get(Stack.size()-1);

		--Moves;
		for (int i=0;i<Final.length;++i){
		
		System.out.println("Final delete"+Final[i].getText());
	
	}
		return Final;
		
	}
	
}