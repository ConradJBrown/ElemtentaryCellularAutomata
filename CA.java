/* This program is for simulating the Elementary Cellular Automata in your Terminal
    The code will take in the values given in the terminal
    first the size you want each cell to be
    second the rule you want to implement to design it
    for example:
    java CA 20 90
    will output:
                    *
                   * *
                  *   *
                 * * * *
                *       *
               * *     * *
              *   *   *   *
             * * * * * * * *
          *   *           *   *
         * * * *         * * * *
      *   *   *   *   *   *   *   *
     * * * * * * * * * * * * * * * *
    *                               *
   * *                             * *
  *   *                           *   *
*/
import java.util.Arrays;

public class CA { 
    static int[] ruleset = new int[8]; //the 8 bit binary in an array
    public static void main(final String[] args) {
        Arrays.fill(ruleset, 0); // fills the array with 0 to fill in missing space of the binary bits
        final int n = Integer.parseInt(args[0]);
        final int numCells = 2 * n;
        //this will tak the integer and change into a binary string
        final String maxAmpStr = String.format("%8s",Integer.toBinaryString(Integer.parseInt(args[1]))).replace(' ','0');
        //This will seperate the binary string into an array for each character
        final char[] arr = maxAmpStr.toCharArray();
        
        for (int i = 0; i < maxAmpStr.length(); i++) {
            //This loop will add the binary code to the ruleset array to be used for the rule
            if (arr[i] == '1') {
                ruleset[(ruleset.length - 1) - i] = 1;
            } else if (arr[i] == '0') {
                ruleset[(ruleset.length - 1) - i] = 0;
            }
        }

        final int[] cells = new int[numCells]; // cellular automaton at time t
        final int[] old = new int[numCells]; // cellular automaton at time t-1
        cells[numCells / 2] = 1;

        for (int t = 1; t < n; t++) {

            // draw current row
            for (int i = 0; i < numCells; i++) {
                if (cells[i] == 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println("");

            // save current row
            for (int i = 0; i < numCells; i++)
                old[i] = cells[i];

            // update cells according to rule 90
            for (int i = 1; i < numCells - 1; i++) {
                final int a = old[i - 1];
                final int b = old[i];
                final int c = old[i + 1];
                cells[i] = rules(a, b, c);
            }
        }
    }
    //The code for the rules to be used when creating the next cells
    static int rules(final int a, final int b, final int c) {
        	if      (a == 1 && b == 1 && c == 1) return ruleset[7];
        	else if (a == 1 && b == 1 && c == 0) return ruleset[6];
        	else if (a == 1 && b == 0 && c == 1) return ruleset[5];
        	else if (a == 1 && b == 0 && c == 0) return ruleset[4];
        	else if (a == 0 && b == 1 && c == 1) return ruleset[3];
        	else if (a == 0 && b == 1 && c == 0) return ruleset[2];
        	else if (a == 0 && b == 0 && c == 1) return ruleset[1];
        	else if (a == 0 && b == 0 && c == 0) return ruleset[0];
        	return 0;
    }
 }