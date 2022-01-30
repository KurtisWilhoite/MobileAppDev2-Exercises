import 'package:flutter/material.dart';
import 'components/weather_gui.dart';

void main(){
  runApp(WeatherApp());
}

class WeatherApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Weather App",
      home: WeatherPage()
    );
  }
}

class WeatherPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Weather Forecast")
      ),
      body: WeatherGui(),
    );
  }

}