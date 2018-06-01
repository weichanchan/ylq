CREATE TABLE `delivery_endpoint` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
`name` varchar(32) NOT NULL COMMENT '配送点名',
`location_x` decimal(20,2) NOT NULL COMMENT '配送点坐标x',
`location_y` decimal(20,2) NOT NULL COMMENT '配送点坐标y',
`remark` text NULL,
PRIMARY KEY (`id`) 
)
COMMENT='配送点信息';

CREATE TABLE `delivery_distributor_financial_info` (
`id` int NOT NULL AUTO_INCREMENT,
`amount` decimal(20,2) NOT NULL COMMENT '当前金额',
`disable_amount` decimal(20,2) NOT NULL COMMENT '当前冻结金额',
`enable_amount` decimal(20,2) NOT NULL COMMENT '当前可用金额',
`delivery_distributor_id` int NOT NULL COMMENT '配送员 ID',
PRIMARY KEY (`id`) 
)
COMMENT='配送员收入信息';

CREATE TABLE `delivery_distributor` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(32) NOT NULL,
`phone` varchar(32) NOT NULL,
`birthday` datetime NOT NULL,
`identifycation` varchar(32) NULL COMMENT '身份证号（备用）',
`identifycation_url` varchar(512) NULL COMMENT '身份证照片地址',
`health_url` varchar(512) NULL COMMENT '健康证地址',
`delivery_endpoint` int NOT NULL COMMENT '配送点地址',
PRIMARY KEY (`id`) 
)
COMMENT='配送员信息';

CREATE TABLE `delivery_distributor_financial_flow` (
`id` int NOT NULL AUTO_INCREMENT,
`before_amount` decimal(20,2) NOT NULL COMMENT '流水前金额',
`before_disable_amount` decimal(20,2) NOT NULL COMMENT '流水前冻结金额',
`before_enable_amount` decimal(20,2) NOT NULL COMMENT '流水前可用金额',
`after_enable_amount` decimal(20,2) NOT NULL COMMENT '流水后可用金额',
`after_disable_amount` decimal(20,2) NOT NULL COMMENT '流水后冻结金额',
`after_amount` decimal(20,2) NOT NULL COMMENT '流水后金额',
`type` tinyint(4) NOT NULL COMMENT '流水类型，10：收益，20：提现申请，30：提现成功',
`amount` decimal NOT NULL COMMENT '流水金额',
`delivery_distributor_id` int NOT NULL COMMENT '配送员 ID',
PRIMARY KEY (`id`) 
)
COMMENT='配送员收入信息流水';

CREATE TABLE `delivery_withdraw_apply` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(32) NOT NULL,
`phone` varchar(32) NOT NULL,
`amount` decimal(20,2) NOT NULL COMMENT '提现时账户金额',
`withdraw_amount` decimal(20,2) NOT NULL COMMENT '提现金额',
`status` tinyint(4) NOT NULL COMMENT '状态，10：已处理，20：未处理',
`creation_time` datetime NOT NULL COMMENT '创建时间',
`auditor_id` int NOT NULL COMMENT '审核人 ID',
`audirot_name` varchar(32) NOT NULL COMMENT '审核人姓名',
`delivery_distributor` int NOT NULL COMMENT '配送人主键关联',
PRIMARY KEY (`id`) 
)
COMMENT='配送员提现申请信息';

CREATE TABLE `delivery_count_info` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
`delivery_distributor_id` int NOT NULL COMMENT '配送人 ID',
`order_count` int NOT NULL DEFAULT 0 COMMENT '配送订单总数量',
`order_value` decimal(20,2) NOT NULL DEFAULT 0.0 COMMENT '配送订单总价值',
PRIMARY KEY (`id`) 
)
COMMENT='配送统计类信息';

