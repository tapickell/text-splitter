package TextSplitter;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Created by IntelliJ IDEA.
 * User: todd pickell
 * Date: 3/11/12
 * Time: 11:13 AM
 * This program was designed to assist people (my brother in-law) with their Comp I homework
 * Copy the body (not any titles or  name, class, date) of your paper into a txt file
 * then run this program and enter in the full path of the file (/Users/%yourName&/Desktop/myTextFile.txt)
 * it then creates a text file in the same directory that has Spit appended on the name (myTextFileSplit.txt)
 * This new txt file has every sentence on its own line w/ punctuation and line numbers.
 * Perfect for those pesky Comp I assignments where Mr. Aurthur has you write a paper and then
 * wants a second copy with all the sentences numbered and then label the type of sentence.
 */
public class Main {

    public static void main(String[] args)   throws FileNotFoundException
    {
        while (true)      // to infinity and beyond (till you hit a System.exit() anyways)
        {
            /**
             * get file name from user
             * this is full path of file
             */

	        JFileChooser fChooser = new JFileChooser();
	        int status = fChooser.showOpenDialog(null);
	        if(status == JFileChooser.APPROVE_OPTION)
	        {
		        File fileIn = fChooser.getSelectedFile();
		        String filePath = fileIn.getPath();

	            if (fileIn.exists())         //check for ioException
	            {
	                Scanner inputFile = new Scanner(fileIn);
	                String fileOutName = filePath.replace(".txt", "Split.txt");    //create new file name by changing old
	                File fileOut = new File(fileOutName);            // create new file object for writing out to

	                if (fileOut.exists())                   //check for ioException
	                {
		                int replace = JOptionPane.showConfirmDialog(null, "File already exists. Would you like to replace file?", "File Already Exists!", JOptionPane.YES_NO_OPTION);
	                    if(replace == JOptionPane.NO_OPTION)
	                    {
	                        JOptionPane.showMessageDialog(null, "Please try program again after removing file.");
		                    System.exit(0);
	                    }
	                    //noinspection ResultOfMethodCallIgnored
	                    fileOut.delete();        //remove file so new file can be created
	                }

	                PrintWriter outputFile = new PrintWriter(fileOut);

	                /**
	                 * uses start of new file as delim so it loads whole file into a String and trims white space
	                 */
	                String myString = inputFile.useDelimiter("\\A").next().trim();
	                // tokenizer is  end of sentence punctuation, true makes punctuation its own token to be used
	                StringTokenizer strToken = new  StringTokenizer(myString, ".!?", true);
	                int lineNumb = 1;         // used for displaying line numbers
	                while (strToken.countTokens() > 0)       //.hasTokens would cause errors at runtime
	                {
	                    String temp1 = strToken.nextToken();      // get sentence
	                    outputFile.print("\n" + lineNumb + ".  " + temp1.trim());   //print to file w/ line number
	                    outputFile.println(strToken.nextToken());                  //add punctuation back to sentence
	                    lineNumb++;                                               //increment line number
	                }
	                inputFile.close();
	                outputFile.close();                     //close files
	            }
	        }

	        int answer = JOptionPane.showConfirmDialog(null, "Want to split more files?", "More Files?", JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.NO_OPTION)
            {
                System.exit(0);
            }
        }
    }
}
