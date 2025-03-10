# E2E_TestAutomation_ECommerce

This repository contains an End-to-End (E2E) Test Automation framework for testing an E-Commerce web application. The framework is built using Selenium, Java, TestNG, Maven, and Extent Reports.

### Table of Contents
- [Technologies Used](#technologies-used)
- [Framework Structure](#framework-structure)
- [Installation and Setup](#installation-and-setup)
- [Running Tests](#running-tests)
- [Generating Reports](#generating-reports)

### Technologies Used
- **Java** - Programming language for writing test scripts.
- **Selenium** - Browser automation tool for E2E testing.
- **TestNG** - Testing framework for managing test cases.
- **Maven** - Build automation tool for managing dependencies and running tests.
- **Extent Reports** - Library for generating detailed test reports.

### Framework Structure
```

E2E_TestAutomation_ECommerce/
│
├── .idea/
├── .settings/
├── E2E_TestAutomation_RahulShettyAcademy/
├── src/
│   ├── main/
│   │   ├── java/com/qa/
|   |   |   ├── listeners/
|   |   |   ├── pages/
|   |   |   └── utilities/
|   |   └── resources/logger/
│   └── test/
│       ├── java/com/qa/
│       |   ├── base/
│       |   └── tests/
|       └── resources/
|           ├── config/
|           ├── testdata/
|           └── testrunner/
├── target/
├── test-output/
├── .classpath
├── .project
├── my-app.iml
└── pom.xml
```

### Installation and Setup
1. **Clone the repository:**
```bash
   git clone https://github.com/hemanthsofficial/E2E_TestAutomation_ECommerce.git
```

2. **Import the project in IntelliJ IDEA or Eclipse.**

3. **Install dependencies:**
```bash
   mvn clean install
```

### Running Tests
To execute all tests, use:
```bash
   mvn test
```

To run specific test classes, modify the `Smoke.xml` file or use:
```bash
   mvn test -Dtest=TestClassName
```

### Generating Reports
- After test execution, reports are generated using **Extent Reports** and can be found in the `reports` directory.
