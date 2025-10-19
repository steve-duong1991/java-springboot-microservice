package steveduong.v1.service.impl;

import org.springframework.stereotype.Service;
import steveduong.v1.dto.User;
import steveduong.v1.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public User getCustomer() {
    return new User(123L, "Steve", "steve@email.com");
  }
}
