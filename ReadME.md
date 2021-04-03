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
    
    * **Code:** 200 <br />
      **Content:** `{
      "userIDPK": 99,
      "username": "Mario",
      "password": "Password1",
      "firstname": Mario,
      "lastname": Lopez,
      "email": "plumber@friday.com",
      "role_id": 0,
      "active": true
  }`
  
  * **Error Response:**

    * **Code:** Wrong input of login credentials  <br />
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
    
    * **Code:** 200 <br />
      **Content:** `Successfully created user`
  
  * **Error Response:**

    * **Code:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Code:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Code:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Code:** Not a valid last name<br />
      **Content:** `Invalid last name`

     * **Code:** Not a valid email<br />
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
    
    * **Code:** 200 <br />
      **Content:** `Successfully updated user`
  
  * **Error Response:**

    * **Code:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`


    * **Code:** If username is already taken <br />
      **Content:** `Username already taken`

     * **Code:** Not a valid first name<br />
      **Content:** `Invalid first name`

     * **Code:** Not a valid last name<br />
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
    
    * **Code:** 200 <br />
      **Content:** `User deleted`
  
  * **Error Response:**

    * **Code:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`





  * **Notes:**

  This method updates a user's isActive flag to false;