package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.ImportController;
import Controller.AmountController;
import Controller.DatePickerController;
import DatePickerEx.Dateformet;
import Model.AmountModel;
import Model.ImportModel;
import Model.UsersModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ImportView extends JPanel {
	UtilDateModel model = new UtilDateModel();
	static String date;
	public static String monthdate;
	int num = 0;
	String[] datelist = new String[2];
	DatePickerController dpc = new DatePickerController();

	JPanel panMain;
	private String day;
	private int price;
	private int imtype;
	private String memo;
	private JTable totalTable;
	private JTable dayTable;
	private JTable monthTable;

	private JScrollPane sp = new JScrollPane();
	private JTabbedPane tabPanel = new JTabbedPane();
	private JPanel mainPanel, btnPanel, checkPanel, totalPanel, dayPanel, monthPanel, btnsPanel, updatePanel,
			datePanelL, datePan, amountPanelL, amountPanelR, typePanelL, typePanelR, memoPanelL, memoPanelR;
	private JTextField amountField;
	private JTextField memoField;
	private JComboBox<String> typeBox;

	private Color colBack = new Color(255, 255, 255);
	private Color colBtn = new Color(240, 248, 255);
	private Color colHeader = new Color(255, 230, 230);
	private Color colTable = new Color(255, 230, 230);
	private Font f = new Font("나눔고딕", Font.BOLD, 16);
	private Font f2 = new Font("나눔고딕", Font.PLAIN, 15); // 테이블 폰트
	private Font f3 = new Font("나눔고딕", Font.PLAIN, 13); // 콤보박스 폰트
	private Font f4 = new Font("나눔고딕", Font.BOLD, 13); // 탭 폰트
	private Font fMain = new Font("나눔고딕", Font.BOLD, 17);
	private int selectrownum = 0;

	DefaultTableModel[] importModel = new DefaultTableModel[4];
	ImportController ic = new ImportController();

	AmountController amountController = new AmountController();

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

	// 전체조회테이블 마우스 클릭
	private class MyMouseListener1 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// 테이블에서 선택한 행 가져오기
			int selectRow = totalTable.getSelectedRow();
			selectrownum = selectRow + 1;
			try {
				
				// 선택한 행이 있는지 확인
				if (selectRow != -1) {
					String datetext = (String) totalTable.getValueAt(selectRow, 0);
					
					int year = 2000 + Integer.parseInt(datetext.substring(0, 2));
					int month = Integer.parseInt(datetext.substring(4, 5));
					int day = Integer.parseInt(datetext.substring(6));
					String selPrice = (String) totalTable.getValueAt(selectRow, 1);
					StringBuilder sb = new StringBuilder();
					for (String selPrice1 : selPrice.split(",|원")) {
						sb.append(selPrice1);
					}
					
					String sel = (String) totalTable.getValueAt(selectRow, 2);
					int num = 0;
					switch (sel) {
					case "급여":
						num = 0;
						break;
					case "이자":
						num = 1;
						break;
					case "고정수입":
						num = 2;
						break;
					case "기타":
						num = 3;
						break;
					}
					model.setDate(year, month - 1, day);
					typeBox.setSelectedIndex(num);
					amountField.setText(sb.toString());
					memoField.setText((String) totalTable.getValueAt(selectRow, 3));
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	// 일별조회테이블 마우스 클릭
	private class MyMouseListener2 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// 테이블에서 선택한 행 가져오기
			int selectRow = dayTable.getSelectedRow();
			selectrownum = selectRow + 1;
			try {
				
				// 선택한 행이 있는지 확인
				if (selectRow != -1) {
					String datetext = (String) dayTable.getValueAt(selectRow, 0);
					
					int year = 2000 + Integer.parseInt(datetext.substring(0, 2));
					int month = Integer.parseInt(datetext.substring(4, 5));
					int day = Integer.parseInt(datetext.substring(6));
					String selPrice = (String) dayTable.getValueAt(selectRow, 1);
					StringBuilder sb = new StringBuilder();
					for (String selPrice1 : selPrice.split(",|원")) {
						sb.append(selPrice1);
					}
					
					String sel = (String) dayTable.getValueAt(selectRow, 2);
					int num = 0;
					switch (sel) {
					case "급여":
						num = 0;
						break;
					case "이자":
						num = 1;
						break;
					case "고정수입":
						num = 2;
						break;
					case "기타":
						num = 3;
						break;
					}
					model.setDate(year, month - 1, day);
					typeBox.setSelectedIndex(num);
					amountField.setText(sb.toString());
					memoField.setText((String) dayTable.getValueAt(selectRow, 3));
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	// 월별테이블 마우스 클릭
	private class MyMouseListener3 extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				
				// 테이블에서 선택한 행 가져오기
				int selectRow = monthTable.getSelectedRow();
				selectrownum = selectRow + 1;
				// 선택한 행이 있는지 확인
				if (selectRow != -1) {
					String datetext = (String) monthTable.getValueAt(selectRow, 0);
					
					int year = 2000 + Integer.parseInt(datetext.substring(0, 2));
					int month = Integer.parseInt(datetext.substring(4, 5));
					int day = Integer.parseInt(datetext.substring(6));
					String selPrice = (String) monthTable.getValueAt(selectRow, 1);
					StringBuilder sb = new StringBuilder();
					for (String selPrice1 : selPrice.split(",|원")) {
						sb.append(selPrice1);
					}
					String sel = (String) monthTable.getValueAt(selectRow, 2);
					int num = 0;
					switch (sel) {
					case "급여":
						num = 0;
						break;
					case "이자":
						num = 1;
						break;
					case "고정수입":
						num = 2;
						break;
					case "기타":
						num = 3;
						break;
					}
					model.setDate(year, month - 1, day);
					typeBox.setSelectedIndex(num);
					amountField.setText(sb.toString());
					memoField.setText((String) monthTable.getValueAt(selectRow, 3));
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public void print() {
		Rectangle rect = panMain.getBounds();
		setPreferredSize(rect.getSize());
		// setBounds(7, 0, 1170, 765);
		setLayout(null);

		// main 패널 (tab 생성 포함)
		mainPanel = new JPanel();
		// mainPanel.setBorder(new LineBorder(Color.green, 8));
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setLayout(null);

		// 메뉴 패널(메인으로 돌아가기 버튼)
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.white);
		JButton menuBtn = new JButton("메인으로");
		menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBtn.setBackground(colBtn);
		menuBtn.setFont(fMain);
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
		tabPanel.setBackground(colTable);
		tabPanel.setFont(f4);

		checkPanel.add(tabPanel);
		checkPanel.setBounds(40, 50, 750, 600);
		mainPanel.add(checkPanel);

		// 버튼 패널(추가, 수정, 삭제)
		btnsPanel = new JPanel();
		btnsPanel.setBounds(870, 600, 250, 100);
		btnsPanel.setBackground(Color.white);

		// 추가 버튼
		JButton addBtn = new JButton("추가");
		addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addBtn.setBackground(colBtn);
		addBtn.setFont(f);
		btnsPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateText = date;
				if (!amountField.getText().isEmpty()) {
					int amount = Integer.parseInt(amountField.getText());
					String type = typeBox.getSelectedItem().toString();
					int type_id = 1;

					// 콤보박스에서 type을 받으면 type_id로 저장하는 반복문
					for (int i = 0; i < typeBox.getItemCount(); i++) {
						if (type.equals(typeBox.getItemAt(i))) {
							type_id = i + 1;
						}
					}
					String memo = memoField.getText();

					ImportModel importModel = new ImportModel(UsersModel.user.getId(), dateText, amount, type_id, memo);
					if (ic.add(importModel)) {

						// amount 추가 코드
						String amounttype = "수입";

						AmountModel amountModel = new AmountModel(dateText, amount, amounttype, type, memo);
						amountController.insert(amountModel);
						// amount 추가 코드 끝

						JOptionPane.showMessageDialog(null, "수입 내역에 기입되었습니다!", "성공", JOptionPane.PLAIN_MESSAGE);

						tabPanel.removeAll();
						tabPanel.add("전체", dayPanel.add(totalCheck()));
						tabPanel.add("일별", dayPanel.add(dayCheck()));
						tabPanel.add("월별", dayPanel.add(monthCheck()));
						tabPanel.revalidate();
						tabPanel.repaint();

					} else {
						JOptionPane.showMessageDialog(null, "수입 내역에 기입되지 않았습니다!", "실패", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "금액 부분을 채워주세요!", "실패", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// 수정 버튼
		JButton updateBtn = new JButton("수정");
		updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateBtn.setBackground(colBtn);
		updateBtn.setFont(f);
		btnsPanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String dateText = date;
				if (!amountField.getText().isEmpty() && selectrownum != 0) {
					int amount = Integer.parseInt(amountField.getText());
					String type = typeBox.getSelectedItem().toString();
					int type_id = 1;
					// 콤보박스에서 type을 받으면 type_id로 저장하는 반복문
					for (int i = 0; i < typeBox.getItemCount(); i++) {
						if (type.equals(typeBox.getItemAt(i))) {
							type_id = i + 1;
						}
					}
					String memo = memoField.getText();

					// amount 수정 코드
					String amounttype = "수입";
					AmountModel amountModel = new AmountModel(dateText, amount, amounttype, type, memo, selectrownum);
					amountController.update(amountModel);
					// amount 수정 코드 끝

					ImportModel importmodel = new ImportModel(UsersModel.user.getId(), dateText, amount, type_id, memo,
							selectrownum);

					if (ic.update(importmodel)) {
						System.out.println("gggggggggggggg");
						tabPanel.removeAll();
						tabPanel.add("전체", dayPanel.add(totalCheck()));
						tabPanel.add("일별", dayPanel.add(dayCheck()));
						tabPanel.add("월별", dayPanel.add(monthCheck()));
						tabPanel.revalidate();
						tabPanel.repaint();

						JOptionPane.showMessageDialog(null, "수입 내역에 수정되었습니다!", "성공", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "수입 내역에 수정되지 않았습니다!", "실패", JOptionPane.ERROR_MESSAGE);
					}
					amountField.setText("");
					memoField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "수정할 셀을 선택해주세요!!", "실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 삭제 버튼
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteBtn.setBackground(colBtn);
		deleteBtn.setFont(f);
		btnsPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateText = date;
				if(!amountField.getText().isEmpty() && selectrownum != 0) {
				int amount = Integer.parseInt(amountField.getText());
				String type = typeBox.getSelectedItem().toString();
				int type_id = 1;

				// 콤보박스에서 type을 받으면 type_id로 저장하는 반복문
				for (int i = 0; i < typeBox.getItemCount(); i++) {
					if (type.equals(typeBox.getItemAt(i))) {
						type_id = i + 1;
					}
				}
				String memo = memoField.getText();

				// amount 삭제 코드
				String amounttype = "수입";
				amountController.delete(selectrownum, amounttype);
				// amount 삭제 코드 끝

				ImportModel importmodel = new ImportModel(UsersModel.user.getId(), dateText, amount, type_id, memo,
						selectrownum);

				if (ic.delete(importmodel)) {
					tabPanel.removeAll();
					tabPanel.add("전체", dayPanel.add(totalCheck()));
					tabPanel.add("일별", dayPanel.add(dayCheck()));
					tabPanel.add("월별", dayPanel.add(monthCheck()));
					tabPanel.revalidate();
					tabPanel.repaint();

					JOptionPane.showMessageDialog(null, "수입 내역에 삭제되었습니다!", "성공", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "수입 내역에 삭제되지 않았습니다!", "실패", JOptionPane.ERROR_MESSAGE);
				}
				amountField.setText("");
				memoField.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "삭제할 셀을 선택해주세요!", "실패", JOptionPane.ERROR_MESSAGE);
			}
			}
		});

		// 수정 패널(추가, 수정, 삭제)
		updatePanel = new JPanel(new GridLayout(4, 2));
		updatePanel.setBounds(650, 250, 500, 300);
		updatePanel.setBackground(Color.white);

		// 날짜 패널(왼쪽)
		datePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽부터 정렬
		JLabel dateLabel = new JLabel("날    짜 : ", 10);
		dateLabel.setFont(f);
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
		amountLabel.setFont(f);
		amountPanelL.add(amountLabel);
		amountPanelL.setBackground(Color.white);
		updatePanel.add(amountPanelL);

		// 금액 패널(오른쪽)
		amountPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		amountField = new JTextField(20);
		amountField.setFont(f3);
		amountPanelR.add(amountField);
		amountPanelR.setBackground(Color.white);
		updatePanel.add(amountPanelR);

		// 유형 패널(왼쪽)
		typePanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel typeLabel = new JLabel("유    형 : ", 10);
		typeLabel.setFont(f);
		typePanelL.add(typeLabel);
		typePanelL.setBackground(Color.white);
		updatePanel.add(typePanelL);

		// 유형 패널(오른쪽)
		typePanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		String[] exportType = { "급여", "이자", "고정수입", "기타" };
		typeBox = new JComboBox<>(exportType);
		typeBox.setBackground(Color.white);
		typeBox.setFont(f3);

		typePanelR.add(typeBox);
		typePanelR.setBackground(Color.white);
		updatePanel.add(typePanelR);

		// 비고 패널(왼쪽)
		memoPanelL = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel memoLabel = new JLabel("비    고 : ", 10);
		memoLabel.setFont(f);
		memoPanelL.add(memoLabel);
		memoPanelL.setBackground(Color.white);
		updatePanel.add(memoPanelL);

		// 비고 패널(오른쪽)
		memoPanelR = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽부터 정렬
		memoField = new JTextField(20);
		memoField.setFont(f3);
		memoPanelR.add(memoField);
		memoPanelR.setBackground(Color.white);
		updatePanel.add(memoPanelR);

		mainPanel.add(btnsPanel);
		mainPanel.add(updatePanel);

		add(mainPanel);
		setVisible(true);
	}

	public JScrollPane totalCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		importModel[0] = ic.getImport(header);

		totalTable = new JTable(importModel[0]);
		totalTable.setFont(f2);
		totalTable.getTableHeader().setReorderingAllowed(false);
		totalTable.getTableHeader().setResizingAllowed(false);
		totalTable.setRowHeight(30);
		totalTable.getTableHeader().setBackground(colHeader);
		JScrollPane scrollpane = new JScrollPane(totalTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));
		scrollpane.getViewport().setBackground(colTable);

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel tcm = totalTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		totalTable.getColumn("날짜").setPreferredWidth(50);
		totalTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		totalTable.getColumn("금        액").setPreferredWidth(230);
		totalTable.getColumn("금        액").setCellRenderer(dtcrCenter);
		totalTable.getColumn("구분").setPreferredWidth(30);
		totalTable.getColumn("구분").setCellRenderer(dtcrCenter);
		totalTable.getColumn("비        고").setPreferredWidth(250);
		totalTable.getColumn("비        고").setCellRenderer(dtcrCenter);

		totalTable.addMouseListener(new MyMouseListener1());
		return scrollpane;
	}

	public JScrollPane dayCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		importModel[0] = ic.getImportdayselect(header);

		dayTable = new JTable(importModel[0]);
		dayTable.setFont(f2);
		dayTable.getTableHeader().setReorderingAllowed(false);
		dayTable.getTableHeader().setResizingAllowed(false);
		dayTable.setRowHeight(30);
		dayTable.getTableHeader().setBackground(colHeader);
		JScrollPane scrollpane = new JScrollPane(dayTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));
		scrollpane.getViewport().setBackground(colTable);

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel tcm = dayTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		dayTable.getColumn("날짜").setPreferredWidth(50);
		dayTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		dayTable.getColumn("금        액").setPreferredWidth(230);
		dayTable.getColumn("금        액").setCellRenderer(dtcrCenter);
		dayTable.getColumn("구분").setPreferredWidth(30);
		dayTable.getColumn("구분").setCellRenderer(dtcrCenter);
		dayTable.getColumn("비        고").setPreferredWidth(250);
		dayTable.getColumn("비        고").setCellRenderer(dtcrCenter);
		dayTable.addMouseListener(new MyMouseListener2());
		return scrollpane;
	}

	public JScrollPane monthCheck() {
		String[] header = { "날짜", "금        액", "구분", "비        고" };
		importModel[0] = ic.getImportmonthselect(header);

		monthTable = new JTable(importModel[0]);
		monthTable.setFont(f2);
		monthTable.getTableHeader().setReorderingAllowed(false);
		monthTable.getTableHeader().setResizingAllowed(false);
		monthTable.setRowHeight(30);
		monthTable.getTableHeader().setBackground(colHeader);
		JScrollPane scrollpane = new JScrollPane(monthTable);
		scrollpane.setPreferredSize(new Dimension(700, 550));
		scrollpane.getViewport().setBackground(colTable);

		// table의 data 가운데, 오른쪽 정렬하는 변수 선언
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel tcm = monthTable.getColumnModel();

		// "금액"만 오른쪽 정렬, 나머지는 가운데 정렬
		monthTable.getColumn("날짜").setPreferredWidth(50);
		monthTable.getColumn("날짜").setCellRenderer(dtcrCenter);
		monthTable.getColumn("금        액").setPreferredWidth(230);
		monthTable.getColumn("금        액").setCellRenderer(dtcrCenter);
		monthTable.getColumn("구분").setPreferredWidth(30);
		monthTable.getColumn("구분").setCellRenderer(dtcrCenter);
		monthTable.getColumn("비        고").setPreferredWidth(250);
		monthTable.getColumn("비        고").setCellRenderer(dtcrCenter);
		monthTable.addMouseListener(new MyMouseListener3());
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

	// 캘린더
	public JPanel imprtJDatePickerEx() {

		JPanel j1 = new JPanel();
		// 현재 날짜를 가져옴...
		LocalDate now = LocalDate.now();
		int year = now.getYear();// 년도 저장
		int month = now.getMonthValue();// 월 저장
		int day = now.getDayOfMonth();// 일 저장

		model.setDate(year, month - 1, day);// 현재날짜를 표시
		model.setSelected(true); // 텍스트 필드에 보이기

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new Dateformet());

		j1.add(datePicker);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		date = dateFormat.format(model.getValue());

		// JComboBox 생성
		String[] months = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
		JComboBox<String> monthComboBox = new JComboBox<>(months);

		monthComboBox.setBackground(Color.white);
		monthComboBox.setFont(f3);

		j1.add(monthComboBox);

		// JComboBox에서 월 선택시 JDatePicker의 월을 변경
		monthComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int selectedMonthIndex = monthComboBox.getSelectedIndex();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime((Date) model.getValue());
					calendar.set(Calendar.MONTH, selectedMonthIndex);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					// calendar.add(Calendar.DATE, -1);
					model.setValue(calendar.getTime());

					// 월탭 선택
					tabPanel.setSelectedIndex(2);
					monthdate = dateFormat.format(model.getValue());
					datelist[num] = monthdate;

					if (datelist[0] != null) {
						// 데이터가 있는지 확인하고 있다면 조건의맞는 값을 불러와 테이블을 지웠다가 다시 그린다.
						if (dpc.importmonthsearch(datelist)) {

							tabPanel.removeAll();
							tabPanel.add("전체", monthPanel.add(totalCheck()));
							tabPanel.add("일별", monthPanel.add(dayCheck()));
							tabPanel.add("월별", monthPanel.add(monthCheck()));

							tabPanel.revalidate();
							tabPanel.repaint();
							tabPanel.setSelectedIndex(2);
							datelist[0] = null;
						} else {
							JOptionPane.showMessageDialog(null, "선택날짜에 내용이 없습니다.", "실패", JOptionPane.ERROR_MESSAGE);
							datelist[0] = null;
						}

					}

				}
			}
		});

		datePanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tabPanel.setSelectedIndex(1);
				date = dateFormat.format(model.getValue());
				datelist[num] = date;
				if (datelist[0] != null) {

					if (dpc.importsearch(datelist)) {

						tabPanel.removeAll();
						tabPanel.add("전체", dayPanel.add(totalCheck()));
						tabPanel.add("일별", dayPanel.add(dayCheck()));
						tabPanel.add("월별", dayPanel.add(monthCheck()));
						tabPanel.revalidate();
						tabPanel.repaint();
						tabPanel.setSelectedIndex(1);
						datelist[0] = null;
					} else {
						// JOptionPane.showMessageDialog(null, "선택날짜에 내용이 없습니다.", "실패",
						// JOptionPane.ERROR_MESSAGE);
						datelist[0] = null;
					}

				}

			}
		});

		return j1;

	}

}