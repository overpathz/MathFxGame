package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Buffer;
import util.Question;

public class PrimaryController {

    @FXML
    private TextField textEntry;

    @FXML
    private Text question;

    @FXML
    private Button answerButton;

    @FXML
    private Text timer;

    @FXML
    private Text afterResultText;

    Question randomQuestion;
    List<Node> elements;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Stage primaryStage = Buffer.getStage();
    int rightAnswers = 0;

    @FXML
    void processAnswer(ActionEvent event) {

        executorService.execute(()-> {
            if (textEntry.getText().equals(randomQuestion.correctAnswer)) {
                afterAnswer("RIGHT ANSWER", "green");
                timer.setText(String.valueOf(++rightAnswers));
            } else {
                afterAnswer("FAIL", "red");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            afterResultText.setVisible(false);

            randomQuestion = new Question(Question.generateRandomMathQuestion());
            question.setText(randomQuestion.toString());
            textEntry.clear();
            setVisibleForUiElements(true);
        });

    }

    @FXML
    void initialize() {

        actOnCloseStage();

        elements = new ArrayList<>();
        elements.addAll(List.of(question, textEntry, answerButton));

        question.setVisible(true);
        randomQuestion = new Question(Question.generateRandomMathQuestion());
        question.setText(randomQuestion.toString());

        timer.setVisible(true);
        timer.setText("0");
    }

    void setVisibleForUiElements(boolean visible) {
        elements.forEach(x->x.setVisible(visible));
    }

    void afterAnswer(final String answer, String color) {
        setVisibleForUiElements(false);
        afterResultText.setVisible(true);
        afterResultText.setStyle("-fx-fill: " + color);
        afterResultText.setText(answer);
    }

    void actOnCloseStage() {

        primaryStage.setOnCloseRequest(windowEvent -> {

            Platform.runLater(() -> {
                primaryStage.show();
                setVisibleForUiElements(false);
                afterResultText.setVisible(true);
                afterAnswer("Bye-Bye! You have " + rightAnswers + " right answers!", "blue");
            });

            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> System.exit(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        });
    }

}
