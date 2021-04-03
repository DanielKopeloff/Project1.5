#  User and Reimbursement API
##### BY John Stone and Daniel Kopeloff

Basic Rules for the API<br />
-----------------

The User can not access other users information <br />
Managers can not resolve their own Reimbursements <br />
If a manager wants to see all the reimbursements he can input -1 in the manager/reimbursements/list URL <br >
If a reimbursement has been either ACCEPTED or REJECTED it can no longer be edited by anyone <br >

---------
---------
###  **User**

* **URL**
  <br /><br />

  /user<br /><br />

    * **Method:**

      `GET` 

    *  **URL Params**

    **Required:**
  
    1. `username=[string]`
    2. `password=[string]`


    
* **Optional:**
  


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

      `POST` 

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

  This method creates a user


  
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
This method is how you can update the user


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

This method doesn't actually delete the user.
It actually just set their isActive flag to false to indicate
that they are no longer active

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
    4. `newusername=[string]`
    5. `newpassword=[string]`

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
        
    * **Reason:** Not a valid username<br />
      **Content:** `Invalid Username`
      
    * **Reason:** Not a valid password <br />
      **Content:** `Invalid Password`
        
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
        
    * **Reason:** Trying to access another user's reimbursement  <br />
      **Content:** `You do not have permission to perform this action`

    * **Reason:** Invalid Id number  <br />
      **Content:** `Invalid Id`


  * **Notes:**

  This method gets either all the reimbursements linked to this user or just one specific one 

   
   * **Method:**
    
        `Post` 
    
  *  **URL Params**
  
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `amount=[float]`
    4. `type_id=[integer]`
    5. `description=[string]`

**Optional:**
  
  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully created reimbursement`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`
      
    * **Reason:** Not a valid amount <br />
      **Content:** `Invalid amount`

     * **Reason:** Not a valid type ID<br />
      **Content:** `Invalid type`
         
    * **Reason:** No Description provided<br />
      **Content:** `Invalid Description`
       
    * **Reason:** Reimbursment already settled<br />
      **Content:** `Reimbursement has already been settled`

      

  * **Notes:**

  This method creates a new reimbursement 


  
   * **Method:**
    
        `Put` 
    
  *  **URL Params**
  
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `reimId=[integer]`
   

**Optional:**

    1. `amount=[float]`
    2.  `type_id=[int] ` 
    3. `description=[string]`

   


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully updated reimbursement`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`
      
    * **Reason:** Not a valid type<br />
      **Content:** `Invalid type`

     * **Reason:** Not a valid amount<br />
      **Content:** `Invalid Amount`

     * **Reason:** Do not have access<br />
      **Content:** `Do not have access to this`

    * **Reason:** Not a valid Reimbursement ID<br />
      **Content:** `Invalid reimbursement number`
      
    * **Reason:** Not a valid Description<br />
      **Content:** `Invalid Description`


  * **Notes:**
This method updates a reimbursement
 

   * **Method:**
    
        `Delete` 
    
  *  **NOT IMPLEMENTED**

     ###  **Manager and Reimbursements**

* **URL**

  /manager/reimbursement

  * **Method:**
    
    `GET` 
    
  *  **URL Params**


  **Required:**
  

    1. `username=[string]`
    2. `password=[string]`
    <br />
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
      
    * **Reason:** Invalid Id number  <br />
      **Content:** `Invalid Id`


  * **Notes:**

  This method gets either all the reimbursements linked to this user or just one specific one 

   
   * **Method:**
    
        `Post` 
    
  *  **URL Params**
     
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `amount=[float]`
    4. `type_id=[integer]`
    5. `description=[string]`

    
**Optional:**
  
  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully created reimbursement`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`
      
    * **Reason:** Not a valid amount <br />
      **Content:** `Invalid amount`

     * **Reason:** Not a valid type ID<br />
      **Content:** `Invalid type`
       
    * **Reason:** No Description provided<br />
      **Content:** `Invalid Description`



  * **Notes:**

  This method creates a new reimbursement 


  
   * **Method:**
    
        `Put` 
    
  *  **URL Params**
  
    
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
    3. `reimId=[integer]`
   

    
**Optional:**

    1. `amount=[float]`
    2.  `type_id=[int] ` 
    3. `description=[string]`
    4. `newstatus=[int]`
   


  * **Success Response:**
    
    * **Reason:** Success <br />
      **Content:** `Successfully updated reimbursement`
  
  * **Error Response:**

    * **Reason:** Wrong input of login credentials  <br />
      **Content:** `Invalid user credentials`
      
    * **Reason:** Not a valid type<br />
      **Content:** `Invalid type`

     * **Reason:** Not a valid amount<br />
      **Content:** `Invalid Amount`

     * **Reason:** Do not have access<br />
      **Content:** `Do not have access to this`
       
    * **Reason:** Not a valid Reimbursement ID<br />
      **Content:** `Invalid reimbursement number`
      
    * **Reason:** Not a valid Description<br />
      **Content:** `Invalid Description`

    * **Reason:** Not a valid status<br />
      **Content:** `Invalid status`    

    * **Reason:** Same author and resolver<br />
      **Content:** `Not allowed to edit your own reimbursements`  
      
    * **Reason:** Reimbursment already settled<br />
      **Content:** `Reimbursement has already been settled`

      

  * **Notes:**
This method updates a manager account
 

   * **Method:**
    
        `Delete` 
    
  *  **NOT IMPLEMENTED**
  
   ###  **User and Reimbursements List**

* **URL**

  /user/reimbursement/list

  * **Method:**
    
    `GET` 
    
  *  **URL Params**
  
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
 


**Optional:**

     1. `statusId=[integer]`
  


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
        
    * **Reason:** Invalid Id number  <br />
      **Content:** `Invalid Id`


  * **Notes:**

  This method gets either all the reimbursements linked to this user and their current status either PENDING , ACCEPTED , REJECTED


   ###  **Manager and Reimbursements List**

* **URL**

  /manager/reimbursement/list

  * **Method:**
    
    `GET` 
    
  *  **URL Params**
   
**Required:**
  
    1. `username=[string]`
    2. `password=[string]`
 


    
**Optional:**
        
    1. `statusId=[integer]`
  


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
        
    * **Reason:** Invalid Id number  <br />
      **Content:** `Invalid Id`
      
    * **Reason** No access <br />
      **Content:**`You do not have permission to perform this action`


  * **Notes:**

  This method gets either all the reimbursements linked to this user and their current status either PENDING , ACCEPTED , REJECTED
  If the manager wants to all the reimbursements they would input a -1 as the statusId

   