import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class QuizApp extends Application {

    private int score = 0;
    private int questionIndex = 0;
    private List<Question> questions = new ArrayList<>();
    private RadioButton[] options = new RadioButton[4];
    private ToggleGroup group = new ToggleGroup();
    private Stage primaryStage;
    private Stage quizStage;
    private Label questionLabel;
    private Button backButton;
    private Button nextButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Set up categories
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Python", "Java", "C", "C++", "HTML", "CSS", "JavaScript", "MySQL");
        categoryComboBox.setValue("Pick your quiz language");

        // Start button
        Button startButton = new Button("Start Quiz");
        startButton.setOnAction(e -> startQuiz(categoryComboBox.getValue()));

        VBox mainLayout = new VBox(10, categoryComboBox, startButton);
        Scene mainScene = new Scene(mainLayout, 400, 200);

        primaryStage.setTitle("Programming Language Quiz");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void startQuiz(String category) {
        score = 0; // Reset score when starting a new quiz
        questionIndex = 0; // Reset question index

        loadQuestions(category);

        // Set up quiz UI
        VBox quizLayout = new VBox(10);
        questionLabel = new Label(questions.get(questionIndex).getQuestion());

        for (int i = 0; i < 4; i++) {
            options[i] = new RadioButton();
            options[i].setToggleGroup(group);
        }

        options[0].setText(questions.get(questionIndex).getOptions()[0]);
        options[1].setText(questions.get(questionIndex).getOptions()[1]);
        options[2].setText(questions.get(questionIndex).getOptions()[2]);
        options[3].setText(questions.get(questionIndex).getOptions()[3]);

        nextButton = new Button("Next");
        nextButton.setOnAction(e -> checkAnswerAndNext(category));

        backButton = new Button("Back");
        backButton.setOnAction(e -> goBackToPreviousQuestion());

        // Layout to handle first question with a special back button to change language
        Button backToLanguageButton = new Button("Choose Another Language");
        backToLanguageButton.setOnAction(e -> goBackToLanguageSelection());

        // Layout for first question
        if (questionIndex == 0) {
            quizLayout.getChildren().add(backToLanguageButton);
        }

        // Disable Back button for the first question
        backButton.setDisable(questionIndex == 0);

        HBox navigationButtons = new HBox(10, backButton, nextButton);
        quizLayout.getChildren().addAll(questionLabel, options[0], options[1], options[2], options[3], navigationButtons);

        Scene quizScene = new Scene(quizLayout, 400, 300);
        quizStage = new Stage();
        quizStage.setTitle("Quiz - " + category);
        quizStage.setScene(quizScene);

        quizStage.setOnCloseRequest(event -> System.exit(0));
        quizStage.show();
    }

    private void loadQuestions(String category) {
        questions.clear();
        switch (category) {
            case "Python":
                addQuestionsForPython();
                break;
            case "Java":
                addQuestionsForJava();
                break;
            case "C":
            	addQuestionsForC();
            	break;
            case "C++":
            	addQuestionsForCPP();
            	break;
            case "HTML":
            	addQuestionsForHtml();
            	break;
            case "CSS":
            	addQuestionsForCSS();
            	break;
            case "JavaScript":
            	addQuestionsForJavaScript();
            	break;
            case "MySQL":
            	addQuestionsForMySQL();
            	break;
            // Add similar methods for other categories
        }
    }

    private void addQuestionsForPython() {
        questions.add(new Question("What is the correct file extension for Python files?", new String[]{".py", ".java", ".cpp", ".html"}, 0));
        questions.add(new Question("Which function is used to output to the console in Python?", new String[]{"print()", "echo()", "log()", "out()"}, 0));
        questions.add(new Question("What is the maximum possible length of an identifier?", new String[]{"16", "32", "64", "None of these above"}, 3));
        questions.add(new Question("What do we use to define a block of code in Python language?", new String[]{"Key", "Brackets", "Indentation", "None of these"}, 2));
        questions.add(new Question("Which character is used in Python to make a single line comment?", new String[]{"/", "//", "#", "!"}, 2));
        questions.add(new Question("What is the method inside the class in python language?", new String[]{"Object", "Function", "Attribute", "Argument"}, 1));
        questions.add(new Question("Which of the following declarations is incorrect?", new String[]{"_x = 2", "__x = 3", "__xyz__ = 5", "None of these"}, 3));
        questions.add(new Question("Which of the following is not a keyword in Python language?", new String[]{"val", "raise", "try", "with"}, 0));
        questions.add(new Question("Which of the following functions is a built-in function in python language?", new String[]{"val()", "print()", "read()", "None of these"}, 1));
        questions.add(new Question("Which of the following commands will create a list?", new String[]{"list1 = list()", "list1 = []", "list1 = list([1, 2, 3])", "all of the mentioned"}, 3));
        // Add more Python questions
    }

    private void addQuestionsForJava() {
        questions.add(new Question("What is the default value of a boolean in Java?", new String[]{"false", "true", "0", "null"}, 0));
        questions.add(new Question("Which of the following is used to declare a constant in Java?", new String[]{"final", "constant", "static", "var"}, 0));
        questions.add(new Question("Which of the following option leads to the portability and security of Java?", new String[] {"Bytecode is executed by JVM", "The applet makes the Java code secure and portable", "Use of exception handling", "Dynamic binding between objects"}, 0));
        questions.add(new Question(" _____ is used to find and fix bugs in the Java programs.", new String[]{"JVM", "JRE", "JDK", "JDB"}, 3));
        questions.add(new Question("Which package contains the Random class?", new String[]{"java.util package", "java.lang package", "java.awt package", "java.io package"}, 0));
        questions.add(new Question("An interface with no fields or methods is known as a ______.", new String[]{"Runnable Interface", "Marker Interface", "Abstract Interface", "CharSequence Interface"}, 1));
        questions.add(new Question("Which of the following is an immediate subclass of the Panel class?", new String[]{"Dialog class", "Window class", "Frame class", "Applet class"}, 3));
        questions.add(new Question("Which of these classes are the direct subclasses of the Throwable class?", new String[]{"RuntimeException and Error class", "Exception and VirtualMachineError class", "Error and Exception class", "IOException and VirtualMachineError class"}, 2));
        questions.add(new Question("In which memory a String is stored, when we create a string using new operator?", new String[]{"Stack", "String memory", "Heap memory", "Random storage space"}, 2));
        questions.add(new Question("Which of the following is a reserved keyword in Java?", new String[]{"object", "strictfp", "main", "system"}, 1));
        // Add more Java questions
    }
    
    private void addQuestionsForC() {
        questions.add(new Question("All keywords in C are in ____________", new String[]{"LowerCase letters", "UpperCase letters", "CamelCase letters", "None of the mentioned"}, 0));
        questions.add(new Question("Which is valid C expression?", new String[]{"int my_num = 100,000;", "int my_num = 100000;", "int my num = 1000;", "int $my_num = 10000;"}, 1));
        questions.add(new Question("What is short int in C programming?", new String[]{"The basic data type of C", "Qualifier", "Short is the qualifier and int is the basic data type", "All of the mentioned"}, 2));
        questions.add(new Question("Which keyword is used to prevent any changes in the variable within a C program?", new String[]{"immutable", "mutable", "const", "volatile"}, 2));
        questions.add(new Question("Which of the following typecasting is accepted by C language?", new String[]{"Widening conversions", "Narrowing conversions", "Widening & Narrowing conversions", "None of the mentioned"}, 2));
        questions.add(new Question("What is an example of iteration in C?", new String[]{"for", "while", "do-while", "all of the mentioned"}, 3));
        questions.add(new Question("Functions can return enumeration constants in C?", new String[]{"true", "false", "depends on the compiler", "depends on the standard"}, 0));
        questions.add(new Question("Functions in C Language are always _________", new String[]{"Internal", "External", "Both Internal and External", "External and Internal are not valid terms for functions"}, 1));
        questions.add(new Question("What is #include <stdio.h>?", new String[]{"Preprocessor directive", "Inclusion directive", "File inclusion directive", "None of the mentioned"}, 0));
        questions.add(new Question("The C-preprocessors are specified with _________ symbol.", new String[]{"#", "$", "&", "None of the above"}, 0));
        // Add more C questions
    }
    
    private void addQuestionsForCPP() {
        questions.add(new Question("Which of the following is used for comments in C++?", new String[]{"/* comment */", "// comment */", "// comment", "both // comment or /* comment */"}, 3));
        questions.add(new Question("Which of the following extension is used for user-defined header file in c++?", new String[]{".hg", ".cpp", ".h", ".hf"}, 1));
        questions.add(new Question("Which of the following is not a type of Constructor in C++?", new String[]{"Default constructor", "Parameterized constructor", "Copy constructor", "Friend constructor"}, 3));
        questions.add(new Question("Which of the following approach is used by C++?", new String[]{"Left-right", "Right-left", "Bottom-up", "Top-down"}, 2));
        questions.add(new Question("Which is more effective while calling the C++ functions?", new String[]{"call by object", "call by pointer", "call by value", "call by reference"}, 3));
        questions.add(new Question("Which of the following is used to terminate the function declaration in C++?", new String[]{";", "]", ")", ":"}, 0));
        questions.add(new Question("Which keyword is used to define the macros in c++?", new String[]{"#macro", "#define", "macro", "define"}, 1));
        questions.add(new Question("What is Inheritance in C++?", new String[]{"Deriving new classes from existing classes", "Overloading of classes", "Classes with same names", "Wrapping of data into a single class"}, 0));
        questions.add(new Question("What is meant by a polymorphism in C++?", new String[]{"class having only single form", "class having four forms", "class having many forms", "class having two forms"}, 2));
        questions.add(new Question("Which concept allows you to reuse the written code in C++?", new String[]{"Inheritance", "Polymorphism", "Abstraction", "Encapsulation"}, 0));
        // Add more C++ questions
    }
    
    private void addQuestionsForHtml() {
        questions.add(new Question("HTML stands for -", new String[]{"HighText Machine Language", "HyperText and links Markup Language", "HyperText Markup Language", "None of these"}, 2));
        questions.add(new Question("The correct sequence of HTML tags for starting a webpage is -", new String[]{"Head, Title, HTML, body", "HTML, Body, Title, Head", "HTML, Head, Body, Title", "HTML, Head, Title, Body"}, 3));
        questions.add(new Question("Which of the following element is responsible for making the text bold in HTML?", new String[]{"<pre>", "<a>", "<b>", "<br>"}, 2));
        questions.add(new Question("Which of the following tag is used for inserting the largest heading in HTML?", new String[]{"<h3>", "<h1>", "<h5>", "<h6>"}, 1));
        questions.add(new Question("Which of the following tag is used to insert a line-break in HTML?", new String[]{"<b>", "<a>", "<pre>", "<br>"}, 0));
        questions.add(new Question("How to create an unordered list (a list with the list items in bullets) in HTML?", new String[]{"<li>", "<ol>", "<ul>", "<i>"}, 2));
        questions.add(new Question("Which of the following element is responsible for making the text italic in HTML?", new String[]{"<i>", "<italic>", "<it>", "<pre>"}, 0));
        questions.add(new Question("<input> is -", new String[]{"a format tag.", "an empty tag.", "All of the above", "None of the above"}, 1));
        questions.add(new Question("Which of the following tag is used to make the underlined text?", new String[]{"<i>", "<ul>", "<u>", "<pre>"}, 2));
        questions.add(new Question("How to create a checkbox in HTML?", new String[]{"<input type = \\\"checkbox\\\">", "<input type = \\\"button\\\">", "<checkbox>", "<input type = \"check\">"}, 0));
        // Add more HTML questions
    }
    
    private void addQuestionsForCSS() {
        questions.add(new Question("CSS stands for -", new String[]{"Cascade style sheets", "Color and style sheets", "Cascading style sheets", "None of the above"}, 2));
        questions.add(new Question("The property in CSS used to change the background color of an element is -", new String[]{"bgcolor", "color", "background-color", "All of the above"}, 2));
        questions.add(new Question("The property in CSS used to change the text color of an element is -", new String[]{"bgcolor", "color", "background-color", "All of the above"}, 1));
        questions.add(new Question("The CSS property used to control the element's font-size is -", new String[]{"text-style", "text-size", "font-size", "None of the above"}, 2));
        questions.add(new Question("The HTML attribute used to define the inline styles is -", new String[]{"style", "styles", "class", "None of the above"}, 0));
        questions.add(new Question("The HTML attribute used to define the internal stylesheet is -", new String[]{"<style>", "style", "<link>", "<script>"}, 0));
        questions.add(new Question("Which of the following property is used as the shorthand property for the padding properties?", new String[]{"padding-left", "padding-right", "padding", "All of the above"}, 2));
        questions.add(new Question("The CSS property used to make the text bold is -", new String[]{"font-weight : bold", "weight: bold", "font: bold", "style: bold"}, 0));
        questions.add(new Question("Are the negative values allowed in padding property?", new String[]{"Yes", "No", "Can't say", "May be"}, 1));
        questions.add(new Question("The CSS property used to specify the transparency of an element is -", new String[]{"overlay", "filter", "visibility", "opacity"}, 3));
        // Add more CSS questions
    }
    
    private void addQuestionsForJavaScript() {
        questions.add(new Question("Which of the following is correct about JavaScript?", new String[]{"JavaScript is an Object-Based language", "JavaScript is Assembly-language", "JavaScript is an Object-Oriented language", "JavaScript is a High-level language"}, 0));
        questions.add(new Question("JavaScript is a High-level language", new String[]{"Null type", "Undefined type", "Number type", "All of the mentioned"}, 3));
        questions.add(new Question("Which of the following object is the main entry point to all client-side JavaScript features and APIs?", new String[]{"Position", "Window", "Standard", "Location"}, 1));
        questions.add(new Question("Which of the following can be used to call a JavaScript Code Snippet?", new String[]{"Function/Method", "Preprocessor", "Triggering Event", "RMI"}, 0));
        questions.add(new Question("Which of the following scoping type does JavaScript use?", new String[]{"Sequential", "Segmental", "Lexical", "Literal"}, 2));
        questions.add(new Question("Why JavaScript Engine is needed?", new String[]{"Both Compiling & Interpreting the JavaScript", "Parsing the javascript", "Interpreting the JavaScript", "Compiling the JavaScript"}, 2));
        questions.add(new Question("Which of the following is not a framework?", new String[]{"JavaScript .NET", "JavaScript", "Cocoa JS", "jQuery"}, 1));
        questions.add(new Question("Which of the following is not an error in JavaScript?", new String[]{"Missing of Bracket", "Division by zero", "Syntax error", "Missing of semicolons"}, 1));
        questions.add(new Question("The type of a variable that is volatile is _______________", new String[]{"Volatile variable", "Mutable variable", "Immutable variable", "Dynamic variable"}, 1));
        questions.add(new Question("A hexadecimal literal begins with __________", new String[]{"00", "0x", " 0X", "Both 0x and 0X"}, 3));
        // Add more  questions JavaScript
    }
    
    private void addQuestionsForMySQL() {
        questions.add(new Question("What is MySQL?", new String[]{"An operating system", "A relational database management system (RDBMS)", "A programming language", "A web server"}, 1));
        questions.add(new Question("What does “MySQL” stand for?", new String[]{"My Sequence Query Language", "My Structured Query Language", "Mine Sequence Query Language", "My Special Query Language"}, 1));
        questions.add(new Question("Who developed MySQL?", new String[]{"MySQL AB", "Microsoft", "Oracle Corporation", "Sun Microsystems"}, 0));
        questions.add(new Question("Which type of database management system is MySQL?", new String[]{"Network", "Object-oriented", "Relational", "Hierarchical"}, 2));
        questions.add(new Question("Which of the following best describes the purpose of MySQL?", new String[]{"To store and retrieve data", "To create user interfaces", "To build websites", "To manage file systems"}, 0));
        questions.add(new Question("Which query language does MySQL use?", new String[]{"NoSQL", "JavaScript", "Structured Query Language (SQL)", "MongoDB Query Language"}, 2));
        questions.add(new Question("Which is the MySQL instance responsible for data processing?", new String[]{"MySQL server", "SQL", "MySQL client", "Server daemon program"}, 0));
        questions.add(new Question("MySQL is commonly used as the database component in which software stack?", new String[]{"LAMP", "MERN", "JAM", "MEAN"}, 0));
        questions.add(new Question("How are identifiers quoted in MySQL?", new String[]{"double quotes", "backticks", "can’t be quoted", "single quotes"}, 1));
        questions.add(new Question("Which of the following is case sensitive in MySQL?", new String[]{"Column names", "Event names", "Logfile group names", "Indexes"}, 2));
        // Add more MySQL questions
    }
    
    private void checkAnswerAndNext(String category) {
        Question currentQuestion = questions.get(questionIndex);
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();

        if (selectedRadioButton != null && selectedRadioButton.getText().equals(currentQuestion.getOptions()[currentQuestion.getCorrectOptionIndex()])) {
            score++;
        }

        questionIndex++;

        // Make sure questionIndex doesn't exceed the total number of questions
        if (questionIndex < questions.size()) {
            updateQuestion();
        } else {
            showResults(category);
        }
    }

    private void goBackToPreviousQuestion() {
        if (questionIndex > 0) {
            questionIndex--; // Go back to the previous question
            updateQuestion(); // Update UI with the previous question
        }
    }

    private void goBackToLanguageSelection() {
        quizStage.close(); // Close the quiz stage
        start(primaryStage); // Restart the application and show the language selection screen
    }

    private void updateQuestion() {
        Question nextQuestion = questions.get(questionIndex);
        questionLabel.setText(nextQuestion.getQuestion());

        for (int i = 0; i < 4; i++) {
            options[i].setText(nextQuestion.getOptions()[i]);
            options[i].setSelected(false);  // Deselect previously selected options
        }

        // Enable back button only after the first question
        backButton.setDisable(questionIndex == 0); // Disable back button for the first question, enable for others
    }

    private void showResults(String category) {
        // Close the quiz window
        quizStage.close();

        // Set up results stage
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Quiz Results");

        // Results layout
        VBox resultsLayout = new VBox(10);
        resultsLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        // Result message
        Label resultMessage = new Label("Quiz Completed! Your Score: " + score + " / " + questions.size());

        // Buttons for next steps
        Button retryButton = new Button("Retry Quiz");
        retryButton.setOnAction(e -> {
            resultsStage.close();
            startQuiz(category); // Restart the same category quiz
        });

        Button returnButton = new Button("Choose Another Language");
        returnButton.setOnAction(e -> {
            resultsStage.close();
            goBackToLanguageSelection(); // Return to the language selection screen
        });

        resultsLayout.getChildren().addAll(resultMessage, retryButton, returnButton);

        Scene resultsScene = new Scene(resultsLayout, 400, 200);
        resultsStage.setScene(resultsScene);

        resultsStage.show();
    }

    public static class Question {
        private String question;
        private String[] options;
        private int correctOptionIndex;

        public Question(String question, String[] options, int correctOptionIndex) {
            this.question = question;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectOptionIndex() {
            return correctOptionIndex;
        }
    }
}
