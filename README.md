# Ticketing Master API
Create and Manage Tickets and Users swiftly and seamlessly

### Installation and Build
- Clone the repository.
- If building on an IDE, build as a maven/spring project.
- If building on CLI
  - Run `mvn clean install` to install dependencies.
  - Run `mvn spring-boot:run` to start the project.
- Import Postman Collections in the root dir into Postman

### List of APIs Supported

- `/purchase-ticket` - Allows users to purchase ticket for given user details
  - **Input**: 
  ```json
    {
      "from": "London",
      "to": "Paris",
      "firstName": "Test",
      "lastName": "Test",
      "email": "abc@def.com"
    }
  ```
  - **Output**:
  ```json
  {
     "Receipt Number":"1",
     "Passenger Name":"ABC DEF",
     "Passenger Email":"abc@def.com",
     "From": "London,"
     "To": "Paris",
     "Seat Section": "Section A",
     "Seat Number": "17",
     "Ticket Price": "20"
  }
  ```

- `/get-receipt` - Allows users to view receipt for given receipt number
    - **Input**:
  ```json
    {
      "receiptNumber": "1"
    }
  ```
    - **Output**:
  ```json
    {
     "Receipt Number":"1",
     "Passenger Name":"ABC DEF",
     "Passenger Email":"abc@def.com",
     "From": "London,"
     "To": "Paris",
     "Seat Section": "Section A",
     "Seat Number": "17",
     "Ticket Price": "20"
    }
  ```

- `/get-user` - Allows users to get user details for given user email
    - **Input**:
  ```json
    {
      "email": "abc@def.com"
    }
  ```
    - **Output**:
  ```json
    {
      "First Name": "ABC",
      "Last Name": "DEF",
      "Email": "abc@def.com"
    }
  ```

- `/get-user-receipts` - Allows users to view all ticket receipts for given user
    - **Input**:
  ```json
     {
       "email": "abc@def.com"
     }
  ```
    - **Output**:
  ```json
  [
    {
     "Receipt Number":"1",
     "Passenger Name":"ABC DEF",
     "Passenger Email":"abc@def.com",
     "From": "London,"
     "To": "Paris",
     "Seat Section": "Section A",
     "Seat Number": "17",
     "Ticket Price": "20"
    },
    {
     "Receipt Number":"4",
     "Passenger Name":"ABC DEF",
     "Passenger Email":"abc@def.com",
     "From": "Paris,"
     "To": "London",
     "Seat Section": "Section B",
     "Seat Number": "84",
     "Ticket Price": "20"
    }
  ]
  ```

- `/remove-user` - Allows users remove the given user
    - **Input**:
  ```json
     {
      "email": "abc@def.com"
     }
  ```
    - **Output**:
  ```json
    {
      "Removed Tickets": [
        {
         "Receipt Number":"1",
         "Passenger Name":"ABC DEF",
         "Passenger Email":"abc@def.com",
         "From": "London,"
         "To": "Paris",
         "Seat Section": "Section A",
         "Seat Number": "17",
         "Ticket Price": "20"
        },
        {
         "Receipt Number":"4",
         "Passenger Name":"ABC DEF",
         "Passenger Email":"abc@def.com",
         "From": "Paris,"
         "To": "London",
         "Seat Section": "Section B",
         "Seat Number": "84",
         "Ticket Price": "20"
        }
    ],
    "Removed User": {
        "First Name": "ABC",
        "Last Name": "DEF",
        "Email": "abc@def.com"
      }
  }        
  ```

- `/change-seats` - Allows users to change the seats of all tickets for the given user
    - **Input**:
  ```json
     {
        "email": "abc@def.com"
     }
  ```
    - **Output**:
  ```json
    [
      {
        "User Name": "ABC DEF",
        "Previous Seat": "17",
        "New Seat": "63",
        "New Receipt": {
           "Receipt Number": "1",
           "Passenger Name": "ABC DEF",
           "Passenger Email": "abc@def.com",
           "From": "London,"
           "To": "Paris",
           "Seat Section": "Section B",
           "Seat Number": "63",
           "Ticket Price": "20"
        },
    },
    {
        "User Name": "ABC DEF",
        "Previous Seat": "84",
        "New Seat": "36",
        "New Receipt": {
           "Receipt Number":"4",
           "Passenger Name":"ABC DEF",
           "Passenger Email":"abc@def.com",
           "From": "Paris,"
           "To": "London",
           "Seat Section": "Section A",
           "Seat Number": "36",
           "Ticket Price": "20"
        }
    }
  ]
  ```

- `/get-users-by-section` - Allows users to view all users and seats for a given section.
    - **Input**:
  ```json
    {
      "section": "Section A"
    }
  ```
    - **Output**:
  ```json
    [
      {
        "User Name": "ABC DEF",
        "Seat Number": "36"
      },
      {
        "User Name": "Test Test",
        "Seat Number": "47"
      },
      {
        "User Name": "XY XY",
        "Seat Number": "12"
      },
      {
        "User Name": "JOHN DOE",
        "Seat Number": "29"
      }
    ]
  ```
