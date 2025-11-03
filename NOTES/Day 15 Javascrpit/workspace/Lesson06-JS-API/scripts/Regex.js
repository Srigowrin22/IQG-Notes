var regexp = "a*b"; // Zero or More a's Terminated By b
var inputValue ="xyzabab";
console.log(inputValue.match(regexp));
 
// Email Regex
var emailRegexp = new RegExp("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}");
var inputEmailValue="steven@oracle.com";
//console.log(inputEmailValue.test(emailRegexp));
 
console.log(emailRegexp.test(inputEmailValue));