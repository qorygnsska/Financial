package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.AmountController;
import Controller.ConsumeController;
import Controller.ExportController;
import Controller.ImportController;
import Controller.MainExportController;
import Controller.MainImportController;
import Controller.SaveController;
import Model.UsersModel;

public class MainMenuView extends JPanel {

	DecimalFormat df = new DecimalFormat("#,###,000");
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
	private Color colBtn = new Color(240, 248, 255);
	private Font f = new Font("나눔고딕", Font.BOLD, 15);
	private Font f1 = new Font("나눔고딕", Font.BOLD, 17);
	private Font title = new Font("나눔고딕", Font.BOLD, 15);
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image imageExport = tk.getImage("image/export.png");
	private Image imageImport = tk.getImage("image/import.png");
	private Image imageAmount = tk.getImage("image/amount.png");
	private Point point = new Point(10,10);
	private Cursor csExport = tk.createCustomCursor(imageExport, point, "");
	private Cursor csImport = tk.createCustomCursor(imageImport, point, "");
	private Cursor csAmount = tk.createCustomCursor(imageAmount, point, "");

	private AmountController amountController = new AmountController();
	private ImportController importController = new ImportController();
	private ExportController exportController = new ExportController();
	
	public MainMenuView(JPanel panel) {
		panMain = panel;
		Rectangle rect = panel.getBounds();
		setPreferredSize(rect.getSize());

		setLayout(null);

		setBackground(colBack);
		checkfport();
		importTable();
		exportTable();
		moneyPrint();
		userButton();
		consume();
		savingMoney();
		userInfo();

		setVisible(true);
	}

	private void checkfport() {
		exportController.check();
		importController.check();
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

		// 커서 변경 이벤트 위한 이벤트
		@Override
		public void mouseEntered(MouseEvent e) {
			// 패널에서 포인터 변경
			portList[0].setCursor(csImport);
			portList[1].setCursor(csExport);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// 패널 밖에서 포인터 원래대로
			portList[0].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			portList[1].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			portList[0].setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			portList[1].setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			portList[0].setCursor(csImport);
			portList[1].setCursor(csExport);
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

		// 커서 변경 이벤트 위한 이벤트
		@Override
		public void mouseEntered(MouseEvent e) {
			// 패널에서 포인터 변경
			tableList[0].setCursor(csImport);
			tableList[1].setCursor(csExport);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// 패널 밖에서 포인터 원래대로
			tableList[0].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			tableList[1].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			tableList[0].setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			tableList[1].setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			tableList[0].setCursor(csImport);
			tableList[1].setCursor(csExport);
		}
	}

	// 버튼 클릭
	private class MyButtonListener extends MouseAdapter implements ActionListener {
		JButton btn = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			btn = (JButton) e.getSource();
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
		JPanel upPanel = new JPanel();
		JPanel myPanel = new MyPanel();
		myPanel.setBounds(0, 0, 900, 115);
		
		userPan.setBackground(colBack);
		JLabel nameTitle = new JLabel("이름 : ");
		JLabel name = new JLabel(UsersModel.user.getName());
		JLabel dayTitle = new JLabel("날짜 : ");
		JLabel day = new JLabel(LocalDate.now().toString());

		// 오늘날짜1
		LocalDate today = LocalDate.now();
		String t = today.toString();
		t = t.replace('-', '/');
		t = t.substring(2, 10);

		nameTitle.setHorizontalAlignment(JLabel.RIGHT);
		name.setHorizontalAlignment(JLabel.LEFT);

		dayTitle.setHorizontalAlignment(JLabel.RIGHT);
		day.setHorizontalAlignment(JLabel.LEFT);

		nameTitle.setFont(new Font("나눔고딕", Font.BOLD, 18));
		name.setFont(new Font("나눔고딕", Font.BOLD, 18));
		dayTitle.setFont(new Font("나눔고딕", Font.BOLD, 18));
		day.setFont(new Font("나눔고딕", Font.BOLD, 18));

		userPan.setBounds(1000, 10, 200, 100);
		userPan.add(nameTitle);
		userPan.add(name);
		userPan.add(dayTitle);
		userPan.add(day);
		
		upPanel.add(myPanel);
		upPanel.add(userPan);
		upPanel.setBounds(-100, 0, 1200, 115);
		upPanel.setBackground(new Color(255, 255, 255));
		add(upPanel);
	}

	// 수입 테이블
	public void importTable() {

		String[] header = { "날짜", "금액", "유형" };
		portList[0] = new JPanel();

		portList[0].setBorder(BorderFactory.createTitledBorder(new LineBorder(colLine, 7, true), " 수입 ",
				TitledBorder.LEFT, TitledBorder.TOP, f, Color.black));

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

		portList[1].setBorder(new TitledBorder(new LineBorder(colLine, 7, true), " 지출 ", TitledBorder.LEFT,
				TitledBorder.TOP, f, Color.black));

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
		String amount = amountController.amount();

		String[] str = { " 잔고 ", " 이 달 수입 ", " 이 달 지출 ", " 지난 달 수입 ", " 지난 달 지출 " };

		String[] money = { amount, IC.thisMonth(), EC.thisMonth(), IC.beforeMonth(), EC.beforeMonth() };

		for (int i = 0; i < moneyList.length; i++) {
			moneyList[i] = new JPanel();
			moneyList[i].setBackground(colBack);

			moneyList[i].setBackground(Color.white);

			add(moneyList[i]);
			labelList[i] = new JLabel(money[i]);
			moneyList[i].add(labelList[i]);
			labelList[i].setFont(title);
			moneyList[i].setBounds(900, 120 + 90 * i, 240, 60);

			if (i >= 1 && i % 2 == 1) {
				moneyList[i].setBorder(new TitledBorder(new LineBorder(colLineR, 3, true), str[i], TitledBorder.LEFT,
						TitledBorder.TOP, f, Color.black));
				moneyList[i].setBackground(new Color(255, 245, 245));
			}else if (i >= 1 && i % 2 == 0) {
				moneyList[i].setBorder(new TitledBorder(new LineBorder(colLine, 3, true), str[i], TitledBorder.LEFT,
						TitledBorder.TOP, f, Color.black));
				moneyList[i].setBackground(new Color(245, 245, 255));
			}
			
			
		}

		moneyList[0].setBorder(new TitledBorder(new LineBorder(colLine, 7, true), str[0]));
		moneyList[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				panMain.removeAll();
				panMain.add(new AmountView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}

			// 커서 변경 이벤트 위한 이벤트
			@Override
			public void mouseEntered(MouseEvent e) {
				// 패널에서 포인터 변경
				setCursor(csAmount);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 패널 밖에서 포인터 원래대로
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setCursor(csAmount);
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
			btnList[i].setFont(f1);
			btnPan.add(btnList[i]);
			btnList[i].addActionListener(new MyButtonListener());
			btnList[i].setBackground(colBtn);

		}

	}

	// 소비태크
	public void consume() {
		JPanel conPan = new JPanel(new GridLayout(0, 3));
		conPan.setBounds(520, 580, 300, 80);
		conPan.setBackground(colBack);

		conPan.setBorder(new TitledBorder(new LineBorder(colLine, 3, true), " 소비유형 ", TitledBorder.LEFT,
				TitledBorder.TOP, f, Color.black));

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
		sPan.setBounds(30, 530, 460, 190);
		sPan.setBackground(colBack);

		sPan.setBorder(new TitledBorder(new LineBorder(colLine, 3, true), "저축", TitledBorder.LEFT, TitledBorder.TOP, f,
				Color.black));
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