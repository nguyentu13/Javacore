package edu.fa;

public class ThreadPriority {

	public static void main(String[] args) {
		EducationThread et1= new EducationThread();
		et1.setName("A");
		et1.setPriority(Thread.MAX_PRIORITY);
		EducationThread et2= new EducationThread();
		et2.setName("B");
		et2.setPriority(Thread.MIN_PRIORITY);
		et1.run();
		et1.start();;
		et2.start();

	}

}
class EducationThread extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " - " + this.getPriority());
	}
	
}