for(let year = 2014; year<=2025 ; year++){
    const date = new Date(year, 0, 1);
    if(date.getDay() === 0){
        console.log(`January 1st is sunday in the year ${year}`);
    }
}