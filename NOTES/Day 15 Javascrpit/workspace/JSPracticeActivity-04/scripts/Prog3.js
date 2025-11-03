const companies = [
  { name: "Company A", category: "Retail", start: 1985, end: 2000 },
  { name: "Company B", category: "Technology", start: 1990, end: 2010 },
  { name: "Company C", category: "Retail", start: 1995, end: 2020 }
];

const container = document.getElementById('companyContainer');

companies.forEach(company => {
  if (company.category === "Retail") {
    company.start += 1;

    const div = document.createElement('div');
    div.innerHTML = `
      <p>Name: ${company.name}</p>
      <p>Category: ${company.category}</p>
      <p>Start: ${company.start}</p>
      <p>End: ${company.end}</p>
    `;
    container.appendChild(div);
  }
});
