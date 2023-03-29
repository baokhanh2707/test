package com.example.be.repository;

import com.example.be.dto.CartDto;
import com.example.be.model.CartDetail;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select product.amount_exist as amountExist , cart_detail.id_card_detail as idCardDetail,cart_detail.quantity as quantity,product.name_product as nameProduct,image_product.url as url ,product.id_product as idProduct,product.interior as interior,product.price as price,customer.phone_number as phoneNumber,customer.address as address,customer.name_customer as nameCustomer,cart.id_card as idCard from cart_detail join cart on cart.id_card=cart_detail.cart_id_card join customer on customer.id_customer=cart.customer_id_customer join product on product.id_product=cart_detail.product_id_product join image_product on image_product.product_id_product=product.id_product where cart_detail.flag_deleted = false AND cart_detail.flag_status = false AND cart.customer_id_customer = :idCustomer group by cart_detail.cart_id_card,product.id_product  ORDER BY cart_detail.create_date",
            countQuery = "select product.amount_exist as amountExist , cart_detail.id_card_detail as idCardDetail,cart_detail.quantity as quantity,product.name_product as nameProduct,image_product.url as url ,product.id_product as idProduct,product.interior as interior,product.price as price,customer.phone_number as phoneNumber,customer.address as address,customer.name_customer as nameCustomer,cart.id_card as idCard from cart_detail join cart on cart.id_card=cart_detail.cart_id_card join customer on customer.id_customer=cart.customer_id_customer join product on product.id_product=cart_detail.product_id_product join image_product on image_product.product_id_product=product.id_product where cart_detail.flag_deleted = false AND cart_detail.flag_status = false AND cart.customer_id_customer = :idCustomer group by cart_detail.cart_id_card,product.id_product  ORDER BY cart_detail.create_date"
            , nativeQuery = true)
    List<CartDto> getAllCart(@Param("idCustomer") Long idCustomer);

    @Transactional
    @Modifying
    @Query(value = "update cart_detail set quantity = :quantity where flag_deleted = false and flag_status = false and id_card_detail = :idCartDetail", nativeQuery = true)
    void updateAmountInCart(@Param("quantity") Long quantity, @Param("idCartDetail") Long idCartDetail);

    boolean existsByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer);

    Optional<CartDetail> findByProductIdProductAndCartCustomerIdCustomer(Long idProduct, Long idCustomer);

    @Query(value = "SELECT sum(cart_detail.quantity * product.price) as totalMoney from cart_detail join cart on cart_detail.cart_id_card = cart.id_card join product on product.id_product=cart_detail.product_id_product where cart_detail.flag_deleted = false and cart_detail.flag_status = false and cart.customer_id_customer = :idCustomer",
            countQuery = "SELECT sum(cart_detail.quantity * product.price) as totalMoney from cart_detail join cart on cart_detail.cart_id_card = cart.id_card join product on product.id_product=cart_detail.product_id_product where cart_detail.flag_deleted = false and cart_detail.flag_status = false and cart.customer_id_customer = :idCustomer", nativeQuery = true)
    Double getTotalMoneyCart(@Param("idCustomer") Long idCustomer);
    @Transactional
    @Modifying
    @Query (value = "update cart_detail set flag_deleted = true where id_card_detail = :idCartDetail",nativeQuery = true)
    void deleteByIdCartDetail(@Param("idCartDetail") @NotNull Long idCartDetail);

    @Query(value = "select * from cart_detail where flag_deleted = false and id_card_detail = :idCartDetail", nativeQuery = true)
    CartDetail findByIdCartDetail(@Param("idCartDetail") @NotNull Long idCartDetail);
}

