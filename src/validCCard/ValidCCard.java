package validCCard;

import java.util.*;

public class ValidCCard 
{
	
	static Scanner console = new Scanner(System.in);

	public static Vector<Integer> v = new Vector<Integer>();
	public static String cardType = null;
	public static int actualCheckNum = 0;
	public static int prefixID = 0;
	
	public static void main(String[] args)
	{
		boolean pass1 = false;
		boolean pass2 = false;
		
		System.out.println("Please enter a valid Visa, Discover Card, or MasterCard credit card number:\n");
		
		String coolbeans = "";
		
		coolbeans = console.nextLine();

		for (int i = 0; i <= 15; i++)
		{
			String h = Character.toString(coolbeans.charAt(i));
			Integer a = Integer.parseInt(h);
			v.add(a);
			a = 0;
			h = null;
		}
		
		pass1 = Prefixer(v);
		pass2 = Checksum(v);
		
		if (pass1)
			System.out.println("Validating prefix.....");
		else
			{
				System.out.println("Invalid prefix! Exiting!");
				System.exit(0);
			}
		
		if (pass2)
			System.out.println("Validating checksum.....");
		else
			{
				System.out.println("Invalid checksum! Exiting!");
				System.exit(0);
			}
		
		System.out.println("\n\n\n\n\n---------------------------------------------------------------------------");
		System.out.println("\nCard Details:  ");
		System.out.println("Card Number:  " + coolbeans);
		System.out.println("Card Type:  " + cardType);
		System.out.println("Prefix ID:  " + prefixID);
		System.out.println("Checksum:  " + actualCheckNum);
		
		System.exit(0);
		
		
	};
	
	public static boolean Checksum(Vector<Integer> x)
	{
		boolean test = false;
		int sum = 0;
		Vector<Integer> doubled = new Vector<Integer>();
		int check = 0;
		int doubledCounter = 0;
		
		
		
		for (int a = 0; a <= 14; a++)
		{
			if (a%2 == 0)
			{
				doubled.add(x.get(a)*2);
				doubledCounter++;
				if(doubled.get(doubledCounter-1) > 9)
				{
					String aa = doubled.get(doubledCounter-1).toString();
					String ab = aa.substring(1);
					String ac = aa.replace(ab, "");
					
					int omfgfinally = Integer.parseInt(ab) + Integer.parseInt(ac);
					
					doubled.remove(doubledCounter-1);
					doubled.add(omfgfinally);
				}
			}
		}
		
		sum += doubled.get(0);
		doubledCounter--;
		
		for (int B = 1; B <= 14; B++)
		{
			if (B%2 == 0)
			{
				sum += doubled.get(doubledCounter);
				doubledCounter--;
			}
			else
				sum += x.get(B);
		}
		
		check = (sum * 9)%10;
		
		
		actualCheckNum = v.get(15);
		
		
		if (check == actualCheckNum)
			test = true;
		
		return test;
	};
	
	public static boolean Prefixer(Vector<Integer> y)
	{
		boolean result = false;
		boolean test1 = false;
		boolean test2 = false;
		boolean test3 = false;
		
		switch(v.get(0))
		{
		case 0: {break;}
		case 1: {break;}
		case 2: {break;}
		case 3: {break;}
		case 4: {
					test1 = true;
					prefixID += v.get(0);
					break;
				}
		case 5: {
					boolean master = MCTest(y);
					if (master)
					{
						test1 = true;
						test2 = true;
					}
					break;
				}
		case 6: {
					boolean discover = DiscoverTest(y);
					if (discover)
					{
						test1 = true;
						test2 = true;
						test3 = true;
					}
					break;
				}
		case 7: {break;}
		case 8: {break;}
		case 9: {break;}
		default: {break;}
		
		
		
		}
		
		if (test1)
		{
			if (!test2)
				cardType = "Visa Card";
			else if (!test3)
				cardType = "MasterCard";
			else
				cardType = "Discover Card";
			
			result = true;
		}
		
		
		
		
		return result;
	};
	
	public static boolean MCTest(Vector<Integer> mc)
	{
		String text = Integer.toString(mc.get(0)) + Integer.toString(mc.get(1));
		boolean answer = false;
		
		switch(text)
		{
			case "51":
			case "52":
			case "53":
			case "54":
			case "55":
				{
					prefixID += Integer.parseInt(text);
					answer = true;
					break;
				}
			default:
				{
					answer = false;
					break; 
				}
		}
		
		return answer;
	}
	
	public static boolean DiscoverTest(Vector<Integer> d)
	{
		String text = Integer.toString(d.get(0)) + Integer.toString(d.get(1));
		boolean answer = false;
		Integer num1 = Integer.parseInt(text);
		
		if (num1 == 65)
		{
			prefixID += num1;
			answer = true;
			return answer;
		}
		
		text += Integer.toString(d.get(2));
		num1 = Integer.parseInt(text);
		
		if (num1 <= 649 && num1 > 643)
		{
			prefixID += num1;
			answer = true;
			return answer;
		}
		
		text += Integer.toString(d.get(3));
		num1 = Integer.parseInt(text);
		
		if (num1 == 6011)
		{
			answer = true;
			prefixID += num1;
			return answer;
		}
		
		text += Integer.toString(d.get(4)) + Integer.toString(d.get(5));
		num1 = Integer.parseInt(text);
		
		if (num1 <= 622925 && num1 > 622125)
		{
			answer = true;
			prefixID += num1;
			return answer;
		}
		
		return answer;
	}
	
}
