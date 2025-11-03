var givenNumber = new Number("09876.098");
console.log(givenNumber);
 
var convertedRes = givenNumber.toLocaleString("en-US",{
    style : "currency",
    currency : "USD",
    maximumFractionDigits : 2
})

console.log(convertedRes);