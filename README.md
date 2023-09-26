# dagserver-selenium-tests

## Overview
This project, "dagserver-selenium-tests," is a Selenium testing project designed to test the frontend of the "dagserver" application. It utilizes the TestNG framework for organizing and running test cases and streamlines WebDriver management using WebDriverManager.

## Project Structure
The project is organized as follows:

- **src/main/java**: This directory contains the Java test classes.
  - **dagserver/test**: Contains test classes for various aspects of the "dagserver" frontend.
  - **dagserver/pom**: Contains Page Object Model (POM) implementations for interacting with the frontend.
  - **dagserver/utils**: Contains utility classes and helper methods.
- **src/main/resources**: This directory contains TestNG XML suite configuration files.

## Getting Started
Follow these steps to set up and run the tests:

1. **Prerequisites**: Ensure you have Java JDK installed.

2. **Dependency Management**: WebDriverManager is used for WebDriver management and is included as a test dependency. No additional configuration is required.

3. **Clone the Repository**: Clone this repository to your local machine.

4. **Configuration**: Open the test xmls in the `resources` directory and configure the test scenarios, URLs, and any other test-specific settings as needed.

5. **Running the Tests**: Run the tests using your preferred testNG compatible IDE or build tool. 

6. **Review Results**: After running the tests, review the test reports and logs to verify the test results and any potential issues.

## Test Organization
The tests in this project are organized into logical test suites, and each suite focuses on a specific aspect of the "dagserver" frontend. TestNG annotations are used for test execution and reporting.

## Contributing
Contributions to this project are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

<a href="https://www.buymeacoffee.com/maximolira" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>

  [Apache 2.0](LICENSE)
