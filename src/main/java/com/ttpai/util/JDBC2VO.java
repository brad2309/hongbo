package com.ttpai.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * @Description:生成VO和基本的DAO
 * @author: rickycoder
 * @date: 2014年12月29日 下午8:55:08
 * see com.ttpai.boss.JavaBeanGenerator
 */
public class JDBC2VO {
    private String host;
    private Integer port = 3306;
    private String dbname;
    private String root;
    private String password;

    public JDBC2VO(String host, String dbname, String root, String password) {
        this(host, 3306, dbname, root, password);
    }

    public JDBC2VO(String host, Integer port, String dbname, String root, String password) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.root = root;
        this.password = password;
    }
    
    public static void main(String[] args) throws Exception{
    	JDBC2VO jv = new JDBC2VO("172.16.2.15", "ttpai_boss_v1", "bossuser", "51auto_v4");
        String packageNameDao = "com.ttpai.boss.aftersale.dao1";//dao包名
        String packageNameModel = "com.ttpai.boss.aftersale.model1";//model包名
        String dbname = "ttpai_boss_v1";//数据名
        String basePath = "D:\\" + dbname + "\\";//保存路径
    	jv.buildDaoFile(basePath, packageNameDao, packageNameModel);
    	jv.buildModelFile(basePath, packageNameModel);
	}

    /**
     * java文件的import
     *
     * @param source
     * @param dbtype
     * @return
     */
    public String buildImport(String source, int dbtype) {
        String importstr = "";
        switch (dbtype) {
            case Types.TIMESTAMP:
                importstr += "import java.util.Date;\n";
                importstr += "import java.util.List;\n";
                break;
            default:
                break;
        }
        if (source.contains(importstr)) {
            return "";
        }
        return importstr;
    }

    /**
     * 数据库类型转换成JAVA类型
     *
     * @param dbtype
     * @param sqlTypeName
     * @return
     */
    public String dbtype2java(int dbtype, String sqlTypeName) {
        // java.sql.Types.
        String type = "";
        switch (dbtype) {
            case Types.BIGINT:
                type = "Long";
                break;
            case Types.VARCHAR:
                type = "String";
                break;
            case Types.LONGVARCHAR:
                type = "String";
                break;
            case Types.INTEGER:
                type = "Integer";
                if (sqlTypeName.endsWith("UNSIGNED")) {
                    type = "Long";
                }
                break;
            case Types.TINYINT:
                type = "Integer";
                break;
            case Types.CHAR:
                type = "String";
                break;
            case Types.TIMESTAMP:
                type = "Date";
                break;
            case Types.FLOAT:
                type = "Double";
                break;
            case Types.REAL:
                type = "Float";
                break;
            case Types.TIME:
                type = "Date";
                break;
            default:
                break;
        }
        return type;
    }

    public String dbtype2java(String dbtype, String sqlTypeName) {
        if (null == dbtype) return null;
        if ("BIGINT".equalsIgnoreCase(dbtype)) {
            return "Long";
        }
        if ("VARCHAR".equalsIgnoreCase(dbtype)) {
            return "String";
        }
        if ("LONGVARCHAR".equalsIgnoreCase(dbtype)) {
            return "String";
        }
        if ("INT".equalsIgnoreCase(dbtype)) {
            if (sqlTypeName.endsWith("UNSIGNED")) {
                return "Long";
            }
            return "Integer";
        }
        if ("CHAR".equalsIgnoreCase(dbtype)) {
            return "String";
        }
        if ("TIMESTAMP".equalsIgnoreCase(dbtype)) {
            return "Date";
        }
        if ("FLOAT".equalsIgnoreCase(dbtype)) {
            return "Double";
        }
        if ("REAL".equalsIgnoreCase(dbtype)) {
            return "Float";
        }
        if ("TIME".equalsIgnoreCase(dbtype)) {
            return "Date";
        }
        return null;
    }

    public String hump(String str) {
        return hump(str, true);
    }

    /**
     * 表名转换成驼峰
     *
     * @param str
     * @param flag
     * @return
     */
    public static String hump(String str, boolean flag) {
        String[] words = str.toLowerCase().split("_");
        String hump = "";
        for (String s : words) {
            hump += Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
        if (!flag) {
            hump = Character.toLowerCase(hump.charAt(0)) + hump.substring(1);
        }
        return hump;
    }

    /**
     * 保存文件
     *
     * @param filename
     * @param source
     * @throws IOException
     */
    public void saveFile(String filename, String source)
            throws IOException {
        BufferedWriter fos = new BufferedWriter(new FileWriter(new File(
                filename)));
        fos.write(source);
        fos.flush();
        fos.close();
    }

    /**
     * 创建文件夹
     *
     * @param dir
     */
    public void createDir(String dir) {
        if (null == dir || "".equals(dir)) return;
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

    }

    /**
     * @param basePath    生成路径
     * @param packageName 包名
     * @param dbname      数据库名
     * @param root        账号
     * @param pwd         密码
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws
     * @Description: 生成VO
     * @return: void
     */
    public void buildModelFile(String basePath, String packageName) throws IOException,
            ClassNotFoundException, SQLException {
        String packagetemp = "package " + packageName + ";\n\n";
        String headtemp = "/** {tabcomment} */\npublic class {table}VO {\n";
        String coltemp = "/**\n" + "* {colcomment}\n" + "*/\n"
                + "private {datatype} {colname};//{colcommentline}\n";
        String getsettemp = "/**\n"
                + "* {colcomment}\n"
                + "*/\n"
                + "public void set{colname}({datatype} {lcolname}){this.{lcolname}={lcolname};}\n"
                + "/**\n" + "* {colcomment}\n" + "*/\n"
                + "public {datatype} get{colname}(){return this.{lcolname};}\n";

        String jdbcurl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname
                + "?useUnicode=true&characterEncoding=UTF8";

        Properties props = new Properties();
        Class.forName("com.mysql.jdbc.Driver");
        props.setProperty("user", this.root);
        props.setProperty("password", this.password);
        props.setProperty("remarks", "true"); // 设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");// 设置可以获取tables
        // remarks信息
        Connection conn = DriverManager.getConnection(jdbcurl, root, this.password);
        ResultSet rs = null;

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        // ResultSet tableSet = databaseMetaData.getTables(conn.getCatalog(),
        // null, "%", new String[]{"TABLE"});
        ResultSet tableSet = conn
                .prepareStatement(
                        "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE table_name='BOSS_COMPLAINT_MOBILE_PIC' and TABLE_SCHEMA='"
                                + dbname + "'").executeQuery();

        while (tableSet.next()) {
            String vo = "";
            String tabname = tableSet.getString("TABLE_NAME");
            String tableremarks = tableSet.getString("TABLE_COMMENT");
            String filename = hump(tabname) + "VO.java";
            String headstr = headtemp.replace("{table}", hump(tabname))
                    .replace("{tabcomment}", tableremarks);
            String importstr = "";
            String colstr = "";
            String getsetstr = "";

            rs = databaseMetaData.getColumns(null, "%", tabname, "%");
            // SQL
            while (rs.next()) {
                String colname = rs.getString("COLUMN_NAME");
                String columnComment = rs.getString("REMARKS");
                int sqlType = rs.getInt("DATA_TYPE");
                String sqlTypeName = rs.getString("TYPE_NAME");

                // String typeName = rs.getString("TYPE_NAME");
                importstr += buildImport(importstr, sqlType);
                colstr += coltemp.replaceAll("\\{colcomment\\}", columnComment)
                        .replaceAll("\\{datatype\\}", dbtype2java(sqlType, sqlTypeName))
                        .replaceAll("\\{colname\\}", hump(colname, false))
                        .replaceAll("\\{colcommentline\\}", columnComment.replaceAll("\\s*|\t|\r|\n", ""));
                getsetstr += getsettemp
                        .replaceAll("\\{datatype\\}", dbtype2java(sqlType, sqlTypeName))
                        .replaceAll("\\{colname\\}", hump(colname))
                        .replaceAll("\\{colcomment\\}", columnComment)
                        .replaceAll("\\{lcolname\\}", hump(colname, false));
            }
            vo += packagetemp;
            vo += importstr;
            vo += headstr;
            vo += colstr;
            vo += getsetstr;
            vo += "}";
            createDir(basePath);
            saveFile(basePath + filename, vo);
        }
        conn.close();
    }

    /**
     * @param basePath
     * @param packageName
     * @param dbname
     * @param root
     * @param pwd
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws
     * @Description: 生成DAO文件
     * @return: void
     */
    @SuppressWarnings("deprecation")
    public void buildDaoFile(String basePath, String packageName, String packageNameModel)
            throws ClassNotFoundException, SQLException, IOException {
        String jdbcurl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname
                + "?useUnicode=true&characterEncoding=UTF8";

        Properties props = new Properties();
        Class.forName("com.mysql.jdbc.Driver");
        props.setProperty("user", this.root);
        props.setProperty("password", this.password);
        props.setProperty("remarks", "true"); // 设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");// 设置可以获取tables
        // remarks信息
        Connection conn = DriverManager.getConnection(jdbcurl, root, this.password);
        ResultSet rs = null;

        ResultSet tableSet = conn
                .prepareStatement(
                        "SELECT TABLE_NAME, TABLE_COMMENT FROM information_schema.tables WHERE table_name='BOSS_COMPLAINT_MOBILE_PIC' and TABLE_SCHEMA='"
                                + dbname + "'").executeQuery();

        while (tableSet.next()) {
            String strselect = "SELECT ";
            String strinsert = "INSERT INTO ";
            String strupdate = "UPDATE ";
            String strupdateNotNull = "UPDATE ";
            String strdelete = "DELETE FROM ";
            String strlistNotNull = "";
            String strlistNotNullWhere = " WHERE 1=1 ";
            String strlist = " ";
            String strcount = "SELECT COUNT(1) FROM ";
            String strlistwhere = "";
            String strcountwhere = "SELECT COUNT(1) FROM ";
            String lprikey = "";
            String prikeycol = "";
            String prikeytype = "";
            String insert1 = "";
            String insert2 = "";
            String update1 = "";
            String updateNotNull = "";

            String tabname = tableSet.getString("TABLE_NAME");
            String tableremarks = tableSet.getString("TABLE_COMMENT");
            String tabalias = hump(tabname, false);
            String voname = hump(tabname) + "VO";
            String methodname = hump(tabname.replace("CARMODEL_", ""));
            rs = conn
                    .prepareStatement(
                            "SELECT TABLE_NAME,COLUMN_NAME,COLUMN_COMMENT REMARKS,COLUMN_KEY,DATA_TYPE,COLUMN_TYPE FROM information_schema.COLUMNS WHERE  TABLE_SCHEMA='" + this.dbname + "' and TABLE_NAME='"
                                    + tabname + "'").executeQuery();
            while (rs.next()) {

                String colname = rs.getString("COLUMN_NAME");
                String lcolname = hump(colname, false);
                String colkey = rs.getString("COLUMN_KEY");
                if (null != colkey && colkey.equals("PRI")) {
                    lprikey = lcolname;
                    prikeycol = colname;
                    String sqlType = rs.getString("DATA_TYPE");
                    String sqlTypeName = rs.getString("COLUMN_TYPE");
                    prikeytype = dbtype2java(sqlType, sqlTypeName);
                }
                // String columnComment = rs.getString("REMARKS");
                // int sqlType = rs.getInt("DATA_TYPE");
                // String typeName = rs.getString("TYPE_NAME");

                strselect += colname + ",";

                if ("MODIFY_TIME".equals(colname)) {
                    insert1 += colname + ",";
                    insert2 += "NOW(),";
                    update1 += colname + "= NOW() ,";
                    updateNotNull += colname + "= NOW() ,";
                } else if ("CREATE_TIME".equals(colname)) {
                    insert1 += colname + ",";
                    insert2 += "NOW(),";
                    //忽略更新create_time字段
                } else if (prikeycol.equals(colname)) {
                    insert1 += colname + ",";
                    insert2 += ":" + tabalias + "." + lcolname + ",";
                } else {
                    insert1 += colname + ",";
                    insert2 += ":" + tabalias + "." + lcolname + ",";
                    update1 += colname + "=:" + tabalias + "." + lcolname + ",";
                    updateNotNull += "#if(:" + tabalias + "." + lcolname + "!=null){" + colname + " =:" + tabalias + "." + lcolname + "  , }";
                    strlistNotNullWhere += "#if(:" + tabalias + "." + lcolname + "!=null){ AND " + colname + " =:" + tabalias + "." + lcolname + "  }";
                }

            }

            strselect = strselect.substring(0, strselect.length() - 1);
            strlist += strselect + " FROM " + tabname + " ORDER BY " + prikeycol + " DESC LIMIT :startIndex,:endIndex ";
            strlistwhere += strselect + " FROM " + tabname + strlistNotNullWhere + " ORDER BY " + prikeycol + " DESC LIMIT :startIndex,:endIndex ";
            strcountwhere += tabname + strlistNotNullWhere;
            strlistNotNull += strselect + " FROM " + tabname + " " + strlistNotNullWhere + " ORDER BY " + prikeycol + " DESC ";
            strselect += " FROM " + tabname + " WHERE " + prikeycol + "=" + ":"
                    + lprikey;
            strinsert += tabname + " ("
                    + insert1.substring(0, insert1.length() - 1)
                    + " ) VALUES ("
                    + insert2.substring(0, insert2.length() - 1) + " )";

            strupdate += tabname + " SET "
                    + ("".equals(update1) ? "" : update1.substring(0, update1.length() - 1)) + " WHERE "
                    + prikeycol + "=" + ":" + tabalias + "." + lprikey;

            strupdateNotNull += tabname + " SET "
                    + ("".equals(updateNotNull) ? "" : updateNotNull.substring(0, updateNotNull.length() - 1)) + " WHERE "
                    + prikeycol + "=" + ":" + tabalias + "." + lprikey;

            strdelete += tabname + " WHERE " + prikeycol + "=" + ":" + lprikey;

            strcount += tabname + " ORDER BY " + prikeycol + " DESC LIMIT :startIndex,:endIndex ";


            // 拼接函数
            String methodselect = "@SQL(\"" + strselect + "\")\n" + "public "
                    + voname + " select" + methodname + "(@SQLParam(\""
                    + lprikey + "\") " + prikeytype + " " + lprikey + " );";
            String methodlistNotNull = "@SQL(\"" + strlistNotNull + "\")\n" + "public List<"
                    + voname + "> select" + methodname + "List(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + " );";

            String methodinsert = "@SQL(\"" + strinsert + "\")\n"
                    + "public void insert" + methodname + "(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + " );";
            String methodupdate = "@SQL(\"" + strupdate + "\")\n"
                    + "public void update" + methodname + "(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + " );";
            String methodupdateNotNull = "@SQL(\"" + strupdateNotNull + "\")\n"
                    + "public void updateNotNull" + methodname + "(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + " );";
            String methoddelete = "@SQL(\"" + strdelete + "\")\n"
                    + "public void delete" + methodname + "(@SQLParam(\""
                    + lprikey + "\") " + prikeytype + " " + lprikey + " );\n";
            String methodlist = "@SQL(\"" + strlist + "\")\n"
                    + "public List<" + voname + "> selectList(@SQLParam(\"startIndex\") Integer startIndex,@SQLParam(\"endIndex\") Integer endIndex);";
            String methodcount = "@SQL(\"" + strcount + "\")\n"
                    + "public Integer selectListCount(@SQLParam(\"startIndex\") Integer startIndex,@SQLParam(\"endIndex\") Integer endIndex);";
            String methodlistwhere = "@SQL(\"" + strlistwhere + "\")\n"
                    + "public List<" + voname + "> selectList(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + " ,@SQLParam(\"startIndex\") Integer startIndex,@SQLParam(\"endIndex\") Integer endIndex);";
            String methodcountwhere = "@SQL(\"" + strcountwhere + "\")\n"
                    + "public Integer selectListCount(@SQLParam(\""
                    + tabalias + "\") " + voname + " " + tabalias + ");";


            // 拼接class
            String classResource = "package " + packageName + ";\n\n"
                    + "import " + packageNameModel + "." + voname + ";\n\n"
                    + "import java.util.List;\n\n"
                    + "import net.paoding.rose.jade.annotation.DAO;\n"
                    + "import net.paoding.rose.jade.annotation.SQL;\n"
                    + "import net.paoding.rose.jade.annotation.SQLParam;\n\n"
                    + "/** \n" + " * @Description: " + tableremarks
                    + " 数据库操作\n" + " * @author: auto build by jdbc2vo\n" + " * @date:   "
                    + new Date().toLocaleString() + "  \n"
                    + " *    \n" + " */\n" + "@DAO\n"
                    + "public interface " + methodname + "DAO {\n";
            classResource += methodselect + "\n\n";
            classResource += methodlistNotNull + "\n\n";
            classResource += methodinsert + "\n\n";
            classResource += methodupdate + "\n\n";
            classResource += methodupdateNotNull + "\n\n";
            classResource += methoddelete + "\n\n";
            classResource += methodlist + "\n\n";
            classResource += methodcount + "\n\n";
            classResource += methodlistwhere + "\n\n";
            classResource += methodcountwhere + "\n\n";
            classResource += "}";

            createDir(basePath);
            saveFile(basePath + methodname + "DAO.java", classResource);
        }

        conn.close();
    }

    //  see com.ttpai.boss.JavaBeanGenerator
}