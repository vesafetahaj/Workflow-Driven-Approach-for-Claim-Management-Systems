# Workflow-Driven-Approach-for-Claim-Management-Systems

This repository contains a Claim Management System built using Spring Boot, Angular, PostgreSQL, and Camunda. The system allows users to submit claims and automates the claim submission workflow.

## Features

- **Claim Submission Workflow**: Implemented using BPMN, with delegates for service tasks.
- **Client-Side Validation**: Ensures that user input is validated before submission.
- **Email Notifications**: When a claim is submitted, the claim details are sent via email to the claims department.
- **Spring Security**: Secure login and registration functionality for users using refresh and access tokens.
- **User-Friendly Forms**: Styled forms for login, registration, and claim submission.

## Getting Started

### Prerequisites

- JDK 11 or higher
- Node.js and npm
- PostgreSQL
- Camunda BPM

### Installation

1. **Clone the repository:**
   - git clone [https://github.com/vesafetahaj/Workflow-Driven-Approach-for-Claim-Management-Systems.git]
   - cd claim-management-system
2. **Backend Setup:**
  - Navigate to the backend directory:
    cd backend
  - Configure your PostgreSQL database settings in **src/main/resources/application.properties**. Make sure to set the correct URL, username, and password for your PostgreSQL database.
  - Run the Spring Boot application:
    ./mvnw spring-boot:run
  - Ensure the backend is running without errors. You should see logs indicating that the application has started successfully.
3. **Frontend Setup:**
  - Navigate to the frontend directory:
    cd frontend
  - Install the dependencies using npm:
    npm install
  - Run the Angular application:
    ng serve
  - Access the Angular application in your web browser at http://localhost:4200.
4. **Camunda Configuration:**
  - Ensure that the Camunda BPM engine is running. You can set up Camunda as a standalone server or integrate it directly with your Spring Boot application. In this app it is integrated on the Spring Boot App.
5. **Testing the Application:**
  - Once everything is set up and running, navigate to the web application and perform some basic tests:
    - Register a new user.
    - Log in with the registered credentials.
    - Submit a claim and verify that it triggers the workflow correctly.

## Views

- Login Page:
 <br>
<img width="1112" alt="login" src="https://github.com/user-attachments/assets/c9482ef6-c5da-4eb1-baea-df8b897cb1fa">

<br>
- Register Page:
  <br>
  <img width="1101" alt="register" src="https://github.com/user-attachments/assets/86551084-8e53-4330-80ed-966b3d580aa7">

<br>
- Submit Claim Page:
<br>
<img width="1113" alt="submitclaim" src="https://github.com/user-attachments/assets/a2a0ed0b-5402-412d-b0de-f06af3082ea9">

