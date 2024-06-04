package View;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import Controller.ConsumeController;
import Controller.MainExportController;
import Controller.MainImportController;
import Controller.SaveController;
import DAO.AmountDAO;
import Model.UsersModel;

public class MainMenuView extends JPanel {

	DecimalFormat df = new DecimalFormat("#,##0,000");
	MainImportController IC = new MainImportController();
	MainExportController EC = new MainExportController();
	SaveController SC = new SaveController();
	ConsumeController CC = new ConsumeController();
	JPanel panMain;
	JPanel[] portList = new JPanel[2];
	JPanel[] moneyList = new JPanel[5];
	JLabel[] labelList = new JLabel[5];
	DefaultTableModel[] model = new DefaultTableModel[2];
	JTable[] tableList = new JTable[2];
	JScrollPane[] paneList = new JScrollPane[2];
	JPanel btnPan = new JPanel();
	JButton[] btnList = new JButton[2];
	JPanel userPan = new JPanel(new GridLayout(2, 2));
	DefaultTableModel saveModel;
	private Color colBack = new Color(255, 255, 255);
	private Color colLine = new Color(225, 235, 255);
	private Color colLineR = new Color(255, 230, 230);
	private Font f = new Font("나눔고딕", Font.BOLD, 15);
	private Font title = new Font("나눔고딕", Font.BOLD, 15);
	
	AmountDAO amountDAO = new AmountDAO();

	public MainMenuView(JPanel panel) {
		panMain = panel;
		Rectangle rect = panel.getBounds();
		setPreferredSize(rect.getSize());

		setLayout(null);

		setBackground(colBack);

		importTable();
		exportTable();
		moneyPrint();
		userButton();
		consume();
		savingMoney();
		userInfo();

		setVisible(true);
	}

