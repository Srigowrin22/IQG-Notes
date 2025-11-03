function amountToCoins(amount, coins) {
  let result = [];
  for (let i = 0; i < coins.length; i++) {
    while (amount >= coins[i]) {
      result.push(coins[i]);
      amount -= coins[i];
    }
  }
  return result;
}

console.log(amountToCoins(46, [25, 10, 5, 2, 1])); 