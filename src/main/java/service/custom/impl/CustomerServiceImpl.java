package service.custom.impl;

import dto.Customer;
import entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.DaoType;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public boolean addCustomer(Customer customer) {
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        customerDao.save(entity);
        System.out.println("service layer "+ customer);
        return false;
    }
}
