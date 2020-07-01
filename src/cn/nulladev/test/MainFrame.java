package cn.nulladev.test;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private ImageIcon imgDog = new ImageIcon(getClass().getResource("/img/dog.jpg"));
	private JTextField time = new JTextField();
	
	private AutoPress thread;

	public MainFrame() {
		this.setTitle("自动保存小助手");
		this.setSize(600, 480);
		this.setResizable(false);
		this.setIconImage(imgDog.getImage());
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.addWindowListener(
		new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "程序隐藏到托盘了哦", "提示", JOptionPane.INFORMATION_MESSAGE);
				MainFrame.this.setVisible(false);
				systemTray();
			}
		});
		this.setVisible(true);
	}
	
	private void systemTray() {
		if (SystemTray.isSupported()) {
			PopupMenu popupMenu = new PopupMenu();
			MenuItem itemPause = new MenuItem("暂停");
			itemPause.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (thread != null)
						thread.stop();
				}
			});
			popupMenu.add(itemPause);
			MenuItem itemExit = new MenuItem("退出");
			itemExit.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "自动保存小助手 Powered By Nulladev\n感谢您的使用！", "提示", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			});
			popupMenu.add(itemExit);
	
			TrayIcon trayIcon = new TrayIcon(imgDog.getImage(), "自动保存小助手", popupMenu);
			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame.this.setVisible(true);
				}
			});
			try {
				SystemTray.getSystemTray().add(trayIcon);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void initComponents() {
		JLabel title = new JLabel();
		title.setText("自动保存小助手");
		title.setFont(new Font("微软雅黑", 0, 48));
		title.setBounds(20, 20, 580, 50);
		this.add(title);
		
		JLabel dog = new JLabel(imgDog);
		dog.setBounds(20, 120, 150, 150);
		this.add(dog);
		
		JLabel label1 = new JLabel();
		label1.setText("请输入自动保存间隔");
		label1.setFont(new Font("微软雅黑", 0, 36));
		label1.setBounds(200, 130, 580, 40);
		this.add(label1);
		
		time.setFont(new Font("微软雅黑", 0, 36));
		time.setBounds(300, 200, 100, 40);
		time.setText("30");
		this.add(time);
		
		JButton start = new JButton("开始");
		start.setBounds(200, 300, 100, 40);
		start.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (thread != null)
						thread.stop();
					int i = Integer.parseInt(time.getText());
					i = Math.max(i, 1);
					i *= 1000;
					thread = new AutoPress();
					thread.t = i;
					thread.start();
				}
			});
		this.add(start);
		
		JButton pause = new JButton("暂停");
		pause.setBounds(350, 300, 100, 40);
		pause.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (thread != null)
						thread.stop();
				}
			});
		this.add(pause);
	}
		 
	public static void main(String[] args) {
		new MainFrame();
	}

}
