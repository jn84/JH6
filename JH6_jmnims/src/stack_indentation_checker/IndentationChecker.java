package stack_indentation_checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class BadIndentationException extends RuntimeException
{
    BadIndentationException(String error)
    {
        super(error);
    }
}

public class IndentationChecker{
    Stack<Integer> indentStack = new Stack<Integer>();
    
    private int findFirstNonBlank(String line)
    {
        // return index of first non-blank character
        // return -1 if the line doesn't contain a non-blank character
    	return -1;
    }
    
    private void processLine(String line, int lineNumber)
    {
        int index = findFirstNonBlank(line);
        
       // Skip blank lines ... i.e. return immediately
       
       // If the stack is empty, then push this index onto the stack and return        
        
       // If this index > than the top of the stack, then push this index onto the stack and return        
        
       // Pop off all Indentation indexes > index
                
       // At his point the top of the stack should match the current index.  If it 
       // doesn't throw a BadIndentationException.  In the error message, include the source Line Number
                    
    }
    
    public void checkIndentation(String fileName)
    {
        // Clear the stack
        
        Scanner input = null;
        try {
            input = new Scanner (new File(fileName));
            // read through the file line by line 
            // for each line, call processLine to check indentation
        } 
        catch (BadIndentationException error)
        {
            System.out.println(error);
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Can't open file: "+fileName);
        }
        finally
        {
            if (input != null)
                input.close();
        }
    }
    
    
    public static void main(String[] args) {

        IndentationChecker IndentationChecker = new IndentationChecker();
        
        for (int i=0; i < args.length; i++)
        {
            System.out.println("Processing file: " + args[i]);
            IndentationChecker.checkIndentation(args[i]);        
        }

    }


}
