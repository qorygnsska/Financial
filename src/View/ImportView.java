package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.ImportController;
import Controller.DatePickerController;
import DatePickerEx.Dateformet;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ImportView extends JPanel {
 static String date;
	int num = 0;
	String[] datelist = new String[2];
	DatePickerController dpc = new DatePickerController();

	JPanel panMain;
	private String day;
	private int price;
	private int imtype;
	private String memo;
	private JTable totalTable;
	private JScrollPane sp = new JScrollPane();
	private JTabbedPane tabPanel = new JTabbedPane();
	private JPanel mainPanel, btnPanel, checkPanel, totalPanel, dayPanel, monthPanel, btnsPanel, updatePanel,
			datePanelL, datePan, amountPanelL, amountPanelR, typePanelL, typePanelR, memoPanelL, memoPanelR;
	private JTextField amountField;
	DefaultTableModel[] importModel = new DefaultTableModel[4];
	ImportController ic = new ImportController();
	
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

	// 테이블 마우스 클릭
	private class MyMouseListener1 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// 테이블에서 선택한 행 가져오기
			int selectRow = totalTable.getSelectedRow();
			System.out.println("선택한 행:" + selectRow);
			// 선택한 행이 있는지 확인
			if(selectRow!=-1) {
					
				
			}

		}
	}
	
	
	public void print() {
		Rectangle rect = panMain.getBounds();
		setPreferredSize(rect.getSize());
		//setBounds(7, 0, 1170, 765);
		setLayout(null);

		// main 패널 (tab 생성 포함)
		mainPanel = new JPanel();
		//mainPanel.setBorder(new LineBorder(Color.green, 8));
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setLayout(null);

		// 메뉴 패널(메인으로 돌아가기 버튼)
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.white);
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
		checkPanel = new JPanel();
		checkPanel.setBounds(0, 0, 800, 600);
		checkPanel.setBackground(Color.white);

		// tab
		tabPanel.add("전체", totalPanel());
		tabPanel.add("일별", dayPanel());
		tabPanel.add("월별", monthPanel());

		checkPanel.add(tabPanel);
		checkPanel.setBounds(40, 50, 750, 600);
		mainPanel.add(checkPanel);

		// 버튼 패널(추가, 수정, 삭제)
		btnsPanel = new JPanel();
		btnsPanel.setBounds(870, 600, 250, 100);
		btnsPanel.setBackground(Color.white);

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
				  
				// 테이블에서 선택한 행 가져오기
				int selectRow = totalTable.getSelectedRow();
				System.out.println("선택한 행:" + selectRow);
				// 선택한 행이 있는지 확인
				if(selectRow!=-1) {
						
					
				}
				
			}
		});

		// 삭제 버튼
		JButton deleteBtn = new JButton("삭제");
		btnsPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 테이블에서 선택한 행 가져오기
				int selectRow = totalTable.getSelectedRow();
				System.out.println("선택한 행:" + selectRow);
			}
		});

		// 수정 패널(추가, 수정, 삭제)
		updatePanel = new JPanel(new GridLayout(4, 2));
		updatePanel.setBounds(650, 250, 500, 300);
		updatePanel.setBackground(Color.white);

		// 날짜 패널(왼쪽)
		datePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽부터 정렬
		JLabel dateLabel = new JLabel("날    짜 : ", 10);
		datePanelL.add(dateLabel);
		datePanelL.setBackground(Color.white);
		updatePanel.add(datePanelL);

		// 날짜 패널(오른쪽)

		datePan = imprtJDatePickerEx();
		datePan.setLayout(new FlowLayout(FlowLayout.LEFT));
		datePan.setBackground(Color.white);
		updatePanel.add(datePan);

		// 금액 패널(왼쪽)
		amountPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel amountLabel = new JLabel("금    액 : ", 10);
		amountPanelL.add(amountLabel);
		amountPanelL.setBackground(Color.white);
		updatePanel.add(amountPanelL);

		// 금액 패널(오른쪽)
		amountPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		amountField = new JTextField(20);
		amountPanelR.add(amountField);
		amountPanelR.setBackground(Color.white);
		updatePanel.add(amountPanelR);

		// 유형 패널(왼쪽)
		typePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel typeLabel = new JLabel("유    형 : ", 10);
		typePanelL.add(typeLabel);
		typePanelL.setBackground(Color.white);
		updatePanel.add(typePanelL);

		// 유형 패널(오른쪽)
		typePanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		String[] exportType = { "급여", "이자", "기타" };
		JComboBox typeBox = new JComboBox(exportType);
		typePanelR.add(typeBox);
		typePanelR.setBackground(Color.white);
		updatePanel.add(typePanelR);

		// 비고 패널(왼쪽)
		memoPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel memoLabel = new JLabel("비    고 : ", 10);
		memoPanelL.add(memoLabel);
		memoPanelL.setBackground(Color.white);
		updatePanel.add(memoPanelL);

		// 비고 패널(오른쪽)
		memoPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		JTextField memoField = new JTextField(20);
		memoPanelR.add(memoField);
		memoPanelR.setBackground(Color.white);
		updatePanel.add(memoPanelR);

		mainPanel.add(btnsPanel);
		mainPanel.add(updatePanel);

		add(mainPanel);
		setVisible(true);
	}

	public JScrollPane totalCheck() {
		String[] header = {"날짜", "금        액", "구분", "비        고"};
		importModel[0] = ic.getImport(header);
		
	    totalTable = new JTable(importModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		totalTable.addMouseListener(new MyMouseListener1());
			
			
		
		return scrollpane;
	}


	public JScrollPane dayCheck() {
		String[] header = {"날짜", "금        액", "구분", "비        고"};
		importModel[0] = ic.getImport(header);
		
		totalTable = new JTable(importModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	public JScrollPane monthCheck() {
		String[] header = {"날짜", "금        액", "구분", "비        고"};
		importModel[0] = ic.getImport(header);
		
		JTable totalTable = new JTable(importModel[0]);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(20);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(JLabel.RIGHT);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrRight);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		return scrollpane;
	}

	// 전체 조회 패널
	public JPanel totalPanel() {
		totalPanel = new JPanel();
		totalPanel.setBounds(0, 0, 800, 600);
		totalPanel.setBackground(Color.white);
		totalPanel.add(totalCheck());
		return totalPanel;
	}

	// 일별 조회
	public JPanel dayPanel() {
		dayPanel = new JPanel();
		dayPanel.setBounds(100, 50, 1000, 600);
		dayPanel.setBackground(Color.white);
		dayPanel.add(dayCheck());
		return dayPanel;
	}

	// 월별 조회
	public JPanel monthPanel() {
		monthPanel = new JPanel();
		monthPanel.setBounds(100, 50, 1000, 600);
		monthPanel.setBackground(Color.white);
		monthPanel.add(monthCheck());
		return monthPanel;
	}
//캘린더
	public JPanel imprtJDatePickerEx() {
		JPanel j1 = new JPanel();
		// 현재 날짜를 가져옴...
		LocalDate now = LocalDate.now();
		int year = now.getYear();// 년도 저장
		int month = now.getMonthValue();// 월 저장
		int day = now.getDayOfMonth();// 일 저장

		
		UtilDateModel model = new UtilDateModel();
		
		model.setDate(year, month - 1, day);// 현재날짜를 표시
		model.setSelected(true); // 텍스트 필드에 보이기
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new Dateformet());
		
		j1.add(datePicker);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		date = dateFormat.format(model.getValue());
		
		// 날짜가 변경될 때마다 호출되는 listener 추가
		model.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if ("value".equals(evt.getPropertyName()) && "value" != null) {
					date = dateFormat.format(model.getValue());
					datelist[num] = date;

					tabPanel.setSelectedIndex(1);

					if (datelist[0] != null) {

						if (dpc.importsearch(datelist)) {
							System.out.println("검색 성공");

							datelist[0] = null;
						} else {
							JOptionPane.showMessageDialog(null, "선택날짜에 내용이 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
							System.out.println("검색 실패");
							datelist[0] = null;
						}

					}

				}

			}
		});
		return j1;

	}

}
