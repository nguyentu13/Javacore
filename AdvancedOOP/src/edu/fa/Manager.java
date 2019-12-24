package edu.fa;

public class Manager {
	
	public static int numberOfProject = 10;
	
	public static void addProject() {
		numberOfProject++;
		System.out.println("New number of project: "+ numberOfProject);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Manager.numberOfProject);
		Manager.addProject();
		
		Manager projectManager = new Manager();
		Manager accountManager = new Manager();
		System.out.println(projectManager.numberOfProject);
		projectManager.addProject();
		System.out.println(accountManager.numberOfProject);
	}

}
