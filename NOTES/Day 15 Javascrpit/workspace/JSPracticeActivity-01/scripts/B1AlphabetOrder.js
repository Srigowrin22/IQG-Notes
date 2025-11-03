function alphabetOrder(str) {
  return str.toLowerCase().split('').sort().join('');
}

console.log(alphabetOrder('Javascript')); 