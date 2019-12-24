package edu.fa;

public class GCEvaluation {

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("It is finalized...");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		GCEvaluation gce1= new GCEvaluation();
//		GCEvaluation gce2=new GCEvaluation();
//		gce1= gce2;
//		gce1=null;
//		gce2=null;
		new GCEvaluation();
//		System.gc();
		Runtime.getRuntime().gc();
		int count=0;
		while(count++ <5) {
			try {
				Thread.sleep(1000);
				System.out.println("Waiting...");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
