package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel1 extends JPanel implements ActionListener {
	// final 기호를 사용해 상수로 활용, 이미지의 크기 및 초기 위치
	private final int width = 100;
	private final int height = 100;
	private final int start_x = 0;
	private final int start_y = 130;
	// 이미지 파일을 담기 위한 BufferedImage 객체 변수를 생성
	private BufferedImage image;
	// 타이머 객체 생성
	private Timer timer;
	private int x, y;
	
	// 초기 이미지 조건 파일 객체 등 생성만 담당
	public MyPanel1() {
		// 패널 배경식 지정, 패널 크기와 JFrame 크기가 동일
		setBackground(new Color(255, 255, 255));
		setPreferredSize(new Dimension(width, height));
		// 추천 기본 크기를 알아서 지정
		
		// 이미지 파일 객체 생성
		File input = new File("pig.png");
		try {
			// input에 가져온 이미지 파일을 버퍼 이미지에 저장
			image = ImageIO.read(input);
		} catch(IOException e) {
			e.printStackTrace();
		} // 이미지 읽기
		x = start_x;
		y = start_y;
		
		// 타이머 생성자 - 1이상이면 어떤 값이 들어와도 동일한 결과가 수행되고, 0이하에서는 매우 빠른 속도로 움직인다.
		timer = new Timer(1, this);
		timer.start();
	}
	
	// 이미지 동작 메서드
	@Override
	// 그래픽을 출력하는 메서드
	public void paintComponent(Graphics g) {
		// 그래픽 객체를 paintComponent 메서드로 이동시킴
		super.paintComponent(g);
		
		// 이미지를 화면에 그리기
		g.drawImage(image, x, y, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 이벤트 발생 시 좌표를 해당 픽셀 만큼 이동
		x += 1;
		
		// 이미지 객체가 범위를 벗어난 초기 위치로 이동
		if(x > width) {
			x = start_x;
			y = start_y;
		}
		
		// 동작이 완료되면 repaint 메서드로 이미지를 갱신
		repaint();
	}
}
