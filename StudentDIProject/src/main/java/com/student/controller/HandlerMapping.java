package com.student.controller;

public class HandlerMapping {

	public HandlerMapping() {	}

	public Controller createController(int no) {
		Controller controller = null;
		
		switch (no) {
		case 1: 
			controller = new RegisterStudentController();
			break;
		case 2:
			controller = new SearchStudent();
			break;
		case 3:
			controller = new UpdateStudentController();
			break;
		case 4:
			controller = new DeleteStudentController();
			break;
		case 5:
			controller = new PrintAllStudentController();
			break;
		case 6:
			controller = new SelectTopStudentController();
			break;
		case 7:
			controller = new SelectMajorAvgScoreController();
			break;
		case 8:
			controller = new SelectSchoolScholarshipController();
			break;
		}
		
		
		return controller;
	}
}








