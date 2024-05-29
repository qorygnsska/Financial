package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controller.LoginController;
import Model.UsersModel;

public class UseridpwFindView extends JFrame {
	LoginController loginController = new LoginController();

	private JPanel jpmain;
	private JButton idbtn;
	private JLabel jlid;
	private JTextField jtjumin;
	private JPasswordField jpjumin;
	private JLabel jl1;
	private JLabel jp;
	private JTextField jtp;
	private JPanel jp1;
	private JButton backbtn;

	public void idfind() {
		jpmain = new JPanel();
		setTitle("아이디 찾기");
		setBounds(0, 0, 300, 150);

		jpmain.setBorder(new TitledBorder(new LineBorder(Color.green, 4), "아이디 찾기"));
		jpmain.setBackground(Color.white);
		jlid = new JLabel("주민번호");
		jl1 = new JLabel("-");
		jtjumin = new JTextField(6);
		jpjumin = new JPasswordField(7);
		jpmain.add(jlid);
		jpmain.add(jtjumin);
		jpmain.add(jl1);
		jpmain.add(jpjumin);
		idbtn = new JButton("검색");
		backbtn = new JButton("돌아가기");
		jpmain.add(backbtn);
		jpmain.add(idbtn);

		jpmain.setBounds(10, 10, 300, 150);
		add(jpmain);
		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().user();
				dispose();

			}
		});

		idbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String jumin = jtjumin.getText() + jpjumin.getText();
				if (loginController.idfind(jumin) != null) {
					String id = String.valueOf(loginController.idfind(jumin));
					JOptionPane.showMessageDialog(null, "아이디 : " + id, "성공", JOptionPane.PLAIN_MESSAGE);
					new LoginView().user();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "입력하신 정보가 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setResizable(false); // 화면 크기 고정하는 작업
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void pwfind() {
		jp1 = new JPanel();
		jpmain = new JPanel();
		setTitle("비밀번호 찾기");
		setBounds(0, 0, 250, 180);

		jpmain.setBorder(new TitledBorder(new LineBorder(Color.green, 4), "비밀번호 찾기"));
		jpmain.setBackground(Color.white);
		jp = new JLabel("아이디", JLabel.CENTER);
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp1.setBackground(Color.white);
		jtp = new JTextField(10);
		jp1.add(jtp);

		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp2.setBackground(Color.white);
		jlid = new JLabel("주민번호", JLabel.CENTER);
		jl1 = new JLabel("-");
		jtjumin = new JTextField(5);
		jpjumin = new JPasswordField(5);
		jpmain.add(jp);
		jpmain.add(jp1);
		jpmain.add(jlid);
		jp2.add(jtjumin);
		jp2.add(jl1);
		jp2.add(jpjumin);
		jpmain.add(jp2);
		idbtn = new JButton("검색");
		backbtn = new JButton("돌아가기");
		jpmain.add(backbtn);
		jpmain.add(idbtn);

		jpmain.setBounds(10, 10, 250, 180);
		add(jpmain);

		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().user();
				dispose();

			}
		});

		idbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String uid = jtp.getText();
				String jumin = jtjumin.getText() + jpjumin.getText();
				if (loginController.pwfind(jumin, uid) != null) {
					String pw = String.valueOf(loginController.pwfind(jumin, uid));
					JOptionPane.showMessageDialog(null, "비밀번호 : " + pw, "성공", JOptionPane.PLAIN_MESSAGE);
					new LoginView().user();
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "입력하신 정보가 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// setResizable(false); // 화면 크기 고정하는 작업
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}