	// 테이블 몸체 클릭
	private class MyMouseListener1 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JScrollPane c1 = (JScrollPane) e.getSource();
			if (c1 == paneList[0]) {
				panMain.removeAll();
				panMain.add(new ImportView(panMain));
				panMain.revalidate();
				panMain.repaint();
			} else if (c1 == paneList[1]) {
				panMain.removeAll();
				panMain.add(new ExportView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}

		}
	}

	// 테이블 행클릭
	private class MyMouseListener2 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable c2 = (JTable) e.getSource();
			if (c2 == tableList[0]) {
				panMain.removeAll();
				panMain.add(new ImportView(panMain));
				panMain.revalidate();
				panMain.repaint();
			} else if (c2 == tableList[1]) {
				panMain.removeAll();
				panMain.add(new ExportView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}

		}
	}

	// 버튼 클릭
	private class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == btnList[0]) {
				System.out.println("로그아웃 버튼");
				ViewFrame.mainFan.removeAll();
				ViewFrame.mainFrame.dispose();
				new LoginView().user();

			} else {
				System.out.println("종료 버튼");
				System.exit(0);

			}

		}

	}

	// 유저 정보 패널
	public void userInfo() {

		userPan.setBackground(colBack);
		JLabel nameTitle = new JLabel("이름 : ");
		System.out.println(UsersModel.user.getName());
		JLabel name = new JLabel(UsersModel.user.getName());
		JLabel dayTitle = new JLabel("날짜 : ");
		JLabel day = new JLabel(LocalDate.now().toString());

		nameTitle.setHorizontalAlignment(JLabel.RIGHT);
		name.setHorizontalAlignment(JLabel.LEFT);

		dayTitle.setHorizontalAlignment(JLabel.RIGHT);
		day.setHorizontalAlignment(JLabel.LEFT);

		nameTitle.setFont(new Font("D2coding", Font.BOLD, 18));
		name.setFont(new Font("D2coding", Font.BOLD, 18));
		dayTitle.setFont(new Font("D2coding", Font.BOLD, 18));
		day.setFont(new Font("D2coding", Font.BOLD, 18));

		userPan.setBounds(900, 10, 200, 100);
		userPan.add(nameTitle);
		userPan.add(name);
		userPan.add(dayTitle);
		userPan.add(day);

		add(userPan);

	}

	// 수입 테이블
	public void importTable() {

		String[] header = { "날짜", "금액", "유형" };
		portList[0] = new JPanel();
		portList[0].setBorder(BorderFactory.createTitledBorder(new LineBorder(colLine,7,true)," 수입 ", TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		portList[0].setBounds(30, 120, 400, 400);
		portList[0].setBackground(colBack);
		// 내용 수정 불가
		model[0] = IC.getImportModel(header);


		tableList[0] = new JTable(model[0]);
		// 테이블 컬럼 이동불가
		tableList[0].getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		tableList[0].getTableHeader().setResizingAllowed(false);

		// 테이블 cell 높이 설정
		tableList[0].setRowHeight(30);

		// 셀 내용 가운데 정렬
		tableCellCenter(tableList[0]);

		// 테이블 셀 배경
		tableList[0].setBackground(Color.white);
//		model[0].addRow(new Object[] { "5월7일", "100000", "급여" });

		paneList[0] = new JScrollPane(tableList[0]);

		// JScrollPane 사이즈
		paneList[0].setPreferredSize(new Dimension(350, 360));
		// 컬럼 사이즈
		tableList[0].getColumn("날짜").setPreferredWidth(60);
		tableList[0].getColumn("금액").setPreferredWidth(120);
		tableList[0].getColumn("유형").setPreferredWidth(40);

		portList[0].add(paneList[0]);
		add(portList[0]);

		paneList[0].addMouseListener(new MyMouseListener1());
		tableList[0].addMouseListener(new MyMouseListener2());
	}

	// 지출 테이블
	public void exportTable() {
		String[] header = { "날짜", "금액", "유형" };
		portList[1] = new JPanel();
		portList[1].setBorder(new TitledBorder(new LineBorder(colLine,7,true), " 지출 ", TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		
		portList[1].setBounds(460, 120, 400, 400);
		portList[1].setBackground(colBack);
		// 내용 수정 불가
		model[1] = EC.getExportModel(header);

		tableList[1] = new JTable(model[1]);

		// 테이블 컬럼 이동불가
		tableList[1].getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		tableList[1].getTableHeader().setResizingAllowed(false);

		// 테이블 cell 높이 설정
		tableList[1].setRowHeight(30);

		// 셀 내용 가운데 정렬
		tableCellCenter(tableList[1]);

		// 테이블 셀 배경
		tableList[1].setBackground(Color.white);
//		model[1].addRow(new Object[] { "5월7일", "100000", "급여" });

		paneList[1] = new JScrollPane(tableList[1]);
		// JScrollPane 사이즈
		paneList[1].setPreferredSize(new Dimension(350, 360));
		// 컬럼 사이즈
		tableList[1].getColumn("날짜").setPreferredWidth(60);
		tableList[1].getColumn("금액").setPreferredWidth(120);
		tableList[1].getColumn("유형").setPreferredWidth(40);
		portList[1].add(paneList[1]);
		add(portList[1]);

		paneList[1].addMouseListener(new MyMouseListener1());
		tableList[1].addMouseListener(new MyMouseListener2());
	}

	// 테이블 내용 가운데 정렬하기
	public void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {

			// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
			// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
			tcm.getColumn(i).setCellRenderer(dtcr);

		}

	}

	// 잔고 및 수입지출
	public void moneyPrint() {
		String amount = amountDAO.amount();

		String[] str = { " 잔고 ", " 이 달 수입 ", " 이 달 지출 ", " 지난 달 수입 ", " 지난 달 지출 " };

		String[] money = { amount, IC.thisMonth(), EC.thisMonth(), IC.beforeMonth(), EC.beforeMonth() };

		for (int i = 0; i < moneyList.length; i++) {
			moneyList[i] = new JPanel();
			moneyList[0].setBackground(Color.white);
		
			add(moneyList[i]);
			labelList[i] = new JLabel(money[i]);
			moneyList[i].add(labelList[i]);
			labelList[i].setFont(title);
			moneyList[i].setBounds(900, 120 + 90 * i, 240, 60);

		}
		
		// 라인색
		moneyList[1].setBorder(new TitledBorder(new LineBorder(colLineR,3,true), str[2], TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		moneyList[2].setBorder(new TitledBorder(new LineBorder(colLine,3,true), str[2], TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		moneyList[3].setBorder(new TitledBorder(new LineBorder(colLineR,3,true), str[2], TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		moneyList[4].setBorder(new TitledBorder(new LineBorder(colLine,3,true), str[2], TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		// 배경색
		moneyList[1].setBackground(new Color(255,245,245));
		moneyList[2].setBackground(new Color(245,245,255));
		moneyList[3].setBackground(new Color(255,245,245));
		moneyList[4].setBackground(new Color(245,245,255));		

		moneyList[0].setBorder(new TitledBorder(new LineBorder(colLine,7,true), str[0]));
		moneyList[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				panMain.removeAll();
				panMain.add(new AmountView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}
		});

	}

	// 로그아웃, 종료 버튼
	public void userButton() {
		btnPan.setBounds(900, 600, 200, 50);
		add(btnPan);

		btnPan.setBackground(colBack);

		String[] list = { "로그아웃", "종료" };

		for (int i = 0; i < btnList.length; i++) {
			btnList[i] = new JButton(list[i]);
			btnPan.add(btnList[i]);
			btnList[i].addActionListener(new MyButtonListener());
			btnList[i].setBackground(Color.white);

		}

	}

	// 소비태크
	public void consume() {
		JPanel conPan = new JPanel(new GridLayout(0, 3));
		conPan.setBounds(30, 530, 300, 80);
		conPan.setBackground(colBack);

		conPan.setBorder(new TitledBorder(new LineBorder(colLine,3,true), " 소비유형 ", TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		JLabel[] list = new JLabel[3];
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel();
			conPan.add(list[i]);
			list[i].setHorizontalAlignment(JLabel.CENTER);
			list[i].setFont(f);
		}

		String[] ar = CC.consumeTag();
		System.out.println(ar.length);

		try {
			list[0].setText("1. " + ar[0]);
		} catch (Exception e) {
			list[0].setText("");
		}

		try {
			list[1].setText("2. " + ar[1]);
		} catch (Exception e) {
			list[1].setText("");
		}

		
		try {
			list[2].setText("3. " + ar[2]);
		} catch (Exception e) {
			list[2].setText("");
		}
		add(conPan);

		conPan.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("소비 태크 클릭");
			}

		});

	}

	// 저축
	public void savingMoney() {
		JPanel sPan = new JPanel();
		sPan.setBounds(400, 530, 460, 190);
		sPan.setBackground(colBack);

		sPan.setBorder(new TitledBorder(new LineBorder(colLine,3,true), "저축", TitledBorder.LEFT, TitledBorder.TOP, f ,Color.black));
		String[] header = { "날짜", "금액", "유형", "비고" };
		
		saveModel = SC.getSaveModel(header);
		
		JTable table = new JTable(saveModel);

		// 테이블 컬럼 이동불가
		table.getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		table.getTableHeader().setResizingAllowed(false);

		// 테이블 cell 높이 설정
		table.setRowHeight(30);

		// 셀 내용 가운데 정렬
		tableCellCenter(table);

		// 테이블 셀 배경
		table.setBackground(Color.white);
//		saveModel.addRow(new Object[] { "5월7일", "100000", "여가", "태국" });

		JScrollPane savePane = new JScrollPane(table);
		// JScrollPane 사이즈
		savePane.setPreferredSize(new Dimension(440, 150));
		// 컬럼 사이즈
		table.getColumn("날짜").setPreferredWidth(60);
		table.getColumn("금액").setPreferredWidth(120);
		table.getColumn("유형").setPreferredWidth(60);
		table.getColumn("비고").setPreferredWidth(180);
		sPan.add(savePane);

		add(sPan);
	}

}