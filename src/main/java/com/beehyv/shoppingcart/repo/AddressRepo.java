package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
    Address findByAddressId(long addressId);
}
