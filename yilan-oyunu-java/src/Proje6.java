import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;





public class Proje6 extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField field;
	public static int headSize=30;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenHeight=(int) screenSize.getHeight();
	public static int screenWidth=(int) screenSize.getWidth();
	public static int dikey= (int) screenHeight/headSize;
	public static int yatay= (int) screenWidth/headSize;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public String headdirection;
	public static int length=2;
	public static int appleX;
	public static int appleY;
	public static ArrayList <Part> snakeParts;
	public KeyEvent e;
	public Timer timer;
	public Random random;
	public boolean gameOver=false;
	public boolean uzama=false;
	public int point=0;
	public Part apple;
	public int speed=100;
	public JLabel label,lab,labe;
	public JButton button,butn,btn1,btn2;
	public boolean timerStart;
	public static Proje6 frame;
	public boolean paused=false;
	Scanner scanner= new Scanner(System.in);
	public JLayeredPane lp1;
	
	
	
	
	public void formHead() 
	{
		snakeParts.add(new Part());
		snakeParts.set(0,new Part());
		snakeParts.get(0).setFocusable(false);
		snakeParts.get(0).setBackground(Color.black);
		snakeParts.get(0).setBorder(null);
		snakeParts.get(0).setPartX(120);
		snakeParts.get(0).setPartY(120);
		snakeParts.get(0).setPartYon("A");
		snakeParts.get(0).setBounds(120, 120, headSize,headSize);
		contentPane.add(snakeParts.get(0));
	}
	
	public void passWalls() 
	{
		if(snakeParts.get(0).getLocation().getX()<-headSize) {snakeParts.get(0).setLocation(yatay*headSize,snakeParts.get(0).getY());}
		else if(snakeParts.get(0).getLocation().getX()>screenWidth-headSize) {snakeParts.get(0).setLocation(0,snakeParts.get(0).getY());}
		else if(snakeParts.get(0).getLocation().getY()<-headSize) {snakeParts.get(0).setLocation(snakeParts.get(0).getX(),dikey*headSize);}
		else if(snakeParts.get(0).getLocation().getY()>screenHeight-headSize) {snakeParts.get(0).setLocation(snakeParts.get(0).getX(),0);}
		else {}
	}
	
	public void determineHeadDirection(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_W && !snakeParts.get(0).getPartYon().equals("DOWN")) 
		{
			snakeParts.get(0).setPartYon("UP");
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_S && !snakeParts.get(0).getPartYon().equals("UP")) 
		{
			snakeParts.get(0).setPartYon("DOWN");
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_A && !snakeParts.get(0).getPartYon().equals("RIGHT")) 
		{
			snakeParts.get(0).setPartYon("LEFT");
		}
			
		else if(e.getKeyCode() == KeyEvent.VK_D && !snakeParts.get(0).getPartYon().equals("LEFT")) 
		{
			snakeParts.get(0).setPartYon("RIGHT");
		}
		else {}
	}
	
	public void moveHead() 
	{
//		if(e.getKeyCode() == KeyEvent.VK_W && snakeParts.get(0).getPartYon().equals("UP")) 
//		{
//			snakeParts.get(0).setLocation(snakeParts.get(0).getX(),snakeParts.get(0).getY()-headSize);
//				
//		}
//		else if(e.getKeyCode() == KeyEvent.VK_S && snakeParts.get(0).getPartYon().equals("DOWN")) 
//		{
//			snakeParts.get(0).setLocation(snakeParts.get(0).getX(),snakeParts.get(0).getY()+headSize);	
//		}
//		else if(e.getKeyCode() == KeyEvent.VK_A && snakeParts.get(0).getPartYon().equals("LEFT")) 
//		{
//			snakeParts.get(0).setLocation(snakeParts.get(0).getX()-headSize,snakeParts.get(0).getY());	
//		}
//		else if(e.getKeyCode() == KeyEvent.VK_D && snakeParts.get(0).getPartYon().equals("RIGHT")) 
//		{
//			snakeParts.get(0).setLocation(snakeParts.get(0).getX()+headSize,snakeParts.get(0).getY());	
//		}
//
//		else {}
		
		if(snakeParts.get(0).getPartYon().equals("UP")) 
		{
			snakeParts.get(0).setLocation(snakeParts.get(0).getX(),(int) (snakeParts.get(0).getY()-headSize));
				
		}
		else if(snakeParts.get(0).getPartYon().equals("DOWN")) 
		{
			snakeParts.get(0).setLocation(snakeParts.get(0).getX(),(int) (snakeParts.get(0).getY()+headSize));	
		}
		else if(snakeParts.get(0).getPartYon().equals("LEFT")) 
		{
			snakeParts.get(0).setLocation((int) (snakeParts.get(0).getX()-headSize),snakeParts.get(0).getY());	
		}
		else if(snakeParts.get(0).getPartYon().equals("RIGHT")) 
		{
			snakeParts.get(0).setLocation((int) (snakeParts.get(0).getX()+headSize),snakeParts.get(0).getY());	
		}

		else {}
	}
	


	public void putApple()
	{	
		
		random=new Random();
		appleX=random.nextInt(yatay)*headSize;
		appleY=random.nextInt(dikey)*headSize;
		apple=new Part();
		apple.setFocusable(false);
		apple.setBackground(Color.red);
		apple.setBorder(null);
	    apple.setSize(headSize,headSize);
		apple.setLocation(appleX,appleY);
		for(int i=0;i<length-1;i++)
		{
			if((snakeParts.get(i).getLocation().getX()>= apple.getLocation().getX()-headSize 
					&& snakeParts.get(i).getLocation().getX()<= apple.getLocation().getX()+headSize)
					&& (snakeParts.get(i).getLocation().getY()<=apple.getLocation().getY()+headSize 
				    && snakeParts.get(i).getLocation().getY()>=apple.getLocation().getY()-headSize))
			{
				remove(apple);
				putApple();
			}
			else if (snakeParts.get(i).getLocation()!=apple.getLocation())
			{
					
				     contentPane.add(apple);
			}
		}
		
	}
	
	public void appleControl()
	{
		
		if((snakeParts.get(0).getLocation().getX()> apple.getLocation().getX()-headSize 
				&& snakeParts.get(0).getLocation().getX()< apple.getLocation().getX()+headSize)
				&& (snakeParts.get(0).getLocation().getY()<apple.getLocation().getY()+headSize 
			    && snakeParts.get(0).getLocation().getY()>apple.getLocation().getY()-headSize)) 
		{
			contentPane.remove(apple);
			point++;
			length++;
			if(speed>2) {speed-=2;}
			timer.setDelay(speed);
			putApple();
			//repaint();
			
		}
		
//		if((snakeParts.get(0).getLocation().getX()== apple.getLocation().getX()
//			    && snakeParts.get(0).getLocation().getY()==apple.getLocation().getY())) 
//		{
//			contentPane.remove(apple);
//			point++;
//			length++;
//			if(speed>2) {speed-=2;}
//			timer.setDelay(speed);
//			putApple();
//			//repaint();
//			
//		}

	}
	
	public void formParts() 
	{
		
		
		for(int i=snakeParts.size(); i<length;i++) 
		{
			
			snakeParts.add(new Part());
			snakeParts.get(i).setFocusable(false);
			snakeParts.get(i).setBackground(Color.black);
			snakeParts.get(i).setBorder(null);
			//uzama=true;
		}

		for(int i=length-1; i>0 ;i--) 
		{
			snakeParts.get(i).setPartYon(snakeParts.get(i-1).getPartYon());
			
			if(snakeParts.get(0).getPartYon().equals("UP") && !snakeParts.get(0).getPartYon().equals("DOWN")) 
			{
				snakeParts.get(i).setSize(headSize,headSize);
				snakeParts.get(i).setLocation(snakeParts.get(i-1).getX(),snakeParts.get(i-1).getY());

			}
			else if(snakeParts.get(0).getPartYon().equals("DOWN") && !snakeParts.get(0).getPartYon().equals("UP")) 
			{
				snakeParts.get(i).setSize(headSize,headSize);
				snakeParts.get(i).setLocation(snakeParts.get(i-1).getX(),snakeParts.get(i-1).getY());

			}
			else if(snakeParts.get(0).getPartYon().equals("LEFT") && !snakeParts.get(0).getPartYon().equals("RIGHT")) 
			{
				snakeParts.get(i).setSize(headSize,headSize);
				snakeParts.get(i).setLocation(snakeParts.get(i-1).getX(),snakeParts.get(i-1).getY());

			}
				
			else if(snakeParts.get(0).getPartYon().equals("RIGHT") && !snakeParts.get(0).getPartYon().equals("LEFT")) 
			{
				snakeParts.get(i).setSize(headSize,headSize);
				snakeParts.get(i).setLocation(snakeParts.get(i-1).getX(),snakeParts.get(i-1).getY());

			}
			else {}
		}
//		if(uzama==true)
//		{
			contentPane.add(snakeParts.get(snakeParts.size()-1));
//			uzama=false;
//		}
		

	}
	
	public void eatItselfControl()
	{
		for(int i=3; i<length;i++)
		{
			if(snakeParts.get(0).getLocation().getX() == snakeParts.get(i).getLocation().getX() && snakeParts.get(0).getLocation().getY()==snakeParts.get(i).getLocation().getY())
		    {
				
				gameOver=true;
				
			}
		}
	 }
	
	public void pauseGame() 
	{
		
		
		lab.setVisible(true);
		
		
		
		
		
		timer.stop();
		//paused=true;
	}
	
	public void continueGame() 
	{
		lab.setVisible(false);
		timer.start();
		//paused=false;
	}
	
	public void showPoint() 
	{
		labe.setVisible(true);
		labe.setText(point+"");
	}
	public void hidePoint() 
	{
		labe.setVisible(false);
	}
	
	ActionListener al1=new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			
			contentPane.remove(btn1);
			contentPane.remove(btn2);
			requestFocusInWindow();
			//Cursor gizleme kodu başı
			frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0), "null"));
			//Cursor gizleme kodu sonu
			formHead();
			putApple();
			timer.start();
			
		}
	};
	
	ActionListener al2=new ActionListener() 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
			
		}
	};
	
	FocusListener fl1=new FocusListener() 
	{

		
			@Override
			public void focusGained(FocusEvent e) {
				btn1.setBackground(Color.white);
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				btn1.setBackground(Color.lightGray);
				
			}
			
	
		
	};
	
	FocusListener fl2=new FocusListener() 
	{

		
			@Override
			public void focusGained(FocusEvent e) {
				btn2.setBackground(Color.white);
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				btn2.setBackground(Color.lightGray);
				
			}
			
	
		
	};
	
	KeyListener kl1=new KeyListener() 
	{

		@Override
		public void keyTyped(KeyEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER) 
            {
					
				contentPane.remove(btn1);
				contentPane.remove(btn2);
				requestFocusInWindow();
				//Cursor gizleme kodu başı
				frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0), "null"));
				//Cursor gizleme kodu sonu
				formHead();
				putApple();
				timer.start();
			
            	
            }
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
		
	};
	
	KeyListener kl2=new KeyListener() 
	{

		@Override
		public void keyTyped(KeyEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER) 
            {
					
				System.exit(0);
			
            	
            }
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
		
	};
	
	MouseListener ml1=new MouseListener() 
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			if(e.getButton()==1) 
			{

				contentPane.remove(btn1);
				contentPane.remove(btn2);
				requestFocusInWindow();
				//Cursor gizleme kodu başı
				frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0), "null"));
				//Cursor gizleme kodu sonu
				formHead();
				putApple();
				timer.start();
				
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e)
        {
	    btn1.setBackground(Color.white);
	    getRootPane().setDefaultButton(butn);
	    btn1.requestFocus();
		if(btn2.getBackground().equals(Color.white)) btn2.setBackground(Color.lightGray);

         }
         public void mouseExited(MouseEvent e)
         {
         btn1.setBackground(Color.lightGray);
         getRootPane().setDefaultButton(null);

         }
		
	};
	
	MouseListener ml2=new MouseListener() 
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			if(e.getButton()==1) 
			{

				System.exit(0);
				
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e)
        {
	    btn2.setBackground(Color.white);
	    getRootPane().setDefaultButton(butn);
	    btn2.requestFocus();
		if(btn1.getBackground().equals(Color.white)) btn1.setBackground(Color.lightGray);

         }
         public void mouseExited(MouseEvent e)
         {
         btn2.setBackground(Color.lightGray);
         getRootPane().setDefaultButton(null);

         }
		
	};
	
	KeyListener kl=new KeyListener() 
	{

		@Override
		public void keyTyped(KeyEvent e) 
		{
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			determineHeadDirection(e);
			if (e.getKeyCode() == KeyEvent.VK_SPACE && paused==false) 
            {	
					//pauseGame();
					paused=true;

            }
			
			else if (e.getKeyCode() == KeyEvent.VK_SPACE && paused==true) 
            {
				continueGame();
				paused=false;
					
            }
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			if (e.getKeyCode() == KeyEvent.VK_SPACE && paused==false) 
            {	
	
            }
			else if (e.getKeyCode() == KeyEvent.VK_SPACE && paused==true) 
            {
	
            }
		}
		
	};
	
	
	
	
	
	
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Proje6();
					frame.setVisible(true);
					
					
					
					
					
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public Proje6() throws InterruptedException {
		//dekorasyonları kaldırmadan tam ekran yapma kodu
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//dekorasyonları kaldırmadan tam ekran yapma kodu sonu
		//dekorasyonları kaldırma kodu(özellikle tam ekranda kullanılmak için)
		this.setUndecorated(true);
		//dekorasyonları kaldırma kodu sonu (özellikle tam ekranda kullanılmak için)
		this.addKeyListener(kl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		timer=new Timer(speed,this);
		snakeParts = new ArrayList <Part> ();
		
		lab = new JLabel();
		lab.setFocusable(false);
		lab.setFont(new Font("Serif",Font.BOLD,40));
		lab.setForeground(Color.blue);
		lab.setText("PAUSED");
		lab.setBounds(screenWidth/2-100,screenHeight/2-150,800,200);
		contentPane.add(lab);
		lab.setVisible(false);
		
		labe = new JLabel();
		labe.setFocusable(false);
		labe.setFont(new Font("Serif",Font.BOLD,40));
		labe.setForeground(Color.blue);
		labe.setText(point+"");
		labe.setSize(70, 50);
		labe.setLocation(screenWidth-80,20);
		contentPane.add(labe);
		labe.setVisible(false);
		
		
		
		btn1 = new JButton("YENİ OYUN");
		btn1.setSize(200,50);
		btn1.setLocation(screenWidth/2-100,screenHeight/2-80);
		//focus sırasında noktalı görünümü yok ederek focusun belli olmamasını sağlayan kod
		btn1.setFocusPainted(false);
		btn1.setBackground(Color.lightGray);
		btn1.addFocusListener(fl1);
		//hover effectini kaldıran kod
		btn1.setBorderPainted(false);
		contentPane.add(btn1);
		//btn1.addActionListener(al1);
		btn1.addKeyListener(kl1);
		btn1.addMouseListener(ml1);
		
		
		btn2 = new JButton("ÇIKIŞ");
		btn2.setSize(200,50);
		btn2.setLocation(screenWidth/2-100,screenHeight/2);
		//focus sırasında noktalı görünümü yok ederek focusun belli olmamasını sağlayan kod
		btn2.setFocusPainted(false);
		btn2.setBackground(Color.lightGray);
		btn2.addFocusListener(fl2);
		//hover effectini kaldıran kod
		btn2.setBorderPainted(false);
		contentPane.add(btn2);
		//btn2.addActionListener(al2);
		btn2.addKeyListener(kl2);
		btn2.addMouseListener(ml2);
			
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		if(gameOver==false) 
		{
			
			
			passWalls();
			moveHead();
			appleControl();
			formParts();
			eatItselfControl();
			showPoint();
			repaint();
			
			if(paused==true) 
			{
				pauseGame();
				
			}
			
			
			
		}
			
		
			
			if(gameOver==true) 
			{
				timer.stop();
				hidePoint();
				contentPane.remove(apple);
				for(int i=snakeParts.size()-1;i>=0;i--) 
				{
					contentPane.remove(snakeParts.get(i));
					
				}
				for(int i=snakeParts.size()-1;i>=0;i--) 
				{
					snakeParts.remove(i);
					
				}
				
				//cursor geri getirme kodu
				contentPane.setCursor(Cursor.getDefaultCursor());
				//cursor geri getirme kodu sonu
				
				label = new JLabel();
				label.setFocusable(false);
				label.setOpaque(true);
				label.setBackground(Color.black);
				label.setFont(new Font("Serif",Font.BOLD,30));
				label.setForeground(Color.red);
				label.setText("         OYUN BİTTİ PUANINIZ:    " +point+" PUANDIR  ");
				label.setBounds(screenWidth/2-400,screenHeight/2-150,800,200);
				label.setVisible(true);
				contentPane.add(label);
				
				FocusListener focused = new FocusListener() 
				{

					@Override
					public void focusGained(FocusEvent e) {
						butn.setBackground(Color.white);
						
					}

					@Override
					public void focusLost(FocusEvent e) {
						butn.setBackground(Color.lightGray);
						
					}
					
				};
				
				FocusListener focus = new FocusListener() 
				{

					@Override
					public void focusGained(FocusEvent e) {
						button.setBackground(Color.white);
						
					}

					@Override
					public void focusLost(FocusEvent e) {
						button.setBackground(Color.lightGray);
						
					}
					
				};
				
				butn = new JButton("YENİDEN OYNA");
				butn.setSize(200,50);
				butn.setLocation(screenWidth/2-100,screenHeight/2+100);
				//focus sırasında noktalı görünümü yok ederek focusun belli olmamasını sağlayan kod
				butn.setFocusPainted(false);
				butn.setBackground(Color.lightGray);
				butn.addFocusListener(focused);
				//hover effectini kaldıran kod
				butn.setBorderPainted(false);
				contentPane.add(butn);
				butn.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						
						if(e.getButton()==1) 
						{

							contentPane.remove(label);
							contentPane.remove(butn);
							contentPane.remove(button);
							requestFocusInWindow();
							//Cursor gizleme kodu başı
							contentPane.setCursor(contentPane.getToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0), "null"));
							//Cursor gizleme kodu sonu
							speed=100;
							point=0;
							timer.setDelay(speed);
							length=2;
							gameOver=false;
							formHead();
							putApple();
							timer.start();
							
						}
					}
						
				        public void mouseEntered(MouseEvent e)
				        {
					    butn.setBackground(Color.white);
					    getRootPane().setDefaultButton(butn);
					    butn.requestFocus();
						if(button.getBackground().equals(Color.white)) button.setBackground(Color.lightGray);
				
				         }
				         public void mouseExited(MouseEvent e)
		                 {
			             butn.setBackground(Color.lightGray);
			             getRootPane().setDefaultButton(null);
		
		                 }});
				
				
				butn.addKeyListener(new KeyListener() {
			        @Override
			        public void keyTyped(KeyEvent e) 
			        {
			        	
			        }

			        @Override
			        public void keyPressed(KeyEvent e) 
			        {
			        	if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			            {

							contentPane.remove(label);
							contentPane.remove(butn);
							contentPane.remove(button);
							requestFocusInWindow();
							//Cursor gizleme kodu başı
							contentPane.setCursor(contentPane.getToolkit().createCustomCursor(new BufferedImage(3,3,BufferedImage.TYPE_INT_ARGB),new Point(0,0), "null"));
							//Cursor gizleme kodu sonu
							speed=100;
							point=0;
							timer.setDelay(speed);
							length=2;
							gameOver=false;
							formHead();
							putApple();
							timer.start();
			            	
			            }
			            
			        }

			        @Override
			        public void keyReleased(KeyEvent e) 
			        {
			        	
						
			        }
			       
			    });


				button = new JButton("ÇIKIŞ");
				button.setSize(200,50);
				button.setLocation(screenWidth/2-100,screenHeight/2+175);
				//focus sırasında noktalı görünümü yok ederek focusun belli olmamasını sağlayan kod
				button.setFocusPainted(false);
				button.setBackground(Color.lightGray);
				button.addFocusListener(focus);
				//hover effectini kaldıran kod
				button.setBorderPainted(false);
				contentPane.add(button);
				button.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						
						if(e.getButton()==1) 
						{
							//Programı sonlandırma kodu
							System.exit(0);
							//Programı sonlandırma kodu sonu
						}
					}
					
				    public void mouseEntered(MouseEvent e)
		           {
			        button.setBackground(Color.white);
			        getRootPane().setDefaultButton(button);
			        button.requestFocus();
			        if(butn.getBackground().equals(Color.white)) butn.setBackground(Color.lightGray);
		
		            }
				   public void mouseExited(MouseEvent e)
		           {
			        button.setBackground(Color.lightGray);
			        getRootPane().setDefaultButton(null);
		
		            }});
				
				button.addKeyListener(new KeyListener() {
			        @Override
			        public void keyTyped(KeyEvent e) {
			            // your logic here
			        }

			        @Override
			        public void keyPressed(KeyEvent e) {
			            int keyCode = e.getKeyCode();
			            if (keyCode == KeyEvent.VK_ENTER) 
			            {
			            	//Programı sonlandırma kodu
							System.exit(0);
							//Programı sonlandırma kodu sonu
			            }
			        }

			        @Override
			        public void keyReleased(KeyEvent e) {
			            // your logic here
			        }
			    });
				
				

				
				

			}
			
			
				
	}
	
	
}
