const listItems = document.querySelectorAll('li');

listItems.forEach(function(li){
    const header = li.querySelector('h2');
    if(header){
        const boldWord = header.querySelectorAll('b');
        boldWord.forEach(function(bold){
            bold.style.backgroundColor = 'yellow';
        });
    }else{
        const boldWord = li.querySelectorAll("b");
        boldWord.forEach(function(bold){
            bold.style.backgroundColor = 'yellow';
        });
    }
});
