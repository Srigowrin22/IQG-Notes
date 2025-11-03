function RemoveChar(str, x){
    return str.slice(0, x) + str.slice(x + 1);
}
var str = "Hello World";
result = RemoveChar(str, 1);
console.log(result);