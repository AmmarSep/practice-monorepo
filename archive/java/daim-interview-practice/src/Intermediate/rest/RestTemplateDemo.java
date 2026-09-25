package Intermediate.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class demonstrates how to use RestTemplate for making HTTP requests
 * 
 * Note: This is a demonstration class without actual HTTP calls.
 * In a real application, you would need to include Spring Web dependencies:
 * - org.springframework:spring-web
 * - org.springframework.boot:spring-boot-starter-web (if using Spring Boot)
 */
public class RestTemplateDemo {

    public static void main(String[] args) {
        System.out.println("=== RestTemplate Demonstration ===\n");
        System.out.println("Note: This is a code example and doesn't make actual HTTP calls.\n");

        // Create the RestTemplate client
        RestTemplateClient client = new RestTemplateClient();

        // GET request example
        System.out.println("1. GET Request Example:");
        User user = client.getUserById(1);
        System.out.println("Retrieved user: " + user);

        // GET request with parameters
        System.out.println("\n2. GET Request with Query Parameters:");
        List<User> users = client.searchUsers("John", "active");
        System.out.println("Found " + users.size() + " users:");
        for (User u : users) {
            System.out.println("  - " + u);
        }

        // POST request example
        System.out.println("\n3. POST Request Example:");
        User newUser = new User(0, "Sarah Johnson", "sarah.johnson@example.com", "active");
        User createdUser = client.createUser(newUser);
        System.out.println("Created user: " + createdUser);

        // PUT request example
        System.out.println("\n4. PUT Request Example:");
        User userToUpdate = new User(1, "John Smith Updated", "john.smith@example.com", "inactive");
        User updatedUser = client.updateUser(userToUpdate);
        System.out.println("Updated user: " + updatedUser);

        // DELETE request example
        System.out.println("\n5. DELETE Request Example:");
        boolean deleted = client.deleteUser(2);
        System.out.println("User deleted: " + deleted);

        // Error handling example
        System.out.println("\n6. Error Handling Example:");
        try {
            User nonExistentUser = client.getUserById(999);
            System.out.println("Retrieved user: " + nonExistentUser);
        } catch (RestClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
            if (e.getStatusCode() == 404) {
                System.out.println("User not found (404)");
            } else {
                System.out.println("Other error: " + e.getStatusCode());
            }
        }

        // Exchange method example (more control over request/response)
        System.out.println("\n7. Exchange Method Example:");
        User exchangeUser = client.getUserWithExchange(3);
        System.out.println("Retrieved user with exchange: " + exchangeUser);

        // Using headers example
        System.out.println("\n8. Request with Custom Headers:");
        List<User> adminUsers = client.getUsersWithAuthHeader("Bearer admin-token-123");
        System.out.println("Retrieved " + adminUsers.size() + " users with admin access");

        // Async request example (available in real RestTemplate via AsyncRestTemplate)
        System.out.println("\n9. Asynchronous Request Example:");
        client.getUserByIdAsync(4, user4 -> {
            System.out.println("Async callback received user: " + user4);
        });

        System.out.println("\nAll examples completed. In a real application, these would make actual HTTP requests.");
    }
}

/**
 * A client class that demonstrates RestTemplate usage patterns
 * This is a simulated class without actual HTTP connections
 */
class RestTemplateClient {
    // In a real application, you would have:
    // private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.example.com";

    // Simulate a database of users
    private Map<Integer, User> userDatabase;

    public RestTemplateClient() {
        // In a real application:
        // this.restTemplate = new RestTemplate();

        // Initialize our mock database
        userDatabase = new HashMap<>();
        userDatabase.put(1, new User(1, "John Smith", "john.smith@example.com", "active"));
        userDatabase.put(2, new User(2, "Jane Doe", "jane.doe@example.com", "active"));
        userDatabase.put(3, new User(3, "Bob Johnson", "bob.johnson@example.com", "inactive"));
        userDatabase.put(4, new User(4, "Alice Brown", "alice.brown@example.com", "active"));
    }

    /**
     * GET request to retrieve a user by ID
     */
    public User getUserById(int id) {
        // In a real application:
        // return restTemplate.getForObject(BASE_URL + "/users/" + id, User.class);

        // Simulated implementation
        User user = userDatabase.get(id);
        if (user == null) {
            throw new RestClientException("User not found", 404);
        }
        return user;
    }

