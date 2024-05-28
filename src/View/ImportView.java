package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ImportView extends JPanel {
	
	JPanel panMain;
	private String day;
	private int price;
	private int imtype;
	private String memo;

	private JScrollPane sp = new JScrollPane();
	private JTabbedPane tabPanel = new JTabbedPane();
	
	public ImportView() {
	}

	public ImportView(JPanel panel) {
		panMain = panel; 
		print();

	}

	public ImportView(String day, int price, int imtype, String memo) throws HeadlessException {
		this.day = day;
		this.price = price;
		this.imtype = imtype;
		this.memo = memo;
	}

	public void print() {
		Rectangle rect = panMain.getBounds();
		setPreferredSize(rect.getSize());
		setBounds(50, 50, 1200, 800);
		setLayout(null);

		// main 패널 (tab 생성 포함)
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1200, 700);
		mainPanel.setLayout(null);

		// 메뉴 패널(메인으로 돌아가기 버튼)
		JPanel btnPanel = new JPanel();
		JButton menuBtn = new JButton("메인으로");
		menuBtn.addActionListener(new ActionListener() {
			
			// 버튼 클릭 시 메인으로 돌아가는 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				panMain.removeAll();
				panMain.add(new MainMenuView(panMain));
				panMain.revalidate();
				panMain.repaint();
			}
		});
		
		btnPanel.add(menuBtn);
		btnPanel.setBounds(1050, 20, 100, 50);
		mainPanel.add(btnPanel);

		// 조회 패널(전체, 일별, 월별 tab패널)
		JPanel checkPanel = new JPanel();
		checkPanel.setBounds(0, 0, 800, 600);

		// 전체 조회 패널
		JPanel totalPanel = new JPanel();
		totalPanel.setBounds(0, 0, 800, 600);
		String[] column = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData = { { "24/05/27", "3000000", "급여", "보너스 제외" }, { "24/05/27", "1000000", "급여", "보너스" } };

		sp = totalCheck(rowData, column);
		totalPanel.add(sp);

		// 일별 조회
		JPanel dayPanel = new JPanel();
		dayPanel.setBounds(100, 50, 1000, 600);
		// data DB 연결 필요
		String[] column1 = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData1 = { { "24/05/27", "4000000", "급여", "보너스 제외 및 알바비 포함" },
				{ "24/05/27", "2000", "이자", "은행 이자" } };
		sp = totalCheck(rowData1, column1);
		dayPanel.add(sp);

		// 월별 조회
		JPanel monthPanel = new JPanel();
		monthPanel.setBounds(100, 50, 1000, 600);
		String[] column2 = { "날짜", "금        액", "구분", "비        고" };
		String[][] rowData2 = { { "24/05/27", "5000000", "급여", "보너스 포함" }, { "24/05/27", "1000000", "기타", "길에서 주움" } };
		sp = totalCheck(rowData2, column2);
		monthPanel.add(sp);

		// tab
		tabPanel.add("전체", totalPanel);
		tabPanel.add("일별", dayPanel);
		tabPanel.add("월별", monthPanel);

		checkPanel.add(tabPanel);
		checkPanel.setBounds(40, 50, 750, 600);
		mainPanel.add(checkPanel);

		// 버튼 패널(추가, 수정, 삭제)
		JPanel btnsPanel = new JPanel();
		btnsPanel.setBounds(870, 600, 250, 100);

		// 추가 버튼
		JButton addBtn = new JButton("추가");
		btnsPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 수정 버튼
		JButton updateBtn = new JButton("수정");
		btnsPanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 삭제 버튼
		JButton deleteBtn = new JButton("삭제");
		btnsPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 수정 패널(추가, 수정, 삭제)
		JPanel updatePanel = new JPanel(new GridLayout(4, 2));
		updatePanel.setBounds(650, 250, 500, 300);

		// 날짜 패널(왼쪽)
		JPanel datePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽부터 정렬
		JLabel dateLabel = new JLabel("날    짜 : ", 10);
		datePanelL.add(dateLabel);
		updatePanel.add(datePanelL);

		// 날짜 패널(오른쪽)
		JPanel datePanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField dateField = new JTextField(20);
		datePanelR.add(dateField);
		updatePanel.add(datePanelR);

		// 금액 패널(왼쪽)
		JPanel amountPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel amountLabel = new JLabel("금    액 : ", 10);
		amountPanelL.add(amountLabel);
		updatePanel.add(amountPanelL);

		// 금액 패널(오른쪽)
		JPanel amountPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField amountField = new JTextField(20);
		amountPanelR.add(amountField);
		updatePanel.add(amountPanelR);

		// 유형 패널(왼쪽)
		JPanel typePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel typeLabel = new JLabel("유    형 : ", 10);
		typePanelL.add(typeLabel);
		updatePanel.add(typePanelL);

		// 유형 패널(오른쪽)
		JPanel typePanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		String[] exportType = { "급여", "이자", "기타" };
		JComboBox typeBox = new JComboBox(exportType);
		typePanelR.add(typeBox);
		updatePanel.add(typePanelR);

		// 비고 패널(왼쪽)
		JPanel memoPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel memoLabel = new JLabel("비    고 : ", 10);
		memoPanelL.add(memoLabel);
		updatePanel.add(memoPanelL);

		// 비고 패널(오른쪽)
		JPanel memoPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField memoField = new JTextField(20);
		memoPanelR.add(memoField);
		updatePanel.add(memoPanelR);

		mainPanel.add(btnsPanel);
		mainPanel.add(updatePanel);

		add(mainPanel);
		setVisible(true);
	}

	public JScrollPane totalCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane dayCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane monthCheck(String[][] rowData, String[] column) {
		JTable totalTable = new JTable(rowData, column);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(30);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(250);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

}
