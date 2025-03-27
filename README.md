# House Cleaning Service Booking
This is an automation testing framework built with Cucumber, Selenium WebDriver, and JUnit for testing JustLife web application.
It uses Cucumber to define tests using Gherkin, making it easy to understand for both technical and non-technical stakeholders.
Selenium WebDriver is used for interacting with the web application (such as opening pages, clicking buttons, filling out forms, etc.).
JUnit is used as the test execution engine to run Cucumber tests.
This framework allows the creation of reusable step definitions and generates detailed HTML report for test execution results.

Features
1. One-Time Booking
Allows users to make a one-time reservation for a specific date and time.
Users can select the professional they wish to book, and the system will handle the booking process.

2. Weekly Booking
Allows users to schedule house cleaning services on a weekly basis.
Users can choose the day of the week, start time, and duration for the recurring booking.
The system automates the creation of weekly reservations, ensuring consistency in scheduling.

Installation
To run this project locally, follow the steps below:

Prerequisites
Java 8+ (Ensure you have Java installed)
Maven (or Gradle, depending on the build tool used in the project)
IDE (e.g., Eclipse, IntelliJ IDEA) with cucumber plug-in
Git (for version control)

Steps to Run
1-Clone the Repository
git clone https://github.com/MjAMohit/JustLifeAssignement.git
2-Navigate to Project Directory
cd your-repository-name
3-Build the Project
mvn clean install
4-Run the Cucumber Tests
mvn test
5-The tests will automatically run and the results will be displayed in the Cucumber HTML report.

