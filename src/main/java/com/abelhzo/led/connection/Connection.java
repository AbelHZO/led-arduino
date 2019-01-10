package com.abelhzo.led.connection;

import com.fazecast.jSerialComm.SerialPort;

public class Connection {

	public static SerialPort arduinoPort;
	
	public static void connectComm() {
		
		SerialPort serialPorts[] = SerialPort.getCommPorts();
		// arduinoPort = SerialPort.getCommPort("COM8");

		for (SerialPort serialPort : serialPorts) {
			String portName = serialPort.getDescriptivePortName();
			String systemPort = serialPort.getSystemPortName();
			System.out.println(systemPort + ": " + portName);

			if (systemPort.equals("COM8")) {
				arduinoPort = serialPort;
				break;
			}
		}

		arduinoPort.setComPortParameters(9600, 8, 1, 0);
		arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

		if (arduinoPort.openPort()) {
			System.out.println("Port Opened");
		} else {
			System.out.println("Port Failed to Open");
		}
		
	}

}
