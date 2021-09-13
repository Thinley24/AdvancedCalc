package com.example.advancedcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView Screen;
    private Button C, AC, Mod, Div, Multiply, Sub, Add, Equal, Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    private String input, Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Screen = findViewById(R.id.screen);
        C = findViewById(R.id.c);
        AC = findViewById(R.id.ac);
        Mod = findViewById(R.id.modulo);
        Div = findViewById(R.id.divide);
        Multiply = findViewById(R.id.multiply);
        Sub = findViewById(R.id.subtract);
        Add = findViewById(R.id.add);
        Equal = findViewById(R.id.equal);
        Zero = findViewById(R.id.zero);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);
        Three = findViewById(R.id.three);
        Four = findViewById(R.id.four);
        Five = findViewById(R.id.five);
        Six = findViewById(R.id.six);
        Seven = findViewById(R.id.seven);
        Eight = findViewById(R.id.eight);
        Nine = findViewById(R.id.nine);
    }

    public void ButtonClick(View view) {
        Button button = (Button) view; // creating object for the button
        String data = button.getText().toString(); //taking input from the button that was pressed and converting it into string
        switch (data) {
            case "C":
                String newText = input.substring(0, input.length() - 1); // erases the most recent string
                input = newText; //latest input is the updated string after erasing the most recent string
                break;
            case "AC":
                input = ""; //clearing all inputs and functions
                break;
/*
String concatenation: The same + operator you use for adding two numbers can be used to concatenate two strings.
You can also use += , where a += b is a shorthand for a = a + b
*/
            case "%":
                solve();
                input += "%"; //String concatenation: concatenating inputs to %
                break;
            case "x":
                solve();
                input += "*"; // String concatenation: concatenating inputs to *
                break;
            case "=":
                solve();
                Answer = input; // answer is set as the input, so that we can use the answer as input for further operation
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")) { // taking input as +, - and /
                    solve();
                }
                input += data; // String concatenation: concatenating inputs to data
        }
        Screen.setText(input);
    }

    private void solve() {
/*
A split() method splits a String into multiple Strings given the delimiter that separates them.
The delimiter used are all the operators (%,/,*,-,+).

Parameters:
    regex - a delimiting regular expression
    Limit - the result threshold

Returns:
    An array of strings computed by splitting the given string.
*/
        if (input.split("%").length == 2) { //if the length of input which is separated by delimiter % is 2 then....
            String entry[] = input.split("%"); //enter the split inputs as an array called entry[]
            try {
                double mod = Double.parseDouble(entry[0]) % Double.parseDouble(entry[1]);
                //parse the splits (i.e. entry[0] & entry[1]) into double and find their modulo, then store it in 'mod'
                input = mod+""; // the existing calculated entries are removed and the answer 'mod' is set to input
            }
            catch (Exception e) {
                //Toggle error: A syntactical error in Java code
            }
        }
        else if (input.split("/").length == 2) {
            String entry[] = input.split("/");
            try {
                double divv = Double.parseDouble(entry[0]) / Double.parseDouble(entry[1]);
                input = divv+"";
            }
            catch (Exception e) {
                //Toggle error
            }
        }
        else if (input.split("\\*").length == 2) { // "\\*" represents special symbol of regex
            String entry[] = input.split("\\*");
            try {
                double pdt = Double.parseDouble(entry[0]) * Double.parseDouble(entry[1]);
                input = pdt+"";
            }
            catch (Exception e) {
                //Toggle error
            }
        }
        else if (input.split("-").length > 1) {
            String entry[] = input.split("-");

            //if the negative number is subtracted eg. -8-5 then do the following
            if (entry[0] == "" && entry.length==2){ // if the entry of index 0 is nil & entry length is 2 then
                entry[0] = 0+""; // add 0 to entry[0]
                //Now, -8-5 becomes 0-8-5
            }
            try {
                double subt = 0;
                if (entry.length==2) {
                    subt = Double.parseDouble(entry[0]) - Double.parseDouble(entry[1]);
                }
                else if (entry.length==3) {
                    subt = Double.parseDouble(entry[1]) - Double.parseDouble(entry[2]);
                }
                input = subt+"";
            }
            catch (Exception e) {
                //Toggle error
            }
        }
        else if (input.split("\\+").length==2) { // "\\+" represents special symbol of regex
            String entry[] = input.split("\\+");
            try {
                double sum = Double.parseDouble(entry[0]) + Double.parseDouble(entry[1]);
                input = sum+"";
            }
            catch (Exception e) {
                //toggle error
            }
        }

        // to remove the decimal .0 from an integer answer
        String integer_ans[] = input.split("\\."); // "\\." represents special symbol of regex
        if (integer_ans.length>1) { // if the answer is in decimal of length > 1. Eg. 4.0 and
            if (integer_ans[1].equals("0")) { // if the number at index 1 equals 0 then
                input = integer_ans[0]; // only take the first split as the input/answer. Eg. 4.0 ---> 4
            }
        }
        Screen.setText(input); // Finally, update the answer from decimal (eg. 4.0) to integer (Eg.4)
    }
}