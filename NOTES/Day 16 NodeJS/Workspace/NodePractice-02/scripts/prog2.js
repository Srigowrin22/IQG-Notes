// Node.js snippet to print a message every 1 second,
// and after 5 seconds print a final message and stop.

let count = 0;
const intervalId = setInterval(() => {
  count++;
  console.log(`Message ${count}`);

  if (count === 5) {
    console.log("Final message after 5 seconds!");
    clearInterval(intervalId);
  }
}, 1000);
