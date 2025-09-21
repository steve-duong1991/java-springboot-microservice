package steveduong.service.impl;

import org.springframework.stereotype.Service;
import steveduong.dto.User;
import steveduong.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public User getCustomer() {
    return new User(123L, "Steve", "steve@email.com");
  }
}
