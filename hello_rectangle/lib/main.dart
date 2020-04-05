import 'package:flutter/material.dart';

class HelloRectangle extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Center(
      child: Container(
        color: Colors.pink,
        height: 400.0,
        width: 300.0,
        child: Center(
          child: Text('Hello Vamsi!', style: TextStyle(fontSize: 40.0),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(
    MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('Hello Vamsi'),
        ),
        body: HelloRectangle(),
      ),
    ),
  );
}
