CREATE TABLE `order_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `order_code` varchar(45) NOT NULL COMMENT '订单号',
  `order_type` varchar(15) NOT NULL COMMENT '订单类型',
  `deleted` varchar(15) NOT NULL COMMENT '逻辑删除，T/F',
  `create_by` varchar(45) NOT NULL COMMENT '创建人',
  `modify_by` varchar(45) NOT NULL COMMENT '修改人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(3) NOT NULL COMMENT '修改时间',
  `extend_field` varchar(45) DEFAULT NULL COMMENT '拓展字段',
  PRIMARY KEY (`id`)
);