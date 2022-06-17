import requests
import serial

ser = serial.Serial('COM5', 9600, timeout=1)
data = {"temperature": None, "humidity": None}

while 1:
    serial_data = ser.read(5).decode("ascii")
    if len(serial_data) > 0:
        if serial_data[0] == "t":
            data["temperature"] = serial_data[1:]
        elif serial_data[0] == "h":
            data["humidity"] = serial_data[1:]
    if data["temperature"] and data["humidity"]:
        requests.post('http://localhost:8080/sensor_data', json=data)
        data["temperature"] = None
        data["humidity"] = None
