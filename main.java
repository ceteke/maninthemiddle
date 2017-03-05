public class main {

	public static void main(String[] args) {
		boolean isEav = true; //Set to true to add eavsdropper.s
		Alice A = new Alice(256); //Input is security parameter.
		Bob B = new Bob();
		
		if(isEav){
			Eav E = new Eav();
			E.eavsdrop(A, B);
			System.out.println("EAV Key Alice: " + E.getKeyA());
			System.out.println("Alice key: " + A.getKey());
			System.out.println("EAV Key Bob: " + E.getKeyB());
			System.out.println("Bob key: " + B.getKey());
			try {
				A.sendMessageToBob(B, E, "Test");
				System.out.println();
				B.sendMessageToAlice(A, E, "Test2");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		B.getFromAlice(A.G, A.g, A.hA);
		A.getFromBob(B.hB);
		System.out.println("Bob key: " + B.getKey());
		System.out.println("Alice key: " + A.getKey());
		try {
			B.sendMessageToAlice(A, "Hello");
			A.sendMessageToBob(B, "Hi there");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
