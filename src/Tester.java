
public class Tester {

	public static void main(String[] args) {
		listTest();
		exampleTest();
		sameKeyTest();
		removeHotKeyTest();
		HotKeyTest();
		finalTest();
		System.out.println("done!");
	}
	
	public static void listTest() {
		WarmestHistoryList<Integer,String> list = new WarmestHistoryList<Integer,String>();
		/*
		list.insert(1,"one");
		list.insert(2,"two");
		list.remove(1);
		list.remove(2);
		 */
		
		
		list.insert(1,"one");
		list.insert(2,"two");
		list.insert(3,"three");
		list.insert(4,"four");
		list.insert(5,"five");
		list.remove(3);
		list.remove(5);
		list.remove(1);
		//System.out.println("done!");
		
	}
	
	public static void exampleTest() {
		
		Warmest<Integer,String> warmest = new Warmest<>();
		
		warmest.put(1,"hello");
		String answer = "hello";
		String myAnswer = warmest.getWarmest(); //returns "hello"
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"hello\"");
		
		warmest.put(2, "warmest");
		answer = "warmest";
		myAnswer = warmest.getWarmest(); //returns "warmest"
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"warmest\"");
		 
		myAnswer = warmest.get(1); //returns "hello"
		answer = "hello";
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"hello\"");
		
		
		myAnswer = warmest.getWarmest(); //returns "hello"
		answer = "hello";
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"hello\"");
		
		
		myAnswer = warmest.remove(1); //returns "hello"
		answer = "hello";
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"hello\"");
		
		
		answer = "warmest";
		myAnswer = warmest.getWarmest(); //returns "warmest"
		if(!myAnswer.equals(answer)) 
			System.out.println("warmest.getWarmest() != \"warmest\"");
		
		//System.out.println("exampleTest() done!");
		
	}

	public static void sameKeyTest() {
		Warmest<Integer,String> warmest = new Warmest<>();
		
		String val = warmest.getWarmest();
		if(val != null)
			System.out.println("val !=null");
		
		warmest.put(1, "1");
		val = warmest.getWarmest();
		if(!val.equals("1"))
			System.out.println("val != 1");
		
		warmest.put(1, "2");
		val = warmest.getWarmest();
		if(!val.equals("2"))
			System.out.println("val != 2");
		
		warmest.put(1, "3");
		val = warmest.getWarmest();
		if(!val.equals("3"))
			System.out.println("val != 3");
		
		warmest.put(1, "4");
		val = warmest.getWarmest();
		if(!val.equals("4"))
			System.out.println("val != 4");
		
		warmest.put(1, "5");
		val = warmest.getWarmest();
		if(!val.equals("5"))
			System.out.println("val != 5");
		
		
		//System.out.println("sameKeyTest() done!");
	}

	public static void removeHotKeyTest() {
		Warmest<Integer,String> warmest = new Warmest<>();
		warmest.put(1,"one");
		warmest.put(2,"two");
		warmest.put(3,"three");
		warmest.put(4,"four");
		warmest.put(5,"five");
		String s = "four";
		/* <1,"one">,<2,"two">,<3,"three">,<4,"four">,<5,"five"> */
		warmest.remove(5);
		/* <1,"one">,<2,"two">,<3,"three">,<4,"four"> */
		if(!s.equals(warmest.getWarmest()))
			System.out.println("wrong hot key, hot key = \"four\"");
		
		s = "four";
		warmest.remove(5);
		// do nothing - remove key that does not exist
		if(!s.equals(warmest.getWarmest()))
			System.out.println("wrong hot key, hot key = \"four\"");
		
		s = "three";
		warmest.remove(4);
		/* <1,"one">,<2,"two">,<3,"three"> */
		if(!s.equals(warmest.getWarmest()))
			System.out.println("wrong hot key, hot key = \"three\"");
		
		warmest.remove(1);
		/* <2,"two">,<3,"three"> */
		if(!s.equals(warmest.getWarmest()))
			System.out.println("wrong hot key, hot key = \"three\"");
		
		warmest.remove(2);
		/* <3,"three"> */
		if(!s.equals(warmest.getWarmest()))
			System.out.println("wrong hot key, hot key = \"three\"");
		
		warmest.remove(3);
		/* {} */
		s = warmest.getWarmest();
		if(s != null)
			System.out.println("wrong hot key, hot key = null");	
	}

	public static void HotKeyTest() {
		
		Warmest<Integer,String> warmest = new Warmest<>();
		warmest.put(1,"one");
		warmest.put(2,"two");
		warmest.put(3,"three");
		warmest.put(4,"four");
		warmest.put(5,"five");
		warmest.put(6,"six");
		warmest.put(7,"seven");
		String s = "";
		String answer = "";
		for(int i = 6 ; i>0 ; i--) {
			s = warmest.get(i);
			answer = warmest.getWarmest();
			if(!answer.equals(s))
				System.out.println(s+" is the hot key, not "+answer);	
		}
	}

	public static void finalTest() {
		
		Warmest<Integer,String> warmest = new Warmest<>();		
		if(!(warmest.getWarmest() == null))
			System.out.println("warmest is empty but V getWarmest() not returning null");
		
		if(!(warmest.remove(4) == null))
			System.out.println("remove key that Does not exist not returning null");
		
		String hotKeyVal ="";
		
		// insert (1,"A"),...,(26,"Z")
		String s = "A";
		char c = 'A';
		for(int i = 1 ; i<27 ; i++) {
			warmest.put(i, s);
			//System.out.println(i+","+c);
			c++;
			s = c+"";
		}
		// remove and update hot key test
		c ='Z';
		s ="Z";
		for(int i = 26 ; i>0 ; i--) {
			hotKeyVal = warmest.getWarmest();
			if(!hotKeyVal.equals(s))
				System.out.println("remove <"+i+","+s+"> failed, not update hot key");
			warmest.remove(i);
			c--;
			s = c+"";
		}
		
		// insert again
		c ='A';
		s = "A";
		for(int i = 1 ; i<27 ; i++) {
			warmest.put(i, s);
			c++;
			s = c+"";
		}
		
		// get(K k) test
		int[] arr = {1,5,8,12,17,26};
		String[] strArr = {"A","E","H","L","Q","Z"};
		for(int i = 0 ; i<arr.length ; i++) {
			hotKeyVal = warmest.get(arr[i]);
			if(!hotKeyVal.equals(strArr[i]))
				System.out.println("get("+arr[i]+") failed, need to be "+strArr[i]+" not "+hotKeyVal);
		}
	}

}
