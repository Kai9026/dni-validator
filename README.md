# DNI Validator

## Custom Bean Validator for DNI, the Spanish ID Document.

### How to use it?

In your Java Bean, you can insert the **@Dni** annotation in the field you want to validate. For instance,

```java

public class MyPersonBean {
  ...
  
  @Dni
  private String documentId;
  
  ...
}

```
