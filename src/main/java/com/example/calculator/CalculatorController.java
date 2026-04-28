package com.example.calculator;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalculatorController {
    @FXML
    private Button equalSign;
    @FXML
    private TextField calculatorOutput;
    @FXML
    private VBox buttonContainer;
    @FXML
    private Button clearButton;
    @FXML
    public void initialize(){
        calculatorOutput.setEditable(false);
        clearButton.setOnAction(e -> {
            calculatorOutput.setText("");
        });
        buttonContainer.getChildren().forEach(node -> {
            HBox hbox = (HBox) node;
            hbox.getChildren().forEach(buttonNode -> {
                Button button = (Button) buttonNode;
                setButton(button);
            });
        });
    }

    public void setButton(Button button){
        button.setOnAction(e -> {
            if (!button.equals(equalSign)) {
                calculatorOutput.setText(calculatorOutput.getText() + button.getText());
            } else {
                calculatorOutput.setText(evalExpression(calculatorOutput.getText()));
            }

        });
    }
    String evalExpression(String expressionStr){
        Expression expression = new Expression(expressionStr);
        try {
            EvaluationValue result = expression.evaluate();
            return String.format("%7.2f",result.getNumberValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (EvaluationException e) {
            throw new RuntimeException(e);
        }
    }
}

