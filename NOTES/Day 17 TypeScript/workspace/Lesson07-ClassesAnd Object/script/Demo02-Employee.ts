export class Employees {

    private bonus: number;

    public getBonus(): number {
        return this.bonus;
    }

    public setBonus(bonus: number): void {
        this.bonus = bonus;
    }

    constructor(private empno: number, private name: string,
        private salary: number) {
        this.bonus = this.salary * 0.3;
    }
    public displayDetails(): string {
        return `Employee Details [Employee Number : ${this.empno} ,Employee Name : ${this.name} ,Employee salary : ${this.salary} ,Employee bonus : ${this.bonus} ]`;
    }

}


console.log(empRef1.displayDetails);