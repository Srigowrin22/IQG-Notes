doAdd = (...args) => {
    let sum = 0;
    for (let i of args) {
        sum += i;
    }
    console.log("Sum is: " + sum);
}


doAdd(10, 20, 30, 440, 40);
doAdd(100);