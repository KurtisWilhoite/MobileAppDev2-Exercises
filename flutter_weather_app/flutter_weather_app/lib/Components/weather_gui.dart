import 'package:flutter/material.dart';

class WeatherGui extends StatelessWidget{
  final ButtonStyle style =
      ElevatedButton.styleFrom(textStyle: const TextStyle(fontSize: 20));

  @override
  Widget build(BuildContext context){
    return(Center(
      child: Container(
        width: 320,
        child: Card(
          color: Colors.white,
          elevation: 10,
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Container(
                  child: const Image(
                    image: AssetImage('graphics/cloudy.png'),
                  ),
                ),
                  const Text(
                    "Cloudy",
                    style: TextStyle(fontSize: 30),
                    textAlign: TextAlign.center,
                  ),
                Container(
                  child: const Text(
                    "-2C",
                    textAlign: TextAlign.center,
                  ),
                ),
                Container(
                  child: const Text(
                    "3.2 m/s",
                    textAlign: TextAlign.center,
                  ),
                ),
                ElevatedButton(
                  onPressed: () {},
                  child: Text('Get Weather Data'),
                )
              ],
            ),
          ),
        ),
      ),
    ));
  }
}