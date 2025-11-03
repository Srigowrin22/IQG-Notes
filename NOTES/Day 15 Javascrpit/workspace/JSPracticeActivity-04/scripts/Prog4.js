var companies = [
  { name: "Company A", category: "Retail", start: 1985, end: 2000 },
  { name: "Company B", category: "Technology", start: 1990, end: 2030 },
  { name: "Company C", category: "Retail", start: 1995, end: 2020 }
];

companies.sort(function(a, b) {
  return a.end - b.end;
});

console.log(companies);

