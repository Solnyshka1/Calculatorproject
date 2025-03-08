![image](https://github.com/user-attachments/assets/f1d8c6b4-3a62-4682-acbf-9054c527f37e)
Project Report: Calculator
This project is a simple calculator that can perform basic and advanced mathematical operations. It supports addition, subtraction, multiplication, division, modulo, and functions like absolute value (abs), square root (sqrt), power (power), and rounding (round). It also has a history feature to store and view past calculations.

Modular Structure: The program is divided into different classes:
Calculator: Manages user input and main loop.
HistoryManager: Handles the history of calculations.
ExpressionEvaluator: Evaluates arithmetic expressions.
Utils: Contains helper methods like finding closing parentheses.
Data Structures:

ArrayList stores the history of calculations.
String is used to process and evaluate user input.
Algorithms and Data Structures
The program uses a recursive method to evaluate expressions with operations like abs(), sqrt(), and power(). It checks for operator precedence and parentheses correctly using a simple parsing method.
ArrayList helps store and display past calculations easily.
Challenges
Handling Nested Functions and Parentheses: The program had to handle nested functions like abs(sqrt(16)) correctly, which required recursion.
Error Handling: Managing cases like division by zero or square root of negative numbers was tricky, but exceptions were added to avoid crashes.
Improvements
History Feature: The calculator now stores and shows past calculations, which was not present in the original version.
Additional Functions: Functions like abs(), sqrt(), and round() were added for more advanced calculations.
Better Error Handling: The program was improved with checks for invalid expressions and errors like division by zero.
