CREATE TABLE inv_tbl_customer
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date  TIMESTAMP WITHOUT TIME ZONE,
    updated_date  TIMESTAMP WITHOUT TIME ZONE,
    full_name     VARCHAR(255),
    username      VARCHAR(255)                            NOT NULL,
    phone_number  VARCHAR(255)                            NOT NULL,
    address       VARCHAR(255),
    email_address VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_inv_tbl_customer PRIMARY KEY (id)
);

CREATE TABLE inv_tbl_order
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    updated_date       TIMESTAMP WITHOUT TIME ZONE,
    order_number       VARCHAR(255)                            NOT NULL,
    status             VARCHAR(20) DEFAULT 'PENDING',
    customer_id        BIGINT,
    total_order_amount DECIMAL                                 NOT NULL,
    CONSTRAINT pk_inv_tbl_order PRIMARY KEY (id)
);

CREATE TABLE inv_tbl_order_items
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date      TIMESTAMP WITHOUT TIME ZONE,
    updated_date      TIMESTAMP WITHOUT TIME ZONE,
    order_item_number VARCHAR(255)                            NOT NULL,
    product_id        BIGINT,
    quantity          BIGINT                                  NOT NULL,
    total_amount      DECIMAL                                 NOT NULL,
    unit_price        DECIMAL                                 NOT NULL,
    status            VARCHAR(20) DEFAULT 'PENDING',
    order_id          BIGINT,
    CONSTRAINT pk_inv_tbl_order_items PRIMARY KEY (id)
);

CREATE TABLE inv_tbl_product
(
    id                                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date                        TIMESTAMP WITHOUT TIME ZONE,
    updated_date                        TIMESTAMP WITHOUT TIME ZONE,
    name                                VARCHAR(255)                            NOT NULL,
    code                                VARCHAR(255)                            NOT NULL,
    price                               DECIMAL                                 NOT NULL,
    status                              VARCHAR(20) DEFAULT 'AVAILABLE',
    general_discount                    DECIMAL,
    allowable_minimum_quantity_in_stock INTEGER     DEFAULT 0                   NOT NULL,
    quantity_in_stock                   BIGINT      DEFAULT 0                   NOT NULL,
    description                         TEXT,
    CONSTRAINT pk_inv_tbl_product PRIMARY KEY (id)
);

ALTER TABLE inv_tbl_customer
    ADD CONSTRAINT uc_inv_tbl_customer_emailaddress UNIQUE (email_address);

ALTER TABLE inv_tbl_customer
    ADD CONSTRAINT uc_inv_tbl_customer_phonenumber UNIQUE (phone_number);

ALTER TABLE inv_tbl_customer
    ADD CONSTRAINT uc_inv_tbl_customer_username UNIQUE (username);

ALTER TABLE inv_tbl_order_items
    ADD CONSTRAINT uc_inv_tbl_order_items_orderitemnumber UNIQUE (order_item_number);

ALTER TABLE inv_tbl_order
    ADD CONSTRAINT uc_inv_tbl_order_ordernumber UNIQUE (order_number);

ALTER TABLE inv_tbl_product
    ADD CONSTRAINT uc_inv_tbl_product_code UNIQUE (code);

ALTER TABLE inv_tbl_product
    ADD CONSTRAINT uc_inv_tbl_product_name UNIQUE (name);

ALTER TABLE inv_tbl_order_items
    ADD CONSTRAINT FK_INV_TBL_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES inv_tbl_order (id);

ALTER TABLE inv_tbl_order_items
    ADD CONSTRAINT FK_INV_TBL_ORDER_ITEMS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES inv_tbl_product (id);

ALTER TABLE inv_tbl_order
    ADD CONSTRAINT FK_INV_TBL_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES inv_tbl_customer (id);