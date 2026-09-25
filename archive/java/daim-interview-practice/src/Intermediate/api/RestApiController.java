package Intermediate.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A demonstration of a RESTful API controller with CRUD operations
 * 
 * Note: This class doesn't include the actual Spring framework annotations and dependencies.
 * In a real Spring Boot application, you would need to include:
 * - org.springframework.boot:spring-boot-starter-web
 * - org.springframework.boot:spring-boot-starter-data-jpa (if using JPA)
 */
public class RestApiController {

    public static void main(String[] args) {
        System.out.println("=== REST API Controller Demonstration ===\n");
        System.out.println("Note: This is a code example without the actual Spring framework.\n");

        // Initialize service and controller
        ProductService productService = new ProductServiceImpl();
        ProductController controller = new ProductController(productService);

        // Simulate HTTP requests to our REST endpoints
        simulateApiRequests(controller);
    }

    /**
     * Simulate HTTP requests to our REST API endpoints
     */
    private static void simulateApiRequests(ProductController controller) {
        System.out.println("1. GET /api/products - Get all products");
        ApiResponse<List<Product>> allProducts = controller.getAllProducts();
        printResponse(allProducts);

        System.out.println("\n2. POST /api/products - Create a new product");
        Product newProduct = new Product();
        newProduct.setName("Smart Watch");
        newProduct.setDescription("Latest smartwatch with health monitoring");
        newProduct.setPrice(299.99);
        newProduct.setCategory("Electronics");
        ApiResponse<Product> createdProduct = controller.createProduct(newProduct);
        printResponse(createdProduct);

        // Get the ID of the created product
        Long productId = createdProduct.getData().getId();

        System.out.println("\n3. GET /api/products/" + productId + " - Get product by ID");
        ApiResponse<Product> productById = controller.getProductById(productId);
        printResponse(productById);

        System.out.println("\n4. PUT /api/products/" + productId + " - Update product");
        Product updatedProduct = productById.getData();
        updatedProduct.setPrice(249.99); // Discount the price
        updatedProduct.setDescription(updatedProduct.getDescription() + " - Limited time offer");
        ApiResponse<Product> updateResponse = controller.updateProduct(productId, updatedProduct);
        printResponse(updateResponse);

        System.out.println("\n5. GET /api/products/category/Electronics - Get products by category");
        ApiResponse<List<Product>> productsByCategory = controller.getProductsByCategory("Electronics");
        printResponse(productsByCategory);

        System.out.println("\n6. DELETE /api/products/" + productId + " - Delete product");
        ApiResponse<Void> deleteResponse = controller.deleteProduct(productId);
        printResponse(deleteResponse);

        System.out.println("\n7. GET /api/products/" + productId + " - Try to get deleted product");
        ApiResponse<Product> getDeletedProduct = controller.getProductById(productId);
        printResponse(getDeletedProduct);

        System.out.println("\n8. GET /api/products/search?query=laptop - Search products");
        ApiResponse<List<Product>> searchResults = controller.searchProducts("laptop");
        printResponse(searchResults);
    }

    /**
     * Helper method to print API responses
     */
    private static void printResponse(ApiResponse<?> response) {
        System.out.println("Status: " + response.getStatus());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());
        System.out.println("Timestamp: " + response.getTimestamp());

        if (response.getData() != null) {
            System.out.println("Data:");
            if (response.getData() instanceof List) {
                List<?> list = (List<?>) response.getData();
                if (list.isEmpty()) {
                    System.out.println("  (empty list)");
                } else {
                    for (Object item : list) {
                        System.out.println("  " + item);
                    }
                }
            } else {
                System.out.println("  " + response.getData());
            }
        }
    }
}

/**
 * Product Controller
 * 
 * In a real Spring application, this would have annotations like:
 * @RestController
 * @RequestMapping("/api/products")
 */
