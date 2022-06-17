const URL = 'http://localhost:8080/sensor_data';

const getTemperature = async ()=> {
    const response = await fetch(URL);
    const data = await response.json();
    const length = data.length-1;
    let temperature = data[length].temperature;
    document.getElementById("temperature").innerHTML =` ${temperature} °C`
    console.log(data[length]);
}

const getHumidity = async ()=> {
    const response = await fetch(URL);
    const data = await response.json();
    const length = data.length-1;
    let humidity = data[length].humidity;
    document.getElementById("humidity").innerHTML =` ${humidity} %`
    console.log(data[length]);
}

window.onload = setInterval(getTemperature,500);
window.onload = setInterval(getHumidity, 500);




