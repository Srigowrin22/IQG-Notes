var fs = require('fs');
 
fs.appendFile('Data1.txt' , 'This is New Content', (err, fd) => {
    if(err) {
        throw err;
    }
    else {
        console.log("Appending is Complete !");
    }
});