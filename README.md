Overview

This project is a test suite set up using IntelliJ IDEA, utilizing Java SDK 23, JUnit, and Maven to manage dependencies. The test suite ensures functionality and stability through structured unit, integration, and system testing.

Technologies Used

Java SDK 23

JUnit (for writing and executing tests)

Maven (for dependency management and build automation)

Test Suite Setup

The test suite is designed within IntelliJ IDEA.

Dependencies are managed via Maven.

Test execution follows a structured approach, incorporating initialization hooks directly within the test class.

Initialization Hooks

For simplicity and maintainability, all initialization hooks have been placed within the respective test class. This approach ensures that setup and teardown processes are clearly defined and easily accessible within the test structure.

Adjustments to LoginPage.xml

The originally attached LoginPage.xml contained errors that prevented it from functioning as expected. To proceed with testing, necessary modifications were made to ensure compatibility and correct behavior.


Notes

The project follows best practices for automated testing for the purpose of the example.

Adjustments to XML files were made only where necessary to meet test case requirements.