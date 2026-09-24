practise this part only
```java 
// Step 2: Find second non-repeated character
int found = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == 1) {
                found++;

                if (found == 2) {
                    System.out.println(c);
                    break;
                }
            }
        }
```

```java
int found = 0;
        for (int i = 0;i < s.length();i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1){
                found++;
                
        }
            if (found == 2){
                System.out.println(c);
                break;
        }
        }
}
```
