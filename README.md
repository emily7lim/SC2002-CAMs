# SC2002 CAMs

**Camp Application and Management System (CAMs)**

The Camp Application and Management System (CAMs) is designed and implemented to serve as a centralized hub for staff and students from NTU. The system has been developed as a Command Line Interface (CLI) application with a primary purpose for users to manage, view and register for camps. The application accommodates the requirements of various user roles by offering different functionalities, allowing for flexibility and continuous future development.

## Project Folder Structure

### Top Level Folder Structure

```
.
│   README.md 
│
├── bin         // Compiled Java Classes
├── javadocs    // Javadocs documentation
└── src         // Source Code
```

### Source Code Folder

```
./src
│   App.java    // Entry point of Application
│
├── controller  // Controller Classes - Application Logic
├── database    // Database (DAO) Classes - Database Access and Manipulation
├── model       // Model Classes - Data Models
├── report      // Report Classes - Generate Reports
├── utils       // Utilities Classes - Common Functionalities
└── view        // View Classes - User Interface and Presentation
```

## Setup Instructions

1. Run the application using the following in the SC2002-CAMS directory:

    ```
    java --enable-preview -cp ".\bin" App
    ```

2. Login - Press `1` then press `<Enter>`

3. Reset Database

    3.1 Manage Database - Press `2` then press `<Enter>`

        ──────────────────────────────────────────────────────────────────────
                                    Manage Database
        ──────────────────────────────────────────────────────────────────────

        1)  Reset Database
        2)  Initialize Users
        3)  Initialize Camps
        4)  Back

        Enter your choice:
    
    3.2 Reset Database - Press `1` then press `<Enter>`
    
    3.3 Confirm Reset Database - Press `y` then press `<Enter>`

        ──────────────────────────────────────────────────────────────────────
                                    Reset Database
        ──────────────────────────────────────────────────────────────────────

        Are you sure you want to reset the database? (y/n) y
        Database successfully resetted.

        Press any key to continue...   

4. Initialize Test Data

    4.1 Initialize User Test Data - Press `2` then press `<Enter>`

    4.2 Confirm Intialize User Test Data - Press `y` then press `<Enter>`

     > Default User Password is "password"

        ──────────────────────────────────────────────────────────────────────
                                Initialize Users
        ──────────────────────────────────────────────────────────────────────

        Are you sure you want to initialize users? (y/n) y
        User data initialized successfully.

        Press any key to continue...      

    4.3 Initialize Camp Test Data - Press `3` then press `<Enter>`

    4.4 Confirm Initialize Camp Test Data - Press `y` then press `<Enter>`

        ──────────────────────────────────────────────────────────────────────
                                Initialize Camps
        ──────────────────────────────────────────────────────────────────────

        Are you sure you want to initialize camps? (y/n) y
        Camp data initialized successfully.

        Press any key to continue...

## Javadocs

1. Generate Javadocs - Run the following in the SC2002-CAMS directory:

    ```
    javadoc -private -d "./javadocs/" -sourcepath "./src/" -subpackages controller database model report utils view report.enums report.writer model.enums
    ```

2. View Javadocs - Open the `index.html` file in the "./javadocs" folder
