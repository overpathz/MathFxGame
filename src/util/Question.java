package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private String question;
    public String correctAnswer;

    public Question(String question) {
        this.question = question;
        this.correctAnswer = processCorrectAnswer(this.question);

        System.out.println(question);
        System.out.println(correctAnswer);
    }

    public String processCorrectAnswer(String question) {

        String correctAnswer = null;

        String[] strings = question.strip().split(" ");

        int firstNumber = Integer.parseInt(strings[0]);
        int secondNumber = Integer.parseInt(strings[2]);

        String operation = strings[1];

        switch (operation) {
            case "+" -> correctAnswer = String.valueOf(firstNumber + secondNumber);
            case "-" -> correctAnswer = String.valueOf(firstNumber - secondNumber);
            case "*" -> correctAnswer = String.valueOf(firstNumber * secondNumber);
        }

        return correctAnswer;
    }

    public static String generateRandomMathQuestion() {
        return generateRandomMathQuestion(1, 100);
    }

    public static String generateRandomMathQuestion(int min, int max) {

        int randomNumberInGivenRange1 = min + (int) (Math.random() * max);
        int randomNumberInGivenRange2 = min + (int) (Math.random() * max);

        List<String> operations = new ArrayList<>(List.of("+", "-", "*"));

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(operations);
        }

        String operation = operations.get((int) (Math.random() * operations.size()));

        String firstNumber = String.valueOf(randomNumberInGivenRange1);
        String secondNumber = String.valueOf(randomNumberInGivenRange2);

        if (operation.equals("-")) {
            if (randomNumberInGivenRange1 < randomNumberInGivenRange2) {
                firstNumber = String.valueOf(randomNumberInGivenRange2);
                secondNumber = String.valueOf(randomNumberInGivenRange1);
            }
        }

        String resultQuestion = "%s %s %s =".formatted(firstNumber, operation, secondNumber);

        System.out.println(resultQuestion);

        return resultQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return question;
    }
}
