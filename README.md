
# Online Book Store 


## About this Kata
This short and simple Kata should be performed using Test Driven Development (TDD).
## Rules
This project is a simple code kata that involves creating a basic online bookstore which will display a list of books and users will have the possibility to add books to their cart, display the cart and modify the quantity of items and remove items from the cart.

## Requirements

- **Java** : 1.8 and above
- **Springboot** : 2.x
- **Maven** : For Dependency management
- **JUnit** : 5.x

## How to Build the Application

- Clone this repository:
   ```bash
   https://github.com/khanhafsa3/online-bookstore
- Build the project and run the tests by running
    ```bash
    mvn clean install

## Sample Input and Output

The sample input and output is provided in the test/resources folder

## Test reports

- Once after successful build of
  `mvn clean install`, navigate to target folder of the project root directory
- **Jacoco code coverage report :** Code Coverage report will be available in `target\site\jacoco` folder. View the report by launching **index.html**


## API end points

- Register user (post) : http://localhost:8083/api/v1/user/register
- Login user (post) : http://localhost:8083/api/v1/user/login
- Create book (post) : http://localhost:8083/api/v1/books/createBook
- Get all books (Get) : http://localhost:8083/api/v1/books/getAllBooks
- Add to cart (post) :http://localhost:8083/api/v1/cart/add
- Get books from cart (Get): http://localhost:8083/api/v1/cart/user/1
- Remove book from cart (Delete): http://localhost:8083/api/v1/cart/remove
- Update cart (put) : http://localhost:8083/api/v1/cart/update
- Place order (post) : http://localhost:8083/api/v1/orders/placeOrder
