package com.ttpai.hbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
//@Component
public class HbaseTest {

    @Autowired
    private HbaseTemplate hbaseTemplate;
    
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HbaseTest t = (HbaseTest)context.getBean(HbaseTest.class);
		t.test1();
	}
	
	public void test1(){
		
		hbaseTemplate.get("test_boss_operate_log_v1", gerReverseAuctionId(50015720L),new RowMapper<Map<String, Object>>() {
			public Map<String, Object> mapRow(Result result, int rowNum)throws Exception {
				List<Cell> ceList = result.listCells();
				Map<String, Object> map = new HashMap<String, Object>();
				if (ceList != null && ceList.size() > 0) {
					for (Cell cell : ceList) {
						map.put(Bytes.toString(cell.getFamilyArray(),
								cell.getFamilyOffset(),
								cell.getFamilyLength())
								+ "_"
								+ Bytes.toString(
										cell.getQualifierArray(),
										cell.getQualifierOffset(),
										cell.getQualifierLength()),
								Bytes.toString(cell.getValueArray(),
										cell.getValueOffset(),
										cell.getValueLength()));
					}
				}
//				System.out.println(map);
				return null;
			}
		});
				
		hbaseTemplate.execute("test_boss_operate_log_v1", new TableCallback<ArrayList>() {

			public ArrayList doInTable(HTableInterface table) throws Throwable {
				Long id = (long)50015720;
				List<Get> getList = new ArrayList<Get>();
				Get get = new Get(Bytes.toBytes(gerReverseAuctionId(id)));
				get.setMaxVersions();
				get.addColumn(Bytes.toBytes("log"), Bytes.toBytes("detail"));
				getList.add(get);
				Result res = table.get(get);
				List<Cell> cells = res.getColumnCells(Bytes.toBytes("log"),Bytes.toBytes("detail"));
				for (Cell cell : cells) {
					String s = new String(CellUtil.cloneValue(cell));
//					System.out.println(s);
				}
				return null;
			}
			
		});
	}
	
	public static String gerReverseAuctionId(Long auctionId){
        String fillStr = String.format("%015d", auctionId);
        String result = StringUtils.reverse(fillStr);
        
        return result;
    }
	
}