class ProductController {
    // In a real Spring application:
    // @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /api/products
     * Get all products
     * 
     * In a real Spring application:
     * @GetMapping
     */
    public ApiResponse<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.findAll();
            return ApiResponse.success(products, "Products retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.error("Error retrieving products: " + e.getMessage());
        }
    }

    /**
     * GET /api/products/{id}
     * Get product by ID
     * 
     * In a real Spring application:
     * @GetMapping("/{id}")
     */
    public ApiResponse<Product> getProductById(Long id) {
        try {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                return ApiResponse.success(product.get(), "Product retrieved successfully");
            } else {
                return ApiResponse.notFound("Product not found with id: " + id);
            }
        } catch (Exception e) {
            return ApiResponse.error("Error retrieving product: " + e.getMessage());
        }
    }

    /**
     * POST /api/products
     * Create a new product
     * 
     * In a real Spring application:
     * @PostMapping
     * @ResponseStatus(HttpStatus.CREATED)
     */
    public ApiResponse<Product> createProduct(Product product) {
        try {
            // Validate product
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                return ApiResponse.badRequest("Product name cannot be empty");
            }

            if (product.getPrice() <= 0) {
                return ApiResponse.badRequest("Product price must be greater than zero");
            }

            Product savedProduct = productService.save(product);
            return ApiResponse.created(savedProduct, "Product created successfully");
        } catch (Exception e) {
            return ApiResponse.error("Error creating product: " + e.getMessage());
        }
    }

    /**
     * PUT /api/products/{id}
     * Update an existing product
     * 
     * In a real Spring application:
     * @PutMapping("/{id}")
     */
    public ApiResponse<Product> updateProduct(Long id, Product productDetails) {
        try {
            Optional<Product> existingProduct = productService.findById(id);

            if (existingProduct.isPresent()) {
                // Update the existing product with new details
                Product product = existingProduct.get();
                product.setName(productDetails.getName());
                product.setDescription(productDetails.getDescription());
                product.setPrice(productDetails.getPrice());
                product.setCategory(productDetails.getCategory());

                Product updatedProduct = productService.save(product);
                return ApiResponse.success(updatedProduct, "Product updated successfully");
            } else {
                return ApiResponse.notFound("Product not found with id: " + id);
            }
        } catch (Exception e) {
            return ApiResponse.error("Error updating product: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/products/{id}
     * Delete a product
     * 
     * In a real Spring application:
     * @DeleteMapping("/{id}")
     * @ResponseStatus(HttpStatus.NO_CONTENT)
     */
    public ApiResponse<Void> deleteProduct(Long id) {
        try {
            Optional<Product> product = productService.findById(id);

            if (product.isPresent()) {
                productService.deleteById(id);
                return ApiResponse.success(null, "Product deleted successfully");
            } else {
                return ApiResponse.notFound("Product not found with id: " + id);
            }
        } catch (Exception e) {
            return ApiResponse.error("Error deleting product: " + e.getMessage());
        }
    }

    /**
     * GET /api/products/category/{category}
     * Get products by category
     * 
     * In a real Spring application:
     * @GetMapping("/category/{category}")
     */
    public ApiResponse<List<Product>> getProductsByCategory(String category) {
        try {
            List<Product> products = productService.findByCategory(category);
            if (products.isEmpty()) {
                return ApiResponse.success(products, "No products found in category: " + category);
            }
            return ApiResponse.success(products, "Products in category '" + category + "' retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.error("Error retrieving products by category: " + e.getMessage());
        }
    }

    /**
     * GET /api/products/search?query={query}
     * Search products
     * 
     * In a real Spring application:
     * @GetMapping("/search")
     */
    public ApiResponse<List<Product>> searchProducts(String query) {
        try {
            if (query == null || query.trim().isEmpty()) {
                return ApiResponse.badRequest("Search query cannot be empty");
            }

            List<Product> products = productService.search(query);
            return ApiResponse.success(products, "Search results for '" + query + "'");
        } catch (Exception e) {
            return ApiResponse.error("Error searching products: " + e.getMessage());
        }
    }
}

/**
 * Product entity class
 * 
 * In a real Spring application:
 * @Entity
 * @Table(name = "products")
 */
class Product {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    private String name;

    // @Column(length = 1000)
    private String description;

    // @Column(nullable = false)
    private double price;

    private String category;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=$" + price + ", category=" + category + "]";
    }
}

/**
 * Product Service interface
 */
interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    List<Product> findByCategory(String category);
    List<Product> search(String query);
}

/**
 * Product Service implementation
 * 
 * In a real Spring application:
 * @Service
 */
class ProductServiceImpl implements ProductService {
    // In a real application this would use a repository
    // @Autowired
    // private ProductRepository productRepository;

    // For this demo, we'll use an in-memory map
    private final Map<Long, Product> productMap = new HashMap<>();
    private Long nextId = 1L;

    // Initialize with some sample products
    public ProductServiceImpl() {
        // Laptop
        Product laptop = new Product();
        laptop.setName("Ultrabook Pro");
        laptop.setDescription("Powerful laptop with 16GB RAM and 512GB SSD");
        laptop.setPrice(1299.99);
        laptop.setCategory("Electronics");
        save(laptop);

        // Smartphone
        Product phone = new Product();
        phone.setName("SmartPhone X");
        phone.setDescription("Latest smartphone with dual camera");
        phone.setPrice(899.99);
        phone.setCategory("Electronics");
        save(phone);

        // Book
        Product book = new Product();
        book.setName("Java Programming Masterclass");
        book.setDescription("Complete guide to Java programming");
        book.setPrice(49.99);
        book.setCategory("Books");
        save(book);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            // New product
            product.setId(nextId++);
        }
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        productMap.remove(id);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productMap.values().stream()
                .filter(product -> category.equalsIgnoreCase(product.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> search(String query) {
        String lowerQuery = query.toLowerCase();
        return productMap.values().stream()
                .filter(product -> 
                    product.getName().toLowerCase().contains(lowerQuery) ||
                    (product.getDescription() != null && 
                     product.getDescription().toLowerCase().contains(lowerQuery))
                )
                .collect(Collectors.toList());
    }
}

/**
 * Generic API response class for consistent response format
 */
class ApiResponse<T> {
    private String status;
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    private ApiResponse(String status, int statusCode, String message, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    // Success response - 200 OK
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("SUCCESS", 200, message, data);
    }

    // Created response - 201 Created
    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>("CREATED", 201, message, data);
    }

    // Bad request response - 400 Bad Request
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>("BAD_REQUEST", 400, message, null);
    }

    // Not found response - 404 Not Found
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>("NOT_FOUND", 404, message, null);
    }

    // Error response - 500 Internal Server Error
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("ERROR", 500, message, null);
    }

    // Getters
    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }
}
