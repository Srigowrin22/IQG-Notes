import {Employees} from "./Demo02-Employee";

class Manager extends Employees{
    constructor(givenEmpNo: number, givenEmpName: string,
        givenSalary: number,private noOfReportees: number) {
        super(givenEmpNo, givenEmpName, givenSalary);
    }
    public displayDetails(): string {
        var details = super.displayDetails;
        return `${details} and the Manager has ${this.noOfReportees} Reportees.`;
    }
}

var manager1 = new Manager(1001, "Steven", 1500000000, 230);
console.log(manager1.displayDetails);