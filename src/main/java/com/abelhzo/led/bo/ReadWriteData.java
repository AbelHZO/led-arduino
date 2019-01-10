package com.abelhzo.led.bo;

import java.awt.BorderLayout;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.abelhzo.led.connection.Connection;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

public class ReadWriteData implements SerialPortPacketListener, ChangeListener {
	
	public SerialPort arduinoPort;
	public static int PACKET_SIZE_IN_BYTES = 16;
	public PrintWriter output;
	public JSlider slider;
	
	public ReadWriteData() {
		JFrame frame = new JFrame("Dimmer LED");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		slider = new JSlider(0, 255, 0);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(50); 
		slider.setMinorTickSpacing(5);
		slider.addChangeListener(this);
		
		Connection.connectComm();
		arduinoPort = Connection.arduinoPort;
		output = new PrintWriter(arduinoPort.getOutputStream());
		
		arduinoPort.addDataListener(this);

		panel.add(slider, BorderLayout.CENTER);
		frame.add(panel);
		
		frame.setSize(320, 80);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	public void serialEvent(SerialPortEvent evt) {		
		
		byte[] buffer = new byte[evt.getSerialPort().bytesAvailable()];
		evt.getSerialPort().readBytes(buffer, buffer.length);
		String data = new String(buffer);

		System.out.print(data);

	}

	public int getPacketSize() {
		return PACKET_SIZE_IN_BYTES;
	}

	public void stateChanged(ChangeEvent e) {
		output.println(slider.getValue());
		output.flush();
	}

}