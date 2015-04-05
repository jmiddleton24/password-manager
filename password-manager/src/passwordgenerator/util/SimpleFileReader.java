package passwordgenerator.util;
import java.io.File; //for File class
import java.io.FileNotFoundException; //for FileNotFoundException
import java.util.Scanner; //for Scanner class
import java.util.ArrayList;//for LinkedList class
import java.util.InputMismatchException; //for InputMismatchException

public class SimpleFileReader {
	private File in; //file variable
	private Scanner input; //for taking in input from the file
	private ArrayList<String> list; //for holding the input

	/**
	 * A constructor for <code>SimpleFileReader</code> that takes in a
	 * <code>File</code> as an argument and stores it in a member variable.
	 * 
	 * @param file
	 */
	public SimpleFileReader(File file)
	{
		in = file.getAbsoluteFile();
	}

	/**
	 * This method returns an <code>ArrayList<String></code> that contains data collected
	 * from the file being read in
	 * @return 		ArrayList<String> containing data tokens
	 */
	public ArrayList<String> processFile() throws FileNotFoundException
	{
		try
		{
			input = new Scanner(in);
			//input.useDelimiter("[|]"); //ignore '|' characters
			list = new ArrayList<>(); //token holder
			while(input.hasNext())
			{
				list.add(input.next()); //add Strings to list
			}
			input.close(); //close the file
		}
		catch(FileNotFoundException e) //catch FileNotFoundException
		{
			throw new FileNotFoundException();
		}
		catch(InputMismatchException e)//catch non-String cases
		{
			System.out.println("Encountered input that was not of type String");
		}
		return list; 
	}




}
