function newString(str) {
  if (str.length < 3) {
    return "String length must be 3 or more";
  }
  const lastThree = str.slice(-3);
  return lastThree + str + lastThree;
}

console.log(newString("JavaScript"));