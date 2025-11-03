// Node.js snippet illustrating usage of process global object

// a. How much memory is getting used (in bytes)
const memoryUsage = process.memoryUsage();
console.log("Memory Usage (bytes):", memoryUsage);

// b. Get the Process ID
console.log("Process ID:", process.pid);

// c. How much time it took to get the process in action (uptime in seconds)
console.log("Process Uptime (seconds):", process.uptime());

// d. How to kill a process and abort it
// You can terminate the current process by calling:
process.exit(0); // 0 means successful exit, non-zero means error

// Alternatively, to kill another process by PID:
// process.kill(pid);  // where pid is the target process id
