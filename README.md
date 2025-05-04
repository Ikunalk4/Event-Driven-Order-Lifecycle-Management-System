<h1 align="center" id="title">Event-Driven-Order-Lifecycle-Management-System</h1>

<p id="description">This project is a Java-based event-driven microservices system built using Spring Boot and Apache Kafka managing the full lifecycle of an order — from placement to user notification. It emphasizes clean architecture SOLID principles and scalable system design.</p>

  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   1\. Order Lifecycle Management
*   2\. Event-Driven Architecture
*   3\. Microservices-Based Design
*   4\. Kafka-Based Event Propagation
*   5\.Real-Time Notification


<h2>💻 Built with</h2>

Technologies used in the project:

*   Java 21
*   Spring Boot
*   Microservices
*   MongoDB
*   Event-Driven
*   Apache Kafka

Implemented Workflow
**1. Order Service**
Accepts incoming order requests
Persists order data
Publishes order-created event
 
**2. Fraud Service**
Consumes order-created event
Performs fraud validation
Publishes order-validated event
 
**3. Payment Service**
Consumes order-validated event
Adds payment info and processes payment
Publishes payment-processed event
 
**4. Shipment Service**
Consumes payment-processed event
Dispatches and delivers shipment
Publishes shipment-dispatched and shipment-delivered events

**5. Order Completion**
Consumes shipment-delivered event
Updates order status to COMPLETED
Publishes order-completed event
 
**6. Notification Service**
Consumes order-completed event
Sends user notification

**How to Run**
Ensure Kafka and Zookeeper are running (Docker or local).
Start each Spring Boot service individually.
Use Swagger or a Postman to place an order and trace the lifecycle through Kafka events.

**Key Highlights**
Asynchronous event handling using Kafka topics.
Clear domain ownership and service boundaries.
Robust error handling for serialization and deserialization failures.
Follows clean architecture and system design principles.
