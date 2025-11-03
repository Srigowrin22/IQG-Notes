var fs = require('fs');
 
fs.writeFile('Data1.txt' , 'Hello World', (err, fd) => {
    if(err) {
        throw err;
    }
    else {
        console.log("Writing is Complete !");
    }
});