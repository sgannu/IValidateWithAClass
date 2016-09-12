# IValidateWithAClass
Hibernate validation framework extension for customized validations
The frame work is built on a simple phrase "I Validate with A class" => @IValidate(with=A.class)

TODO: and "I validate with bean 'validationBean'" => @IValidate(withBean="validationBeanId")
## Usage
```
public Class form {
@IValidate(with=A.class)
private String name;
...
}


