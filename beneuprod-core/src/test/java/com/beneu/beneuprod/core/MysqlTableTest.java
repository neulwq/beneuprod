package com.beneu.beneuprod.core;

import com.alibaba.innodb.java.reader.TableReaderImpl;
import com.alibaba.innodb.java.reader.page.AbstractPage;
import com.alibaba.innodb.java.reader.page.fsphdr.FspHdrXes;
import com.alibaba.innodb.java.reader.page.index.GenericRecord;
import com.alibaba.innodb.java.reader.page.index.Index;
import com.alibaba.innodb.java.reader.page.inode.Inode;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import com.alibaba.innodb.java.reader.TableReader;

import java.util.List;

import static com.alibaba.innodb.java.reader.page.PageType.EXTENT_DESCRIPTOR;
import static com.alibaba.innodb.java.reader.page.PageType.FILE_SPACE_HEADER;
import static com.alibaba.innodb.java.reader.page.PageType.INDEX;
import static com.alibaba.innodb.java.reader.page.PageType.INODE;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 0:28
 */
public class MysqlTableTest {

    @Test
    public void testPage() {
        String createTableSql = "CREATE TABLE `pay_tc_trade` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增',\n" +
                "  `trade_no` varchar(64) NOT NULL COMMENT '交易单号',\n" +
                "  `main_event_no` varchar(64) NOT NULL COMMENT '主事件单号',\n" +
                "  `out_trade_no` varchar(64) NOT NULL COMMENT '外部交易单号',\n" +
                "  `sub_out_trade_no` varchar(64) NOT NULL COMMENT '外部交易子单号',\n" +
                "  `biz_prod` varchar(16) NOT NULL COMMENT '产品码，第一码',\n" +
                "  `biz_mode` varchar(16) NOT NULL COMMENT '业务模式码，第二码',\n" +
                "  `trade_type` varchar(16) NOT NULL COMMENT '交易类型',\n" +
                "  `state` varchar(16) NOT NULL COMMENT '交易状态',\n" +
                "  `settle_state` varchar(16) NOT NULL COMMENT '结算状态',\n" +
                "  `total_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '交易总金额',\n" +
                "  `pay_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '支付总金额',\n" +
                "  `refund_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '退款总金额',\n" +
                "  `refunding_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '退款中金额',\n" +
                "  `settle_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '结算总金额',\n" +
                "  `settling_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '结算中金额',\n" +
                "  `shared_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '分账总金额',\n" +
                "  `sharing_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '分账中金额',\n" +
                "  `refund_shared_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '退分账总金额',\n" +
                "  `refund_sharing_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '退分账中金额',\n" +
                "  `currency` varchar(16) NOT NULL COMMENT '交易币种',\n" +
                "  `partner_id` varchar(32) NOT NULL COMMENT '合作方',\n" +
                "  `payee_id` varchar(64) NOT NULL COMMENT '收款方',\n" +
                "  `payer_id` varchar(64) NOT NULL COMMENT '付款方',\n" +
                "  `settle_time` datetime(3) DEFAULT NULL COMMENT '结算时间',\n" +
                "  `close_time` datetime(3) DEFAULT NULL COMMENT '关单时间',\n" +
                "  `expire_time` datetime(3) NOT NULL COMMENT '过期时间',\n" +
                "  `env` varchar(16) NOT NULL COMMENT '环境',\n" +
                "  `tags` varchar(512) DEFAULT NULL COMMENT '业务标记',\n" +
                "  `create_time` datetime(3) NOT NULL COMMENT '创建时间',\n" +
                "  `modify_time` datetime(3) NOT NULL COMMENT '修改时间',\n" +
                "  `extend_field` varchar(2048) DEFAULT NULL COMMENT '拓展字段',\n" +
                "  `attachment` varchar(2048) DEFAULT NULL COMMENT '业务透传字段',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_trade_no` (`trade_no`),\n" +
                "  KEY `idx_out_trade_no` (`out_trade_no`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4";
        String ibdFilePath = "C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Data\\stu_db\\pay_tc_trade.ibd";
        try (TableReader reader = new TableReaderImpl(ibdFilePath, createTableSql)) {
            reader.open();
            long numOfPages = reader.getNumOfPages();
            List<AbstractPage> pages = reader.readAllPages();
            for (AbstractPage page : pages) {
                StringBuilder sb = new StringBuilder();
                sb.append(page.getPageNumber());
                sb.append(",");
                sb.append(page.pageType().name());
                if (FILE_SPACE_HEADER.equals(page.pageType()) || EXTENT_DESCRIPTOR.equals(page.pageType())) {
                    sb.append(",numPagesUsed=");
                    sb.append(((FspHdrXes) page).getFspHeader().getNumberOfPagesUsed());
                    sb.append(",size=");
                    sb.append(((FspHdrXes) page).getFspHeader().getSize());
                    sb.append(",xdes.size=");
                    sb.append(((FspHdrXes) page).getXdesList().size());
                } else if (INODE.equals(page.pageType())) {
                    sb.append(",inode.size=");
                    sb.append(((Inode) page).getInodeEntryList().size());
                } else if (INDEX.equals(page.pageType()) && !((Index) page).isLeafPage()) {
                    sb.append(",level=");
                    sb.append(((Index) page).getIndexHeader().getPageLevel());
                    sb.append(",numOfRecs=");
                    sb.append(((Index) page).getIndexHeader().getNumOfRecs());
                } else {
                    //continue;
                }
                System.out.println(sb.toString());
            }
        }
    }
}