    /**
     * GET request with query parameters
     */
    public List<User> searchUsers(String name, String status) {
        // In a real application:
        // String url = BASE_URL + "/users?name={name}&status={status}";
        // Map<String, String> params = new HashMap<>();
        // params.put("name", name);
        // params.put("status", status);
        // return restTemplate.getForObject(url, List.class, params);

        // Simulated implementation
        return userDatabase.values().stream()
                .filter(user -> user.getName().contains(name) && user.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    /**
     * POST request to create a new user
     */
    public User createUser(User user) {
        // In a real application:
        // return restTemplate.postForObject(BASE_URL + "/users", user, User.class);

        // Simulated implementation
        int newId = userDatabase.size() + 1;
        User newUser = new User(newId, user.getName(), user.getEmail(), user.getStatus());
        userDatabase.put(newId, newUser);
        return newUser;
    }

    /**
     * PUT request to update an existing user
     */
    public User updateUser(User user) {
        // In a real application:
        // restTemplate.put(BASE_URL + "/users/" + user.getId(), user);
        // return user;

        // Simulated implementation
        if (!userDatabase.containsKey(user.getId())) {
            throw new RestClientException("User not found", 404);
        }
        userDatabase.put(user.getId(), user);
        return user;
    }

    /**
     * DELETE request to remove a user
     */
    public boolean deleteUser(int id) {
        // In a real application:
        // restTemplate.delete(BASE_URL + "/users/" + id);
        // return true;

        // Simulated implementation
        if (!userDatabase.containsKey(id)) {
            return false;
        }
        userDatabase.remove(id);
        return true;
    }

    /**
     * Using exchange method for more control over request/response
     */
    public User getUserWithExchange(int id) {
        // In a real application:
        // HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // HttpEntity<?> entity = new HttpEntity<>(headers);
        // ResponseEntity<User> response = restTemplate.exchange(
        //     BASE_URL + "/users/" + id, 
        //     HttpMethod.GET, 
        //     entity, 
        //     User.class
        // );
        // return response.getBody();

        // Simulated implementation
        return getUserById(id);
    }

    /**
     * Request with custom headers (e.g., for authorization)
     */
    public List<User> getUsersWithAuthHeader(String authToken) {
        // In a real application:
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", authToken);
        // HttpEntity<?> entity = new HttpEntity<>(headers);
        // ResponseEntity<User[]> response = restTemplate.exchange(
        //     BASE_URL + "/users/admin", 
        //     HttpMethod.GET, 
        //     entity, 
        //     User[].class
        // );
        // return Arrays.asList(response.getBody());

        // Simulated implementation
        if (authToken.contains("admin")) {
            return new ArrayList<>(userDatabase.values());
        }
        return Collections.emptyList();
    }

    /**
     * Asynchronous request example
     * In Spring, you would use AsyncRestTemplate or WebClient
     */
    public void getUserByIdAsync(int id, UserCallback callback) {
        // In a real application with AsyncRestTemplate:
        // ListenableFuture<ResponseEntity<User>> futureEntity = asyncRestTemplate.getForEntity(
        //     BASE_URL + "/users/" + id, User.class);
        // futureEntity.addCallback(
        //     response -> callback.onUserReceived(response.getBody()),
        //     ex -> System.err.println("Error retrieving user: " + ex.getMessage())
        // );

        // Simulated implementation with a thread
        new Thread(() -> {
            try {
                // Simulate network delay
                Thread.sleep(500);
                User user = getUserById(id);
                callback.onUserReceived(user);
            } catch (Exception e) {
                System.err.println("Error retrieving user: " + e.getMessage());
            }
        }).start();
    }

    /**
     * Callback interface for async operations
     */
    interface UserCallback {
        void onUserReceived(User user);
    }
}

/**
 * Custom exception for REST client errors
 */
class RestClientException extends RuntimeException {
    private final int statusCode;

    public RestClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

/**
 * User model class
 */
class User {
    private int id;
    private String name;
    private String email;
    private String status;

    public User() {
        // Default constructor needed for JSON deserialization
    }

    public User(int id, String name, String email, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", status=" + status + "]";
    }
}
