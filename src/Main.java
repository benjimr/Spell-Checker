import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a mispelled word.");
		
		String word = in.nextLine();
		Dictionary d = new Dictionary("../words.txt");
		String match = d.findClosest(word);

		if(word == match)
		{
			System.out.println(word + " is the correct spelling");
		}
		else
		{
			System.out.println("Did you mean " + match + "?");

			System.out.println("Edit distance between: " + word + 
			" and: " + match + " is " + EditDistance.EditDist(word, match, true));
		}
	}
}
