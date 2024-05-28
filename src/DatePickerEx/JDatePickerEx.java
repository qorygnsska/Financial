package DatePickerEx;


import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class JDatePickerEx extends JFrame {


	public JDatePickerEx() {
		//현재 날짜를 가져옴
		LocalDate now= LocalDate.now();
		int year= now.getYear();//년도 저장
		int month=now.getMonthValue();//월 저장
		int day=now.getDayOfMonth();//일 저장
		setLayout(new BorderLayout());
		
		JPanel jp1 = new JPanel();
		
	
		//	SqlDateModel: 날짜 선택기는 선택한 날짜를 java.sql.Date 형식의 개체로 반환합니다
		// 날짜를 저장하고 관리하기 위해 UtilDateModel 객체를 생성합니다.
		UtilDateModel model = new UtilDateModel();
		//초기 날짜 설정  Month는 +1반환 해서 결과 월이 나옴 5월을 표기하고 싶으면 4로넣어야함
		// 텍스트 필드에 표시된 날짜의 기본 형식은 사용자의 요구에 맞지 않을 수 있습니다. 이 경우 javax.swing.JFormattedTextField.AbstractFormatter 클래스를 확장
		model.setDate(year, month-1, day);//현재날짜를 표시
		model.setSelected(true); //텍스트 필도에 보이기
		// 날짜를 선택할 수 있는 패널(JDatePanelImpl)을 생성하고, 앞서 생성한 model을 인자로 전달하여 연결합니다.
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		// 실제로 사용자에게 보여지는 날짜 선택 위젯(JDatePickerImpl)을 생성하고, 앞서 생성한 datePanel을 인자로 전달합니다.
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new Dateformet());
		
		jp1.add(datePicker);
		
		add(jp1,BorderLayout.CENTER);
		setBounds(300, 300, 400, 400);

		// 날짜가 변경될 때마다 호출되는 listener 추가
		model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName())&&"value"!=null) {
					String date=model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
					System.out.println(date);
					
					
				//new ExportView();
					
					
					// 선택한 날짜 출력
					// System.out.println(model.getValue());
				}

			}
		});
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new JDatePickerEx();

		
		
//		String[] date = planeDTO.getDate().split("-");
//		int dateY = Integer.parseInt(date[0]);
//		int dateM = Integer.parseInt(date[1]) - 1;
//		int dateD = Integer.parseInt(date[2]);
//		model.setDate(dateY, dateM, dateD);
//		model.setSelected(true);
	}
}