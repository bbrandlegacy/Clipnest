# Course Project Report

**\[ClipNest]**

**Course:** Modern Programming Practices

**Block:** July 2025

**Instructor:** Dr. Bright Gee Varghese R

**Team Members:**

\[Nicolo Bonasera]  \[114929]

\[Name 2]  \[Student ID]

**Date of Submission:** \[07/17/2025]

---

# Important Project Requirements

**Stream API:**

 I applied stream api to filter through the ID numbers when trying to delete an existing video. It is presenting a list of videos on the screen and hten depending on the returned line it will sort through the list filtering based on the ID to delete and will find the first one (these are unique primary keys from the database to maintain source of truth)

**Unit Testing:**

Implement unit tests for your business logic using JUnit or a similar testing framework. Include instructions for running your test suite and ensure your tests cover major functionalities and edge cases.

**Singleton Pattern:**

I made use of the singleton pattern for the database manager to maintain single instances so only ONE database manager is interacting with ONE database.

---

## 1. Problem Description

Provide a clear and concise explanation of the real-world problem your project aims to solve. Include background, motivation, and significance.

The real-world problem this program aims to solve is digital media organization, period. Most often we go to send videos to people or try to relate information or media that we've seen online to a specific purpose. But it may not be the right time or we want to remind ourselves of it at a later point. Little snippets or nuggets of information. And if we had a good way to reference that information based on an internal structure, that would help us access the information easily.

---

## 2. User Stories

Describe the system from the user's perspective using user stories:

As a user, I want to be able to add videos, delete videos, and view the videos so that I can access the data that I'm trying to find. 
As a user, I would also like to be able to sort the information based on tag that was created by the user in order to be able to create a self-referential organization system.


---

## 3. Functional Requirements

List the system's essential features and functionalities.

Retrieve metadata based on user URL input and Custom Tag
CRUD Information to the database
Interact with the videos based on Custom Tag data

---

## 4. Non-Functional Requirements

List requirements such as usability, security, reliability, performance, scalability, etc.

MVP: must be able to access the menu well for user experience and dev testing.
Future: REST API to give access to frontends.

---

## 5. Architecture of Project

### 5.1 Overview

Provide an overview of your system's layered architecture. Identify each layer and briefly describe its purpose.

Currently, I have a three-layer structure with models, repository, and services. You could consider the fourth layer being the operator, which is the main class which will be replaced by API and front end later


### 5.2 Architecture Diagram

https://github.com/bbrandlegacy/Clipnest/blob/main/Diagrams/Architecture%20Diagram.png

### 5.3 Technologies Used

Java 21
Youtube-dlp


### 5.4 Layer Descriptions

* **Presentation Layer:** CLI with a menu for access
* **Service Layer:** Metadata Fetcher that retrieves video data and returns as an object to be handled
* **Data Access Layer:** handles requests to the database and includes secure database logic to maintain data integrity (prepared statements)
* **Database:** accesses a MYSQL database

---

## 6. Use Case Diagram(s)

https://github.com/bbrandlegacy/Clipnest/blob/main/Diagrams/Use%20Case%20Diagram.png

---

## 7. Use Case Descriptions

Provide detailed descriptions for each use case:

* **Use Case Name: adding a video**
* **Primary Actor(s): User**
* **Preconditions: must have a URL and Custom_Tag**
* **Postconditions: none**
* **Main Success Scenario: video gets added to the database with metadata**

---

## 8. Class Diagram

https://github.com/bbrandlegacy/Clipnest/blob/main/Diagrams/Class%20Diagram.png

---

## 9. Sequence Diagrams

https://github.com/bbrandlegacy/Clipnest/blob/main/Diagrams/Sequence%20Diagram.png

---

## 10. Screenshots

Include relevant screenshots of your application's interface and features.

---

## 11. Installation & Deployment

Detailed, step-by-step instructions for:

for now download the project, and install youtube-dlp (from homebrew)
run the project and the CLI will interact with youtube-dlp on its own


---

## 12. How to Use

Go to youtube, get a video
select add a video
paste the url
give it a custom tag or an existing tag
revisit later using the tag you utilized

---

## 13. Design Justification & Principles

Explain your key design choices, such as:

* Use of interfaces, abstract classes, inheritance (Liskov Substitution Principle), composition.
* Application of Open-Closed Principle.
* Design patterns used (if any).

---

## 14. Team Members

Me

---

## 15. References

Youtube-DLP

---
## Grading Rubric (Total: 10 Points)

| Criteria                                     | Points | Description / Expectations                                                                                   |
| -------------------------------------------- | :----: | ------------------------------------------------------------------------------------------------------------ |
| **Problem Description & User Stories**       |    1   | Clearly states the problem and provides meaningful, relevant user stories.                                   |
| **Functional & Non-Functional Requirements** |    1   | Functional and non-functional requirements are complete, clear, and relevant.                                |
| **Architecture & Design**                    |    1   | Well-structured layered architecture, clear diagrams (class, sequence, use case), thoughtful design.         |
| **Use of Stream API**                        |    1   | Appropriately uses Java Stream API wherever possible; usage is clear and well-documented.                    |
| **Singleton Pattern (when applicable)**      |    1   | Applies the Singleton pattern where necessary; justification provided in documentation.                      |
| **Unit Testing**                             |    1   | Implements unit tests for key business logic; tests are meaningful and cover main cases.                     |
| **Implementation Quality**                   |    1   | Code quality: modularity, clean structure, meaningful naming, adherence to SOLID principles, error handling. |
| **Deployment, Installation & Usability**     |    1   | Clear setup instructions, successful deployment, working UI/CLI, and usability.                              |
| **Documentation & Reporting**                |    1   | Detailed README: all sections complete (screenshots, diagrams, instructions, principles, references, etc).   |
| **Presentation & Teamwork**                  |    1   | Professionalism in presentation (repo, submission, screenshots), teamwork (if applicable), and originality.  |
| **Total**                                    | **10** |                                                                                                              |

