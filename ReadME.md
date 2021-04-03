# John Stone and Daniel Kopeloff User and Reimbursement API
----
###  **User**

* **URL**

  /user

  * **Method:**
    

    `GET` 
    
  *  **URL Params**

    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`


    **Optional:**
  


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `{
      "userIDPK": 99,
      "username": "Mario",
      "password": "Password1",
      "firstname": Mario,
      "lastname": Lopez,
      "email": "plumber@friday.com",
      "role_id": 0,
      "active": true
  }` *This is just an example user's data will vary
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


  * **Notes:**

  This method gets the user information based on the login parameters passed in from the URL


   
   * **Method:**
    

    `Post` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `email=[string]`

    **Optional:**
  
    1. `firstname=[string]`
    2. `lastname=[string]`


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully created user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`

     * **Reason:** Not a valid email<br />
      **Content:** `Invalid email`



  * **Notes:**

  This method updates a user


  
   * **Method:**
    

    `Put` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
   

    **Optional:**
    1. `newusername=[string]`
    2.  `newpassword=[string] ` 
    3. `firstname=[string]`
    4. `lastname=[string]`
    5. `email=[string]`


  * **Success Response:**
    
    * **Reason:** success <br />
      **Content:** `Successfully updated user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`




  * **Notes:**

  This method doesn't actually delete the user.
  It actually just set their isActive flag to false to indicate
  that they are no longer active

   * **Method:**
    

    `Delete` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
   

    **Optional:**


  * **Success Response:**
    
    * **Code:** Success <br />
      **Content:** `User deleted`
  
  * **Error Response:**

    * **Code:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`





  * **Notes:**

  This method updates a user's isActive flag to false;

  ###  **Manager**

* **URL**

  /manager

  * **Method:**
    
    `GET` 
    
  *  **URL Params**

    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`


    **Optional:**
  


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `{
      "userIDPK": 99,
      "username": "Mario",
      "password": "Password1",
      "firstname": Mario,
      "lastname": Lopez,
      "email": "plumber@friday.com",
      "role_id": 1,
      "active": true
  }`*This is just an example user's data will vary
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`

        * **Reason:** Trying to login when you are not a manager  <br />
      **Content:** `You do not have permission to perform this action`


  * **Notes:**

  This method gets the manager information based on the login parameters passed in from the URL

   
   * **Method:**
    
    `Post` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `email=[string]`

    **Optional:**
  
    1. `firstname=[string]`
    2. `lastname=[string]`


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully created user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`

     * **Reason:** Not a valid email<br />
      **Content:** `Invalid email`



  * **Notes:**

  This method creates a new manager


  
   * **Method:**
    
    `Put` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
   

    **Optional:**
    1. `newusername=[string]`
    2.  `newpassword=[string] ` 
    3. `firstname=[string]`
    4. `lastname=[string]`
    5. `email=[string]`


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully updated user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`




  * **Notes:**
This method updates a manager account
 

   * **Method:**
    
    `Delete` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
   

    **Optional:**


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `User deleted`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


  * **Notes:**

   This method doesn't actually delete the user.
  It actually just set their isActive flag to false to indicate
  that they are no longer active

   ###  **User and Reimbursements**

* **URL**

  /user/reimbursement

  * **Method:**
    
    `GET` 
    
  *  **URL Params**

    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`


    **Optional:**

    1. `reimID=[integer]`
  


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `{
      [
    {
        "id": 33,
        "amount": 23423.0,
        "submitted": 1617410399607,
        "resolved": null,
        "description": "Dress To impress",
        "author": {
            "userIDPK": 32,
            "username": "Mario",
            "password": "Password1",
            "firstname": null,
            "lastname": null,
            "email": "weekend@friday.com",
            "role_id": 0,
            "active": true
        },
        "resolver": null,
        "status_id": 0,
        "type_id": 2,
        "reimbursementID": 5
    }
]
  }`*This is just an example user's data will vary
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`

        * **Reason:** Trying to login when you are not a manager  <br />
      **Content:** `You do not have permission to perform this action`

       * **Reason:** Trying to login when you are not a manager  <br />
      **Content:** `You do not have permission to perform this action`


  * **Notes:**

  This method gets the manager information based on the login parameters passed in from the URL

   
   * **Method:**
    
    `Post` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `email=[string]`

    **Optional:**
  
    1. `firstname=[string]`
    2. `lastname=[string]`


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully created user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`

     * **Reason:** Not a valid email<br />
      **Content:** `Invalid email`



  * **Notes:**

  This method creates a new manager


  
   * **Method:**
    
    `Put` 
    
  *  **URL Params**
  
    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`
   

    **Optional:**
    1. `newusername=[string]`
    2.  `newpassword=[string] ` 
    3. `firstname=[string]`
    4. `lastname=[string]`
    5. `email=[string]`


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully updated user`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Reason:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Reason:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Reason:** Not a valid last name<br />
      **Content:** `Invalid last name`




  * **Notes:**
This method updates a manager account
 

   * **Method:**
    
    `Delete` 
    
  *  **NOT IMPLEMENTED**
  
   