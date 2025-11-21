# RestAssured_API_Automation

**API (Application Programming Interface)**
An API allows two software systems to communicate with each other. Think of it as a waiter taking your order (request) to the kitchen (server) and bringing back your food (response).

**Different tools for API testing manually:**
1. Postman - works by saving the API's as a collections, preferred by testers.
2. Swagger(OpenAPI) - can directly work from browser, preferred by Developers.

**API's are categorized into 5 different types based on the style**
1. Rest API - REST APIs are stateless, use standard HTTP methods (GET, POST, PUT, DELETE), and are widely used in web and mobile apps.
2. SOAP API - SOAP APIs are used in enterprise systems like banking, insurance, and legacy applications where strict contracts and security are essential.
3. GraphQL API - GraphQL is ideal for mobile apps and complex UIs where over-fetching or under-fetching data is a concern. ex: github.
4. gRPC API - gRPC is used in high-performance, low-latency systems like internal microservices, streaming, and IoT.
5. WebSocket API - WebSockets are perfect for chat apps, live dashboards, gaming, and financial tickers where instant updates are critical.

**HTTP Methods (CRUD Operations)**
1. Get - Read data
2. Put - Update data(Updates full record, ex: Replace a student’s full record)
3. Post - Create data. 
4. Patch - Modify data(Specific to field, ex: Update only the student’s email)
5. Delete - Remove data

**HTTP Status Codes**
1. 200 - OK(Request succeeded)
2. 201 - Created(Resource created)
3. 400 - Bad request(Invalid input or missing data)
4. 401 - Unauthorized(Missing or invalid authentication)
5. 404 - Not found(Resource doesn’t exist)
6. 500 - Internal Server Error(Server crashed or misconfigured)

**Types of API errors**
1. Server side errors(500 series)
2. Client side errors(400 series)

**Server side errors:**
1. 500 Internal Server Error - Generic server failure error
2. 502 Bad Gateway - Invalid response from upstream server
3. 503 Service Unavailable - Server temporarily overloaded or down
4. 504 Gateway Timeout - Upstrean server didn't get response in time

**Client side errors:**
1. 400 Bad request - Malformed syntax or invalid parameters
2. 401- Unauthorized - Authentication required or failed
3. 403 - Forbidden  - Authenticated but not allowed(Trying to access restricted resource)
4. 404 not found - Resource doesn't exist
5. 405 Method Not Allowed - HTTP method not supported
6. 429 Too Many Requests - Rate limit exceeded

**Status Code Validation**
- statusCode(200)
- statusCode(equalTo(200))
- statusLine("HTTP/1.1 200 OK")
  
**Body Content Validation**
- body("field", equalTo("value"))
- body("field", containsString("partial"))
- body("field", hasItems("val1", "val2"))



   

