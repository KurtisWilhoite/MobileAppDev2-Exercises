import 'package:flutter/material.dart';
import 'screens/current_weather_screen.dart';
import 'screens/weather_forecast_screen.dart';

void main(){
  runApp(MaterialApp(
    title: 'Weather app',
    initialRoute: '/',
    routes: {
      '/': (context) => const CurrentWeatherScreen(),
      '/forecast': (context) => const WeatherForecastScreen(),
    })
  );
}
