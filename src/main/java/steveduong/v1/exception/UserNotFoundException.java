package steveduong.v1.exception;

import java.io.Serial;
import java.io.Serializable;

public class UserNotFoundException extends Exception implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  public UserNotFoundException() {
    super("User not found. Please retry or find another one");
  }
}
