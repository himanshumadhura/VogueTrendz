package com.himanshu.voguetrendz.Repository;

import com.himanshu.voguetrendz.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Address findAddressByAddressId(int addressId);
}
