INSERT INTO location VALUES ('3abb2ee5-9224-40b0-a044-ae075323a52f', 'WALMART', 'http', 'api.walmartlabs.com', '/v1/search',
'query', 'items', 'salePrice', 'name');
INSERT INTO location VALUES ('2509d1ce-4cd3-4572-9710-8a34ae7e1af6', 'BESTBUY', 'http', 'api.bestbuy.com', '/v1/products(name=%s*)',
'', 'items', 'salePrice', 'name');

INSERT INTO requestparam VALUES ('486196d0-0333-4d4f-a88c-391021eab019', '3abb2ee5-9224-40b0-a044-ae075323a52f',
    'apiKey', 'rm25tyum3p9jm9x9x7zxshfa');
INSERT INTO requestparam VALUES ('86ff2d64-523a-4a82-879b-3f8ba1f0ca8a', '3abb2ee5-9224-40b0-a044-ae075323a52f',
    'lsPublisherId', 'false');
INSERT INTO requestparam VALUES ('76339d64-fa1e-489a-a91a-ae38c677f4b7', '3abb2ee5-9224-40b0-a044-ae075323a52f',
    'sort', 'price');
INSERT INTO requestparam VALUES ('0501537c-5e84-4d63-bf5f-e7db8698f1be', '3abb2ee5-9224-40b0-a044-ae075323a52f',
    'order', 'asc');
INSERT INTO requestparam VALUES ('687084e9-bb32-45e6-887a-3196255298db', '2509d1ce-4cd3-4572-9710-8a34ae7e1af6',
    'apiKey', 'pfe9fpy68yg28hvvma49sc89');
INSERT INTO requestparam VALUES ('5eaa0b55-aa37-47ca-878c-b571e4a7deda', '2509d1ce-4cd3-4572-9710-8a34ae7e1af6',
    'format', 'json');
INSERT INTO requestparam VALUES ('fac3deea-c98f-4536-84cd-21e56d1fcad0', '2509d1ce-4cd3-4572-9710-8a34ae7e1af6',
    'sort', 'salePrice.asc');
INSERT INTO requestparam VALUES ('28578ad2-4204-40fd-9b0a-83b125caa427', '2509d1ce-4cd3-4572-9710-8a34ae7e1af6',
    'show', 'name,salePrice');
