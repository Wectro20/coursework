#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>

#include <LiquidCrystal.h>
#include "DHT.h"

#define DHTPIN 14

LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

char temperature[] = "Temp = 00.0 C";
char humidity[]    = "RH   = 00.0 %";
 
void setup() {
  Serial.begin(9600);
  delay(1000);
  lcd.begin(16, 2);
  dht.begin();
}

void loop() {
  delay(1000);
  int RH = dht.readHumidity() * 10;
  int Temp = dht.readTemperature() * 10;

  if (isnan(RH) || isnan(Temp)) {
    lcd.clear();
    lcd.setCursor(5, 0);
    lcd.print("Error");
    return;
  }

  if (Temp < 0) {
    temperature[6] = '-';
    Temp = abs(Temp);
  }
  else
    temperature[6] = ' ';
  temperature[7]   = (Temp / 100) % 10  + 48;
  temperature[8]   = (Temp / 10)  % 10  + 48;
  temperature[9] = '.';
  temperature[10]  =  Temp % 10 + 48;
  temperature[11]  = 223;        // Degree symbol ( °)
  if (RH >= 1000)
    humidity[6]    = '1';
  else
    humidity[6]    = ' ';
  humidity[7]      = (RH / 100) % 10 + 48;
  humidity[8]      = (RH / 10) % 10 + 48;
  humidity[9] = '.';
  humidity[10]     =  RH % 10 + 48;
  lcd.setCursor(0, 0);
  lcd.print(temperature);
  lcd.setCursor(0, 1);
  lcd.print(humidity);

  Serial.write("t");
  Serial.write(temperature[7]);
  Serial.write(temperature[8]);
  Serial.write(temperature[9]);
  Serial.write(temperature[10]);

  Serial.write("h");
  Serial.write(humidity[7]);
  Serial.write(humidity[8]);
  Serial.write(humidity[9]);
  Serial.write(humidity[10]);
}