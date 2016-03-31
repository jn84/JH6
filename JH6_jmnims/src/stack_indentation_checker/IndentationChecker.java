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

public class IndentationChecker
{
    Stack<Integer> indentStack = new Stack<Integer>();
    
    private int findFirstNonBlank(String line)
    {
    	for (int i = 0; i < line.length(); i++)
    		if (line.charAt(i) != ' ')
    			return i;
    	return -1;
    }
    
    private void processLine(String line, int lineNumber) throws BadIndentationException
    {
        int index = findFirstNonBlank(line);
        System.out.println(String.format("%03d", lineNumber) + ": " + line);
        
        if (index < 0)
        	return;
       
       if (indentStack.isEmpty())
    	   indentStack.push(index);
        
       else if (index > indentStack.peek())
    	   indentStack.push(index);

       while (index < indentStack.peek())
    	   indentStack.pop();
       
       if (index != indentStack.peek())
    	   throw new BadIndentationException("Index mismatch at line " + lineNumber);
    }
    
    public void checkIndentation(String fileName)
    {
        Scanner input = null;
    	
        indentStack.clear();
    	
        try 
        {
            input = new Scanner (new File(fileName));
            for (int i = 1; input.hasNextLine(); i++)
            	processLine(input.nextLine(), i);
        } 
        catch (BadIndentationException error)
        {
            System.out.println(error);
            System.out.println("******* " + fileName + " is NOT properly indented\n");
            return;
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Can't open file: " + fileName + "\n");
            System.out.println();
            return;
        }
        finally
        {
            if (input != null)
                input.close();
        }
        
        System.out.println("******* " + fileName + " is properly indented\n");
    }
    
    
    public static void main(String[] args) {

        IndentationChecker IndentationChecker = new IndentationChecker();
        
        for (int i = 0; i < args.length; i++)
        {
            System.out.println("Checking for proper indentation of file: " + args[i]);
            IndentationChecker.checkIndentation(args[i]);        
        }

    }


}
