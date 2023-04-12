package com.be.repository.product;

import com.be.dto.customer.CartDto;
import com.be.model.CartDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer);
    @Transactional
    @Modifying
    @Query(value = "update cart_detail set quantity = :quantity where flag_deleted = false and flag_status = false and id_card_detail = :idCartDetail", nativeQuery = true)
    void updateAmountInCart(@Param("quantity") Long quantity, @Param("idCartDetail") Long idCartDetail);

    @Query(value = "SELECT laptop.image,\n" +
            "       laptop.name,\n" +
            "       laptop.price,\n" +
            "       customer.address,\n" +
            "       customer.name,\n" +
            "       customer.email,\n" +
            "       customer.birthday,\n" +
            "       cart_detail.id_card_detail,\n" +
            "       cart_detail.quantity\n" +
            "from cart_detail\n" +
            "         join cart on cart.id_card = cart_detail.cart_id_card\n" +
            "         join customer on customer.id_customer = cart.customer_id_customer\n" +
            "         join laptop on laptop.id = cart_detail.laptop_id\n" +
            "where cart_detail.flag_deleted = false\n" +
            "  AND cart_detail.flag_status = false\n" +
            "  AND cart.customer_id_customer = :idCustomer\n" +
            "group by cart_detail.cart_id_card\n" +
            "ORDER BY cart_detail.create_date DESC",countQuery = "SELECT laptop.image,\n" +
            "       laptop.name,\n" +
            "       laptop.price,\n" +
            "       customer.address,\n" +
            "       customer.name,\n" +
            "       customer.email,\n" +
            "       customer.birthday,\n" +
            "       cart_detail.id_card_detail,\n" +
            "       cart_detail.quantity\n" +
            "from cart_detail\n" +
            "         join cart on cart.id_card = cart_detail.cart_id_card\n" +
            "         join customer on customer.id_customer = cart.customer_id_customer\n" +
            "         join laptop on laptop.id = cart_detail.laptop_id\n" +
            "where cart_detail.flag_deleted = false\n" +
            "  AND cart_detail.flag_status = false\n" +
            "  AND cart.customer_id_customer = :idCustomer\n" +
            "group by cart_detail.cart_id_card\n" +
            "ORDER BY cart_detail.create_date DESC",nativeQuery = true)
    List<CartDto> getAllCart(@Param("idCustomer") Long idCustomer);

    @Query(value = "SELECT sum(cart_detail.quantity * laptop.price) as totalMoney from cart_detail \n" +
            "join cart on cart_detail.cart_id_card = cart.id_card \n" +
            "join laptop  on cart_detail.laptop_id = laptop.id \n" +
            "where cart_detail.flag_deleted = false and cart_detail.flag_status = false and cart.customer_id_customer = :idCustomer", nativeQuery = true)
    Double getTotalMoneyCart(@Param("idCustomer") Long idCustomer);

    Optional<CartDetail> findByLaptopIdLaptopAndCartCustomerIdCustomer(Long idLaptop, Long idCustomer);
}
