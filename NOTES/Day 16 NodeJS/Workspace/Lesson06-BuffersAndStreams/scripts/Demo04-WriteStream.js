var fs = require('fs');
var data ='Node JS is Simply Superb ... ';
 
var writerStream = fs.createWriteStream('Empty.txt');
 
writerStream.write(data,'UTF8');
 
writerStream.end();
 
writerStream.on('finish', function(){
    console.log(' Writing is Complete ... ');
});
writerStream.on('error',function(err){
    console.log(err.stack);
})
console.log(" Program Ended ... ");