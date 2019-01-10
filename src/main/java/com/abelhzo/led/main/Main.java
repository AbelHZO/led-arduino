package com.abelhzo.led.main;

import com.abelhzo.led.bo.ReadWriteData;

public class Main {

	public static void main(String[] args) {
		new ReadWriteData();
	}

}

/**
 * Codigo en arduino:
 * 
int LED = 6;
int brillo;

void setup() {
  pinMode(LED, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if(Serial.available() > 0) {
    brillo = Serial.parseInt();
    if(brillo > 0 and brillo < 256) {
      Serial.print(" B: ");
      Serial.print(brillo);
      Serial.println();
      analogWrite(LED, brillo);
    } else if(brillo == 0 && Serial.available() != 0) {
      analogWrite(LED, 0);
    }
  }
}

**/