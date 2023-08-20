## Spring Boot Protocol Buffers (gRPC)
This project demonstrates usage of protocol buffers 
within Spring Boot allowing us to build high-performance, 
cross-language communication between services (microservices).

## Tech Stack
- Java 17
- Spring Boot 3.1.2
- gRPC
- H2 Database
- Maven

## Why gRPC?
gRPC is a modern, open-source framework developed by Google that enables efficient and high-performance communication 
between microservices. 

It uses the HTTP/2 protocol, which offers features like multiplexing and header compression, 
making it ideal for scenarios where low-latency and efficient data transfer are crucial. 

gRPC supports multiple programming languages and provides a structured way to define services and messages using 
Protocol Buffers.

## gRPC vs REST
- **Performance:** gRPC uses HTTP/2, which can lead to faster data transfer compared to REST's HTTP/1.1. 
This is especially important for microservices communication with reduced latency.  


- **Data Format:** gRPC uses Protocol Buffers, a binary serialization format, which is more compact and efficient 
compared to JSON used in REST.


- **Strongly Typed:** Protocol Buffers allow you to define a strict contract for the message structure, ensuring 
type safety and minimizing errors.


- **Streaming:** gRPC supports bidirectional streaming, allowing both the client and server to send a stream of 
messages. REST is typically request-response based.


- **Language Agnostic:** gRPC supports multiple programming languages, making it suitable for polyglot microservices 
architectures.


- **Code Generation:** gRPC generates client and server code from service definitions, making it easy to 
integrate and use.


## Running the project
1. Navigate to root project directory
2. Execute command: `mvn clean compile` (this will generate Java classes from proto files)
3. Run the project (app will be listening on port 9090)

## Important files
- Proto definitions: `src/main/proto`  
- gRPC service example: `src/main/java/com/hedza06/grpc/services/UserService.java`


## Contribution/Suggestions
If someone is interested in contribution or have some suggestions please contact me on email hedzaprog@gmail.com.

## Author
Heril Muratović  
Software Engineer  
<br>
**Mobile**: +38269657962  
**E-mail**: hedzaprog@gmail.com  
**Skype**: hedza06  
**Twitter**: hedzakirk  
**LinkedIn**: https://www.linkedin.com/in/heril-muratovi%C4%87-021097132/  
**StackOverflow**: https://stackoverflow.com/users/4078505/heril-muratovic