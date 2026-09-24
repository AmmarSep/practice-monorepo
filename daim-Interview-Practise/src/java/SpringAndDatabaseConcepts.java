package java;

/**
 * This class demonstrates various Spring and Database concepts
 * 1. Dependency Injection
 * 2. Spring vs Spring Boot
 * 3. Spring Boot Annotations
 * 4. Spring Boot Actuator
 * 5. @Transactional
 * 6. @PathVariable vs @RequestParam
 * 7. Spring Boot Features
 * 8. Spring MVC
 * 9. Controller vs RestController
 * 10. Bean Scopes
 * 11. SQL Joins
 * 12. Hibernate vs JDBC
 * 13. Database Indexing
 * 14. JPA vs JDBC
 */
public class SpringAndDatabaseConcepts {

    public static void main(String[] args) {
        System.out.println("===== Spring and Database Concepts Demonstration =====");
        
        // Note: Most Spring and Database concepts are demonstrated through code examples
        // and explanations rather than actual running code, as they typically require
        // a Spring application context or a database connection.
        
        explainDependencyInjection();
        explainSpringVsSpringBoot();
        explainSpringBootAnnotations();
        explainSpringBootActuator();
        explainTransactional();
        explainPathVariableVsRequestParam();
        explainSpringBootFeatures();
        explainSpringMVC();
        explainControllerVsRestController();
        explainBeanScopes();
        explainSQLJoins();
        explainHibernateVsJDBC();
        explainDatabaseIndexing();
        explainJPAvsJDBC();
    }
    
    /**
     * 1. Dependency Injection
     * 
     * Dependency Injection (DI) is a design pattern that implements Inversion of Control (IoC)
     * for resolving dependencies. In DI, the dependencies are "injected" into a class rather
     * than the class creating them.
     */
    private static void explainDependencyInjection() {
        System.out.println("\n1. Dependency Injection:\n");
        
        System.out.println("Dependency Injection is a design pattern where dependencies are provided to a class");
        System.out.println("rather than the class creating them. Spring Framework implements this pattern.");
        
        System.out.println("\nTypes of Dependency Injection in Spring:");
        System.out.println("1. Constructor Injection - Dependencies are provided through a constructor");
        System.out.println("2. Setter Injection - Dependencies are provided through setter methods");
        System.out.println("3. Field Injection - Dependencies are injected directly into fields (using @Autowired)");
        
        System.out.println("\nExample of Constructor Injection:");
        System.out.println("```java");
        System.out.println("@Service");
        System.out.println("public class UserService {");
        System.out.println("    private final UserRepository userRepository;");
        System.out.println("    ");
        System.out.println("    // Constructor Injection");
        System.out.println("    @Autowired // Optional in newer Spring versions");
        System.out.println("    public UserService(UserRepository userRepository) {");
        System.out.println("        this.userRepository = userRepository;");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of Setter Injection:");
        System.out.println("```java");
        System.out.println("@Service");
        System.out.println("public class UserService {");
        System.out.println("    private UserRepository userRepository;");
        System.out.println("    ");
        System.out.println("    // Setter Injection");
        System.out.println("    @Autowired");
        System.out.println("    public void setUserRepository(UserRepository userRepository) {");
        System.out.println("        this.userRepository = userRepository;");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of Field Injection:");
        System.out.println("```java");
        System.out.println("@Service");
        System.out.println("public class UserService {");
        System.out.println("    // Field Injection");
        System.out.println("    @Autowired");
        System.out.println("    private UserRepository userRepository;");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nBenefits of Dependency Injection:");
        System.out.println("1. Reduces coupling between classes");
        System.out.println("2. Makes testing easier through mocking");
        System.out.println("3. Increases code modularity and reusability");
        System.out.println("4. Enables easier configuration changes");
        
        System.out.println("\nRecommended Practice:");
        System.out.println("Constructor injection is generally preferred because:");
        System.out.println("- It ensures all required dependencies are available at object creation");
        System.out.println("- It supports immutability (fields can be final)");
        System.out.println("- It makes dependencies explicit in the class API");
    }
    
    /**
     * 2. Spring vs Spring Boot
     * 
     * Comparison between the traditional Spring Framework and Spring Boot,
     * highlighting the key differences and benefits of each.
     */
    private static void explainSpringVsSpringBoot() {
        System.out.println("\n2. Spring vs Spring Boot:\n");
        
        System.out.println("Spring Framework:");
        System.out.println("- A comprehensive programming and configuration model for Java applications");
        System.out.println("- Provides core features like Dependency Injection, AOP, Data Access, etc.");
        System.out.println("- Requires extensive configuration (XML or Java-based)");
        System.out.println("- Requires manual setup of server, dependencies, etc.");
        System.out.println("- Gives developers more control over configurations");
        
        System.out.println("\nSpring Boot:");
        System.out.println("- Built on top of Spring Framework");
        System.out.println("- Provides opinionated defaults to reduce boilerplate configuration");
        System.out.println("- Includes embedded servers (Tomcat, Jetty, Undertow)");
        System.out.println("- Auto-configuration based on classpath dependencies");
        System.out.println("- Simplified dependency management with starters");
        System.out.println("- Production-ready features (metrics, health checks, externalized configuration)");
        
        System.out.println("\nKey Differences:");
        System.out.println("1. Configuration: Spring requires explicit configuration, Spring Boot uses auto-configuration");
        System.out.println("2. Server: Spring requires external server, Spring Boot includes embedded server");
        System.out.println("3. Dependencies: Spring Boot simplifies with starter dependencies");
        System.out.println("4. Production Readiness: Spring Boot includes actuator for monitoring and management");
        
        System.out.println("\nExample of Spring Configuration:");
        System.out.println("```java");
        System.out.println("@Configuration");
        System.out.println("@EnableWebMvc");
        System.out.println("@ComponentScan(basePackages = \"com.example\")");
        System.out.println("public class AppConfig {");
        System.out.println("    @Bean");
        System.out.println("    public ViewResolver viewResolver() {");
        System.out.println("        InternalResourceViewResolver resolver = new InternalResourceViewResolver();");
        System.out.println("        resolver.setPrefix(\"/WEB-INF/views/\");");
        System.out.println("        resolver.setSuffix(\".jsp\");");
        System.out.println("        return resolver;");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    // Many more beans and configurations...");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of Spring Boot Application:");
        System.out.println("```java");
        System.out.println("@SpringBootApplication");
        System.out.println("public class Application {");
        System.out.println("    public static void main(String[] args) {");
        System.out.println("        SpringApplication.run(Application.class, args);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nWhen to use which:");
        System.out.println("- Use Spring Boot for rapid development, microservices, and most new applications");
        System.out.println("- Use Spring Framework when you need fine-grained control over every configuration");
        System.out.println("  or when working with legacy systems that require specific setups");
    }
    
