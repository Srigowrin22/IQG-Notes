const companies = [
    { name: "IQGateway", founded: 1990 },
    { name: "Zoho", founded: 1996 },
    { name: "Google", founded: 1998 },
    { name: "Deloitte", founded: 1845 }
];

companies.forEach(company => {
    if (company.founded > 1987) {
        console.log(company.name);
    }
});