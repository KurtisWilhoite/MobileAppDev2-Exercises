class Person {
  String firstName;
  int age;
  int? height;
  int? weight;

  Person(this.firstName, this.age, [this.height, this.weight]);

  Person.smallPerson(this.firstName, this.age, [this.weight = null])
      : this.height = 50 {}

  //Setters
  set setName(String name) {
    this.firstName = name;
  }

  set setAge(int newAge) {
    this.age = newAge;
  }

  set setHeight(int newHeight) {
    this.height = newHeight;
  }

  set setWeight(int newWeight) {
    this.weight = newWeight;
  }

  //Getters
  String get getName {
    return this.firstName;
  }

  int get getAge {
    return this.age;
  }

  int? get getHeight {
    return this.height;
  }

  int? get getWeight {
    return this.weight;
  }

  //Printer
  String printInfo() {
    String info = this.firstName + ", " + this.age.toString() + " years old";
    if (height != null) {
      String heightInfo = ", " + this.height.toString() + "cm";
      info += heightInfo;
    }
    if (weight != null) {
      String weightInfo = ", " + this.weight.toString() + "kg";
      info += weightInfo;
    }
    return info;
  }
}

class Student extends Person {
  String id;
  int credits;

  Student(String firstName, int age, this.id, this.credits,
      [int? height = null, int? weight = null])
      : super(firstName, age, height, weight);

  //Setters
  set setId(String newId) {
    this.id = newId;
  }

  set setCredits(int newCredits) {
    this.credits = newCredits;
  }

  //Getters
  String get getId {
    return this.id;
  }

  int get getCredits {
    return this.credits;
  }

  //Printer
  String printInfo() {
    String info = this.firstName + ", " + 
    this.age.toString() + " years old, "+
    "#"+ this.id + ", "+
    this.credits.toString()+ " credits";

    if (height != null) {
      String heightInfo = ", " + this.height.toString() + "cm";
      info += heightInfo;
    }
    if (weight != null) {
      String weightInfo = ", " + this.weight.toString() + "kg";
      info += weightInfo;
    }
    return info;
  }
}

void main() {
  var list = [];
  list.add(new Person("Pekka", 40));
  list.add(new Person("Samuel", 23, 180, 90));
  list.add(new Person.smallPerson("Kalle", 19));
  list.add(new Person.smallPerson("Thomas", 29, 80));
  list.add(new Student("Samantha", 17, "SSFE123", 132));
  list.add(new Student("Rick", 15, "SSFE09", 93, 164, 60));

  list.forEach((element) => print(element.printInfo()));
}
