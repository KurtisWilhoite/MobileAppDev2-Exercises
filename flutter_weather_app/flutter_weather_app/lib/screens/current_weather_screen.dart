import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class CurrentWeatherScreen extends StatefulWidget {
  const CurrentWeatherScreen({Key? key}) : super(key: key);

  @override
  State<CurrentWeatherScreen> createState() {
    return _CurrentWeatherScreenState();
  }
}

class _CurrentWeatherScreenState extends State<CurrentWeatherScreen> {
  String cityName = "Tampere";
  String currentWeather = "Sunny";
  double temperature = 0;
  double windSpeed = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(cityName),
      ),
      body: Center(
        child: Column(
          children: [
            Text(currentWeather, style: TextStyle(fontSize: 40)),
            Text(temperature.toString()+"C", style: TextStyle(fontSize: 40)),
            Text(windSpeed.toString()+"m/s", style: TextStyle(fontSize: 40)),
            ElevatedButton(
              child: const Text('Get weather data'),
              onPressed: () {
                fetchWeatherData();
              },
            ),
          ],
        ),
      ),
    );
  }

    void fetchWeatherData() async{
    Uri url = Uri.parse('https://api.openweathermap.org/data/2.5/weather?q=${cityName}&units=metric&appid=6c433438776b5be4ac86001dc88de74d');
    final response = await http.get(url);
    if(response.statusCode == 200){
      var weatherData = json.decode(response.body);
      setState((){
        temperature = weatherData['main']['temp'];
        currentWeather = weatherData['weather'][0]['description'];
        windSpeed = weatherData['wind']['speed'];
      });
    }
  }
}

