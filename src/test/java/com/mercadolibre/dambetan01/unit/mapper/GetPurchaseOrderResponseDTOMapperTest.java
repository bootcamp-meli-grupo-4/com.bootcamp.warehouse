package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.GetPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.purchase.OrderStatusResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.purchase.ProductPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.mapper.GetPurchaseOrderResponseDTOMapper;
import com.mercadolibre.dambetan01.mapper.OrderStatusResponseDTOMapper;
import com.mercadolibre.dambetan01.mapper.ProductPurchaseOrderResponseDTOMapper;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPurchaseOrderResponseDTOMapperTest {
    private GetPurchaseOrderResponseDTOMapper mapper;
    private ProductPurchaseOrderResponseDTOMapper productMapper;
    private OrderStatusResponseDTOMapper statusResponseDTOMapper;

    @BeforeEach
    public void setUp(){
        this.productMapper = mock(ProductPurchaseOrderResponseDTOMapper.class);
        this.statusResponseDTOMapper = mock(OrderStatusResponseDTOMapper.class);
        this.mapper = new GetPurchaseOrderResponseDTOMapper(productMapper, statusResponseDTOMapper);
    }

    @Test
    public void shouldConvertModelToDtoCorrectly(){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrder.setDate(LocalDate.now());
        StatusPurchaseOrder status = new StatusPurchaseOrder();
        purchaseOrder.setStatusPurchaseOrder(status);
        ProductStockPurchaseOrder product = new ProductStockPurchaseOrder();
        purchaseOrder.setProducts(Arrays.asList(product));

        OrderStatusResponseDTO statusDto = new OrderStatusResponseDTO();
        statusDto.setStatusCode("Finalizado");
        ProductPurchaseOrderResponseDTO productDto = new ProductPurchaseOrderResponseDTO();
        productDto.setProductId(1L);
        productDto.setQuantity(3);

        when(statusResponseDTOMapper.modelToDto(status)).thenReturn(statusDto);
        when(productMapper.modelToDto(product)).thenReturn(productDto);

        GetPurchaseOrderResponseDTO dto = mapper.modelToDto(purchaseOrder);
        Assertions.assertEquals(purchaseOrder.getDate(), dto.getDate());
        Assertions.assertEquals(statusDto.getStatusCode(), dto.getStatus().getStatusCode());
        Assertions.assertEquals(productDto.getProductId(), dto.getProducts().get(0).getProductId());
        Assertions.assertEquals(productDto.getQuantity(), dto.getProducts().get(0).getQuantity());

    }

}
