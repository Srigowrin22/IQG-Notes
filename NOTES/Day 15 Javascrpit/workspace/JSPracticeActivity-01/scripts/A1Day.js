function getCurrentDayAndTime() {
    const now = new Date();

    console.log(now);

    const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const dayName = days[now.getDay()];

    let hours = now.getHours();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();

    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    if (hours === 0) hours = 12; 

    const minutesStr = minutes < 10 ? '0' + minutes : minutes;
    const secondsStr = seconds < 10 ? '0' + seconds : seconds;

    console.log('Today is: ' + dayName);
    console.log('Current time : ' + hours + ampm + ':' + minutesStr + ':' + secondsStr);
}

getCurrentDayAndTime();
