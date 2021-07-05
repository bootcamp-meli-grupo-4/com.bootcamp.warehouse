package com.mercadolibre.dambetan01.model.user;

public enum EPermission {
    REGISTER_STOCK_PERMISSION(Constants.REGISTER_STOCK_PERMISSION),
    BUY_PRODUCT_PERMISSION(Constants.BUY_PRODUCT_PERMISSION),
    EDIT_PURCHASE_ORDER_PERMISSION(Constants.EDIT_PURCHASE_ORDER_PERMISSION),
    AUTHENTICATED_USER_PERMISSION(Constants.AUTHENTICATED_USER_PERMISSION);

    private final String value;

    EPermission(String value) {
        this.value = value;
    }

    public static class Constants {
        public static final String REGISTER_STOCK_PERMISSION = "REGISTER_STOCK_PERMISSION";
        public static final String BUY_PRODUCT_PERMISSION = "BUY_PRODUCT_PERMISSION";
        public static final String AUTHENTICATED_USER_PERMISSION = "AUTHENTICATED_USER_PERMISSION";
        public static final String EDIT_PURCHASE_ORDER_PERMISSION = "EDIT_PURCHASE_ORDER_PERMISSION";
    }
}
