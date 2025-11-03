class Employee {
    private empNo: number;
    private name: string;
    private salary: number;
    private bonus: number;

    public getEmpNo(): number {
        return this.empNo;
    }

    public setEmpNo(empNo: number): void {
        this.empNo = empNo;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getSalary(): number {
        return this.salary;
    }

    public setSalary(salary: number): void {
        this.salary = salary;
    }

    public getBonus(): number {
        return this.bonus;
    }

    public setBonus(bonus: number): void {
        this.bonus = bonus;
    }

    constructor(givenEmpNo: number, givenEmpName: string,
        givenSalary: number, givenBonus: number) {
        this.empNo = givenEmpNo;
        this.name = givenEmpName;
        this.salary = givenSalary;
        this.bonus = givenBonus;
    }
    public displayDetails(): string {
        return `Employee Details [Employee Number : ${this.empNo} ,Employee Name : ${this.name} ,Employee salary : ${this.salary} ,Employee bonus : ${this.bonus} ]`;
    }

}


var empRef1 = new Employee(1001, "Rahul", 12345, 9876);
console.log(empRef1.displayDetails);