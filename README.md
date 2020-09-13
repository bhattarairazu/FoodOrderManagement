# Fuse Canteen Order Management

The documentation can be found at below url.
https://documenter.getpostman.com/view/11208115/TVK5e2pb

Every section is completed as per the problem statement.
You can directly import to the postman from this url.

#### Workflow

##### 1. User 
* At first respective user should be registered from the url:
   - `localhost:8081/api/v1/users/register`
   
* Login to the respective user to authenticate and receive JWT token on successfull login.
   - `localhost:8081/api/v1/users/login`
##### 2. Category 
* Create Category by:
   - `localhost:8081/api/v1/category/`
   
* Delete Category by:
   - `localhost:8081/api/v1/category/2`
##### 3. Food Items
* Add Food Items with respective category name:
   - `localhost:8081/api/v1/food/`
   
* Get Food Items by `Id`:
   - `localhost:8081/api/v1/food/3`
* Delete Food Items by `Id`:
   - `localhost:8081/api/v1/food/3`
* Update Food Items by `Id`:
   - `localhost:8081/api/v1/food/4`

##### 4. Menu
* Add Menu:
   - `localhost:8081/api/v1/menu/`
   
* Delete Menu By `Id`:
   - `localhost:8081/api/v1/menu/3`
* Get Todays Menu:
   - `localhost:8081/api/v1/menu/todays`

##### 5. Menu Items
* Add Menu Items to the respective menu along with menu id:
   - `localhost:8081/api/v1/menuitem/`
   
* Delete Menu Items by `Id`:
   - `localhost:8081/api/v1/menuitem/1`
* Get All Menu Items
   - `localhost:8081/api/v1/menuitem/`
   
##### 6. Order
* Create Order :
   - `localhost:8081/api/v1/orders/`
   
* Update Order By `Id`
   - `localhost:8081/api/v1/orders/5`
* Delete Order By `Id`
   - `localhost:8081/api/v1/orders/2`
   
* Get Todays Order:
   - `localhost:8081/api/v1/orders/`
* List All Previous Day Order:
   - `localhost:8081/api/v1/orders/previousdays/`
* Get Order History of Specific Date(Date should be in format of 'YYYY-MM-DD')
   -`localhost:8081/api/v1/orders/orderbydate/2020-09-12`
* Change Order Status
   - `localhost:8081/api/v1/orders/changeorderstatus`
* Get Orders By Employee
   - `localhost:8081/api/v1/orders/orderbyemployee`
   
##### 7. Order Items
* Create Order Items with the order `Id`:
   - `localhost:8081/api/v1/orderitems/`
   
* Get all Order Items
   - `localhost:8081/api/v1/orderitems/`
* Delete Order Items By `Id`:
   - `localhost:8081/api/v1/orderitems/4`
   
##### 8. Food To Be Prepared Today
* Create Food To Be Prepared Today:
  - `localhost:8081/api/v1/foodprepared/`
* Get Todays Food To Be Prepared:
  - `localhost:8081/api/v1/foodprepared/todays`
* Update Todays Food To be Prepared by `Id`:
  - `localhost:8081/api/v1/foodprepared/4`
* Delete Food To be Prepared by `Id`:

  - `localhost:8081/api/v1/foodprepared/6`
* Get Popular Top 5 Food To Prepared Today
  - `localhost:8081/api/v1/foodprepared/popularfood`
<<<<<<< HEAD:README.md
=======
*
>>>>>>> 57e6e7d29432ecefa891b7eafd96baacc27d6ff4:README.md
