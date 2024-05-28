package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupView extends JFrame {

	public void newUser() {

		setTitle("회원가입");
		JPanel back = new JPanel();
		back.setBackground(Color.CYAN);
		JPanel title = new JPanel();
		JLabel Jtitle = new JLabel("회원가입");
		Jtitle.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		back.add(Jtitle);

		JPanel jp1 = new JPanel();

		jp1.setLayout(new GridLayout(6, 2));
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlid = new JLabel("아이디 : ", JLabel.CENTER);
		jlid.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		idPanel.setBackground(Color.CYAN);
		idPanel.add(jlid);

		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtid = new JTextField(10);
		idPanel2.setBackground(Color.CYAN);
		idPanel2.add(jtid);

		jp1.add(idPanel);
		jp1.add(idPanel2);

		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlpw = new JLabel("비밀번호 : ", JLabel.CENTER);
		jlpw.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		pwdPanel.setBackground(Color.CYAN);
		JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtpw = new JPasswordField(10);
		pwdPanel2.setBackground(Color.CYAN);
		pwdPanel.add(jlpw);
		pwdPanel2.add(jtpw);

		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlname = new JLabel("이름 : ", JLabel.CENTER);
		jlname.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		namePanel.setBackground(Color.CYAN);
		JPanel namePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtname = new JTextField(10);
		namePanel2.setBackground(Color.CYAN);
		namePanel.add(jlname);
		namePanel2.add(jtname);

		jp1.add(namePanel);
		jp1.add(namePanel2);

		JPanel juminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jljumin = new JLabel("주민번호 : ", JLabel.CENTER);
		jljumin.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		juminPanel.setBackground(Color.CYAN);
		JPanel juminPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtjumin = new JTextField(5);
		juminPanel2.setBackground(Color.CYAN);
		JPasswordField jtjumin2 = new JPasswordField(5);

		juminPanel.add(jljumin);
		juminPanel2.add(jtjumin);
		juminPanel2.add(jtjumin2);

		jp1.add(juminPanel);
		jp1.add(juminPanel2);

		JPanel fimportPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlfimport = new JLabel("고정수입: ", JLabel.CENTER);
		jlfimport.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		fimportPanel.setBackground(Color.CYAN);
		fimportPanel.add(jlfimport);

		String[] importType = { "용도", "용도", "용도" };
		JComboBox jboxImportType = new JComboBox(importType);

		JPanel fimportPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtfimport = new JTextField(10);
		fimportPanel2.setBackground(Color.CYAN);
		fimportPanel2.add(jtfimport);
		fimportPanel2.add(jboxImportType);
		jp1.add(fimportPanel);
		jp1.add(fimportPanel2);

		JPanel fexportPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlfexport = new JLabel("고정지출 : ", JLabel.CENTER);
		jlfexport.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		fexportPanel.setBackground(Color.CYAN);
		fexportPanel.add(jlfexport);

		String[] exportType = { "용도", "용도", "용도" };
		JComboBox jboxExportType = new JComboBox(exportType);

		JPanel fexportPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtfexport = new JTextField(10);
		fexportPanel2.setBackground(Color.CYAN);
		fexportPanel2.add(jtfexport);
		fexportPanel2.add(jboxExportType);
		jp1.add(fexportPanel);
		jp1.add(fexportPanel2);

		back.add(jp1);

		JButton btuser = new JButton("회원가입");
		back.add(btuser);

		add(back);
		setBounds(0, 0, 400, 350);

		// 화면 크기 고정하는 작업
		setResizable(false);
		// 화면 중앙에 배치할 수있도록 메서드
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