    /**
     * 3. Spring Boot Annotations
     * 
     * Overview of important annotations used in Spring Boot applications.
     */
    private static void explainSpringBootAnnotations() {
        System.out.println("\n3. Spring Boot Annotations:\n");
        
        System.out.println("Core Annotations:");
        System.out.println("@SpringBootApplication - Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan");
        System.out.println("@EnableAutoConfiguration - Enables Spring Boot's auto-configuration mechanism");
        System.out.println("@ComponentScan - Tells Spring where to look for components");
        System.out.println("@Configuration - Indicates that a class declares one or more @Bean methods");
        
        System.out.println("\nComponent Annotations:");
        System.out.println("@Component - Generic stereotype annotation for any Spring-managed component");
        System.out.println("@Service - Indicates that a class is a service layer component");
        System.out.println("@Repository - Indicates that a class is a data access object");
        System.out.println("@Controller - Indicates that a class is a web controller");
        System.out.println("@RestController - Combines @Controller and @ResponseBody");
        
        System.out.println("\nDependency Injection Annotations:");
        System.out.println("@Autowired - Marks a constructor, field, or setter method for auto-wiring");
        System.out.println("@Qualifier - Specifies which bean should be autowired when multiple candidates exist");
        System.out.println("@Value - Injects values from properties files or environment variables");
        System.out.println("@Bean - Indicates that a method produces a bean to be managed by Spring");
        
        System.out.println("\nRequest Mapping Annotations:");
        System.out.println("@RequestMapping - Maps HTTP requests to handler methods");
        System.out.println("@GetMapping - Shortcut for @RequestMapping(method = RequestMethod.GET)");
        System.out.println("@PostMapping - Shortcut for @RequestMapping(method = RequestMethod.POST)");
        System.out.println("@PutMapping - Shortcut for @RequestMapping(method = RequestMethod.PUT)");
        System.out.println("@DeleteMapping - Shortcut for @RequestMapping(method = RequestMethod.DELETE)");
        System.out.println("@PatchMapping - Shortcut for @RequestMapping(method = RequestMethod.PATCH)");
        
        System.out.println("\nRequest Parameter Annotations:");
        System.out.println("@RequestParam - Binds request parameters to method parameters");
        System.out.println("@PathVariable - Binds URI template variables to method parameters");
        System.out.println("@RequestBody - Binds the HTTP request body to a method parameter");
        System.out.println("@ResponseBody - Indicates that the return value should be bound to the web response body");
        
        System.out.println("\nData Access Annotations:");
        System.out.println("@Transactional - Declares transaction boundaries and rollback rules");
        System.out.println("@Query - Defines a custom query in Spring Data repositories");
        
        System.out.println("\nConfiguration Annotations:");
        System.out.println("@Profile - Indicates that a component is eligible for registration when specific profiles are active");
        System.out.println("@ConfigurationProperties - Binds external configuration properties to a class");
        System.out.println("@EnableScheduling - Enables Spring's scheduled task execution capability");
        System.out.println("@EnableCaching - Enables Spring's caching support");
        
        System.out.println("\nExample of using annotations in a Spring Boot application:");
        System.out.println("```java");
        System.out.println("@SpringBootApplication");
        System.out.println("public class Application {");
        System.out.println("    public static void main(String[] args) {");
        System.out.println("        SpringApplication.run(Application.class, args);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("");
        System.out.println("@RestController");
        System.out.println("@RequestMapping(\"/api/users\")");
        System.out.println("public class UserController {");
        System.out.println("    @Autowired");
        System.out.println("    private UserService userService;");
        System.out.println("    ");
        System.out.println("    @GetMapping");
        System.out.println("    public List<User> getAllUsers() {");
        System.out.println("        return userService.findAll();");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @GetMapping(\"/{id}\")");
        System.out.println("    public User getUser(@PathVariable Long id) {");
        System.out.println("        return userService.findById(id);");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @PostMapping");
        System.out.println("    public User createUser(@RequestBody User user) {");
        System.out.println("        return userService.save(user);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
    }
    
    /**
     * 4. Spring Boot Actuator
     * 
     * Overview of Spring Boot Actuator, which provides production-ready features
     * for monitoring and managing Spring Boot applications.
     */
    private static void explainSpringBootActuator() {
        System.out.println("\n4. Spring Boot Actuator:\n");
        
        System.out.println("Spring Boot Actuator provides production-ready features to help monitor and manage applications.");
        
        System.out.println("\nKey Features:");
        System.out.println("1. Health Checks - Provides information about application health");
        System.out.println("2. Metrics - Collects and exposes metrics from the application");
        System.out.println("3. Auditing - Tracks events and actions in the application");
        System.out.println("4. HTTP Tracing - Provides information about HTTP requests and responses");
        System.out.println("5. Environment Information - Exposes environment variables and configuration properties");
        System.out.println("6. Loggers - Allows viewing and modifying logger configurations at runtime");
        System.out.println("7. Thread Dump - Provides thread information for debugging");
        
        System.out.println("\nHow to Enable Actuator:");
        System.out.println("1. Add the dependency to your pom.xml:");
        System.out.println("```xml");
        System.out.println("<dependency>");
        System.out.println("    <groupId>org.springframework.boot</groupId>");
        System.out.println("    <artifactId>spring-boot-starter-actuator</artifactId>");
        System.out.println("</dependency>");
        System.out.println("```");
        
        System.out.println("\n2. Configure endpoints in application.properties:");
        System.out.println("```properties");
        System.out.println("# Enable all endpoints");
        System.out.println("management.endpoints.web.exposure.include=*");
        System.out.println("");
        System.out.println("# Or enable specific endpoints");
        System.out.println("management.endpoints.web.exposure.include=health,info,metrics");
        System.out.println("");
        System.out.println("# Configure base path for actuator endpoints (default is /actuator)");
        System.out.println("management.endpoints.web.base-path=/management");
        System.out.println("```");
        
        System.out.println("\nCommon Actuator Endpoints:");
        System.out.println("/actuator/health - Shows application health information");
        System.out.println("/actuator/info - Displays arbitrary application info");
        System.out.println("/actuator/metrics - Shows metrics information");
        System.out.println("/actuator/env - Exposes environment properties");
        System.out.println("/actuator/loggers - Shows and modifies logger configurations");
        System.out.println("/actuator/mappings - Displays all @RequestMapping paths");
        System.out.println("/actuator/beans - Displays all Spring beans in the application");
        
        System.out.println("\nCustomizing Health Information:");
        System.out.println("```java");
        System.out.println("@Component");
        System.out.println("public class CustomHealthIndicator implements HealthIndicator {");
        System.out.println("    @Override");
        System.out.println("    public Health health() {");
        System.out.println("        // Perform health check logic");
        System.out.println("        boolean isHealthy = checkIfSystemIsHealthy();");
        System.out.println("        ");
        System.out.println("        if (isHealthy) {");
        System.out.println("            return Health.up()");
        System.out.println("                         .withDetail(\"customKey\", \"customValue\")");
        System.out.println("                         .build();");
        System.out.println("        } else {");
        System.out.println("            return Health.down()");
        System.out.println("                         .withDetail(\"error\", \"System is unhealthy\")");
        System.out.println("                         .build();");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nSecurity Considerations:");
        System.out.println("Actuator endpoints may expose sensitive information, so in production:");
        System.out.println("1. Limit exposed endpoints to only what's necessary");
        System.out.println("2. Secure endpoints with Spring Security");
        System.out.println("3. Consider using a separate management port");
    }
    
    /**
     * 5. @Transactional
     * 
     * Explanation of the @Transactional annotation in Spring, which is used to
     * define transaction boundaries and rollback rules.
     */
    private static void explainTransactional() {
        System.out.println("\n5. @Transactional:\n");
        
        System.out.println("The @Transactional annotation is used to specify the transaction attributes for a method or class.");
        
        System.out.println("\nKey Features:");
        System.out.println("1. Defines transaction boundaries");
        System.out.println("2. Specifies rollback rules");
        System.out.println("3. Controls isolation level");
        System.out.println("4. Sets propagation behavior");
        System.out.println("5. Defines timeout for long-running transactions");
        
        System.out.println("\nBasic Usage:");
        System.out.println("```java");
        System.out.println("@Service");
        System.out.println("public class UserService {");
        System.out.println("    @Autowired");
        System.out.println("    private UserRepository userRepository;");
        System.out.println("    ");
        System.out.println("    @Transactional");
        System.out.println("    public void createUser(User user) {");
        System.out.println("        userRepository.save(user);");
        System.out.println("        // If any exception occurs, the transaction will be rolled back");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nAttributes of @Transactional:");
        
        System.out.println("\n1. Propagation - Defines how transactions relate to each other");
        System.out.println("   - REQUIRED (default): Use current transaction or create a new one if none exists");
        System.out.println("   - REQUIRES_NEW: Always create a new transaction");
        System.out.println("   - SUPPORTS: Use current transaction if it exists, otherwise non-transactional");
        System.out.println("   - NOT_SUPPORTED: Execute non-transactionally, suspend current transaction if it exists");
        System.out.println("   - MANDATORY: Use current transaction, throw exception if none exists");
        System.out.println("   - NEVER: Execute non-transactionally, throw exception if a transaction exists");
        System.out.println("   - NESTED: Execute within a nested transaction if a current transaction exists");
        
        System.out.println("\n2. Isolation - Defines how changes made by one transaction are visible to others");
        System.out.println("   - DEFAULT: Use the default isolation level of the underlying database");
        System.out.println("   - READ_UNCOMMITTED: Allows dirty reads, non-repeatable reads, and phantom reads");
        System.out.println("   - READ_COMMITTED: Prevents dirty reads, but allows non-repeatable reads and phantom reads");
        System.out.println("   - REPEATABLE_READ: Prevents dirty reads and non-repeatable reads, but allows phantom reads");
        System.out.println("   - SERIALIZABLE: Prevents dirty reads, non-repeatable reads, and phantom reads");
        
        System.out.println("\n3. Rollback Rules - Define when to rollback a transaction");
        System.out.println("   - rollbackFor: Specifies exception types that must cause rollback");
        System.out.println("   - noRollbackFor: Specifies exception types that must not cause rollback");
        
        System.out.println("\n4. Other Attributes:");
        System.out.println("   - timeout: Timeout for the transaction (in seconds)");
        System.out.println("   - readOnly: Hint to the persistence provider that the transaction is read-only");
        
        System.out.println("\nExample with Attributes:");
        System.out.println("```java");
        System.out.println("@Transactional(");
        System.out.println("    propagation = Propagation.REQUIRED,");
        System.out.println("    isolation = Isolation.READ_COMMITTED,");
        System.out.println("    timeout = 30,");
        System.out.println("    readOnly = false,");
        System.out.println("    rollbackFor = {SQLException.class, DataAccessException.class},");
        System.out.println("    noRollbackFor = {IllegalArgumentException.class}");
        System.out.println(")");
        System.out.println("public void complexTransactionalOperation() {");
        System.out.println("    // Method implementation");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nClass-level vs Method-level:");
        System.out.println("- @Transactional can be applied at class level (affects all methods)");
        System.out.println("- Method-level annotations override class-level settings");
        
        System.out.println("\nImportant Considerations:");
        System.out.println("1. @Transactional only works on public methods (by default)");
        System.out.println("2. Self-invocation (calling a @Transactional method from within the same class) doesn't work");
        System.out.println("3. Requires transaction management to be enabled (@EnableTransactionManagement)");
        System.out.println("4. By default, only RuntimeException and Error trigger rollback (not checked exceptions)");
    }
    
    /**
     * 6. @PathVariable vs @RequestParam
     * 
     * Comparison between @PathVariable and @RequestParam annotations in Spring MVC.
     */
    private static void explainPathVariableVsRequestParam() {
        System.out.println("\n6. @PathVariable vs @RequestParam:\n");
        
        System.out.println("Both annotations are used to extract values from the HTTP request, but they work differently:");
        
        System.out.println("\n@PathVariable:");
        System.out.println("- Extracts values from the URI path");
        System.out.println("- Used with URI templates (placeholders in the URL path)");
        System.out.println("- Values are part of the URI path structure");
        
        System.out.println("\n@RequestParam:");
        System.out.println("- Extracts values from query parameters in the URL");
        System.out.println("- Can also extract form data in POST requests");
        System.out.println("- Values are specified as key-value pairs after '?' in the URL");
        
        System.out.println("\nExample of @PathVariable:");
        System.out.println("```java");
        System.out.println("@GetMapping(\"/users/{id}\")");
        System.out.println("public User getUser(@PathVariable(\"id\") Long userId) {");
        System.out.println("    return userService.findById(userId);");
        System.out.println("}");
        System.out.println("```");
        System.out.println("URL example: /users/123");
        
        System.out.println("\nExample of @RequestParam:");
        System.out.println("```java");
        System.out.println("@GetMapping(\"/users\")");
        System.out.println("public List<User> searchUsers(@RequestParam(\"name\") String name) {");
        System.out.println("    return userService.findByName(name);");
        System.out.println("}");
        System.out.println("```");
        System.out.println("URL example: /users?name=John");
        
        System.out.println("\nMultiple Parameters:");
        
        System.out.println("\nMultiple @PathVariable:");
        System.out.println("```java");
        System.out.println("@GetMapping(\"/users/{userId}/posts/{postId}\")");
        System.out.println("public Post getUserPost(");
        System.out.println("    @PathVariable(\"userId\") Long userId,");
        System.out.println("    @PathVariable(\"postId\") Long postId) {");
        System.out.println("    return postService.findByUserIdAndPostId(userId, postId);");
        System.out.println("}");
        System.out.println("```");
        System.out.println("URL example: /users/123/posts/456");
        
        System.out.println("\nMultiple @RequestParam:");
        System.out.println("```java");
        System.out.println("@GetMapping(\"/search\")");
        System.out.println("public List<User> searchUsers(");
        System.out.println("    @RequestParam(\"name\") String name,");
        System.out.println("    @RequestParam(value = \"age\", required = false) Integer age,");
        System.out.println("    @RequestParam(value = \"active\", defaultValue = \"true\") boolean active) {");
        System.out.println("    return userService.search(name, age, active);");
        System.out.println("}");
        System.out.println("```");
        System.out.println("URL example: /search?name=John&age=30&active=false");
        
        System.out.println("\nKey Differences:");
        System.out.println("1. Location: @PathVariable is part of the path, @RequestParam is a query parameter");
        System.out.println("2. Usage: @PathVariable for identifying resources, @RequestParam for filtering/sorting");
        System.out.println("3. Required: @PathVariable is typically required, @RequestParam can be optional");
        System.out.println("4. REST conventions: @PathVariable aligns with RESTful URL patterns");
        
        System.out.println("\nWhen to use which:");
        System.out.println("- Use @PathVariable for identifying resources (e.g., /users/{id}, /products/{productId})");
        System.out.println("- Use @RequestParam for filtering, sorting, pagination (e.g., /users?role=admin&status=active)");
    }
    
    /**
     * 7. Spring Boot Features
     * 
     * Overview of key features provided by Spring Boot.
     */
    private static void explainSpringBootFeatures() {
        System.out.println("\n7. Spring Boot Features:\n");
        
        System.out.println("Spring Boot provides numerous features to simplify Java application development:");
        
        System.out.println("\n1. Auto-configuration");
        System.out.println("   - Automatically configures Spring application based on dependencies");
        System.out.println("   - Reduces boilerplate configuration code");
        System.out.println("   - Can be customized or overridden as needed");
        
        System.out.println("\n2. Standalone Applications");
        System.out.println("   - Creates self-contained applications that can be run with 'java -jar'");
        System.out.println("   - Embeds application servers (Tomcat, Jetty, Undertow)");
        System.out.println("   - No need for external server deployment");
        
        System.out.println("\n3. Opinionated Defaults");
        System.out.println("   - Provides sensible default configurations");
        System.out.println("   - Follows convention over configuration principle");
        System.out.println("   - Allows developers to focus on business logic");
        
        System.out.println("\n4. Spring Boot Starters");
        System.out.println("   - Curated sets of dependencies for specific functionality");
        System.out.println("   - Simplifies dependency management");
        System.out.println("   - Examples: spring-boot-starter-web, spring-boot-starter-data-jpa");
        
        System.out.println("\n5. Externalized Configuration");
        System.out.println("   - Supports properties files, YAML files, environment variables, command-line arguments");
        System.out.println("   - Type-safe configuration with @ConfigurationProperties");
        System.out.println("   - Profile-specific configuration");
        
        System.out.println("\n6. Production-Ready Features");
        System.out.println("   - Spring Boot Actuator for monitoring and management");
        System.out.println("   - Health checks, metrics, auditing, HTTP tracing");
        System.out.println("   - Easy integration with monitoring systems");
        
        System.out.println("\n7. Easy Testing");
        System.out.println("   - Spring Boot Test module with auto-configuration for tests");
        System.out.println("   - Support for testing slices of the application");
        System.out.println("   - Integration with JUnit, Mockito, etc.");
        
        System.out.println("\n8. Developer Tools");
        System.out.println("   - Automatic restart when code changes");
        System.out.println("   - LiveReload support for browser refresh");
        System.out.println("   - Remote debugging and development");
        
        System.out.println("\n9. Extensive Logging Support");
        System.out.println("   - Default logging configuration");
        System.out.println("   - Support for various logging frameworks");
        System.out.println("   - Easy customization of logging levels");
        
        System.out.println("\n10. Easy Database Integration");
        System.out.println("    - Auto-configuration for various databases");
        System.out.println("    - Support for JPA, JDBC, NoSQL databases");
        System.out.println("    - Database migration tools integration (Flyway, Liquibase)");
        
        System.out.println("\nExample of a minimal Spring Boot application:");
        System.out.println("```java");
        System.out.println("@SpringBootApplication");
        System.out.println("public class Application {");
        System.out.println("    public static void main(String[] args) {");
        System.out.println("        SpringApplication.run(Application.class, args);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
    }
    
    /**
     * 8. Spring MVC
     * 
     * Overview of Spring MVC (Model-View-Controller) framework.
     */
    private static void explainSpringMVC() {
        System.out.println("\n8. Spring MVC:\n");
        
        System.out.println("Spring MVC is a web framework built on the Servlet API that follows the Model-View-Controller design pattern.");
        
        System.out.println("\nCore Components:");
        
        System.out.println("\n1. DispatcherServlet");
        System.out.println("   - Central servlet that dispatches requests to handlers");
        System.out.println("   - Entry point for HTTP requests in a Spring MVC application");
        System.out.println("   - Coordinates the entire request processing workflow");
        
        System.out.println("\n2. Controllers");
        System.out.println("   - Handle incoming requests and return appropriate responses");
        System.out.println("   - Annotated with @Controller or @RestController");
        System.out.println("   - Use @RequestMapping and its variants to map requests to methods");
        
        System.out.println("\n3. Models");
        System.out.println("   - Contain the data to be displayed in the view");
        System.out.println("   - Can be any Java object");
        System.out.println("   - Added to Model, ModelMap, or ModelAndView objects");
        
        System.out.println("\n4. Views");
        System.out.println("   - Render the model data as HTML, JSON, XML, etc.");
        System.out.println("   - Common view technologies: Thymeleaf, JSP, FreeMarker");
        System.out.println("   - Can be bypassed with @ResponseBody for direct response writing");
        
        System.out.println("\n5. View Resolvers");
        System.out.println("   - Determine which view to render based on the view name");
        System.out.println("   - Map logical view names to actual view implementations");
        System.out.println("   - Examples: InternalResourceViewResolver, ThymeleafViewResolver");
        
        System.out.println("\nRequest Processing Workflow:");
        System.out.println("1. Client sends HTTP request to DispatcherServlet");
        System.out.println("2. DispatcherServlet consults HandlerMapping to identify the controller");
        System.out.println("3. Controller processes the request and returns a ModelAndView object");
        System.out.println("4. DispatcherServlet consults ViewResolver to determine the view");
        System.out.println("5. View renders the model data and returns the response to the client");
        
        System.out.println("\nExample of a Spring MVC Controller:");
        System.out.println("```java");
        System.out.println("@Controller");
        System.out.println("@RequestMapping(\"/users\")");
        System.out.println("public class UserController {");
        System.out.println("    @Autowired");
        System.out.println("    private UserService userService;");
        System.out.println("    ");
        System.out.println("    @GetMapping");
        System.out.println("    public String listUsers(Model model) {");
        System.out.println("        model.addAttribute(\"users\", userService.findAll());");
        System.out.println("        return \"user/list\"; // View name");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @GetMapping(\"/{id}\")");
        System.out.println("    public String viewUser(@PathVariable Long id, Model model) {");
        System.out.println("        model.addAttribute(\"user\", userService.findById(id));");
        System.out.println("        return \"user/view\";");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @PostMapping");
        System.out.println("    public String createUser(@ModelAttribute User user, BindingResult result) {");
        System.out.println("        if (result.hasErrors()) {");
        System.out.println("            return \"user/form\";");
        System.out.println("        }");
        System.out.println("        userService.save(user);");
        System.out.println("        return \"redirect:/users\";");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nSpring MVC Features:");
        System.out.println("1. Flexible controller method signatures");
        System.out.println("2. Powerful data binding and validation");
        System.out.println("3. Exception handling with @ExceptionHandler");
        System.out.println("4. Interceptors for cross-cutting concerns");
        System.out.println("5. Content negotiation for different response formats");
        System.out.println("6. File upload handling");
        System.out.println("7. Internationalization and localization support");
        
        System.out.println("\nSpring MVC in Spring Boot:");
        System.out.println("- Auto-configured with spring-boot-starter-web");
        System.out.println("- Embedded Tomcat, Jetty, or Undertow server");
        System.out.println("- Sensible defaults for ViewResolvers, MessageConverters, etc.");
        System.out.println("- Easy customization through properties or configuration beans");
    }
    
    /**
     * 9. Controller vs RestController
     * 
     * Comparison between @Controller and @RestController annotations in Spring MVC.
     */
    private static void explainControllerVsRestController() {
        System.out.println("\n9. Controller vs RestController:\n");
        
        System.out.println("@Controller:");
        System.out.println("- Used for traditional Spring MVC applications");
        System.out.println("- Returns view names by default (to be resolved by ViewResolver)");
        System.out.println("- Requires @ResponseBody to return data directly (not a view)");
        System.out.println("- Typically used with template engines (Thymeleaf, JSP, etc.)");
        
        System.out.println("\n@RestController:");
        System.out.println("- Specialized version of @Controller");
        System.out.println("- Combines @Controller and @ResponseBody");
        System.out.println("- Always returns data directly in the response body, not a view");
        System.out.println("- Typically used for RESTful web services returning JSON/XML");
        
        System.out.println("\nExample of @Controller:");
        System.out.println("```java");
        System.out.println("@Controller");
        System.out.println("@RequestMapping(\"/users\")");
        System.out.println("public class UserController {");
        System.out.println("    @Autowired");
        System.out.println("    private UserService userService;");
        System.out.println("    ");
        System.out.println("    // Returns a view name (resolved by ViewResolver)");
        System.out.println("    @GetMapping");
        System.out.println("    public String listUsers(Model model) {");
        System.out.println("        model.addAttribute(\"users\", userService.findAll());");
        System.out.println("        return \"user/list\"; // View name");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    // Returns data directly (not a view) using @ResponseBody");
        System.out.println("    @GetMapping(\"/api/{id}\")");
        System.out.println("    @ResponseBody");
        System.out.println("    public User getUserJson(@PathVariable Long id) {");
        System.out.println("        return userService.findById(id);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of @RestController:");
        System.out.println("```java");
        System.out.println("@RestController");
        System.out.println("@RequestMapping(\"/api/users\")");
        System.out.println("public class UserRestController {");
        System.out.println("    @Autowired");
        System.out.println("    private UserService userService;");
        System.out.println("    ");
        System.out.println("    // All methods return data directly (not views)");
        System.out.println("    // No need for @ResponseBody");
        System.out.println("    @GetMapping");
        System.out.println("    public List<User> getAllUsers() {");
        System.out.println("        return userService.findAll();");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @GetMapping(\"/{id}\")");
        System.out.println("    public User getUser(@PathVariable Long id) {");
        System.out.println("        return userService.findById(id);");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    @PostMapping");
        System.out.println("    public User createUser(@RequestBody User user) {");
        System.out.println("        return userService.save(user);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nKey Differences:");
        System.out.println("1. Return Values:");
        System.out.println("   - @Controller: Returns view names by default");
        System.out.println("   - @RestController: Returns data directly (serialized to JSON/XML)");
        
        System.out.println("\n2. @ResponseBody:");
        System.out.println("   - @Controller: Requires @ResponseBody to return data directly");
        System.out.println("   - @RestController: Implicitly adds @ResponseBody to all methods");
        
        System.out.println("\n3. Use Cases:");
        System.out.println("   - @Controller: Web applications with HTML views");
        System.out.println("   - @RestController: RESTful APIs returning data");
        
        System.out.println("\n4. Content Negotiation:");
        System.out.println("   - Both support content negotiation (JSON, XML, etc.)");
        System.out.println("   - @RestController is optimized for this use case");
        
        System.out.println("\nWhen to use which:");
        System.out.println("- Use @Controller when building a web application with views");
        System.out.println("- Use @RestController when building a RESTful API");
        System.out.println("- You can use both in the same application for different endpoints");
    }
    
    /**
     * 10. Bean Scopes
     * 
     * Overview of different bean scopes in Spring Framework.
     */
    private static void explainBeanScopes() {
        System.out.println("\n10. Bean Scopes:\n");
        
        System.out.println("Bean scope defines the lifecycle and visibility of beans created by the Spring IoC container.");
        
        System.out.println("\nSpring supports the following bean scopes:");
        
        System.out.println("\n1. Singleton (default)");
        System.out.println("   - Single instance per Spring IoC container");
        System.out.println("   - Default scope if none specified");
        System.out.println("   - Shared instance for all requests");
        System.out.println("   - Example: @Scope(\"singleton\") or @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)");
        
        System.out.println("\n2. Prototype");
        System.out.println("   - New instance created each time the bean is requested");
        System.out.println("   - Not cached in the container");
        System.out.println("   - Destruction lifecycle callbacks not called automatically");
        System.out.println("   - Example: @Scope(\"prototype\") or @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)");
        
        System.out.println("\n3. Request");
        System.out.println("   - New instance per HTTP request");
        System.out.println("   - Only valid in web-aware Spring ApplicationContext");
        System.out.println("   - Example: @Scope(\"request\") or @Scope(WebApplicationContext.SCOPE_REQUEST)");
        
        System.out.println("\n4. Session");
        System.out.println("   - New instance per HTTP session");
        System.out.println("   - Only valid in web-aware Spring ApplicationContext");
        System.out.println("   - Example: @Scope(\"session\") or @Scope(WebApplicationContext.SCOPE_SESSION)");
        
        System.out.println("\n5. Application");
        System.out.println("   - Single instance per ServletContext (per web application)");
        System.out.println("   - Only valid in web-aware Spring ApplicationContext");
        System.out.println("   - Example: @Scope(\"application\") or @Scope(WebApplicationContext.SCOPE_APPLICATION)");
        
        System.out.println("\n6. Websocket");
        System.out.println("   - New instance per WebSocket session");
        System.out.println("   - Only valid in web-aware Spring ApplicationContext");
        System.out.println("   - Example: @Scope(\"websocket\")");
        
        System.out.println("\nExample of Bean Scope Usage:");
        System.out.println("```java");
        System.out.println("// Singleton scope (default)");
        System.out.println("@Component");
        System.out.println("public class SingletonBean {");
        System.out.println("    // Bean definition");
        System.out.println("}");
        System.out.println("");
        System.out.println("// Prototype scope");
        System.out.println("@Component");
        System.out.println("@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)");
        System.out.println("public class PrototypeBean {");
        System.out.println("    // Bean definition");
        System.out.println("}");
        System.out.println("");
        System.out.println("// Request scope");
        System.out.println("@Component");
        System.out.println("@Scope(WebApplicationContext.SCOPE_REQUEST)");
        System.out.println("public class RequestScopedBean {");
        System.out.println("    // Bean definition");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nScoped Proxy:");
        System.out.println("When injecting a shorter-lived bean (e.g., request-scoped) into a longer-lived bean (e.g., singleton),");
        System.out.println("you need to use a proxy to ensure the correct instance is used:");
        
        System.out.println("\n```java");
        System.out.println("@Component");
        System.out.println("@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)");
        System.out.println("public class RequestScopedBean {");
        System.out.println("    // Bean definition");
        System.out.println("}");
        System.out.println("");
        System.out.println("@Component");
        System.out.println("public class SingletonBean {");
        System.out.println("    @Autowired");
        System.out.println("    private RequestScopedBean requestScopedBean; // This will be a proxy");
        System.out.println("    ");
        System.out.println("    public void doSomething() {");
        System.out.println("        // The proxy will delegate to the current request-scoped instance");
        System.out.println("        requestScopedBean.someMethod();");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nConsiderations for Bean Scopes:");
        System.out.println("1. Singleton is the most efficient scope (less object creation)");
        System.out.println("2. Prototype beans require more memory and GC overhead");
        System.out.println("3. Web-aware scopes require additional configuration in non-web environments");
        System.out.println("4. Be careful with mutable state in singleton beans");
        System.out.println("5. Use proxies when injecting shorter-lived beans into longer-lived ones");
    }
    
    /**
     * 11. SQL Joins
     * 
     * Overview of different types of SQL joins.
     */
    private static void explainSQLJoins() {
        System.out.println("\n11. SQL Joins:\n");
        
        System.out.println("SQL joins are used to combine rows from two or more tables based on a related column.");
        
        System.out.println("\nMain Types of SQL Joins:");
        
        System.out.println("\n1. INNER JOIN");
        System.out.println("   - Returns rows when there is a match in both tables");
        System.out.println("   - Syntax: SELECT * FROM table1 INNER JOIN table2 ON table1.column = table2.column");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT users.name, orders.order_date");
        System.out.println("     FROM users");
        System.out.println("     INNER JOIN orders ON users.id = orders.user_id");
        System.out.println("     ```");
        
        System.out.println("\n2. LEFT JOIN (or LEFT OUTER JOIN)");
        System.out.println("   - Returns all rows from the left table and matched rows from the right table");
        System.out.println("   - If no match, NULL values are returned for right table columns");
        System.out.println("   - Syntax: SELECT * FROM table1 LEFT JOIN table2 ON table1.column = table2.column");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT users.name, orders.order_date");
        System.out.println("     FROM users");
        System.out.println("     LEFT JOIN orders ON users.id = orders.user_id");
        System.out.println("     ```");
        
        System.out.println("\n3. RIGHT JOIN (or RIGHT OUTER JOIN)");
        System.out.println("   - Returns all rows from the right table and matched rows from the left table");
        System.out.println("   - If no match, NULL values are returned for left table columns");
        System.out.println("   - Syntax: SELECT * FROM table1 RIGHT JOIN table2 ON table1.column = table2.column");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT users.name, orders.order_date");
        System.out.println("     FROM users");
        System.out.println("     RIGHT JOIN orders ON users.id = orders.user_id");
        System.out.println("     ```");
        
        System.out.println("\n4. FULL JOIN (or FULL OUTER JOIN)");
        System.out.println("   - Returns rows when there is a match in either left or right table");
        System.out.println("   - If no match, NULL values are returned for the columns from the table without a match");
        System.out.println("   - Syntax: SELECT * FROM table1 FULL JOIN table2 ON table1.column = table2.column");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT users.name, orders.order_date");
        System.out.println("     FROM users");
        System.out.println("     FULL JOIN orders ON users.id = orders.user_id");
        System.out.println("     ```");
        
        System.out.println("\n5. CROSS JOIN");
        System.out.println("   - Returns the Cartesian product of both tables (all possible combinations)");
        System.out.println("   - No ON clause is used");
        System.out.println("   - Syntax: SELECT * FROM table1 CROSS JOIN table2");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT users.name, products.name");
        System.out.println("     FROM users");
        System.out.println("     CROSS JOIN products");
        System.out.println("     ```");
        
        System.out.println("\n6. SELF JOIN");
        System.out.println("   - Joins a table to itself");
        System.out.println("   - Useful for hierarchical or recursive data");
        System.out.println("   - Uses table aliases to distinguish between instances of the same table");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT e1.name AS employee, e2.name AS manager");
        System.out.println("     FROM employees e1");
        System.out.println("     INNER JOIN employees e2 ON e1.manager_id = e2.id");
        System.out.println("     ```");
        
        System.out.println("\nAdditional Join Operations:");
        
        System.out.println("\n1. NATURAL JOIN");
        System.out.println("   - Joins tables based on columns with the same name");
        System.out.println("   - No need to specify the join condition");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT * FROM users NATURAL JOIN user_profiles");
        System.out.println("     ```");
        
        System.out.println("\n2. JOIN with USING clause");
        System.out.println("   - Specifies the common column names to join on");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     SELECT * FROM users JOIN orders USING (user_id)");
        System.out.println("     ```");
        
        System.out.println("\nJoin Performance Considerations:");
        System.out.println("1. Use appropriate indexes on join columns");
        System.out.println("2. Be cautious with CROSS JOINs on large tables (Cartesian product)");
        System.out.println("3. Consider query execution plans for complex joins");
        System.out.println("4. Use WHERE clauses to filter data before joining when possible");
        System.out.println("5. Consider denormalization for frequently joined tables in read-heavy applications");
    }
    
    /**
     * 12. Hibernate vs JDBC
     * 
     * Comparison between Hibernate (ORM) and JDBC for database access.
     */
    private static void explainHibernateVsJDBC() {
        System.out.println("\n12. Hibernate vs JDBC:\n");
        
        System.out.println("JDBC (Java Database Connectivity):");
        System.out.println("- Low-level API for database operations in Java");
        System.out.println("- Requires manual SQL queries and result set handling");
        System.out.println("- Direct control over database operations");
        System.out.println("- No automatic object-relational mapping");
        System.out.println("- Requires more boilerplate code");
        
        System.out.println("\nHibernate:");
        System.out.println("- High-level ORM (Object-Relational Mapping) framework");
        System.out.println("- Automatically maps Java objects to database tables");
        System.out.println("- Uses HQL (Hibernate Query Language) or Criteria API");
        System.out.println("- Manages database connections and transactions");
        System.out.println("- Implements JPA (Java Persistence API) specification");
        
        System.out.println("\nKey Differences:");
        
        System.out.println("\n1. Abstraction Level");
        System.out.println("   JDBC: Low-level, close to the database");
        System.out.println("   Hibernate: High-level, abstracts database details");
        
        System.out.println("\n2. Productivity");
        System.out.println("   JDBC: More code required for basic operations");
        System.out.println("   Hibernate: Less code, automatic mapping reduces boilerplate");
        
        System.out.println("\n3. SQL Knowledge");
        System.out.println("   JDBC: Requires writing SQL queries manually");
        System.out.println("   Hibernate: Can generate SQL automatically");
        
        System.out.println("\n4. Performance");
        System.out.println("   JDBC: Potentially better for specific optimized queries");
        System.out.println("   Hibernate: May have overhead but offers caching and optimizations");
        
        System.out.println("\n5. Database Portability");
        System.out.println("   JDBC: SQL may need changes for different databases");
        System.out.println("   Hibernate: Better database independence through dialect system");
        
        System.out.println("\nExample of JDBC Code:");
        System.out.println("```java");
        System.out.println("// JDBC example to fetch a user by ID");
        System.out.println("public User getUserById(long id) {");
        System.out.println("    User user = null;");
        System.out.println("    Connection conn = null;");
        System.out.println("    PreparedStatement stmt = null;");
        System.out.println("    ResultSet rs = null;");
        System.out.println("    ");
        System.out.println("    try {");
        System.out.println("        conn = DriverManager.getConnection(\"jdbc:mysql://localhost:3306/mydb\", \"user\", \"password\");");
        System.out.println("        stmt = conn.prepareStatement(\"SELECT * FROM users WHERE id = ?\");");
        System.out.println("        stmt.setLong(1, id);");
        System.out.println("        rs = stmt.executeQuery();");
        System.out.println("        ");
        System.out.println("        if (rs.next()) {");
        System.out.println("            user = new User();");
        System.out.println("            user.setId(rs.getLong(\"id\"));");
        System.out.println("            user.setName(rs.getString(\"name\"));");
        System.out.println("            user.setEmail(rs.getString(\"email\"));");
        System.out.println("        }");
        System.out.println("    } catch (SQLException e) {");
        System.out.println("        e.printStackTrace();");
        System.out.println("    } finally {");
        System.out.println("        // Close resources (rs, stmt, conn)");
        System.out.println("        try {");
        System.out.println("            if (rs != null) rs.close();");
        System.out.println("            if (stmt != null) stmt.close();");
        System.out.println("            if (conn != null) conn.close();");
        System.out.println("        } catch (SQLException e) {");
        System.out.println("            e.printStackTrace();");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    return user;");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of Hibernate Code:");
        System.out.println("```java");
        System.out.println("// Entity class");
        System.out.println("@Entity");
        System.out.println("@Table(name = \"users\")");
        System.out.println("public class User {");
        System.out.println("    @Id");
        System.out.println("    @GeneratedValue(strategy = GenerationType.IDENTITY)");
        System.out.println("    private Long id;");
        System.out.println("    ");
        System.out.println("    private String name;");
        System.out.println("    private String email;");
        System.out.println("    ");
        System.out.println("    // Getters and setters");
        System.out.println("}");
        System.out.println("");
        System.out.println("// Hibernate example to fetch a user by ID");
        System.out.println("public User getUserById(long id) {");
        System.out.println("    Session session = sessionFactory.openSession();");
        System.out.println("    try {");
        System.out.println("        return session.get(User.class, id);");
        System.out.println("    } finally {");
        System.out.println("        session.close();");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nWhen to use which:");
        System.out.println("Use JDBC when:");
        System.out.println("- You need fine-grained control over SQL queries");
        System.out.println("- Performance is critical for specific operations");
        System.out.println("- The application has simple database interactions");
        System.out.println("- You're working with legacy systems or specific database features");
        
        System.out.println("\nUse Hibernate when:");
        System.out.println("- You want to reduce boilerplate code");
        System.out.println("- Object-relational mapping is important");
        System.out.println("- Database portability is required");
        System.out.println("- You want features like caching, lazy loading, and dirty checking");
        System.out.println("- You're building a complex application with many entities and relationships");
        
        System.out.println("\nHybrid Approach:");
        System.out.println("Many applications use both:");
        System.out.println("- Hibernate for most operations");
        System.out.println("- JDBC for performance-critical or complex queries");
        System.out.println("- Spring Data JPA provides a good balance with repositories and native query support");
    }
    
    /**
     * 13. Database Indexing
     * 
     * Overview of database indexing concepts and best practices.
     */
    private static void explainDatabaseIndexing() {
        System.out.println("\n13. Database Indexing:\n");
        
        System.out.println("Database indexing is a technique to optimize the performance of database queries by");
        System.out.println("creating data structures that improve the speed of data retrieval operations.");
        
        System.out.println("\nPurpose of Indexing:");
        System.out.println("- Speed up query performance");
        System.out.println("- Reduce disk I/O operations");
        System.out.println("- Enforce uniqueness constraints");
        System.out.println("- Support efficient sorting and grouping");
        
        System.out.println("\nTypes of Indexes:");
        
        System.out.println("\n1. Primary Index");
        System.out.println("   - Created on the primary key of a table");
        System.out.println("   - Ensures uniqueness and provides fast access to rows");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     CREATE TABLE users (");
        System.out.println("         id INT PRIMARY KEY,  -- Automatically creates a primary index");
        System.out.println("         name VARCHAR(100),");
        System.out.println("         email VARCHAR(100)");
        System.out.println("     );");
        System.out.println("     ```");
        
        System.out.println("\n2. Unique Index");
        System.out.println("   - Ensures all values in the indexed column(s) are unique");
        System.out.println("   - Can be applied to non-primary key columns");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     CREATE UNIQUE INDEX idx_users_email ON users(email);");
        System.out.println("     ```");
        
        System.out.println("\n3. Composite Index");
        System.out.println("   - Created on multiple columns");
        System.out.println("   - Useful for queries that filter on multiple columns");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     CREATE INDEX idx_users_name_email ON users(name, email);");
        System.out.println("     ```");
        
        System.out.println("\n4. Clustered Index");
        System.out.println("   - Determines the physical order of data in a table");
        System.out.println("   - Only one clustered index per table");
        System.out.println("   - In many databases, the primary key is automatically a clustered index");
        System.out.println("   - Example (SQL Server):");
        System.out.println("     ```sql");
        System.out.println("     CREATE CLUSTERED INDEX idx_users_id ON users(id);");
        System.out.println("     ```");
        
        System.out.println("\n5. Non-Clustered Index");
        System.out.println("   - Contains a pointer to the data rather than the data itself");
        System.out.println("   - Multiple non-clustered indexes per table");
        System.out.println("   - Example:");
        System.out.println("     ```sql");
        System.out.println("     CREATE NONCLUSTERED INDEX idx_users_name ON users(name);");
        System.out.println("     ```");
        
        System.out.println("\n6. Partial/Filtered Index");
        System.out.println("   - Indexes only a subset of rows based on a condition");
        System.out.println("   - Reduces index size and improves performance for specific queries");
        System.out.println("   - Example (PostgreSQL):");
        System.out.println("     ```sql");
        System.out.println("     CREATE INDEX idx_active_users ON users(name) WHERE active = true;");
        System.out.println("     ```");
        
        System.out.println("\n7. Full-Text Index");
        System.out.println("   - Specialized index for text search operations");
        System.out.println("   - Supports complex text searches (e.g., word proximity, stemming)");
        System.out.println("   - Example (MySQL):");
        System.out.println("     ```sql");
        System.out.println("     CREATE FULLTEXT INDEX idx_users_bio ON users(bio);");
        System.out.println("     ```");
        
        System.out.println("\nIndex Data Structures:");
        System.out.println("- B-Tree: Balanced tree structure, most common for general-purpose indexing");
        System.out.println("- Hash: Fast for equality comparisons, not for range queries");
        System.out.println("- R-Tree: Spatial data indexing");
        System.out.println("- GiST: Generalized Search Tree, extensible index structure");
        System.out.println("- Bitmap: Efficient for low-cardinality columns (few distinct values)");
        
        System.out.println("\nWhen to Create Indexes:");
        System.out.println("- Columns used in WHERE clauses");
        System.out.println("- Columns used in JOIN conditions");
        System.out.println("- Columns used in ORDER BY or GROUP BY");
        System.out.println("- Foreign key columns");
        System.out.println("- Columns with high selectivity (many unique values)");
        
        System.out.println("\nWhen to Avoid Indexes:");
        System.out.println("- Small tables where full table scans are efficient");
        System.out.println("- Columns with low selectivity (few unique values)");
        System.out.println("- Columns that are frequently updated");
        System.out.println("- Tables with heavy write operations and few reads");
        
        System.out.println("\nIndex Maintenance:");
        System.out.println("- Indexes need to be updated when data changes");
        System.out.println("- Can slow down INSERT, UPDATE, and DELETE operations");
        System.out.println("- Regular index statistics updates improve query optimization");
        System.out.println("- Consider rebuilding or reorganizing fragmented indexes");
        
        System.out.println("\nIndexing Best Practices:");
        System.out.println("1. Don't over-index - each index has maintenance overhead");
        System.out.println("2. Monitor index usage to identify unused indexes");
        System.out.println("3. Consider the order of columns in composite indexes");
        System.out.println("4. Be aware of index selectivity");
        System.out.println("5. Use EXPLAIN or execution plans to verify index usage");
        System.out.println("6. Balance read and write performance needs");
    }
    
    /**
     * 14. JPA vs JDBC
     * 
     * Comparison between JPA (Java Persistence API) and JDBC for database access.
     */
    private static void explainJPAvsJDBC() {
        System.out.println("\n14. JPA vs JDBC:\n");
        
        System.out.println("JDBC (Java Database Connectivity):");
        System.out.println("- Low-level API for database operations");
        System.out.println("- Part of the Java SE standard library");
        System.out.println("- Requires manual SQL queries and result handling");
        System.out.println("- Direct control over database operations");
        
        System.out.println("\nJPA (Java Persistence API):");
        System.out.println("- High-level specification for ORM in Java");
        System.out.println("- Part of the Jakarta EE (formerly Java EE) platform");
        System.out.println("- Abstracts database details through object-relational mapping");
        System.out.println("- Implementations include Hibernate, EclipseLink, OpenJPA");
        
        System.out.println("\nKey Differences:");
        
        System.out.println("\n1. Abstraction Level");
        System.out.println("   JDBC: Low-level, requires manual SQL and result set handling");
        System.out.println("   JPA: High-level, works with Java objects mapped to database tables");
        
        System.out.println("\n2. Programming Model");
        System.out.println("   JDBC: Procedural approach with SQL statements");
        System.out.println("   JPA: Object-oriented approach with entity classes");
        
        System.out.println("\n3. Query Language");
        System.out.println("   JDBC: Native SQL");
        System.out.println("   JPA: JPQL (Java Persistence Query Language) or Criteria API");
        
        System.out.println("\n4. Database Portability");
        System.out.println("   JDBC: SQL may need changes for different databases");
        System.out.println("   JPA: Better database independence through dialect system");
        
        System.out.println("\n5. Features");
        System.out.println("   JDBC: Basic database operations");
        System.out.println("   JPA: Caching, lazy loading, dirty checking, cascading operations");
        
        System.out.println("\nExample of JDBC Code:");
        System.out.println("```java");
        System.out.println("// JDBC example to fetch a user by ID");
        System.out.println("public User getUserById(long id) {");
        System.out.println("    User user = null;");
        System.out.println("    Connection conn = null;");
        System.out.println("    PreparedStatement stmt = null;");
        System.out.println("    ResultSet rs = null;");
        System.out.println("    ");
        System.out.println("    try {");
        System.out.println("        conn = DriverManager.getConnection(\"jdbc:mysql://localhost:3306/mydb\", \"user\", \"password\");");
        System.out.println("        stmt = conn.prepareStatement(\"SELECT * FROM users WHERE id = ?\");");
        System.out.println("        stmt.setLong(1, id);");
        System.out.println("        rs = stmt.executeQuery();");
        System.out.println("        ");
        System.out.println("        if (rs.next()) {");
        System.out.println("            user = new User();");
        System.out.println("            user.setId(rs.getLong(\"id\"));");
        System.out.println("            user.setName(rs.getString(\"name\"));");
        System.out.println("            user.setEmail(rs.getString(\"email\"));");
        System.out.println("        }");
        System.out.println("    } catch (SQLException e) {");
        System.out.println("        e.printStackTrace();");
        System.out.println("    } finally {");
        System.out.println("        // Close resources (rs, stmt, conn)");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    return user;");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nExample of JPA Code:");
        System.out.println("```java");
        System.out.println("// Entity class");
        System.out.println("@Entity");
        System.out.println("@Table(name = \"users\")");
        System.out.println("public class User {");
        System.out.println("    @Id");
        System.out.println("    @GeneratedValue(strategy = GenerationType.IDENTITY)");
        System.out.println("    private Long id;");
        System.out.println("    ");
        System.out.println("    private String name;");
        System.out.println("    private String email;");
        System.out.println("    ");
        System.out.println("    // Getters and setters");
        System.out.println("}");
        System.out.println("");
        System.out.println("// JPA example to fetch a user by ID");
        System.out.println("@PersistenceContext");
        System.out.println("private EntityManager entityManager;");
        System.out.println("");
        System.out.println("public User getUserById(long id) {");
        System.out.println("    return entityManager.find(User.class, id);");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nJPA with Spring Data JPA:");
        System.out.println("```java");
        System.out.println("// Repository interface");
        System.out.println("public interface UserRepository extends JpaRepository<User, Long> {");
        System.out.println("    // Spring Data JPA generates implementation automatically");
        System.out.println("    ");
        System.out.println("    // Custom query methods");
        System.out.println("    List<User> findByNameContaining(String name);");
        System.out.println("    ");
        System.out.println("    @Query(\"SELECT u FROM User u WHERE u.email = :email\")");
        System.out.println("    User findByEmailAddress(@Param(\"email\") String email);");
        System.out.println("}");
        System.out.println("");
        System.out.println("// Service using repository");
        System.out.println("@Service");
        System.out.println("public class UserService {");
        System.out.println("    @Autowired");
        System.out.println("    private UserRepository userRepository;");
        System.out.println("    ");
        System.out.println("    public User getUserById(long id) {");
        System.out.println("        return userRepository.findById(id).orElse(null);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nWhen to use which:");
        System.out.println("Use JDBC when:");
        System.out.println("- You need fine-grained control over SQL queries");
        System.out.println("- Performance is critical for specific operations");
        System.out.println("- You're working with legacy systems or specific database features");
        System.out.println("- The application has simple database interactions");
        
        System.out.println("\nUse JPA when:");
        System.out.println("- You want to work with objects rather than SQL");
        System.out.println("- You need database portability");
        System.out.println("- You want features like caching and lazy loading");
        System.out.println("- You're building a complex application with many entities and relationships");
        System.out.println("- You want to reduce boilerplate code");
        
        System.out.println("\nHybrid Approach:");
        System.out.println("Many applications use both:");
        System.out.println("- JPA for most operations");
        System.out.println("- JDBC for performance-critical or complex queries");
        System.out.println("- Spring Data JPA provides a good balance with repositories and native query support");
    }
}