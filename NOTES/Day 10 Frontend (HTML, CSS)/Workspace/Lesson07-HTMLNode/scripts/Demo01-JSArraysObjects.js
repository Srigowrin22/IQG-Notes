// Arrays in Javascript
var intArray = [1, 2, 3, 4, 5];
var stringArray = ["A", "B", "C"];

// Objects in Javascript
var personObj = {
    "name" : "Rahul",
    "age" : 46,

    displayDetails : function(){
        console.log("Person Details");
        console.log("Name: " + this.name + "\t" + "Age: " + this.age);
    }
}

var orderDetailsObj = {
    "orderId" : 1001,
    "items" : ["Keyboard", "Mouse", "LED"],
    "orderItems" : 15000,
    "active" : true
}

for(var i=0; i<intArray.length; i++){
    console.log(intArray[i]);
}

console.log("Object Details");
console.log("Name: " + personObj.name + "\t" + "Age: " + personObj.age);

personObj.displayDetails();