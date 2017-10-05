// Fig. 13.26: GridLayoutDemo.java
// Demonstrating GridLayout.    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import javax.swing.Timer;
public class GridLayoutDemoV1 extends JFrame implements ActionListener  {
   private JButton[] buttons;

   private  ArrayList<String> names;
   private Container container;
   private GridLayout grid1; 
 private Stack_Class Arrangement;
	private	JLabel label1;
	private JButton back_Button;
	private JButton solve_button;
	private JMenuBar b1;
	private JMenu Menu;
	private JMenuItem Undo;
	private JMenuItem Randomize;
	private JMenuItem back;
	private JMenuItem solve;
	private JMenuItem Help;
	private JMenuItem Help1;
	private JMenuItem Help2;
	private JMenuItem Help3;
	private JMenuItem Help4;
	   // set up GUI
   public GridLayoutDemoV1()
   {
     super( "GridLayout Demo" );
		
	Initialize();//First attempt to make a board that is solvable. Will not return from function until solvable board is made.
		 
	b1  = new JMenuBar();
	Menu=new JMenu("Options");
	Help=new JMenuItem ("Help");
	Help1=new JMenuItem("Help-Undo All");
	Help2=new JMenuItem("Help-Randomize");
	Help3=new JMenuItem("Help-Back");
	Help4=new JMenuItem("Help-Solve");
	Randomize=new JMenuItem("Randomize");
	back = new JMenuItem("Back");
	Undo=new JMenuItem("Undo All");
	solve=new JMenuItem("Solve");
	////////////Action Listeners are initialized for all the Menu options.//////////////////
	//Help action listener///
	Help.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		 JOptionPane.showMessageDialog( null,
            "Click on squares that are adjacent to the blank square in order to swap the squares."+"."+" "+
			"Game is complete once they are in order 1-15, with the blank at the bottom right. "
	

			);

		
      }
    });
	Help1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		 JOptionPane.showMessageDialog( null,
            "Undo All option reverses ALL your moves with animation"+"."+" "+
			"It also decrements your move counter in the upper left corner"
	

			);

		
      }
    });
	Help2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		 JOptionPane.showMessageDialog( null,
            "Randomize option randomizes the squares into a solvable puzzle"+"."+" "+
			"It also initializes your move counter to 0"
	

			);

		
      }
    });
	Help3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		 JOptionPane.showMessageDialog( null,
            "Back option reverses ONE move"+"."+" "+
			"It also decrements your move counter in the upper left by 1"
	

			);

		
      }
    });
	back.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		System.out.println("entered back button");
		JButton [] Temporary=Arrangement.goBack();
		
		Update_Grid(Temporary);

		
      }
    });
	Help4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		 JOptionPane.showMessageDialog( null,
            "Solve option will solve the puzzle for you starting from the current arrangement of squares"+"."+" "+
			"The algorithm is a Bread First Search, implemented by stacks and arrays"
	

			);

		
      }
    });
	//Undo action listener
	Undo.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent ev) {
		System.out.println("entered Undo button");
		ActionListener taskPerformer = new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if (Arrangement.get_size()==1){
				//System.out.println("entered empty");
				 ((Timer)evt.getSource()).stop();}
				 else{
				JButton [] Temporary;
				Temporary=Arrangement.goBack();
				 Update_Grid(Temporary);}
			}
			
		};
		Timer timer=new Timer(1000,taskPerformer);
			timer.start();
			
      }
	 
    });
	////Solve action listener
	solve.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
		System.out.println("entered solve button");
		ActionListener taskPerformer = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
				
		Solve_Class Solve=new Solve_Class(buttons);
		if (Solve==null){JOptionPane.showMessageDialog( null,
            "Puzzle cannot be solved, taking to long!"
			

		);}
		ArrayList<Integer> Temporary;
		Temporary=Solve.Solve();
		for (int i=0;i<Temporary.size();++i){//Solve function returns a list of integers that describe the movement of the blank space
			
			
			if (Math.abs(Temporary.get(i))==4){
				
				SwapVertical(blank_index(buttons),Temporary.get(i));
			}
			else{
				SwapHorin(blank_index(buttons),Temporary.get(i));
			}
			
		}
      };
	  
		
		};
		Timer timer=new Timer(1000,taskPerformer);
		timer.start();
		}
    });
	//Randomize action listener
	Randomize.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
      System.out.println("PRessed Randomize");
	  Initialize();
	 b1.removeAll();
	 b1.add(Menu);
	  b1.add(label1);
	  
      }
    });
		//////////////
	Menu.add(Undo);
	Menu.add(Randomize);
	Menu.add(back);
	Menu.add(solve);
	Menu.add(Help);
	Menu.add(Help1);
	Menu.add(Help2);
	Menu.add(Help3);
	Menu.add(Help4);
	b1.add(Menu);
	b1.add(label1);
	 
	this.setJMenuBar(b1);
	
	Menu.setVisible(true);
    setVisible( true );

   } // end constructor 

  
	// handle button events by swapping squares
   public void actionPerformed( ActionEvent event ) {
     
	int buttonPos = -1;
     JButton temp = (JButton) event.getSource();
	  for ( int count = 0; count < buttons.length; count++ ) {
		 if (count==0){//First Sqaure was clicked
			if (Check_Down(temp,count)){
				 
				SwapVertical(count,4);
				Arrangement.Push_Back(buttons);
				break;
			 }
					
			else if (Check_Right(temp,count)){
				 SwapHorin(count,1);
				 Arrangement.Push_Back(buttons);
				 break;
			 }		 
		 
		 }
		 else if (count==15){//Last Sqaure was clicked
			 if ( Check_Left(temp,count)){
				SwapHorin(count,-1);
				Arrangement.Push_Back(buttons);
				break;
			}
			else if ( Check_Up(temp,count)){
				SwapVertical(count,-4);
				Arrangement.Push_Back(buttons);
				break;
			 
			}
			 
		 }
		 else if (count<=3){//Upper Row was clicked
			 if (Check_Down(temp,count)){
				 
				SwapVertical(count,4);
				Arrangement.Push_Back(buttons);
				break;
			 }
			 else if (Check_Right(temp,count)){
				 SwapHorin(count,1);
				 Arrangement.Push_Back(buttons);
				 break;
			 }
			 else if(Check_Left(temp,count)){
				 SwapHorin(count,-1);
				Arrangement.Push_Back(buttons);
				 break;
				 
			 }
			 
			}
		 else if (count<12){//Middle Rows
			if (Check_Down(temp,count)){
				
				SwapVertical(count,4);
				Arrangement.Push_Back(buttons);
				
				break;
				
			}
			else if (Check_Right(temp,count)){
				SwapVertical(count,1);
				Arrangement.Push_Back(buttons);
				break;
			}
			else if (Check_Left(temp,count)){
				SwapHorin(count,-1);
				Arrangement.Push_Back(buttons);
				break;
			}
			else if (Check_Up(temp,count)){
				SwapVertical(count,-4);
				Arrangement.Push_Back(buttons);
				break;
			}
			 
		 }
		
	  
		else{//Bottom Row was clicked
		
			if (Check_Right(temp,count)){
				SwapHorin(count,1);
			
				Arrangement.Push_Back(buttons);
				break;
			}  
			else if ( Check_Left(temp,count)){
				SwapHorin(count,-1);
				Arrangement.Push_Back(buttons);
				break;
			}
			else if ( Check_Up(temp,count)){
				SwapVertical(count,-4);
				Arrangement.Push_Back(buttons);
				break;
			 
			}
		  
		}
	  }	
	 ///Label that displays count is updated 	
	label1.setText(Integer.toString(Arrangement.get_Moves()+1));
	  					
				
   }
   public void Update_Grid(JButton [] Temporary){
	///Function is used to update grid once 'buttons' array is changed. 
	 buttons = Temporary.clone();
	container.removeAll();
	for (int i=0;i<buttons.length;++i){
		
	container.add(buttons[i]);
	}
	

	label1.setText(Integer.toString(Arrangement.get_Moves()+1));
	getContentPane().validate();
	getContentPane().repaint();
	   
   }
	public int blank_index(JButton [] buttons){////Searches for blank space in the buttons array
		for (int i=0;i<buttons.length;++i){
			if (buttons[i].getText()==""){
			return i;}
		}
		return -1;
	}
	///"Check" Functions below make sure if a swap is possible in a certain direction/////////////////////// 
	
	public boolean Check_Down(JButton temp,int count){
		
		if ( temp.equals(buttons[count] ) && buttons[count+4].getText()=="" ){
			return true;
		}
		else{return false;}	
		
	}
	private boolean Check_Up(JButton temp,int count){
		if (temp.equals(buttons[count] ) && buttons[count-4].getText()==""){
			return true;
		}
		else{return false;}
		
		
	}
	private boolean Check_Left(JButton temp,int count){
		if (temp.equals(buttons[count] ) && buttons[count-1].getText()==""){
		return true;}
		else{return false;}
		
	}
	private boolean Check_Right(JButton temp,int count){
		if (temp.equals(buttons[count] ) && buttons[count+1].getText()==""){
		return true;}
		else{return false;}
		
	}
	public void Solve(JButton [] buttons){///Function that calls the solve function form the solve class. 
		Solve_Class BFS =new Solve_Class(buttons);
		BFS.Solve();
	}
	/////Swap functions that swap in one direction////////////////
	public void	SwapVertical(int count,int Offset)   {
		
		int index=container.getComponentZOrder(buttons[count]);
		int index_2=container.getComponentZOrder(buttons[count+Offset]);
		System.out.println("Uppwer Rows bottom"+index+"index2"+index_2);
			//container.remove(index);
		container.setComponentZOrder(buttons[count],index+Offset);
		container.setComponentZOrder(buttons[count+Offset],index);
			//container.getComponent(index+1);
		JButton Temp=buttons[count];
		buttons[count]=buttons[count+Offset];
		buttons[count+Offset]=Temp;
		getContentPane().validate();
		getContentPane().repaint();
		
		
		}
	public void SwapHorin(int count,int Offset){
		int index=container.getComponentZOrder(buttons[count]);
		 int index_2=container.getComponentZOrder(buttons[count+Offset]);
		 System.out.println("Upper rows right"+index+"index2"+index_2);
		 container.setComponentZOrder(buttons[count],index+Offset);
		 container.setComponentZOrder(buttons[count+Offset],index);
		 JButton Temp=buttons[count];
		buttons[count]=buttons[count+Offset];
		buttons[count+Offset]=Temp;
		 getContentPane().validate();
		 getContentPane().repaint();
		
		
		
	}
	public void Initialize(){
	////////Intialization of  board, it recursively calls itself if board isn't solvable/////////
	names= new ArrayList<String>(14);
	names.add("1");
	names.add("2");
	names.add("3");
	names.add("4");
	names.add("5");
	names.add("6");
	names.add("7");
	names.add("8");
	names.add("9");
	names.add("10");
	names.add("11");
	names.add("12");
	names.add("13");
	names.add("14");
	names.add("15");
      // set up layouts
      grid1 = new GridLayout( 4, 4);
      

      // get content pane and set its layout
      container = getContentPane();
      container.setLayout( grid1 );

      // create and add buttons
      buttons = new JButton[ names.size()+1 ];
	int original=names.size();
      for ( int count = 0; count < original; count++ ) {
		  int randomNum = ThreadLocalRandom.current().nextInt(names.size());
		  
         buttons[ count ] = new JButton( names.get(randomNum)  );
		
         buttons[ count ].addActionListener( this );
        
		 names.remove(randomNum);
		 System.out.println("Size"+names.size());
		 container.add(buttons[count]);
      }
	  

	  buttons[15]=new JButton("");
	  buttons[15].addActionListener(this);
	
	  container.add(buttons[15]);
	while(Inversions(buttons)!=1){
	System.out.println("Did not work...trying again");
	container.removeAll();
	Initialize();
		  
	  }	
	 
	Arrangement =new Stack_Class(buttons);
	System.out.println("Moves refreshed"+Integer.toString(Arrangement.get_Moves()+1));
	label1 = new JLabel (Integer.toString(Arrangement.get_Moves()+1));
	
	Update_Grid(buttons); 
		
	}
	public int Inversions(JButton []buttons){
		/////Inversions functions that calculates total inversions and this if its even/odd
		int total=0;
		for (int i =0;i<buttons.length-1;++i){
			int count=0;
			int temp= Integer.parseInt(buttons[i].getText());
			System.out.println("Integer"+temp);
			for (int j=i+1;j<buttons.length-1;++j){
				
				if (temp<Integer.parseInt(buttons[j].getText())){
					++count;
				}
			}
		total=total+count;
		}
		if (total%2==0){
		return 1;}
		else{return 0;}
		
	}
   
   public static void main( String args[] )
   {
      GridLayoutDemoV1 application = new GridLayoutDemoV1();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   } 

} // end class GridLayoutDemo


