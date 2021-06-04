## Premium Calculator

The design follows the common MVC pattern. A Controller receiving the http requests, 
a dto PolicyDto converted to the business model Policy, the model then passed to the service PremiumCalculator.
The business model structure is then navigated by a Visitor. The visitor is then invoked for the Theft and Fire sub-object.
The visitor will dispatch the type-specific logic to a proper calculator that aggregates data.
The visitor then combines the 2 calculations to produce the Policy Premium.

To introduce a new risk type then it will be needed to implement the correspondent class, 
extend the visitor and inject a visitor factory creating the newly extended visitor.

Unfortunately I didn't have time to work on the input validation. 
I have placed some basic annotations on the dto and I didn't provide any test for those sorry. 
The validation approach would be using the standard Java validation framework    

To run the application: 
gradle run
