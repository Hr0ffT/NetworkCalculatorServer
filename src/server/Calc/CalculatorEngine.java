package server.Calc;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorEngine {



    private final StringBuilder input;
    private final StringBuilder displayText;
    private final List<Character> operationSequence;
    private final List<Double> numberSequence;
    private final StringBuilder lastResult;
    private boolean calculated = false;
    private Character lastOperation;
    private Character lastButtonPressed;

    Sender sender;

    public CalculatorEngine(Sender sender) {
        this.sender = sender;
        this.input = new StringBuilder();
        this.lastResult = new StringBuilder();
        this.displayText = new StringBuilder();
        this.operationSequence = new ArrayList<>();
        this.numberSequence = new ArrayList<>();
    }

    public void receiveButtonText(String buttonText) {
        handleInput(buttonText.charAt(0));
    }

//    @Override
//    public void actionPerformed(ActionEvent event) {
//        JButton clickedButton= (JButton)event.getSource();
//        handleInput(clickedButton);
//    }

    private void calculate() {

        System.out.println();
        System.out.println("''Starting calculation''");

        int currentNumberIndex = 0;
        Double firstNum = numberSequence.get(currentNumberIndex++);
        Double secondNum;

        System.out.println(operationSequence.size() + " OPERATIONS: " + operationSequence);
        System.out.println(numberSequence.size() + " NUMBERS: " + numberSequence);

        for (char currentOperation : operationSequence) {
            secondNum = numberSequence.get(currentNumberIndex++);
                    System.out.println("Calculating: " + firstNum + " " + currentOperation + " " + secondNum);
            firstNum = perform(currentOperation, firstNum, secondNum);
        }
        System.out.println("The result is: " + firstNum);
        lastResult.setLength(0);
        lastResult.append(firstNum.toString());

        calculated = true;
        System.out.println("''Ending calculation''");
        System.out.println();
    }

    private void calculatePercent() {
        double percentage = numberSequence.remove(numberSequence.size()-1);
        operationSequence.remove(operationSequence.size()-1);

        calculate();
        clean();
        double firstValue = sbToDouble(lastResult);

        numberSequence.add(firstValue);
        operationSequence.add(lastOperation);

        double secondValue = firstValue * percentage/100;
        System.out.println();
        System.out.println("Calculating second value: " + firstValue + " * "  + percentage + " / " + 100);
        System.out.println("The result is: " + " = " + secondValue);
        numberSequence.add(secondValue);

        calculate();
    }

    private Double perform(Character operation, Double firstNum, Double secondNum) {
        double result = 0;

        if        (operation.equals('+')) {
            result = firstNum + secondNum;
        } else if (operation.equals('-')) {
            result = firstNum - secondNum;
        } else if (operation.equals('*')) {
            result = firstNum * secondNum;
        } else if (operation.equals('/')) {
            result = firstNum / secondNum;
        }

        return result;
    }



    private void display(Object text) {
        displayText.append(text);
        sender.send(cutString(displayText));
        System.out.println("Displaying " + cutString(displayText));

    }

    private void rememberNum () {

        numberSequence.add(sbToDouble(input));
        System.out.println("Remembering number: " + input);
    }

    private void clean() {
        System.out.println("Cleaning!");
        input.setLength(0);
        operationSequence.clear();
        numberSequence.clear();
        displayText.setLength(0);
    }

    private void handleInput(Character buttonText) {

//        Character buttonText = clickedButton.getText().charAt(0);

        if (isClearButton(buttonText)) {
            handleClear();
        } else if (isOperation(buttonText)) {
            handleOperation(buttonText);
        } else if (isEqualsButton(buttonText)) {
            handleEquals();
        } else if (isDotButton(buttonText)) {
            handleDot();
        } else if (isNum(buttonText)){
            handleNumber(buttonText);
        } else if (isPercent(buttonText)) {
            handlePercent();
        }

        lastButtonPressed = buttonText;
    }

    private void handleEquals() {
        System.out.println();
        System.out.println("''Pressed button '='''");
         if (input.length() > 0 && operationSequence.size() > 0) {
            rememberNum();
            calculate();
            displayText.setLength(0);
            display(lastResult);
            clean();
            input.append(lastResult);
            lastOperation = null;
        }
    }
    private void handleDot() {
        System.out.println();
        System.out.println("''Pressed button '.'''");
        if (calculated && operationSequence.size() == 0 && lastButtonPressed != '-' && !isDotButton(lastButtonPressed)) {
            clean();
            if (lastOperation != null && !isOperation(lastOperation)) {
                operationSequence.add(lastOperation);
                System.out.println("Adding actrin!");
            }
            calculated = false;
        }
        if (!input.toString().contains(".") && input.length() == 0) {
            input.append("0.");
            display("0.");
        } else if (!input.toString().contains(".")){
            input.append(".");
            display(".");
        }


    }
    private void handleClear() {
        System.out.println();
        System.out.println("''Pressed button 'Clear'''");
        clean();
        display(" ");
        lastResult.setLength(0);
        System.out.println("Last result is cleared");
        System.out.println();
    }
    private void handleOperation(Character buttonText){
        System.out.println(buttonText);
        System.out.println("''Pressed button: " + buttonText + " ''");

        if (operationSequence.size() == 0 && buttonText == '-' && input.length() == 0) {
            input.append(buttonText);
            display(buttonText);
        } else if (operationSequence.size() > 0 && isOperation(lastButtonPressed) && lastButtonPressed != buttonText) {

            operationSequence.remove(operationSequence.size()-1);
            displayText.setLength(displayText.length()-1);
            operationSequence.add(buttonText);
            System.out.println("Fixing operation " + operationSequence);
            display(buttonText);
            input.setLength(0);

        }
        System.out.println();

        lastOperation = buttonText;

        if (input.length() > 0 && !input.toString().equals("-")) {
            if (!calculated) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
                lastResult.setLength(0);
            }
            display(cutString(lastResult) + "" + buttonText);
            rememberNum();
            operationSequence.add(buttonText);
            System.out.println("Fixing operation " + operationSequence);
            input.append(buttonText);
            input.setLength(0);
            System.out.println("input cleared");
        }
    }
    private void handleNumber(Character buttonText) {
        System.out.println();
        System.out.println("''Pressed button: " + buttonText + "''");
        if (calculated && operationSequence.size() == 0 && lastButtonPressed != '-' && !isDotButton(lastButtonPressed)) {
            clean();
            if (lastOperation != null && !isOperation(lastOperation)) {
                operationSequence.add(lastOperation);
                System.out.println("Adding actrin!");
            }
            calculated = false;
        }
        input.append(buttonText);
        display(buttonText);
    }
    private void handlePercent() {

        System.out.println();
        System.out.println("''Pressed Percent button''");
        if (input.length() > 0 && operationSequence.size() > 0) {
            rememberNum();
            calculatePercent();
            display(lastResult);
            clean();

            input.append(lastResult);
            lastOperation = null;

        } else {
            JOptionPane.showMessageDialog(null, """
                1) Если надо узнать сколько будет N% от числа X, то надо набрать X х N% (без знака "=")
                например:
                500 х 3% (ответ сразу на экране: 15)

                2) если надо прибавить к числу X N%, то X плюс N%=
                например:
                1000 + 18% = 1180.
                
                3) Проценты прибавляются к результату предшествующих операций!
                например:
                45+20+5% = 68.25""");
        }
    }


    private boolean isNum(Character buttonText) {
        if (buttonText == null) {
            return false;
        }
        try {
            Double.parseDouble(buttonText.toString());
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
    private boolean isOperation(Character buttonText) {
        String operations = "+-*/";
        return operations.contains(buttonText.toString());
    }
    private boolean isDotButton(Character buttonText) {
        return buttonText.equals('.');
    }
    private boolean isEqualsButton(Character buttonText) {
        return buttonText.equals('=');
    }
    private boolean isClearButton(Character buttonText) {
        return buttonText.equals('C');
    }
    private boolean isPercent(Character buttonText) {
        return buttonText.equals('%');
    }

    private String cutString(StringBuilder stringBuilder) {
        String string = stringBuilder.toString();
        if (string.endsWith(".0")) {
            return  string.contains(".") ? string.replaceAll("0*$","").replaceAll("\\.$","") : string;
        } else return string;

    }
    private Double sbToDouble(StringBuilder stringBuilder) {
        return Double.parseDouble(stringBuilder.toString());
    }




}
