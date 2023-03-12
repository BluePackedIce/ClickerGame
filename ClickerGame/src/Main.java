import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener
{
	//グローバル変数
	public static boolean Game = true;
	static int click = 0;
	static int CPS = 0;
	static JLabel click_cookie,CPS_Label;
	
	static JButton click_button = new JButton();
	static JButton B_Cursor = new JButton();
	static JButton B_Granma = new JButton();
	static JButton B_Farm = new JButton();
	static JButton B_Factory = new JButton();
	static JButton B_Temple = new JButton();
	static JButton B_Magic = new JButton();
	
	static JButton UP_Mouse = new JButton();
	static JButton UP_Cursor = new JButton();
	static JButton UP_Granma = new JButton();
	static JButton UP_Farm = new JButton();
	static JButton UP_Factory = new JButton();
	static JButton UP_Temple = new JButton();
	static JButton UP_Magic = new JButton();

	static Building _Cursor = new Building(1,5,150);
	static Building _Granma = new Building(10,10,300);
	static Building _Farm = new Building(50,50,500);
	static Building _Factory = new Building(100,1000,1000);
	static Building _Temple = new Building(300,2500,3000);
	static Building _Magic = new Building(500,4000,5000);
	static JMenuItem save_menu = new JMenuItem("Save");
	
	static int Mouse_Click = 1;
	static int Mouse_Need = 100;
	
	//メイン
	public static void main(String args[])
	{
		new Main();
		
		_Cursor.start();
		_Granma.start();
		_Farm.start();
		_Factory.start();
		_Temple.start();
		_Magic.start();
		while(true)
		{
			update_text();
			check_upgrade();
			Math_CPS();
		}
	}
	//ボタンとラベルの表示更新
	public static void update_text()
	{
		int Coockie = (int) Math.floor(click);
		click_cookie.setText(String.valueOf(Coockie));
		CPS_Label.setText(String.valueOf(CPS));
		
		B_Cursor.setText("<html>カーソルx"+ _Cursor.count + 
			"<br>+"+ _Cursor.generate*_Cursor.power + "CPS<br>必要："+ _Cursor.upgrade_need +"クッキー");
		B_Granma.setText("<html>グランマx"+_Granma.count +
			"<br>+"+_Granma.generate*_Granma.power+"CPS<br>必要:"+ _Granma.upgrade_need +"クッキー");
		B_Farm.setText("<html>農場x"+_Farm.count +
			"<br>+"+_Farm.generate*_Farm.power+"CPS<br>必要："+ _Farm.upgrade_need +"クッキー");
		B_Factory.setText("<html>工場x"+_Factory.count+
			"<br>"+_Factory.generate*_Factory.power+"CPS<br>必要："+ _Factory.upgrade_need +"クッキー");
		B_Temple.setText("<html>寺院x"+_Temple.count+
			"<br>+"+_Temple.generate*_Temple.power+"CPS<br>必要："+ _Temple.upgrade_need +"クッキー");
		B_Magic.setText("<html>魔法の塔x"+_Magic.count+
			"<br>+"+_Magic.generate*_Magic.power+"CPS<br>必要："+ _Magic.upgrade_need +"クッキー");
		UP_Mouse.setText("<html>マウスパワー"+ Mouse_Click + "<br>必要："+ Mouse_Need +"クッキー");
		UP_Cursor.setText("<html>カーソルパワー"+ _Cursor.power + "<br>必要："+ _Cursor.powerup_need +"クッキー");
		UP_Granma.setText("<html>グランマパワー"+ _Granma.power  + "<br>必要:"+ _Granma.powerup_need +"クッキー");
		UP_Farm.setText("<html>農場パワー"+ _Farm.power  + "<br>必要:"+ _Farm.powerup_need +"クッキー");
		UP_Factory.setText("<html>工場パワー"+ _Factory.power  + "<br>必要:"+ _Factory.powerup_need +"クッキー");
		UP_Temple.setText("<html>寺院パワー"+ _Temple.power  + "<br>必要:"+ _Temple.powerup_need +"クッキー");
		UP_Magic.setText("<html>魔法の城パワー"+ _Magic.power  + "<br>必要:"+ _Magic.powerup_need +"クッキー");
	}

	Main() 
	{
		//--------------------------------------------
		//Window生成関連
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new GridLayout(1,2));
		
		//クリックボタン
		click_button = new JButton("Click Generate");
		getContentPane().add(click_button);
		
		
		//生成クッキーパネル
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JLabel("所持クッキー：",JLabel.CENTER));
		click_cookie = new JLabel(String.valueOf(click));
		panel.add(click_cookie);
		getContentPane().add(panel);
		panel.add(new JLabel("CPS：",JLabel.CENTER));
		CPS_Label = new JLabel(String.valueOf(CPS));
		panel.add(CPS_Label);
		
		//アップグレードパネル
		JPanel Upgrade = new JPanel();
		Upgrade.setLayout(new GridLayout(5,0));
		Upgrade.add(B_Cursor);
		Upgrade.add(B_Granma);
		Upgrade.add(B_Farm);
		Upgrade.add(B_Factory);
		Upgrade.add(B_Temple);
		Upgrade.add(B_Magic);
		
		//パワーアップパネル
		JPanel PowerUP = new JPanel();
		PowerUP.setLayout(new GridLayout(4,0));
		PowerUP.add(UP_Mouse);
		PowerUP.add(UP_Cursor);
		PowerUP.add(UP_Granma);
		PowerUP.add(UP_Farm);
		PowerUP.add(UP_Factory);
		PowerUP.add(UP_Temple);
		PowerUP.add(UP_Magic);
		
		getContentPane().add(Upgrade);

		getContentPane().add(PowerUP);
		//Windowサイズ
		setSize(1080,720);
		setVisible(true);
		//---------------------------------
		//ボタンイベント登録
		click_button.addActionListener(this);
		B_Cursor.addActionListener(this);
		B_Granma.addActionListener(this);
		B_Farm.addActionListener(this);
		B_Factory.addActionListener(this);
		B_Temple.addActionListener(this);
		B_Magic.addActionListener(this);
		UP_Mouse.addActionListener(this);
		UP_Cursor.addActionListener(this);
		UP_Granma.addActionListener(this);
		UP_Farm.addActionListener(this);
		UP_Factory.addActionListener(this);
		UP_Temple.addActionListener(this);
		UP_Magic.addActionListener(this);
		//---------------------------------
		//メニューバー
		JMenuBar menubar = new JMenuBar();
		JMenu file_menu = new JMenu("File");
		JMenu option_menu = new JMenu("Option");

		file_menu.add(save_menu);
		menubar.add(file_menu);
		setJMenuBar(menubar);
		//---------------------------------
		//ボタンイベント登録
		save_menu.addActionListener(this);
		//---------------------------------
		
	}
	
	//ボタンクリックイベント
	public void actionPerformed(ActionEvent ae)
	{
		//クリック
		if(ae.getSource() == click_button)click += Mouse_Click;
		
		//施設購入
		if(ae.getSource() == B_Cursor)_Cursor.Buy();
		if(ae.getSource() == B_Granma)_Granma.Buy();
		if(ae.getSource() == B_Farm)_Farm.Buy();
		if(ae.getSource() == B_Factory)_Factory.Buy();
		if(ae.getSource() == B_Temple)_Temple.Buy();
		if(ae.getSource() == B_Magic)_Magic.Buy();
		
		//マウス強化
		if(ae.getSource() == UP_Mouse && click >= Mouse_Need)
		{
			click -= Mouse_Need;
			Mouse_Click++;
			Mouse_Need += Mouse_Need*0.25;
		}
		
		//パワーアップボタン処理
		if(ae.getSource() == UP_Cursor)_Cursor.PowerUP();
		if(ae.getSource() == UP_Granma)_Granma.PowerUP();
		if(ae.getSource() == UP_Farm)_Farm.PowerUP();
		if(ae.getSource() == UP_Factory)_Factory.PowerUP();
		if(ae.getSource() == UP_Temple)_Temple.PowerUP();
		if(ae.getSource() == UP_Magic)_Magic.PowerUP();
		if(ae.getSource() == save_menu)System.out.println("Save");
			
		
	}
	
	//購入できないボタンを使えないようにする
	public static void check_upgrade()
	{
		B_Cursor.setEnabled(click >= _Cursor.upgrade_need ? true : false);
		B_Granma.setEnabled(click >= _Granma.upgrade_need ? true : false);
		B_Farm.setEnabled(click >= _Farm.upgrade_need ? true : false);
		B_Factory.setEnabled(click >= _Factory.upgrade_need ? true : false);
		B_Temple.setEnabled(click >= _Temple.upgrade_need ? true : false);
		B_Magic.setEnabled(click >= _Magic.upgrade_need ? true : false);
		
		UP_Mouse.setEnabled(click >= Mouse_Need ? true : false);
		
		UP_Cursor.setEnabled(click >= _Cursor.powerup_need ? true : false);
		UP_Granma.setEnabled(click >= _Granma.powerup_need ? true : false);
		UP_Farm.setEnabled(click >= _Farm.powerup_need ? true : false);
		UP_Factory.setEnabled(click >= _Factory.powerup_need ? true : false);
		UP_Temple.setEnabled(click >= _Temple.powerup_need ? true : false);
		UP_Magic.setEnabled(click >= _Magic.powerup_need ? true : false);
	}
	
	//各建物でCPS計算
	public static void Math_CPS()
	{
		CPS = 0;
		_Cursor.CPS();
		_Granma.CPS();
		_Farm.CPS();
		_Factory.CPS();
		_Temple.CPS();
		_Magic.CPS();
	}
	
	public static class Building extends Thread
	{
		public int count;
		public int generate;
		public int upgrade_need;
		public int powerup_need;
		public int power;
		
		Building(int _generate,int _upgrade_need,int _powerup_need)
		{
			this.count = 0;
			this.generate = _generate;
			this.upgrade_need = _upgrade_need;
			this.powerup_need = _powerup_need;
			this.power = 1;
		}
		
		//購入メソッド
		public void Buy()
		{
			if(click >= upgrade_need)
			{
				click -= upgrade_need;//消費
				count++;//建物購入
				upgrade_need+=upgrade_need*0.5;//購入費増加
			}
		}
		
		//アップグレードメソッド
		public void PowerUP()
		{
			if(click >= powerup_need)
			{
				click -= powerup_need;
				power++;
				powerup_need += powerup_need*0.5;
			}
		}
		
		//建物CPS計算
		public void CPS()
		{
			CPS += (generate*count) * power;
		}
		
		//Tickスレッド
		public void run()
		{
			while(true)
			{
				click += (generate*count);
				//１秒間隔で実行
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}

