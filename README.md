Currency Exchange and Discount Calculation
Overview
This is a Spring Boot application that integrates with a third-party currency exchange API to calculate the final payable amount for a bill. It applies various discounts based on user roles and tenure, then converts the total amount into the desired currency.

Features
Retrieves real-time exchange rates using the ExchangeRate-API.
Applies percentage-based and flat discounts based on predefined business rules.
Converts the final bill total into the target currency.
Provides a REST API endpoint (/api/calculate) to perform calculations.
Includes authentication (can be enabled or disabled as needed).
Provides unit tests with good code coverage.

Tech Stack
Java 8
Spring Boot 2.7.x
Maven
JUnit 5
Mockito
RestTemplate
Jackson
JaCoCo (for code coverage reporting)
System Requirements
Java 8 or higher
Maven 3.6 or higher
Internet access for API calls
IDE (optional): IntelliJ IDEA, Eclipse, etc.
Setup and Running the Application

Clone the Repository

git clone <repository-url>
cd <repository-folder>
Update Configuration
Open src/main/resources/application.properties.

Replace the placeholder API key with your actual API key:

properties
exchange.api.key=your-api-key
exchange.api.base-url=https://v6.exchangerate-api.com/v6
Optional: Update default Spring Security credentials in application.properties:

properties
spring.security.user.name=admin
spring.security.user.password=password
Build the Project
Run the following Maven command:

mvn clean install
Run the Application
Start the application using:

mvn spring-boot:run
The server will start on http://localhost:8080.

How to Use the API
Endpoint
http

POST /api/calculate
Headers
Content-Type: application/json
Authorization: Basic <Base64-encoded username:password>
Sample Request Body
{
    "items": [
        { "name": "Laptop", "category": "electronics", "price": 1000 },
        { "name": "Headphones", "category": "electronics", "price": 200 },
        { "name": "Apples", "category": "groceries", "price": 50 }
    ],
    "userType": "employee",
    "tenure": 3,
    "originalCurrency": "USD",
    "targetCurrency": "INR"
}
Sample Response
{
    "finalAmount": 71804.379
}
Running Tests
Run Unit Tests
To run all tests:

mvn test
Code Coverage Report
Generate the coverage report using:

mvn jacoco:report
Open the coverage report:
Navigate to target/site/jacoco/index.html in your browser.
Project Structure

src/main/java/com/example/currencyexchange/
├── config/                      # Security configuration
├── controller/                  # REST controllers
├── model/                       # Data models (e.g., Bill, Item)
├── service/                     # Business logic interfaces and implementations
├── util/                        # Utility classes (e.g., caching)
└── Application.java             # Main Spring Boot application
Business Rules
Discounts:

Employee: 30% discount (non-groceries).
Affiliate: 10% discount (non-groceries).
Customer for >2 years: 5% discount (non-groceries).
Flat discount: $5 per $100 on the bill.
Rules:
Percentage-based discounts do not apply to groceries.
Only one percentage-based discount can be applied.
Currency Conversion:

Converts the bill amount to the specified target currency using the retrieved exchange rate.
Authentication
The application uses Basic Authentication. Default credentials:

Username: admin
Password: password
To disable authentication for testing, update the SecurityConfig class:

http.authorizeRequests().anyRequest().permitAll();
Known Limitations
The exchange rate API has rate limits for free plans.
Flat discount calculations are based on integer values (truncation).
Contributions
Feel free to contribute by:

Reporting bugs
Suggesting features
Submitting pull requests
License
This project is licensed under the MIT License.

