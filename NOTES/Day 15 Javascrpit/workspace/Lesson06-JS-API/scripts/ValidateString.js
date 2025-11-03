var data = "John,Doe,32,1982,10,23,153.25,  A  ,";
 
var splitData = data.split(",");
 
var personDetails = {
    firstName : splitData[0],
    lastName : splitData[1],
    age: splitData[2],
    bornYear: splitData[3],
    bornMonth: splitData[4],
    bornDate: splitData[5],
    bonus: splitData[6],
    category: splitData[7].trim()
};
 
console.log(personDetails);